package com.transport.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.bean.KYCDetails;
import com.transport.model.Countries;
import com.transport.repository.CountriesRepo;
import com.transport.response.CountriesResponse;
import com.transport.response.TransportResponse;
import com.transport.service.CommonUtilService;

@Service
public class CommonUtilServiceImpl implements CommonUtilService{

	@Autowired
	CountriesRepo countriesRepo;
	@Override
	public CountriesResponse getAllCountries() {
		CountriesResponse response=new CountriesResponse();
		Iterable<Countries> countriesList=countriesRepo.findAll();
		response.setResult(countriesList);
		return response;
	}

}
