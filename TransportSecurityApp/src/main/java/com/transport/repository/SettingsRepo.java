package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.Settings;

public interface SettingsRepo extends CrudRepository<Settings, Long>{

	Settings findByType(@Param("type")String type);

}
