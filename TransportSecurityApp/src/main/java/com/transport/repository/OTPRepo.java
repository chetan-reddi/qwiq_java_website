package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.UseOTP;

public interface OTPRepo extends CrudRepository<UseOTP, Long>{
	@Query("SELECT CASE WHEN COUNT(k) > 0 THEN true ELSE false END FROM UseOTP k WHERE k.emailId= :emailId")
	boolean checkUserExists(@Param("emailId")String emailId);

	UseOTP findByEmailId(@Param("emailId") String emailId);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM UseOTP r WHERE r.emailId= :emailId and r.otp=:otp")
	boolean checkOTPExists(@Param("emailId") String emailId, @Param("otp") String otp);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM UseOTP r WHERE r.emailId= :emailId and r.otp=:otp and TIMESTAMPDIFF(MINUTE, created_date, current_timestamp())<=10")
	boolean checkOTPExpired(@Param("emailId") String emailId, @Param("otp") String otp);
	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM UseOTP r WHERE r.emailId= :emailId")
	boolean checkOTPByMailExists(@Param("emailId")String emailId);

}
