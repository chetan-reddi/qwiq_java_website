package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transport.model.VehicleRegistration;

@Repository
public interface DriverProfileRepo extends CrudRepository<VehicleRegistration,Long>{

}
