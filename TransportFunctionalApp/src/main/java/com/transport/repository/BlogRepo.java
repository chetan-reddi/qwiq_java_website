package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.AddBlog;

public interface BlogRepo extends CrudRepository<AddBlog, Long>{
	
	AddBlog findById(@Param("id") Long id);

}
