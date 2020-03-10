package com.transport.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.transport.model.Bid;
import com.transport.model.Quote;
import com.transport.model.SaveTicket;

public interface TicketRepo extends CrudRepository<SaveTicket,Long>{
	List<SaveTicket> findAllByUserId(@Param("user_id")String user_id);
	
	SaveTicket findByTicketId(@Param("ticketId")String ticketId);
	
//	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM SaveTicket c WHERE c.ticketId= :ticketId")
//	boolean checkTicketIdExists(@Param("id")long id);
	
	@Modifying
	@Transactional
	@Query(value="delete from SaveTicket c where c.ticketId= :ticketId")
	void deleteByTicketId(String ticketId);
	
}
