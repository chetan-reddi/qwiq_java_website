package com.transport.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;


@Service
public interface PrivateKeyService {

	public String getPrivateKey(Date date) throws TransportException, TransportDAOException ;

	public void insertPrivateKey(Date date) throws TransportException, TransportDAOException ;

	public void deletePrivateKey(Date date) throws TransportException, TransportDAOException ;

}