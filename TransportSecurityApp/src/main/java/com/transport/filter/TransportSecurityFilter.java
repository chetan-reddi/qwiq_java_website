package com.transport.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.bean.AccessToken;
import com.transport.constant.EnumUserRole;
import com.transport.constant.TransportConstant;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.exception.InvalidRole;
import com.transport.exception.InvalidUserId;
import com.transport.exception.TokenExpiredException;
import com.transport.exception.TransportAccessTokenException;
import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;
import com.transport.repository.CustomerRepo;
import com.transport.repository.DriverRepo;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;
import com.transport.service.UserSecurityService;
import com.transport.util.UUIDUtil;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@ComponentScan
@Component
public class TransportSecurityFilter implements Filter{

	private static final Logger LOG=LoggerFactory.getLogger(TransportSecurityFilter.class);
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	
	@Value("${functionalAPI.base.url}")
	private String functionalAPIBaseurl;
	@Value("${unauthenticate.urls}")
	private String unauthenticateURLs;
	@Value("${authenticate.urls}")
	private String authenticateURLs;
	@Value("${header.allow.origin}")
	private String allowOrigin;

	@Value("${header.allow.credentials}")
	private String allowCredentials;

	@Value("${header.allow.methods}")
	private String allowMethods;

	@Value("${header.allow.max.age}")
	private String maxAge;

	@Value("${header.allow.headers}")
	private String allowHeaders;
	@Autowired
	private UserSecurityService userService;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	DriverRepo driverRepo;
	OkHttpClient client = new OkHttpClient();
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		LOG.info("TransportSecurityFilter : Initialized.........");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final String transactionId=UUIDUtil.randomUUID();
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		httpServletResponse.setHeader("transaction_id", transactionId);
		httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
	    httpServletResponse.setHeader("Access-Control-Allow-Credentials", allowCredentials);
	    httpServletResponse.setHeader("Access-Control-Allow-Methods", allowMethods);
	    httpServletResponse.setHeader("Access-Control-Max-Age", maxAge);
	    httpServletResponse.setHeader("Access-Control-Allow-Headers", allowHeaders); 
		LOG.info("TransportSecurityFilter : Transport transaction initiated with Transaction Id "+transactionId+" request method "+httpServletRequest.getMethod());
		MDC.put("transactionId",transactionId);
		httpServletResponse.setHeader("transactionId",transactionId);
		String path=httpServletRequest.getRequestURI();
		String userId = httpServletRequest.getHeader("userId");
		String role = httpServletRequest.getHeader("role");
		LOG.debug("userId : "+userId);
		LOG.debug("role : "+userId);
		String resourcePath=functionalAPIBaseurl+path;
		LOG.info("Transport Security filter : path "+path+" resource path "+resourcePath);
		if (path.equals("/trspt/api/v1/customer/register")
				|| path.equals("/trspt/api/v1/customer/login")
				|| path.equals("/trspt/api/v1/driver/register")
				|| path.equals("/trspt/api/v1/setPassword")
				|| path.equals("/trspt/api/v1/resendOTP")
				|| path.equals("/trspt/api/v1/validateCustomerOTP")
				|| path.equals("/trspt/api/v1/validateDriverOTP")
				|| path.equals("/trspt/api/v1/driver/login")
				|| path.equals("/trspt/api/v1/forgotPassword")
				|| path.equals("/trspt/api/v1/getVanDetails")
				|| path.equals("/trspt/api/v1/getDistance")
				|| path.equals("/trspt/api/v1/uploadFiles")
				|| path.equals("/trspt/api/v1/getEstimatedQuote")
				) {
		
			chain.doFilter(request, response);
		}
//		else if(path.equals("/trspt/api/v1/addVehicle"))
//		{
//			LOG.debug("userId for addvehicle: "+userId);
//			try {
//				checkUserIdExists(userId,role);
//				httpServletRequest.setAttribute("userId", userId);
//				chain.doFilter(request, response);
//			} catch (InvalidUserId e) {
//				sendErrorResponse(httpServletResponse,TransportErrorCodes.INVALID_USER_ID,
//						TransportErrorMessages.INVALID_USER_ID);
//			}
//			catch (InvalidRole e) {
//				sendErrorResponse(httpServletResponse,TransportErrorCodes.INVALID_USER_ID,
//						TransportErrorMessages.INVALID_ROLE);
//			}
//		}
		else {
			try {
				if ((userId == null)||(role==null)) {
					sendErrorResponse(httpServletResponse,TransportErrorCodes.USER_ID_EXCEPTION,
							TransportErrorMessages.USER_ID_EXCEPTION);
				}
				else
				{
					String httpMethod = httpServletRequest.getMethod();
					switch (httpMethod) {
					case "GET":
						sendResponse(httpServletResponse,get(resourcePath,transactionId,userId,role));
						break;

					case "POST":
						sendResponse(httpServletResponse,post(resourcePath,getBody(httpServletRequest),transactionId,userId,role));
						break;
					case "PUT":
						sendResponse(httpServletResponse,put(resourcePath,getBody(httpServletRequest),transactionId,userId,role));
						break;

					case "DELETE":
						sendResponse(httpServletResponse,delete(resourcePath,transactionId,userId,role));
						break;
					default:
						sendErrorResponse(httpServletResponse,TransportErrorCodes.TRANSPORT_EXCEPTION,TransportErrorMessages.TRANSPORT_EXCEPTION);
				}
				
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
				sendErrorResponse(httpServletResponse,TransportErrorCodes.TRANSPORT_EXCEPTION,TransportErrorMessages.TRANSPORT_EXCEPTION);
			}
		}
	}

