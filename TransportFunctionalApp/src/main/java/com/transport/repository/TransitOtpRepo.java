package com.transport.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.TransitOTP;
@Repository
public interface TransitOtpRepo extends CrudRepository<TransitOTP,Long>{
	
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM TransitOTP c WHERE c.orderId= :orderId and c.otp=:otp")
	boolean checkOTPExists(@Param("orderId")String orderId,@Param("otp") String otp);
	@Modifying
	@Transactional
	@Query("delete from TransitOTP b where b.orderId=:orderId")
	void deleteByOrderId(@Param("orderId")String orderId);
	TransitOTP findByOrderId(@Param("orderId")String orderId);

}
