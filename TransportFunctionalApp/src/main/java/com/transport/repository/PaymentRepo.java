package com.transport.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.transport.model.Payment;

@Repository
public interface PaymentRepo extends CrudRepository<Payment,Long>{

}
