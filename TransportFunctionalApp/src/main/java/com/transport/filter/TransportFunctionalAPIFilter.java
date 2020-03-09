package com.transport.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.constant.EnumUserRole;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.exception.InvalidRole;
import com.transport.exception.InvalidUserId;
import com.transport.repository.CustomerRepo;
import com.transport.repository.DriverRepo;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;
import com.transport.service.UserSecurityService;

@ComponentScan
@Component
public class TransportFunctionalAPIFilter implements Filter {
	@Autowired
	private UserSecurityService userService;
	private static final Logger LOG=LoggerFactory.getLogger(TransportFunctionalAPIFilter.class);
	@Value("${header.allow.credentials}")
	private String allowCredentials;

	@Value("${header.allow.methods}")
	private String allowMethods;

	@Value("${header.allow.max.age}")
	private String maxAge;

	@Value("${header.allow.headers}")
	private String allowHeaders;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	DriverRepo driverRepo;
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		LOG.info("TransportFunctionaAPIFilter : Initialized.........");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
	    httpServletResponse.setHeader("Access-Control-Allow-Credentials", allowCredentials);
	    httpServletResponse.setHeader("Access-Control-Allow-Methods", allowMethods);
	    httpServletResponse.setHeader("Access-Control-Max-Age", maxAge);
	    httpServletResponse.setHeader("Access-Control-Allow-Headers", allowHeaders);
		String userId=httpServletRequest.getHeader("userId");
		String role=httpServletRequest.getHeader("role");
		String path=httpServletRequest.getRequestURI();
		LOG.debug("path resource : "+path);
		boolean isChainingDisabled = false;
		if(path.equals("/trspt/api/v1/uploadDocs")
			|| path.equals("/trspt/api/v1/getDocs")
			|| path.equals("/trspt/api/v1/addVehicle")
			|| path.equals("/trspt/api/v1/getVehiclesList")
			|| path.equals("/trspt/api/v1/createNewOrder")
			|| path.equals("/trspt/api/v1/getAllCustomerOrders")
			|| path.equals("/trspt/api/v1/getAllDriverOrders")
			|| path.equals("/trspt/api/v1/placeBid")
			|| path.equals("/trspt/api/v1/getDriverBids")
			|| path.equals("/trspt/api/v1/acceptBid")
			|| path.equals("/trspt/api/v1/getConfirmOrders")
			|| path.equals("/trspt/api/v1/addDriver")
			|| path.equals("/trspt/api/v1/getAllDrivers")
			|| path.equals("/trspt/api/v1/assignDriverToVehicle")
			|| path.equals("/trspt/api/v1/changeVehicleStatus")
			|| path.equals("/trspt/api/v1/changeDriverStatus")
			|| path.equals("/trspt/api/v1/editOrder") 
			|| path.equals("/trspt/api/v1/editBid")
			|| path.equals("/trspt/api/v1/edit/vehicleDetails")
			|| path.equals("/trspt/api/v1/save/driverProfileDetails")
			|| path.equals("/trspt/api/v1/get/driverProfileDetails")
			|| path.equals("/trspt/api/v1/driver/changePassword")
			|| path.equals("/trspt/api/v1/saveQuote")
			|| path.equals("/trspt/api/v1/getQuotesByUser")
			|| path.equals("/trspt/api/v1/getQuotes")
			|| path.equals("/trspt/api/v1/getPartnerOrders")
			|| path.equals("/trspt/api/v1/transitRequest")
			|| path.equals("/trspt/api/v1/completeOrder")
			|| path.equals("/trspt/api/v1/partnerCharges")
			|| path.equals("/trspt/api/v1/getPartnersVehicleList")
			|| path.equals("/trspt/api/v1/getDistance")
			|| path.equals("/trspt/api/v1/confirmOrder")
			|| path.equals("/trspt/api/v1/getWalletDetailsByStatus")
			|| path.equals("/trspt/api/v1/saveTicket")

			)
		{
		
				try {
					checkUserIdExists(userId,role);
					httpServletRequest.setAttribute("userId", userId);
				} catch (InvalidUserId e) {
					sendErrorResponse(httpServletResponse,TransportErrorCodes.INVALID_USER_ID,
							TransportErrorMessages.INVALID_USER_ID);
				}
				catch (InvalidRole e) {
					sendErrorResponse(httpServletResponse,TransportErrorCodes.INVALID_USER_ID,
							TransportErrorMessages.INVALID_ROLE);
				}
		}
		if(!isChainingDisabled)
		{
			chain.doFilter(request, response);
		}
	}
	private void checkUserIdExists(String userId,String role) throws InvalidUserId, InvalidRole {
		if(role.equals(EnumUserRole.CUSTOMER.getStatus()))
		{
			boolean result=customerRepo.checkUserIdExists(userId);
			if(!result)
			{
				throw new InvalidUserId(TransportErrorMessages.INVALID_USER_ID);
			}
		}
		else if(role.equals(EnumUserRole.DRIVER.getStatus()))
		{
			boolean result=driverRepo.checkUserIdExists(userId);
			if(!result)
			{
				throw new InvalidUserId(TransportErrorMessages.INVALID_USER_ID);
			}
		}
		else
		{
			throw new InvalidRole(TransportErrorMessages.INVALID_ROLE);
		}
		
		
	}

	private void sendErrorResponse(HttpServletResponse httpServletResponse, String errorCode, String errorMessage)
			throws IOException {
		TransportResponse response = new TransportResponse();
		TransportErrorResp errorResponse = new TransportErrorResp();
		LOG.debug("error code" + errorCode);
		LOG.debug("error Message" + errorMessage);
		errorResponse.setErrorCode(errorCode);
		errorResponse.setErrorMessage(errorMessage);
		response.setErrorResp(errorResponse);
		byte[] responseToSend = restResponseBytes(response);
		((HttpServletResponse) httpServletResponse).setHeader("Content-Type", "application/json");
		((HttpServletResponse) httpServletResponse).setStatus(200);
		httpServletResponse.getOutputStream().write(responseToSend);
		return;
		/*
		 * httpServletResponse.setContentType("application/json");
		 * httpServletResponse.get);
		 */
	}
	private byte[] restResponseBytes(TransportResponse eErrorResponse) throws IOException {
		String serialized = new ObjectMapper().writeValueAsString(eErrorResponse);
		return serialized.getBytes();
	}

	@Override
	public void destroy() {
		
		LOG.info("TransportSecurityFilter : destroying.............");
	}

}
