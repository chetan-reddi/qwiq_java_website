package com.transport.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.transport.bean.KYCDetails;
import com.transport.bean.RegisterVehicle;
import com.transport.exception.FailedUpdateDocs;
import com.transport.exception.MailExistsException;
import com.transport.exception.TransportException;
import com.transport.exception.UserNameExistsException;
import com.transport.exception.VehicleRegFailed;
import com.transport.exception.VehicleUploadFailed;
import com.transport.response.TransportResponse;
import com.transport.response.VehicleRegResp;
import com.transport.service.CustomerService;
import com.transport.service.FileStorageService;
@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class KYCDocsController {
	@Autowired
	CustomerService customerService;
	@Autowired
	private FileStorageService fileStorageService;
	@RequestMapping(value="/uploadDocs",method=RequestMethod.POST)		
	private TransportResponse uploadDocs(@Valid @RequestBody KYCDetails kycDetails,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException
	{
		return customerService.uploadDocs(kycDetails,httpReq);
	}
	
	@RequestMapping(value="/addVehicle",method=RequestMethod.POST)		
	private VehicleRegResp addVehicle(@Valid @RequestBody RegisterVehicle addVehicle,HttpServletRequest httpReq) throws MailExistsException, UserNameExistsException, TransportException, VehicleRegFailed, VehicleUploadFailed
	{
		
		return customerService.addVehicle(addVehicle,httpReq);
	}
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public TransportResponse uploadFile(@RequestParam(value = "vanImage",required = false) MultipartFile file,@RequestParam(value = "motorInsurance",required = false) MultipartFile file1,@RequestParam(value = "goodsTransitCover",required = false) MultipartFile file2,@RequestParam(value = "liabilityInsurance",required = false) MultipartFile file3,@RequestParam("vehicleId") String vehicleId,HttpServletRequest request) throws FailedUpdateDocs  {

		 return customerService.uploadFile(file, file1,file2,file3,vehicleId, request);
	}
}
