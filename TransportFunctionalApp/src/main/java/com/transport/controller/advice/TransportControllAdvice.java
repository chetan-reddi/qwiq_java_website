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
import com.transport.exception.AuthenticationFailed;
import com.transport.exception.BidAlreadyPlaced;
import com.transport.exception.BidCancellFailed;
import com.transport.exception.BidCancellationStatus;
import com.transport.exception.BidIdNotFound;
import com.transport.exception.BidsNotFound;
import com.transport.exception.CapacityExceeds;
import com.transport.exception.FailToUpdatePaymentStatus;
import com.transport.exception.FaileToAcceptBid;
import com.transport.exception.FaileToAssignDriver;
import com.transport.exception.FaileToChangePassword;
import com.transport.exception.FailedToChangeDriverOperationStatus;
import com.transport.exception.FailedToChangeVehicleStatus;
import com.transport.exception.FailedToRegDriver;
import com.transport.exception.FailedToSaveDetails;
import com.transport.exception.FailedToSaveWalletDetails;
import com.transport.exception.InvalidAccount;
import com.transport.exception.InvalidDriverId;
import com.transport.exception.InvalidDriverOperationStatus;
import com.transport.exception.InvalidItemId;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidOTP;
import com.transport.exception.InvalidOrderId;
import com.transport.exception.InvalidOwner;
import com.transport.exception.InvalidPassword;
import com.transport.exception.InvalidQuoteId;
import com.transport.exception.InvalidUserId;
import com.transport.exception.InvalidVehicleId;
import com.transport.exception.KYCDocsNotUpload;
import com.transport.exception.LoginFailedException;
import com.transport.exception.MailExistsException;
import com.transport.exception.NoProfileDetails;
import com.transport.exception.OrderUpdationFailed;
import com.transport.exception.QuoteCancell;
import com.transport.exception.TransportException;
import com.transport.exception.UnableToCompleteOrder;
import com.transport.exception.UserNameExistsException;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;
import com.transport.service.exception.OrderCancell;

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
	@ExceptionHandler(InvalidOwner.class)
	@ResponseBody
	public TransportResponse handleException(InvalidOwner e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_OWNER,
				TransportErrorMessages.INVALID_OWNER);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidOTP.class)
	@ResponseBody
	public TransportResponse handleException(InvalidOTP e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_OTP,
				TransportErrorMessages.INVALID_OTP);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(UnableToCompleteOrder.class)
	@ResponseBody
	public TransportResponse handleException(UnableToCompleteOrder e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.UNABLE_TO_COMPLETE_ORDER,
				TransportErrorMessages.UNABLE_TO_COMPLETE_ORDER);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailedToSaveWalletDetails.class)
	@ResponseBody
	public TransportResponse handleException(FailedToSaveWalletDetails e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_SAVE_WALLET_DETAILS,
				TransportErrorMessages.FAILED_TO_SAVE_WALLET_DETAILS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidUserId.class)
	@ResponseBody
	public TransportResponse handleException(InvalidUserId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_USER_ID,
				TransportErrorMessages.INVALID_USER_ID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(QuoteCancell.class)
	@ResponseBody
	public TransportResponse handleException(QuoteCancell e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.UNABLE_TO_CANCELL_QUOTE,
				e.getMessage());
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(OrderCancell.class)
	@ResponseBody
	public TransportResponse handleException(OrderCancell e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.UNABLE_TO_CANCELL_ORDER,
				e.getMessage());
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailedToSaveDetails.class)
	@ResponseBody
	public TransportResponse handleException(FailedToSaveDetails e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_SAVE_PROFILE,
				TransportErrorMessages.FAILED_TO_SAVE_PROFILE);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(NoProfileDetails.class)
	@ResponseBody
	public TransportResponse handleException(NoProfileDetails e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.UPLOAD_PROFILE_DETAILS,
				TransportErrorMessages.UPLOAD_PROFILE_DETAILS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidPassword.class)
	@ResponseBody
	public TransportResponse handleException(InvalidPassword e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.WRONG_OLD_PASSWORD,
				TransportErrorMessages.WRONG_OLD_PASSWORD);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FaileToChangePassword    .class)
	@ResponseBody
	public TransportResponse handleException(FaileToChangePassword e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.WRONG_OLD_PASSWORD,
				TransportErrorMessages.WRONG_OLD_PASSWORD);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(BidIdNotFound.class)
	@ResponseBody
	public TransportResponse handleException(BidIdNotFound e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.BID_ID_NOT_FOUND    ,
				TransportErrorMessages.BID_ID_NOT_FOUND);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(BidCancellFailed.class)
	@ResponseBody
	public TransportResponse handleException(BidCancellFailed e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_CANCELL_BID    ,
				TransportErrorMessages.FAILED_TO_CANCELL_BID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(BidCancellationStatus.class)
	@ResponseBody
	public TransportResponse handleException(BidCancellationStatus e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_CANCELLATION_STATUS    ,
				TransportErrorMessages.INVALID_CANCELLATION_STATUS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailToUpdatePaymentStatus.class)
	@ResponseBody
	public TransportResponse handleException(FailToUpdatePaymentStatus e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_UPDATE_PAYMENT_STATUS    ,
				TransportErrorMessages.FAILED_TO_UPDATE_PAYMENT_STATUS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(CapacityExceeds.class)
	@ResponseBody
	public TransportResponse handleException(CapacityExceeds e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.CAPACITY_EXCEEDS    ,
				TransportErrorMessages.CAPACITY_EXCEEDS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidItemId.class)
	@ResponseBody
	public TransportResponse handleException(InvalidItemId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_ITEM_ID    ,
				TransportErrorMessages.INVALID_ITEM_ID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailedToChangeVehicleStatus.class)
	@ResponseBody
	public TransportResponse handleException(FailedToChangeVehicleStatus e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_CHANGE_VEHICLE_STATUS,
				TransportErrorMessages.FAILED_TO_CHANGE_VEHICLE_STATUS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailedToChangeDriverOperationStatus.class)
	@ResponseBody
	public TransportResponse handleException(FailedToChangeDriverOperationStatus e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_CHANGE_DRIVER_OPERATON_STATUS,
				TransportErrorMessages.FAILED_TO_CHANGE_DRIVER_OPERATON_STATUS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidDriverOperationStatus.class)
	@ResponseBody
	public TransportResponse handleException(InvalidDriverOperationStatus e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_DRIVER_OPERATON_STATUS,
				TransportErrorMessages.INVALID_DRIVER_OPERATON_STATUS);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(AuthenticationFailed.class)
	@ResponseBody
	public TransportResponse handleException(AuthenticationFailed e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.UNAUTHENTICATED_USER,
				TransportErrorMessages.UNAUTHENTICATED_USER);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(OrderUpdationFailed.class)
	@ResponseBody
	public TransportResponse handleException(OrderUpdationFailed e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.ORDER_UPDATION_FAILED,
				TransportErrorMessages.ORDER_UPDATION_FAILED);
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
//	@ResponseStatus(HttpStatus.ACCEPTED)
//	@ExceptionHandler(OrderCancell.class)
//	@ResponseBody
//	public TransportResponse handleException(OrderCancell e) {
//		LOG.error(e.getMessage(), e);
//		TransportResponse resp = new TransportResponse();
//		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.UNABLE_TO_CANCELL_ORDER,
//				e.getMessage());
//		resp.setErrorResp(errResp);;
//		return resp;
//	}
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
	@ExceptionHandler(InvalidDriverId.class)
	@ResponseBody
	public TransportResponse handleInvalidDriverId(InvalidDriverId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_DRIVER_ID,
				TransportErrorMessages.INVALID_DRIVER_DID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(InvalidVehicleId.class)
	@ResponseBody
	public TransportResponse handleInvalidVehicleId(InvalidVehicleId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_VEHICLE_ID,
				TransportErrorMessages.INVALID_VEHICLE_ID);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FaileToAssignDriver.class)
	@ResponseBody
	public TransportResponse handleFaileToAssignDriver(FaileToAssignDriver e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_ASSIGN_DRIVER,
				TransportErrorMessages.FAILED_TO_ASSIGN_DRIVER);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(BidAlreadyPlaced.class)
	@ResponseBody
	public TransportResponse handleBidAlreadyPlacedException(BidAlreadyPlaced e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.BID_ALREADY_PLACED,
				TransportErrorMessages.BID_ALREADY_PLACED);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FailedToRegDriver.class)
	@ResponseBody
	public TransportResponse handleFailedToRegDriverException(FailedToRegDriver e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_REGISTER_DRIVER,
				TransportErrorMessages.FAILED_TO_REGISTER_DRIVER);
		resp.setErrorResp(errResp);;
		return resp;
	}
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(BidsNotFound.class)
	@ResponseBody
	public TransportResponse handleBidsNotFoundException(BidsNotFound e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.BIDS_NOT_FOUND,
				TransportErrorMessages.BIDS_NOT_FOUND);
		resp.setErrorResp(errResp);;
		return resp;
	}
	
	@ResponseStatus(HttpStatus.ACCEPTED)
	@ExceptionHandler(FaileToAcceptBid.class)
	@ResponseBody
	public TransportResponse handleFaileToAcceptBidException(FaileToAcceptBid e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.FAILED_TO_ACCEPT_BID,
				TransportErrorMessages.FAILED_TO_ACCEPT_BID);
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
	@ExceptionHandler(InvalidQuoteId.class)
	@ResponseBody
	public TransportResponse handleInvalidQuoteIdException(InvalidQuoteId e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.INVALID_QUOTE_ID,
				TransportErrorMessages.INVALID_QUOTE_ID);
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
	@ExceptionHandler(KYCDocsNotUpload.class)
	@ResponseBody
	public TransportResponse handleKYCDocsNotUploadException(KYCDocsNotUpload e) {
		LOG.error(e.getMessage(), e);
		TransportResponse resp = new TransportResponse();
		TransportErrorResp errResp = generateErrorResponse(TransportErrorCodes.KYC_DOCS_NOT_UPLOADED,
				TransportErrorMessages.KYC_DOCS_NOT_UPLOADED);
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
