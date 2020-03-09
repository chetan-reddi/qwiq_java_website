package com.transport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.bean.AddDriver;

public interface AddDriverRepo extends CrudRepository<AddDriver,Long>{

	List<AddDriver> findByOwnerId(@Param("userRefId")String userRefId);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM AddDriver c WHERE c.driverId= :driverId")
	boolean checkDrvierIdExists(@Param("driverId")String driverId);
	AddDriver findByDriverId(@Param("driverId")String driverId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM AddDriver c WHERE c.driverId= :driverId and c.ownerId=:ownerId")
	boolean checkValidOwnerId(@Param("driverId")String driverId, @Param("ownerId") String ownerId);

}
