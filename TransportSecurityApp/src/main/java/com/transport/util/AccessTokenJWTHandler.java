package com.transport.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.transport.bean.AccessToken;
import com.transport.service.impl.AccessTokenPayload;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtHandlerAdapter;


@Component
public class AccessTokenJWTHandler extends JwtHandlerAdapter<AccessToken> {
	
	private static final Logger LOG = LoggerFactory.getLogger(AccessTokenJWTHandler.class);
	
	@Override
	public AccessToken onClaimsJws(Jws<Claims> jws){
		AccessToken accessToken = new AccessToken();
		accessToken.setHeaders(jws.getHeader());
		AccessTokenPayload accessTokenPayload = new AccessTokenPayload();
		accessTokenPayload.setTokenId(jws.getBody().getId());
		accessTokenPayload.setIssuer(jws.getBody().getIssuer());
		accessTokenPayload.setIssuedDate(jws.getBody().getIssuedAt());
		accessTokenPayload.setExpiryDate(jws.getBody().getExpiration());
		accessTokenPayload.setAudience(jws.getBody().getAudience());
		accessTokenPayload.setEmailId((String)jws.getBody().get("emailid"));
		accessToken.setPayload(accessTokenPayload);
		return accessToken;
	}
}
