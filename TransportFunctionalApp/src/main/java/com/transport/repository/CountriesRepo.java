package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transport.model.Countries;

@Repository
public interface CountriesRepo extends CrudRepository<Countries,Long>{

}
