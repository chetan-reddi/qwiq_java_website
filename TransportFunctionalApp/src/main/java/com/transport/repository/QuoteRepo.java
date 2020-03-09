package com.transport.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.Quote;

public interface QuoteRepo extends CrudRepository<Quote,Long>{

	List<Quote> findByUserRefId(@Param("userRefId")String userRefId);

	Quote findByQuoteId(@Param("quoteId")String quoteId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Quote c WHERE c.quoteId= :quoteId")
	boolean checkQuoteIdExists(@Param("quoteId")String quoteId);

	Iterable<Quote> findByOrderByStatusAsc(@Param("status")Integer status);
	@Transactional
	@Query("SELECT q FROM Quote q WHERE q.status=:status order by q.pickUpDate ASC")
	Iterable<Quote> getStatusByStatusAndPickUpDate(@Param("status")Integer status);
	
	@Transactional
	@Query("SELECT q FROM Quote q WHERE q.userRefId=:userRefId order by q.createdDate Desc")
	List<Quote> getQuotesByUser(@Param("userRefId")String userRefId);

}