	private void sendResponse(HttpServletResponse httpServletResponse,String response) throws IOException{
		httpServletResponse.setStatus(HttpServletResponse.SC_OK);
		httpServletResponse.setContentType("application/json");
		httpServletResponse.getWriter().write(response);
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

	

	private String getBody(HttpServletRequest servletRequest) {
		String body = "";
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = servletRequest.getReader();
			char[] charBuffer = new char[128];
			int bytesRead;
			while ((bytesRead = bufferedReader.read(charBuffer)) != -1) {
				stringBuilder.append(charBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}
		body = stringBuilder.toString();
		return body;
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


	private String get(String url,String transactionId,String userId,String role) throws IOException {
		Request request ;
		if(userId==null)
	   {
		    request = new Request.Builder()
			        .url(url)
			        .addHeader("transactionId", transactionId)
			        .build();
	   }
	   else
	   {
		    request = new Request.Builder()
			        .url(url)
			        .addHeader("transactionId", transactionId).addHeader("userId", userId).addHeader("role", role)
			        .build();
	   }

	    try (Response response = client.newCall(request).execute()) {
	      return response.body().string();
	    }
	}

	private String post(String url, String json,String transactionId,String userId,String role) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request ;
		if(userId==null)
	   {
		    request = new Request.Builder()
		    		.url(url)
			        .addHeader("Content-Type", "application/json")
			        .addHeader("Accept", "application/json")
			        .addHeader("transactionId", transactionId)
			        .post(body)
			        .build();
	   }
	   else
	   {
		    request = new Request.Builder()
		    		.url(url)
			        .addHeader("Content-Type", "application/json")
			        .addHeader("Accept", "application/json")
			        .addHeader("transactionId", transactionId).addHeader("userId", userId).addHeader("role", role)
			        .post(body)
			        .build();
	   }
	    try (Response response = client.newCall(request).execute()) {
	      return response.body().string();
	    }
	}


	private String put(String url, String json,String transactionId,String userId,String role) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request ;
		if(userId==null)
	   {
		    request = new Request.Builder()
		    		.url(url)
			        .addHeader("Content-Type", "application/json")
			        .addHeader("Accept", "application/json")
			        .addHeader("transactionId", transactionId)
			        .post(body)
			        .build();
	   }
	   else
	   {
		    request = new Request.Builder()
		    		.url(url)
			        .addHeader("Content-Type", "application/json")
			        .addHeader("Accept", "application/json")
			        .addHeader("transactionId", transactionId).addHeader("userId", userId).addHeader("role", role)
			        .post(body)
			        .build();
	   }
	    try (Response response = client.newCall(request).execute()) {
	      return response.body().string();
	    }
	}

	private String delete(String url,String transactionId,String userId,String role) throws IOException {
	    Request request = new Request.Builder()
	        .url(url)
	        .addHeader("transactionId", transactionId)
	        .addHeader("userId", userId)
	        .delete()
	        .build();
	    try (Response response = client.newCall(request).execute()) {
	      return response.body().string();
	    }
	}


	@Override
	public void destroy() {
		LOG.info("TransportSecurityFilter : destroying.............");
		
	}

}
