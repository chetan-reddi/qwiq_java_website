package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.VanDetails;

@Repository
public interface VanDetailsRepo extends CrudRepository<VanDetails,Long> {


	VanDetails findById(@Param("id") long id);

}
