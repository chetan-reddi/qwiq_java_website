package com.transport.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.transport.TransportException;

import com.transport.exception.MailExistsException;
import com.transport.exception.UserNameExistsException;
import com.transport.response.CountriesResponse;
import com.transport.service.CommonUtilService;
@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class CommonUtilController {

	@Autowired
	CommonUtilService utilService;
	@RequestMapping(value="/getAllCountries",method=RequestMethod.GET)	
	private CountriesResponse getAllCountries() throws MailExistsException, UserNameExistsException, TransportException
	{
		return utilService.getAllCountries();
	}
}
