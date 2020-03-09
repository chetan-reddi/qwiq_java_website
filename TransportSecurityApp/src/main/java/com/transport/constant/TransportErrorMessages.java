package com.transport.constant;

public interface TransportErrorMessages {

	public static final String MAIL_EXISTS="Mail already taken ! please choose another one";
	public static final String TRANSPORT_EXCEPTION="Your request is unsuccessful !";
	public static final String USER_NAME_EXISTS = "User name already taken ! please choose another one";
	public static final String INVALID_EMAIL_ID = "Invalid email id ! please try again";
	public static final String LOGIN_FAILED ="Fail to login !please try again";
	public static final String PASSWORD_MISSMATCH = "Password mismatch please try again";
	public static final String FAILED_TO_UPLOAD_KYC= "Failed to upload kyc ! please try again";
	public static final String ALREADY_APPROVED= "KYC already uploaded";
	public static final String AUTHNETICATION_FAILED = "Unable to authenticate token";
	public static final String TOKEN_EXPIRED_EXCEPTION = "Your Token is Expired . please login again";
	public static final String INVALID_ACCOUNT = "Your email address or password were not recognised";
	public static final String REGISTER_FAILED = "Failed to register ! please try again";
	public static final String OTP_FAILED="Failed to send otp";
	public static final String USER_ID_EXCEPTION = "User id or role is required ! please try again";
	public static final String INVALID_LOCATION = "Invalid location details";
	public static final String INVALID_ORDER_ID = "Invalid Order Id ! please try again";
	public static final String INVALID_USER_ID = "Invalid User Id";
	public static final String INVALID_ROLE = "Invalid Role ";
	public static final String FAILED_TO_SAVE_VEHICLE_DETAILS = "Failed to save vehicle details";
	public static final String FAILED_TO_UPLOAD_VEHICLE_DETAILS = "Failed to upload vehicle details";
	public static final String FAILED_TO_UPLOAD_DOCS = "Failed to upload Documents";
	String INVALID_OTP = "Invalid OTP.Please enter correct OTP";
	String OTP_EXPIRED_EXCEPTION = "your OTP is expired";
	String ACCESS_TOKEN_EXCPTION = "Invalid Authentication Token/Authentication Token is required . please try again";

}
