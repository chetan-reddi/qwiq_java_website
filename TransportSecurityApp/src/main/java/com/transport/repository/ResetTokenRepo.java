package com.transport.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.ResetToken;
@Repository
public interface ResetTokenRepo extends CrudRepository<ResetToken, Long>{

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ResetToken r WHERE r.resetToken= :resetToken")
	boolean checkResetToken(@Param("resetToken")String resetToken);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ResetToken r WHERE r.resetToken= :resetToken and TIMESTAMPDIFF(MINUTE, creation_date, current_timestamp())<=10")
	boolean checkTokenExpired(@Param("resetToken")String resetToken);

	ResetToken getEmailIdByResetToken(@Param("resetToken")String resetToken);
	
	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ResetToken r WHERE r.resetToken= :resetToken and r.status=:expired")
	boolean checkTokenUsed(@Param("resetToken")String resetToken, @Param("expired")String expired);

	@Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM ResetToken r WHERE r.emailId=:emailId")
	boolean checkTokenExistsByEmailId(@Param("emailId") String emailId);

	ResetToken findByEmailId(@Param("emailId") String emailId);

}
