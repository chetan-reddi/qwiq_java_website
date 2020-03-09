package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.Settings;

public interface HelperPriceRepo extends CrudRepository<Settings, Long>{

	Settings findByType(@Param("type")String type);

}
