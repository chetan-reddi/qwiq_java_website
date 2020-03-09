package com.transport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.bean.OrderDetails;
@Repository

public interface OrderDetailsRepo extends CrudRepository<OrderDetails,Long>{

	List<OrderDetails> findByUserRefId(@Param("userRefId")String userRefId);

	List<OrderDetails> findByPartnerId(@Param("partnerId")String partnerId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM OrderDetails c WHERE c.orderId= :orderId")
	boolean checkOrderIdExists(@Param("orderId")String orderId);

	OrderDetails findByOrderId(@Param("orderId")String orderId);

	OrderDetails findByBidId(@Param("bidId")String bidId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM OrderDetails c WHERE c.bidId= :bidId")
	boolean checkBidIdExists(@Param("bidId")String bidId);
	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM OrderDetails r WHERE r.orderId= :orderId and TIMESTAMPDIFF(MINUTE, created_date, current_timestamp())<=2880")
	boolean checkHours(@Param("orderId")String orderId);

	
}
