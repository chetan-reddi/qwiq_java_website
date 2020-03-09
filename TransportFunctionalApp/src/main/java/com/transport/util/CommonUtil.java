package com.transport.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.transport.bean.AccessToken;
import com.transport.constant.TransportConstant;
import com.transport.constant.TransportErrorMessages;
import com.transport.exception.LoginFailedException;
import com.transport.exception.MailExistsException;
import com.transport.model.DriverReg;
import com.transport.model.UserRegistration;
import com.transport.repository.CustomerRepo;
import com.transport.repository.DriverRepo;
@Component
public class CommonUtil {

	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	DriverRepo driverRepo;
	public  void checkMailIdExists(String mailId) throws MailExistsException
	{
		if(customerRepo.checkEmail(mailId))
		{
			throw new MailExistsException(TransportErrorMessages.MAIL_EXISTS);
		}
	}
	
	public UserRegistration checkMailId(String emailId) throws  LoginFailedException {
		UserRegistration customerDetails;
		boolean pwdDetails=customerRepo.checkEmail(emailId);
		if(!pwdDetails)
		{
			throw new LoginFailedException(TransportErrorMessages.LOGIN_FAILED);
		}
		else
		{
			customerDetails=customerRepo.findByEmailId(emailId);
			
		}
		return customerDetails;
	}

	public UserRegistration getUserDetails(String emailId) {
	
		return customerRepo.findByEmailId(emailId);
	}

	public  String getUserRefIdI(HttpServletRequest httpReq)
	{
		AccessToken accessToken = (AccessToken) httpReq.getAttribute(TransportConstant.ACCESS_TOKEN_OBJ);
		System.out.println(accessToken);
		String emailId=accessToken.getPayload().getEmailId().toString();
		UserRegistration details=getUserDetails(emailId);
		return details.getUserId();
	}

	public String getCustomerUserRefIdI(HttpServletRequest httpReq) {
	
		String userId= (String) httpReq.getAttribute("userId");
		return userId;
	}

	private UserRegistration getCustomerDetails(String emailId) {
		return customerRepo.findByEmailId(emailId);
	}

	public String getDrivrUserRefIdI(HttpServletRequest httpReq) {
		String userId= (String) httpReq.getAttribute("userId");
		return userId;
	}

	private DriverReg getDriverDetails(String emailId) {
		return driverRepo.findByEmailId(emailId);
	}

	public String getUserRole(HttpServletRequest httpReq) {
		String role= (String) httpReq.getAttribute("role");
		return role;
	}
}
