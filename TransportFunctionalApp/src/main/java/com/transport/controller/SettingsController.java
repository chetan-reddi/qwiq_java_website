package com.transport.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transport.response.BlogResponse;
import com.transport.bean.ChangePassword;
import com.transport.exception.FaileToChangePassword;
import com.transport.exception.FailedToSaveDetails;
import com.transport.exception.InvalidPassword;
import com.transport.exception.NoProfileDetails;
import com.transport.model.ProfileDetails;
import com.transport.response.ProfiledetailsResp;
import com.transport.response.TransportResponse;
import com.transport.service.SettingsService;

@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class SettingsController {

	@Autowired
	SettingsService settingsService;
//	@RequestMapping(value="/getVanTypes",method=RequestMethod.POST)		
//	private TransportResponse getVanTypes()
//	{
//		return settingsService.getVanTypes();
//	}
	@RequestMapping(value="/save/driverProfileDetails",method=RequestMethod.POST)		
	private TransportResponse save(@RequestBody ProfileDetails profileReq,HttpServletRequest httpReq) throws FailedToSaveDetails
	{
		return settingsService.save(profileReq,httpReq);
	}
	@RequestMapping(value="/get/driverProfileDetails",method=RequestMethod.GET)		
	private ProfiledetailsResp getProfileDetails(HttpServletRequest httpReq) throws FailedToSaveDetails, NoProfileDetails
	{
		return settingsService.getProfileDetails(httpReq);
	}
	@RequestMapping(value ="/driver/changePassword", method = RequestMethod.POST)
	private TransportResponse changePassword(@RequestBody ChangePassword changePwd,HttpServletRequest request) throws InvalidPassword, FaileToChangePassword 
	{
		 return settingsService.changePassword(changePwd,request);
	}
	@RequestMapping(value="/getAllBlogs",method=RequestMethod.GET)	
	private BlogResponse getAllBlogs() 
	{
		return settingsService.getAllBlogs();
	}
	
}
