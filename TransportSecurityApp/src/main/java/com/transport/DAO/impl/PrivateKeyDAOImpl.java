package com.transport.DAO.impl;

import java.sql.Types;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.transport.DAO.PrivateKeyDAO;
import com.transport.constant.P2PUserQuery;
import com.transport.constant.TransportQuery;
import com.transport.exception.TransportException;
@Component
public class PrivateKeyDAOImpl implements PrivateKeyDAO{

	@Resource
	@Qualifier(value="JdbcTemplate")
	JdbcTemplate jdbcTemplate;
	
	@Resource
	@Qualifier(value="NamedParameterJdbcTemplate")
	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Map<String,Object> getPrivateKey(Date date) throws TransportException {
		try{
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("keyDate", date , Types.DATE);
			List<Map<String,Object>> keyMap = namedParameterJdbcTemplate.queryForList(TransportQuery.GET_PRIVATE_KEY,params);
			return keyMap.get(0);
		} catch (Exception e){
			throw new TransportException(e.getMessage(),e);
		}
	}
	
	@Override
	public void insertPrivateKey(Date date, String privateKey) throws TransportException {
		try{
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("keyDate", date , Types.DATE);
			params.addValue("privateKey", privateKey );
			namedParameterJdbcTemplate.update(TransportQuery.INSERT_PRIVATE_KEY,params);
		} catch (Exception e){
			throw new TransportException(e.getMessage(),e);
		}
	}
	
	@Override
	public void deletePrivateKey(Date date) throws TransportException {
		try{
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("keyDate", date , Types.DATE);
			namedParameterJdbcTemplate.update(TransportQuery.DELETE_PRIVATE_KEY,params);
		} catch (Exception e){
			throw new TransportException(e.getMessage(),e);
		}
	}
	@Override
	public boolean isTokenLoggedout(String tokenId) throws TransportException {
		try {
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("tokenId", tokenId);
			List<Map<String, Object>> countMap = namedParameterJdbcTemplate
					.queryForList(P2PUserQuery.GET_LOGGED_OUT_TOKEN_COUNT, params);
			int count = ((Long) countMap.get(0).get("count")).intValue();
			return (count > 0);
		} catch (Exception e) {
			throw new TransportException(e.getMessage(), e);
		}
	}
}
