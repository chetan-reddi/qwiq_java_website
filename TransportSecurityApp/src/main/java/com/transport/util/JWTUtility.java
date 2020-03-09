package com.transport.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.transport.bean.AccessToken;
import com.transport.exception.TransportAccessTokenException;
import com.transport.service.impl.AccessTokenPayload;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JWTUtility {

	private static final Logger LOG = LoggerFactory.getLogger(JWTUtility.class);

	@Value("${accesstoken.algorithm}")
	private String accessTokenAlgorithm;

	@Value("${accesstoken.issuer}")
	private String accessTokenIssuer;

	@Value("${accesstoken.audience}")
	private String accessTokenAudience;

	@Autowired
	private AccessTokenJWTHandler accessTokenJWTHandler;

	public String generateAccessToken(AccessTokenPayload accessTokenPayload , String privateKey){
		LOG.debug("AccessToken========");
		Claims claims = Jwts.claims();
		claims.setId(accessTokenPayload.getTokenId());
		claims.put("emailid", accessTokenPayload.getEmailId());
		claims.setIssuer(accessTokenIssuer);
		claims.setIssuedAt(accessTokenPayload.getIssuedDate());
		claims.setExpiration(accessTokenPayload.getExpiryDate());
		claims.setAudience(accessTokenAudience);
		String accessToken = Jwts.builder()
								 .setHeaderParam("typ", "JWT")
								 .setClaims(claims)
								 .setExpiration(accessTokenPayload.getExpiryDate())
								 .signWith(SignatureAlgorithm.valueOf(accessTokenAlgorithm), privateKey)
								 .compact();
		return accessToken;
	}

	public AccessToken verifyAccessToken(String accessToken , String privateKeyToday , String privateKeyYesterday) throws TransportAccessTokenException  {
		AccessToken token = null;
		try{
			token = verifyAccessTokenWithPrivateKey(accessToken,privateKeyToday);
		} catch(TransportAccessTokenException e){
			token = verifyAccessTokenWithPrivateKey(accessToken,privateKeyYesterday);
		}
		return token;
	}

	private AccessToken verifyAccessTokenWithPrivateKey(String accessToken , String privateKey) throws TransportAccessTokenException {
		AccessToken token = new AccessToken();
		try{
			token = Jwts.parser().setSigningKey(privateKey).parse(accessToken,accessTokenJWTHandler);
			return token;
		} catch(ExpiredJwtException e){
			throw new TransportAccessTokenException(e.getMessage(),e);
		} catch(UnsupportedJwtException e){
			throw new TransportAccessTokenException(e.getMessage(),e);
		} catch(MalformedJwtException e){
			throw new TransportAccessTokenException(e.getMessage(),e);
		} catch(SignatureException e){
			throw new TransportAccessTokenException(e.getMessage(),e);
		}
	}
}
