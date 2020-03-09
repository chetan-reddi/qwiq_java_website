package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.DriverReg;
@Repository
public interface DriverRepo extends CrudRepository<DriverReg,Long>{

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM DriverReg c WHERE c.emailId= :emailId")
	boolean checkEmail(@Param("emailId")String emailId);
	

	DriverReg findByEmailId(@Param("emailId")String emailId);


	DriverReg findByUserId(@Param("userRefId")String userRefId);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM DriverReg c WHERE c.userId= :userId")
	boolean checkUserIdExists(@Param("userId")String userId);
}
