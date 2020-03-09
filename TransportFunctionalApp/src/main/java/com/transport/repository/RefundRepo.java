package com.transport.repository;

import org.springframework.data.repository.CrudRepository;

import com.transport.model.RatingAndReviews;
import com.transport.model.Refund;

public interface RefundRepo extends CrudRepository<Refund,Long>{

}
