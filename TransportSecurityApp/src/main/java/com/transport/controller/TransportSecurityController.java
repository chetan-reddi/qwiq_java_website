package com.transport.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.transport.bean.DistanceReq;
import com.transport.bean.DriverLoginReq;
import com.transport.bean.DriverOTPReq;
import com.transport.bean.ForgotPassword;
import com.transport.bean.LoginRequest;
import com.transport.bean.QuoteDetails;
import com.transport.bean.SetPassword;
import com.transport.bean.UserOTPReq;
import com.transport.exception.InvalidAccount;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidMaildId;
import com.transport.exception.InvalidOTP;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.LoginFailedException;
import com.transport.exception.MailExistsException;
import com.transport.exception.OTPExpiredException;
import com.transport.exception.PasswordMismatchException;
import com.transport.exception.ResendOTPFailed;
import com.transport.exception.SendOTPFailed;
import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;
import com.transport.exception.UserNameExistsException;
import com.transport.model.UserRegistration;
import com.transport.response.DistanceResp;
import com.transport.response.DriverLoginResponse;
import com.transport.response.EstimatedQutoeResp;
import com.transport.response.LoginResult;
import com.transport.response.OTPResponse;
import com.transport.response.TransportResponse;
import com.transport.response.VanDetailsResp;
import com.transport.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping(value="trspt/api/v1",produces="application/json")
public class TransportSecurityController {

	@Autowired
	CustomerService customerService;
	
//	@RequestMapping(value="/customer/register",method=RequestMethod.POST)		
//	private TransportResponse registerCustomer(@Valid @RequestBody UserRegistration customerReq) throws MailExistsException, UserNameExistsException, TransportException
//	{
//		return customerService.registerCustomer(customerReq);
//	}
	@RequestMapping(value="/validateCustomerOTP",method=RequestMethod.POST)		
	private LoginResult validateOTP(@Valid @RequestBody UserOTPReq otpReq) throws MailExistsException, UserNameExistsException, TransportException, InvalidMaildId, ResendOTPFailed, SendOTPFailed, OTPExpiredException, InvalidOTP, TransportDAOException
	{
		return customerService.validateOTP(otpReq);
	}
	
	@RequestMapping(value="/resendOTP",method=RequestMethod.POST)		
	private TransportResponse resendOTP(@Valid @RequestBody UserOTPReq otpReq) throws MailExistsException, UserNameExistsException, TransportException, InvalidMaildId, ResendOTPFailed, SendOTPFailed
	{
		return customerService.resendOTP(otpReq);
	}
	@RequestMapping(value="/customer/login",method=RequestMethod.POST)		
	private TransportResponse loginCustomer(@Valid @RequestBody LoginRequest loginReq) throws UserNameExistsException, TransportException, InvalidMaildId, LoginFailedException, TransportDAOException, InvalidAccount, SendOTPFailed
	{
		return customerService.loginCustomer(loginReq);
	}
	@RequestMapping(value="/driver/register",method=RequestMethod.POST)	
	private TransportResponse registerDriver(@Valid @RequestBody UserRegistration driverReg) throws MailExistsException, UserNameExistsException, TransportException
	{
		return customerService.registerDriver(driverReg);
	}
	@RequestMapping(value="/validateDriverOTP",method=RequestMethod.POST)	
	private OTPResponse validateDriverOTP(@Valid @RequestBody DriverOTPReq driverOtpreq) throws MailExistsException, UserNameExistsException, TransportException, InvalidMaildId, TransportDAOException, LoginFailedException, OTPExpiredException, InvalidOTP
	{
		return customerService.validateDriverOTP(driverOtpreq);
	}
	@RequestMapping(value="/driver/login",method=RequestMethod.POST)		
	private DriverLoginResponse driverLogin(@Valid @RequestBody DriverLoginReq driverLoginReq) throws UserNameExistsException, TransportException, InvalidMaildId, LoginFailedException, TransportDAOException, InvalidAccount, SendOTPFailed
	{
		return customerService.driverLogin(driverLoginReq);
	}
	@RequestMapping(value = "/setPassword", method = RequestMethod.POST)
	private TransportResponse setPassword(@Valid @RequestBody SetPassword setPassword)
			throws PasswordMismatchException {
		return customerService.setPassword(setPassword);
	}
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	private TransportResponse forgotPassword(@Valid @RequestBody ForgotPassword forgotPassword,HttpServletRequest httpReq) throws InvalidMaildId {
		return customerService.forgotPassword(forgotPassword,httpReq);
	}
	@RequestMapping(value="/getVanDetails",method=RequestMethod.GET)
	private VanDetailsResp getVanDetails()  
	{
		 return customerService.getVanDetails();
	}
	
	@RequestMapping(value="/getDistance",method=RequestMethod.POST)
	private DistanceResp getDistance(@Valid @RequestBody DistanceReq distanceReq) throws InvalidOrderId, IOException, InvalidLocation 
	{
		 return customerService.getDistance(distanceReq);
	}
	
	@RequestMapping(value="/getEstimatedQuote",method=RequestMethod.POST)
	private EstimatedQutoeResp getEstimatedQuote(@Valid @RequestBody QuoteDetails quoteReq) throws InvalidOrderId, IOException, InvalidLocation 
	{
		 return customerService.getEstimatedQuote(quoteReq);
	}
}
