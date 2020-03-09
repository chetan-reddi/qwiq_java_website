package com.transport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.Bid;

@Repository
public interface BidRepo extends CrudRepository<Bid,Long>{
	
	List<Bid> findByDriverUserIdAndStatus(@Param("driverUserId")String driverUserId,@Param("status") int status);
	Bid findByBidId(@Param("bidId")String bidId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Bid c WHERE c.bidId=:bidId")
	boolean checkBidIdExists(@Param("bidId")String bidId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Bid c WHERE c.quoteId= :quoteId and c.driverUserId=:driverUserId")
	boolean findByQuoteIdAndDriverUserId(@Param("quoteId")String quoteId, @Param("driverUserId")String driverUserId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Bid c WHERE c.quoteId=:quoteId")
	boolean checkQuoteIdExists(@Param("quoteId")String quoteId);
	
	List<Bid> findByQuoteId(@Param("quoteId")String quoteId);
	

}
