package com.transport.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.RatingAndReviews;

@Repository
public interface RatingAndReviewRepo extends CrudRepository<RatingAndReviews,Long>{

	@Transactional
	@Query("SELECT AVG(R.rating) FROM RatingAndReviews R WHERE R.partnerId=:partnerId")
	double ratingAndReview(@Param("partnerId")String partnerId);

}
