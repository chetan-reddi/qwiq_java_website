package com.transport.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.transport.bean.KYCDetails;
import com.transport.response.CountriesResponse;
import com.transport.response.TransportResponse;

@Service	
public interface CommonUtilService {

	CountriesResponse getAllCountries();

}
