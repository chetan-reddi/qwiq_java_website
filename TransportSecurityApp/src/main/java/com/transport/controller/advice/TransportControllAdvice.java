package com.transport.controller.advice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.exception.FailedUpdateDocs;
import com.transport.exception.InvalidAccount;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidMaildId;
import com.transport.exception.InvalidOTP;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidRole;
import com.transport.exception.InvalidUserId;
import com.transport.exception.LoginFailedException;
import com.transport.exception.MailExistsException;
import com.transport.exception.OTPExpiredException;
import com.transport.exception.SendOTPFailed;
import com.transport.exception.TransportException;
import com.transport.exception.UserNameExistsException;
import com.transport.exception.VehicleRegFailed;
import com.transport.exception.VehicleUploadFailed;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;

@ControllerAdvice(basePackages = { "com.transport.controller" })
public class TransportControllAdvice extends ResponseEntityExceptionHandler{
	public static final Logger LOG = LoggerFactory.getLogger(TransportControllAdvice.class);
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public TransportResponse handleException(Exception e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.TRANSPORT_EXCEPTION,
				TransportErrorMessages.TRANSPORT_EXCEPTION);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(MailExistsException.class)
	@ResponseBody
	public TransportResponse handleException(MailExistsException e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.MAIL_EXISTS,
				TransportErrorMessages.MAIL_EXISTS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(VehicleRegFailed.class)
	@ResponseBody
	public TransportResponse handleVehicleRegFailed(VehicleRegFailed e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_SAVE_VEHICLE_DETAILS,
				TransportErrorMessages.FAILED_TO_SAVE_VEHICLE_DETAILS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(VehicleUploadFailed.class)
	@ResponseBody
	public TransportResponse handleVehicleUploadFailed(VehicleUploadFailed e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_UPLOAD_VEHICLE_DETAILS,
				TransportErrorMessages.FAILED_TO_UPLOAD_VEHICLE_DETAILS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidUserId.class)
	@ResponseBody
	public TransportResponse handleInvalidUserId(InvalidUserId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_USER_ID,
				TransportErrorMessages.INVALID_USER_ID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidRole.class)
	@ResponseBody
	public TransportResponse handleInvalidRole(InvalidRole e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_ROLE,
				TransportErrorMessages.INVALID_ROLE);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailedUpdateDocs.class)
	@ResponseBody
	public TransportResponse handleInvalidRole(FailedUpdateDocs e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_UPLOAD_DOCS,
				TransportErrorMessages.FAILED_TO_UPLOAD_DOCS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidOrderId.class)
	@ResponseBody
	public TransportResponse handleException(InvalidOrderId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_ORDER_ID,
				TransportErrorMessages.INVALID_ORDER_ID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidLocation.class)
	@ResponseBody
	public TransportResponse handleInvalidLocationException(InvalidLocation e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_LOCATION,
				TransportErrorMessages.INVALID_LOCATION);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(SendOTPFailed.class)
	@ResponseBody
	public TransportResponse handleException(SendOTPFailed e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.OTP_FAILED,
				TransportErrorMessages.OTP_FAILED);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(UserNameExistsException.class)
	@ResponseBody
	public TransportResponse handleException(UserNameExistsException e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.USER_NAME_EXISTS,
				TransportErrorMessages.USER_NAME_EXISTS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(TransportException.class)
	@ResponseBody
	public TransportResponse handleException(TransportException e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.USER_NAME_EXISTS,
				TransportErrorMessages.USER_NAME_EXISTS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(LoginFailedException.class)
	@ResponseBody
	public TransportResponse handleLoginFailed(LoginFailedException ex) {
		LOG.error(ex.getMessage(), ex);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.LOGIN_FAILED,
				TransportErrorMessages.LOGIN_FAILED);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(OTPExpiredException.class)
	@ResponseBody
	public TransportResponse OTPExpiredException(OTPExpiredException e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.OTP_EXPIRED_EXCEPTION,
				TransportErrorMessages.OTP_EXPIRED_EXCEPTION);
		resp.setErrorResp(errResp);
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidOTP.class)
	@ResponseBody
	public TransportResponse InvalidOTP(InvalidOTP e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_OTP_EXCEPTION,
				TransportErrorMessages.INVALID_OTP);
		resp.setErrorResp(errResp);
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidAccount.class)
	@ResponseBody
	public TransportResponse handleInvalidAccount(InvalidAccount ex) {
		LOG.error(ex.getMessage(), ex);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_ACCOUNT,
				TransportErrorMessages.INVALID_ACCOUNT);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidMaildId.class)
	@ResponseBody
	public TransportResponse handleInvalidMaildId(InvalidMaildId ex) {
		LOG.error(ex.getMessage(), ex);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_EMAIL_ID,
				TransportErrorMessages.INVALID_EMAIL_ID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public TransportResponse handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		LOG.error(ex.getMessage(), ex);
		String errorMessage = "";
		TransportResponse resp = new TransportResponse();
		for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			errorMessage = violation.getMessage();
		}
		return resp;
	}
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		TransportResponse resp = new TransportResponse();
		TransportErrorResp response = generateErrorResponse(TransportErrorCodes.VALIDATION_FATILED,
				ex.getBindingResult().getFieldError().getField() + " "
						+ ex.getBindingResult().getFieldError().getDefaultMessage());
		/*
		 * ValidationResponse validRes=new ValidationResponse();
		 * validRes.setError(response);
		 */
		resp.setErrorResp(response);
		return new ResponseEntity<Object>(resp, HttpStatus.BAD_REQUEST);
	}
	
	private TransportErrorResp generateErrorResponse(String errorCode, String errorMessage) {
		TransportErrorResp response = new TransportErrorResp();
		response.setErrorCode(errorCode);
		response.setErrorMessage(errorMessage);
		return response;
	}
}
