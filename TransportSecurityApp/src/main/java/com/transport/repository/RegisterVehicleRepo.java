package com.transport.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.VehicleRegistration;

@Repository
public interface RegisterVehicleRepo extends CrudRepository<VehicleRegistration,Long>{

	List<VehicleRegistration> findByUserId(@Param("userRefId")String userRefId);

	VehicleRegistration findByVehicleId(@Param("vehicleId")String vehicleId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM VehicleRegistration c WHERE c.vehicleId= :vehicleId")
	boolean checkVehicleIdExists(@Param("vehicleId")String vehicleId);
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM VehicleRegistration c WHERE c.vehicleId= :vehicleId and c.userId=:userId")
	boolean checkValidOwner(@Param("vehicleId")String vehicleId, @Param("userId")String userId);
	

}
