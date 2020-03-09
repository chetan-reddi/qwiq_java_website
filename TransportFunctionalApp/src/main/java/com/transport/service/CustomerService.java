package com.transport.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.transport.bean.KYCDetails;
import com.transport.exception.KYCDocsNotUpload;
import com.transport.response.KYCDocsResponse;
import com.transport.response.TransportResponse;



@Service
public interface CustomerService {

	

	public TransportResponse uploadDocs(KYCDetails kycDetails, HttpServletRequest httpReq);

	public KYCDocsResponse getDocs(String vehicleId,HttpServletRequest httpReq) throws KYCDocsNotUpload;

}
