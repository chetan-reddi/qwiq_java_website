package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.bean.KYCDetails;

@Repository
public interface KYCRepo extends CrudRepository<KYCDetails, Long> {
	@Query("SELECT CASE WHEN COUNT(k) > 0 THEN true ELSE false END FROM KYCDetails k WHERE k.userRefId= :userId")
	boolean checkKycUploaded(@Param("userId") String userId);

	KYCDetails findByUserRefId(@Param("userId") String userId);
	@Query("SELECT CASE WHEN COUNT(k) > 0 THEN true ELSE false END FROM KYCDetails k WHERE k.userRefId=:userId and k.status=:status")

	boolean checkKycStatus(@Param("userId")String userId, @Param("status")String status);

	
}