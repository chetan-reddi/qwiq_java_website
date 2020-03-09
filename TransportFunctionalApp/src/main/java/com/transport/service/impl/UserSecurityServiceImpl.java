package com.transport.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.DAO.PrivateKeyDAO;
import com.transport.bean.AccessToken;
import com.transport.exception.TokenExpiredException;
import com.transport.exception.TransportAccessTokenException;
import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;
import com.transport.service.PrivateKeyService;
import com.transport.service.UserSecurityService;
import com.transport.util.DateUtil;
import com.transport.util.JWTUtility;

import okhttp3.MediaType;
/**
 * 
 * @author Krishna This class implements all the serivces related to User
 *         Security.
 *
 */
@Service
public class UserSecurityServiceImpl implements UserSecurityService {
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	public static final Logger LOG = LoggerFactory.getLogger(UserSecurityServiceImpl.class);
	@Autowired
	JWTUtility jwtUtility;
	@Autowired
	PrivateKeyService privateKeyService;
	@Autowired
	PrivateKeyDAO privateKeyDAO;
	@Override
	public AccessToken verifyAccessToken(String authorizationToken) throws TransportException, TokenExpiredException, TransportAccessTokenException, TransportDAOException {
		return jwtUtility.verifyAccessToken(authorizationToken,
				privateKeyService.getPrivateKey(DateUtil.currentDateTruncated()),
				privateKeyService.getPrivateKey(DateUtil.getTruncatedDateAfterDays(-1)));
	}
	@Override
	public boolean isLoggedout(String tokenId) throws TransportException {
		return privateKeyDAO.isTokenLoggedout(tokenId);
	}
	
}