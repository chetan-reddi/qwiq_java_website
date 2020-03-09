package com.transport.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends CrudRepository<AddItem,Long>
{

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM AddItem c WHERE c.itemId= :itemId")
	boolean checkItemIdExists(@Param("itemId")String itemId);

	AddItem findByItemId(@Param("itemId")String itemId);
	

}
