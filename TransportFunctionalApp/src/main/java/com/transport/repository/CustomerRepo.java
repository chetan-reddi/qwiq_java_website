package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.UserRegistration;

@Repository
public interface CustomerRepo extends CrudRepository<UserRegistration,Long>{
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM UserRegistration c WHERE c.emailId= :emailId")
	boolean checkEmail(@Param("emailId")String emailId);
	UserRegistration findByEmailId(@Param("emailId")String emailId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM UserRegistration c WHERE c.userId= :userId")
	boolean checkUserIdExists(@Param("userId")String userId);
	

}
