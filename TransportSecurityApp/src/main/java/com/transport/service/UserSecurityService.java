package com.transport.service;


import org.springframework.stereotype.Service;

import com.transport.bean.AccessToken;
import com.transport.exception.TokenExpiredException;
import com.transport.exception.TransportAccessTokenException;
import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;

@Service
public interface UserSecurityService {
	AccessToken verifyAccessToken(String authorizationToken) throws TransportException, TokenExpiredException, TransportAccessTokenException, TransportDAOException;
	boolean isLoggedout(String tokenId) throws TransportException;
}
