package com.transport.repository;

import org.springframework.data.repository.CrudRepository;

import com.transport.model.Bid;
import com.transport.model.SaveTicket;

public interface TicketRepo extends CrudRepository<SaveTicket,Long>{

}
