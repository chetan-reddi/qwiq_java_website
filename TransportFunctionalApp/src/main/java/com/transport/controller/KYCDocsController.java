package com.transport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.transport.TransportException;

import com.transport.bean.KYCDetails;
import com.transport.bean.VehicleDocsReq;
import com.transport.exception.KYCDocsNotUpload;
import com.transport.exception.MailExistsException;
import com.transport.exception.UserNameExistsException;
import com.transport.response.KYCDocsResponse;
import com.transport.response.TransportResponse;
import com.transport.service.CustomerService;


@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class KYCDocsController {
	@Autowired
	CustomerService customerService;
	@RequestMapping(value="/uploadDocs",method=RequestMethod.POST)		
	private TransportResponse uploadDocs(@Valid @RequestBody KYCDetails kycDetails,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException
	{
		return customerService.uploadDocs(kycDetails,httpReq);
	}
	@RequestMapping(value="/getDocs",method=RequestMethod.POST)		
	private KYCDocsResponse getDocs(@RequestBody VehicleDocsReq vehicleId,HttpServletRequest httpReq) throws TransportException, KYCDocsNotUpload
	{
		return customerService.getDocs(vehicleId.getVehicleId(),httpReq);
	}
	
	
}
