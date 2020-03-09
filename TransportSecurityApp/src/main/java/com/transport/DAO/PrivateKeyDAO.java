package com.transport.DAO;

import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;


@Component
public interface PrivateKeyDAO {

	public Map<String, Object> getPrivateKey(Date date) throws TransportDAOException, TransportException;

	public void insertPrivateKey(Date date, String privateKey) throws TransportDAOException, TransportException;

	public void deletePrivateKey(Date date) throws TransportDAOException, TransportException;

	public boolean isTokenLoggedout(String tokenId) throws TransportException;

}
