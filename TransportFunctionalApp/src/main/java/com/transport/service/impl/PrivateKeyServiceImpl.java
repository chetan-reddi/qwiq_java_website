 package com.transport.service.impl;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.DAO.PrivateKeyDAO;
import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;
import com.transport.service.PrivateKeyService;
import com.transport.util.DateUtil;
import com.transport.util.RandomUtil;

@Service
public class PrivateKeyServiceImpl implements PrivateKeyService {
	
	private static final Logger LOG = LoggerFactory.getLogger(PrivateKeyServiceImpl.class);
	
	@Autowired
	PrivateKeyDAO privateKeyDAO;
	
	@Autowired
	RandomUtil randomUtil;
	
	private LinkedHashMap<String, String> privateKeyCacheMap = new LinkedHashMap<String, String>();
	
	@Override
	public String getPrivateKey(Date date) throws TransportException, TransportDAOException{
		if(privateKeyCacheMap.containsKey(date.toString())){
			return privateKeyCacheMap.get(date.toString());
		}
		String privateKey = null;
		synchronized (privateKeyCacheMap) {
			try{
				Map<String,Object> privateKeyMap = null;
				try{
					privateKeyMap = privateKeyDAO.getPrivateKey(date);
				} catch(TransportException e){
					LOG.warn("PrivateKeyServiceImpl : getPrivateKey : PrivateKey not found for the date : ",date);
				}
				if(privateKeyMap!=null&&privateKeyMap.size()>0){
					privateKey = (String) privateKeyMap.get("jwt_private_key");
				} else{
					privateKey = randomUtil.generateSecureRandom(32);
					privateKeyDAO.insertPrivateKey(date, privateKey);
				}
				privateKeyCacheMap.put(date.toString(), privateKey);
				if(privateKeyCacheMap.size()>2){
					String dayBeforeYesterday = DateUtil.getTruncatedDateAfterDays(-2).toString();;
					privateKeyCacheMap.remove(dayBeforeYesterday);
				}
			} catch(TransportException e){
				throw e;
			}
		}
		return privateKey;
	}
	
	@Override
	public void insertPrivateKey(Date date) throws TransportException, TransportDAOException{
		String privateKey = null;
		synchronized (privateKeyCacheMap) {
			try{
				Map<String,Object> privateKeyMap = privateKeyDAO.getPrivateKey(date);
				if(privateKeyMap==null||privateKeyMap.size()<1){
					privateKey = randomUtil.generateSecureRandom(32);
					privateKeyDAO.insertPrivateKey(date, privateKey);
				}
				privateKeyCacheMap.put(date.toString(), privateKey);
				if(privateKeyCacheMap.size()>2){
					String dayBeforeYesterday = DateUtil.getTruncatedDateAfterDays(-2).toString();;
					privateKeyCacheMap.remove(dayBeforeYesterday);
				}
			} catch(TransportException e){
				throw e;
			}
		}
	}
	
	@Override
	public void deletePrivateKey(Date date) throws TransportException, TransportDAOException{
		try{
			privateKeyDAO.deletePrivateKey(date);
			privateKeyCacheMap.remove(date.toString());
		} catch(TransportException e){
			throw e;
		}
	}
	
}
