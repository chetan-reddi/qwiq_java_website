package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transport.model.DriverProfileDetails;

@Repository
public interface DriverProfileRepo extends CrudRepository<DriverProfileDetails,Long>{

}
