package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.ProfileDetails;



public interface ProfileRepo extends CrudRepository<ProfileDetails,Long>{

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ProfileDetails c WHERE c.userId= :userId")
	boolean checkDriverExists(@Param("userId")String userId);

	ProfileDetails findByUserId(@Param("userId")String userRefId);

}
