package com.transport.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.transport.model.PartnerWallet;
@Repository
public interface PartnerWalletRepo extends CrudRepository<PartnerWallet,Long>{

	List<PartnerWallet> findByPartnerId(@Param("partnerId")String partnerId);

	List<PartnerWallet> findByPartnerIdAndStatus(@Param("partnerId")String driverId, @Param("status")int status);

	
}
