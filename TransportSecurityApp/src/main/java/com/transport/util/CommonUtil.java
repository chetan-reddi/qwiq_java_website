package com.transport.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transport.constant.TransportErrorMessages;
import com.transport.exception.LoginFailedException;
import com.transport.exception.MailExistsException;
import com.transport.model.UserRegistration;
import com.transport.repository.DriverRegRepo;
import com.transport.repository.CustomerRepo;
import com.transport.repository.DriverRepo;
@Component
public class CommonUtil {

	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	DriverRepo driverRepo;
	@Autowired
	DriverRegRepo customerRegRepo;
	public  void checkMailIdExists(String mailId) throws MailExistsException
	{
		if(customerRepo.checkEmail(mailId))
		{
			throw new MailExistsException(TransportErrorMessages.MAIL_EXISTS);
		}
	}
	
	public boolean checkMailId(String emailId) throws  LoginFailedException {
		
	return driverRepo.checkEmail(emailId);
	}

	public UserRegistration getUserDetails(String emailId) {
	
		return customerRepo.findByEmailId(emailId);
	}

	public boolean checkCustomerMailId(String emailId) {
	return customerRepo.checkEmail(emailId);
	}

	public void checkDriverMailExists(String emailId) throws MailExistsException {
		
		boolean result=driverRepo.checkEmail(emailId);
		if(result)
		{
			throw new MailExistsException(TransportErrorMessages.MAIL_EXISTS);
		}
	}
	
	public String getDrivrUserRefIdI(HttpServletRequest httpReq) {
		String userId= (String) httpReq.getAttribute("userId");
		return userId;
	}


	
}
