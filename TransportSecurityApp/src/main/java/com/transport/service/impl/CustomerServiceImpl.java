package com.transport.service.impl;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.transport.bean.AccessToken;
import com.transport.bean.DistanceReq;
import com.transport.bean.DriverLoginReq;
import com.transport.bean.DriverOTPReq;
import com.transport.bean.DriverRegistration;
import com.transport.bean.ForgotPassword;
import com.transport.bean.KYCDetails;
import com.transport.bean.LoginRequest;
import com.transport.bean.QuoteDetails;
import com.transport.bean.RegisterVehicle;
import com.transport.bean.SetPassword;
import com.transport.bean.UserOTPReq;
import com.transport.constant.EnumAccountStatus;
import com.transport.constant.EnumUserRole;
import com.transport.constant.EnumVehicleOperationStatus;
import com.transport.constant.EnumVehicleStatus;
import com.transport.constant.TransportConstant;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.constant.TransportSuccessMessages;
import com.transport.constant.TransprotSuccessCodes;
import com.transport.email.service.EmailService;
import com.transport.exception.FailedUpdateDocs;
import com.transport.exception.InvalidAccount;
import com.transport.exception.InvalidLocation;
import com.transport.exception.InvalidMaildId;
import com.transport.exception.InvalidOTP;
import com.transport.exception.LoginFailedException;
import com.transport.exception.MailExistsException;
import com.transport.exception.OTPExpiredException;
import com.transport.exception.PasswordMismatchException;
import com.transport.exception.ResendOTPFailed;
import com.transport.exception.SendOTPFailed;
import com.transport.exception.TransportDAOException;
import com.transport.exception.TransportException;
import com.transport.exception.UserNameExistsException;
import com.transport.exception.VehicleRegFailed;
import com.transport.exception.VehicleUploadFailed;
import com.transport.model.DriverProfileDetails;
import com.transport.model.DriverReg;
import com.transport.model.ResetToken;
import com.transport.model.Settings;
import com.transport.model.UseOTP;
import com.transport.model.UserRegistration;
import com.transport.model.VanDetails;
import com.transport.model.VehicleRegistration;
import com.transport.repository.CustomerRepo;
import com.transport.repository.DriverProfileRepo;
import com.transport.repository.DriverRegRepo;
import com.transport.repository.DriverRepo;
import com.transport.repository.KYCRepo;
import com.transport.repository.OTPRepo;
import com.transport.repository.RegisterVehicleRepo;
import com.transport.repository.ResetTokenRepo;
import com.transport.repository.SettingsRepo;
import com.transport.repository.VanDetailsRepo;
import com.transport.response.DistanceAndHours;
import com.transport.response.DistanceResp;
import com.transport.response.DistanceResponse;
import com.transport.response.DriverLoginResponse;
import com.transport.response.EstimatedQutoeResp;
import com.transport.response.LoginResponse;
import com.transport.response.LoginResult;
import com.transport.response.OTPResponse;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;
import com.transport.response.TransportResult;
import com.transport.response.VanDetailsList;
import com.transport.response.VanDetailsResp;
import com.transport.response.VehicleRegResp;
import com.transport.service.CustomerService;
import com.transport.service.FileStorageService;
import com.transport.service.PrivateKeyService;
import com.transport.util.CommonUtil;
import com.transport.util.JWTUtility;
import com.transport.util.PasswordHashingUtility;
import com.transport.util.UUIDUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

	public static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Value("${accesstoken.expiration}")
	private int accessTokenExpiration;
	@Value("${google.distance.matrix.URL}")
	private String distacneURl;
	@Value("${google.distance.matrix.API.key}")
	private String distanceAPIKey;
	@Value("${file.upload.dir}")
	private String directory;
	@Autowired
	SettingsRepo settingsRepo;
	@Autowired
	PasswordHashingUtility passwordHashingUtility;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	DriverRepo driverRepo;
	@Autowired
	ResetTokenRepo resetTokenRepo;
	@Autowired
	VanDetailsRepo vanDetailsRepo;
	@Autowired
	DriverProfileRepo driverProfileRepo;
	@Autowired
	KYCRepo kycRepo;
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	JWTUtility jwtUtility;
	@Autowired
	PrivateKeyService privateKeyService;
	@Autowired
	EmailService emailService;
	@Autowired
	UUIDUtil uuidService;
	@Autowired
	OTPRepo otpRepo;
	@Autowired
	DriverRegRepo driverRegRepo;
	@Autowired
	RegisterVehicleRepo vehicleRepo;
	@Value("${reset.url}")
	private String resetURL;
	@Autowired
	private FileStorageService fileStorageService;
	@Override
	public TransportResponse registerCustomer(UserRegistration customerReq)
			throws MailExistsException, UserNameExistsException, TransportException {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		commonUtil.checkMailIdExists(customerReq.getEmailId());
		customerReq.setRole(EnumUserRole.CUSTOMER.getStatusId());
		String userRefId = UUIDUtil.randomUUID();
		customerReq.setUserId(userRefId);
		customerReq.setStatus(EnumAccountStatus.ACTIVATION_PENDING.getStatusId());
		try
		{
			customerRepo.save(customerReq);
			sendOTP(customerReq.getEmailId());
			result.setCode(TransprotSuccessCodes.REGISTERATION_SUCCESSFUL);
			result.setMessage(TransportSuccessMessages.REGISTERATION_SUCCESSFUL);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			LOG.debug("Error occured : "+ex.getMessage());
			TransportErrorResp errorResp=new TransportErrorResp();
			errorResp.setErrorCode(TransportErrorCodes.REGISTER_FAILED);
			errorResp.setErrorMessage(TransportErrorMessages.REGISTER_FAILED);
			response.setErrorResp(errorResp);
		}
		return response;
	}

	private void sendOTP(String emailId) throws SendOTPFailed {
		String otp=UUIDUtil.generateOTP();
		try
		{
			emailService.sendOTP(emailId,otp);
			saveOTP(emailId,otp);
		}
		catch(Exception ex)
		{
			LOG.debug(ex.getMessage());
			throw new SendOTPFailed(TransportErrorMessages.OTP_FAILED);
		}
	}

	private void saveOTP(String emailId, String otp) {
		boolean result=checkOTPByMailExists(emailId);
		if(result)
		{
			UseOTP details=otpRepo.findByEmailId(emailId);
			details.setOtp(otp);
			otpRepo.save(details);
		}
		else
		{
			UseOTP otpDetials=new UseOTP();
			otpDetials.setEmailId(emailId);
			otpDetials.setOtp(otp);
			otpRepo.save(otpDetials);
		}
	}

	private boolean checkOTPByMailExists(String emailId) {
	return otpRepo.checkOTPByMailExists(emailId);
	}

	@Override
	public TransportResponse loginCustomer(LoginRequest loginReq) throws InvalidMaildId, LoginFailedException, TransportException, TransportDAOException, InvalidAccount, SendOTPFailed {
		TransportResult result=new TransportResult();
		TransportResponse response=new TransportResponse();
		//boolean status=commonUtil.checkCustomerMailId(loginReq.getEmailId());

//			String userRefId = UUIDUtil.randomUUID();
//			UserRegistration reg=new UserRegistration();
//			reg.setEmailId(loginReq.getEmailId());
//			reg.setUserId(userRefId);
//			reg.setRole(EnumUserRole.CUSTOMER.getStatusId());
//			reg.setStatus(EnumAccountStatus.ACTIVE.getStatusId());
//			customerRepo.save(reg);
			sendOTP(loginReq.getEmailId());
			result.setCode(TransprotSuccessCodes.REGISTERATION_SUCCESSFUL);
			result.setMessage(TransportSuccessMessages.REGISTERATION_SUCCESSFUL);
			response.setResult(result);
			return response;
		
	}

	private String checkKycStatus(String userId) {
		
		boolean uploadResult = kycRepo.checkKycUploaded(userId);
		if(uploadResult)
		{
			KYCDetails details=kycRepo.findByUserRefId(userId);
			return details.getStatus();
		}
		else
		{
			return "Not Uploaded";
		}
	}

	private void checkAccountStatus(String emailId) throws InvalidAccount {
		UserRegistration details=customerRepo.findByEmailId(emailId);
		int statusId=details.getStatus();
		if(EnumAccountStatus.getStatus(statusId).equals(EnumAccountStatus.ACTIVATION_PENDING.getStatus()))
		{
			throw new InvalidAccount(TransportErrorCodes.AUTHNETICATION_FAILED);
		}
	}

	private String encodePassword(String password) {
		return passwordHashingUtility.getPasswordHash(password);
	}

//	private String getPassword(LoginRequest loginReq) throws  LoginFailedException {
//		return commonUtil.checkMailId(loginReq.getEmailId());
//	}

	private boolean verifyPassword(String password, String hashedPassword) {
		if (passwordHashingUtility.verifyPassword(password, hashedPassword)) {
			return true;
		}
		return false;
	}

	@Override
	public TransportResponse registerDriver(UserRegistration driverReg) throws MailExistsException, TransportException {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		commonUtil.checkDriverMailExists(driverReg.getEmailId());
//		driverReg.setRole(EnumUserRole.DRIVER.getStatusId());
//		String userRefId = UUIDUtil.randomUUID();
//		driverReg.setUserId(userRefId);
//		driverReg.setStatus(EnumAccountStatus.ACTIVATION_PENDING.getStatusId());
//		driverReg.setPassword(encodePassword(driverReg.getPassword()));
		try
		{
			//customerRepo.save(driverReg);
			sendOTP(driverReg.getEmailId());
			result.setCode(TransprotSuccessCodes.REGISTERATION_SUCCESSFUL);
			result.setMessage(TransportSuccessMessages.REGISTERATION_SUCCESSFUL);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			LOG.debug("Error occured : "+ex.getMessage());
			TransportErrorResp errorResp=new TransportErrorResp();
			errorResp.setErrorCode(TransportErrorCodes.REGISTER_FAILED);
			errorResp.setErrorMessage(TransportErrorMessages.REGISTER_FAILED);
			response.setErrorResp(errorResp);
		}
		return response;
	}

	private void saveProfileDetails(DriverRegistration driverReg, String userId) throws TransportException {
		DriverProfileDetails profileDetails=new DriverProfileDetails();
		profileDetails.setUserId(userId);
		profileDetails.setBaseRange(driverReg.getBaseRange());
		profileDetails.setCity(driverReg.getCity());
		profileDetails.setCountry(driverReg.getCountry());
		profileDetails.setDrivingLicenseNo(driverReg.getDrivingLicenseNo());
		profileDetails.setFirstName(driverReg.getFirstName());
		profileDetails.setNationalInsuranceNumber(driverReg.getNationalInsuranceNumber());
		profileDetails.setPhoneNumber(driverReg.getPhoneNumber());
		profileDetails.setPostCode(driverReg.getPostCode());
		profileDetails.setRegion(driverReg.getRegion());
		profileDetails.setStreetAddress1(driverReg.getStreetAddress1());
		profileDetails.setStreetAddress2(driverReg.getStreetAddress2());
		profileDetails.setSurName(driverReg.getSurName());
		profileDetails.setVanSize(driverReg.getVanSize());
		profileDetails.setVehicleRegNo(driverReg.getVehicleRegNo());
		driverProfileRepo.save(profileDetails);
		
	}

	@Override
	public TransportResponse setPassword(SetPassword setPassword) throws PasswordMismatchException {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		comparePassword(setPassword);
		String encodePassword = encodePassword(setPassword.getPassword());
	//	savePassword(encodePassword, setPassword.getEmailId());
		result.setCode(TransprotSuccessCodes.PASSWORD_SET_SUCCESSFULLY);
		result.setMessage(TransportSuccessMessages.PASSWORD_SET_SUCCESSFULLY);
		response.setResult(result);
		return response;
		
	}
	
	private void comparePassword(SetPassword setPassword) throws PasswordMismatchException {
		LOG.debug("ConfirmPAssword   " + setPassword.getConfirmPassword());
		if (!(setPassword.getPassword().equals(setPassword.getConfirmPassword()))) {
			throw new PasswordMismatchException(TransportErrorMessages.PASSWORD_MISSMATCH);
		}
	}

	@Override
	public TransportResponse uploadDocs(KYCDetails kycDetails,HttpServletRequest httpReq) {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		AccessToken accessToken = (AccessToken) httpReq.getAttribute(TransportConstant.ACCESS_TOKEN_OBJ);
		LOG.debug("Access Token " + accessToken);
		String emailId = accessToken.getPayload().getEmailId().toString();
		DriverReg details=driverRepo.findByEmailId(emailId);
		// Check kyc already uploaded or not
				boolean uploadResult = kycRepo.checkKycUploaded(details.getUserId());
				if (!uploadResult) {
					// If kyc not uploaded. save kyc details
					kycDetails.setStatus("pending");
					kycDetails.setUserRefId(details.getUserId());
					KYCDetails saveDetails = kycRepo.save(kycDetails);
					if (saveDetails.getUserRefId() != null) {
						result.setCode(TransprotSuccessCodes.KYC_DOCUMENT_UPDATE_SUCCESSFULLY);
						result.setMessage(TransportSuccessMessages.KYC_DOCUMENT_UPDATE_SUCCESSFULLY);
						response.setResult(result);
					} else {
						TransportErrorResp errorResp=new TransportErrorResp();
						errorResp.setErrorCode(TransportErrorCodes.FAILED_TO_UPLOAD_KYC);
						errorResp.setErrorMessage(TransportErrorMessages.FAILED_TO_UPLOAD_KYC);
						response.setErrorResp(errorResp);
					}
				} else {
					// if kyc already uploaded
					boolean status = kycRepo.checkKycStatus(details.getUserId(), "approved");
					if (!status) {
						KYCDetails getKycDetails = kycRepo.findByUserRefId(details.getUserId());
						getKycDetails.setVanImage(kycDetails.getVanImage());
						getKycDetails.setMotorInsurance(kycDetails.getMotorInsurance());
						getKycDetails.setLiabilityInsurance(kycDetails.getLiabilityInsurance());
						getKycDetails.setGoodsTransitCover(kycDetails.getGoodsTransitCover());
						getKycDetails.setDrivingLicense(kycDetails.getDrivingLicense());
						getKycDetails.setStatus("pending");
						KYCDetails updateKycDetails = kycRepo.save(getKycDetails);
						if (updateKycDetails.getUserRefId() != null) {
							result.setCode(TransprotSuccessCodes.KYC_DOCUMENT_UPDATE_SUCCESSFULLY);
							result.setMessage(TransportSuccessMessages.KYC_DOCUMENT_UPDATE_SUCCESSFULLY);
							response.setResult(result);
						} 
					} else {
						TransportErrorResp errorResp=new TransportErrorResp();
						errorResp.setErrorCode(TransportErrorCodes.ALREADY_APPROVED);
						errorResp.setErrorMessage(TransportErrorMessages.ALREADY_APPROVED);
						response.setErrorResp(errorResp);
					}
				}
				return response;
	}

	@Override
	public TransportResponse resendOTP(UserOTPReq otpReq) throws InvalidMaildId, ResendOTPFailed, SendOTPFailed {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		checkUserExists(otpReq.getEmailId());
		UseOTP useOTP=otpRepo.findByEmailId(otpReq.getEmailId());
		String otp=UUIDUtil.generateOTP();
		useOTP.setOtp(otp);
		try
		{
			otpRepo.save(useOTP);
			reSendOTP(otpReq.getEmailId(),otp);
			result.setCode(TransprotSuccessCodes.SEND_OTP);
			result.setMessage(TransportSuccessMessages.SEND_OTP);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new SendOTPFailed(TransportErrorMessages.OTP_FAILED);
		}
		return response;
		
	}

	private void reSendOTP(String emailId,String otp) throws SendOTPFailed {
		
		try
		{
			emailService.sendOTP(emailId,otp);
		
		}
		catch(Exception ex)
		{
			throw new SendOTPFailed(TransportErrorMessages.OTP_FAILED);
		}
	}

	private void checkUserExists(String emailId) throws InvalidMaildId {
		boolean result=otpRepo.checkUserExists(emailId);
		if(!result)
		{
			throw new InvalidMaildId(TransportErrorMessages.INVALID_EMAIL_ID);
		}
		
	}

	@Override
	public LoginResult validateOTP(UserOTPReq otpReq) throws InvalidMaildId, OTPExpiredException, InvalidOTP, TransportException, TransportDAOException {
		LoginResult result=new LoginResult();
		LoginResponse response=new LoginResponse();
		checkUserExists(otpReq.getEmailId());
		checkOtp(otpReq.getEmailId(), otpReq.getOtp());	
		boolean status=commonUtil.checkCustomerMailId(otpReq.getEmailId());
		if(status)
		{
//			AccessTokenPayload accessTokenPayload = new AccessTokenPayload();
//			accessTokenPayload.setEmailId(otpReq.getEmailId());
//			accessTokenPayload.setIssuedDate(DateUtil.currentDate());
//			accessTokenPayload.setExpiryDate(DateUtil.getDateAfterMins(accessTokenExpiration));
//			accessTokenPayload.setTokenId(MDC.get("transactionId"));
//			UserRegistration details=customerRepo.findByEmailId(otpReq.getEmailId());
//			response.setAuthorization(jwtUtility.generateAccessToken(accessTokenPayload,privateKeyService.getPrivateKey(DateUtil.currentDateTruncated())));
//			response.setExpirtyDate(accessTokenPayload.getExpiryDate());
			UserRegistration details=customerRepo.findByEmailId(otpReq.getEmailId());
			response.setRole(EnumUserRole.getStatus(details.getRole()));
			response.setUserId(details.getUserId());
			result.setResult(response);
		}
		else
		{
			String userRefId = UUIDUtil.randomUUID();
			UserRegistration reg=new UserRegistration();
			reg.setEmailId(otpReq.getEmailId());
			reg.setUserId(userRefId);
			reg.setRole(EnumUserRole.CUSTOMER.getStatusId());
			reg.setStatus(EnumAccountStatus.ACTIVE.getStatusId());
			customerRepo.save(reg);
//			AccessTokenPayload accessTokenPayload = new AccessTokenPayload();
//			accessTokenPayload.setEmailId(otpReq.getEmailId());
//			accessTokenPayload.setIssuedDate(DateUtil.currentDate());
//			accessTokenPayload.setExpiryDate(DateUtil.getDateAfterMins(accessTokenExpiration));
//			accessTokenPayload.setTokenId(MDC.get("transactionId"));
//			UserRegistration details=customerRepo.findByEmailId(otpReq.getEmailId());
//			response.setAuthorization(jwtUtility.generateAccessToken(accessTokenPayload,privateKeyService.getPrivateKey(DateUtil.currentDateTruncated())));
//			response.setExpirtyDate(accessTokenPayload.getExpiryDate());
			UserRegistration details=customerRepo.findByEmailId(otpReq.getEmailId());
			response.setRole(EnumUserRole.getStatus(details.getRole()));
			response.setUserId(details.getUserId());
			result.setResult(response);
		}
			
			return result;
	}

	private void checkOtp(String emailId, String otp) throws OTPExpiredException, InvalidOTP {
		boolean result = otpRepo.checkOTPExists(emailId, otp);
		if (result) {
			boolean resp = otpRepo.checkOTPExpired(emailId, otp);
			if (!resp) {
				throw new OTPExpiredException(TransportErrorMessages.OTP_EXPIRED_EXCEPTION);
			}
		} else {
			throw new InvalidOTP(TransportErrorMessages.INVALID_OTP);
		}
		
	}

	@Override
	public DriverLoginResponse driverLogin(DriverLoginReq driverLoginReq) throws SendOTPFailed, LoginFailedException, InvalidMaildId, InvalidAccount, TransportException, TransportDAOException {
		DriverLoginResponse result=new DriverLoginResponse();
		LoginResponse response=new LoginResponse();
		boolean resp=checkDriverMailId(driverLoginReq.getEmailId());
		if(resp)
		{
			if (verifyPassword(driverLoginReq.getPassword(), getDriverPwd(driverLoginReq))) {
//				AccessTokenPayload accessTokenPayload = new AccessTokenPayload();
//				accessTokenPayload.setEmailId(driverLoginReq.getEmailId());
//				accessTokenPayload.setIssuedDate(DateUtil.currentDate());
//				accessTokenPayload.setExpiryDate(DateUtil.getDateAfterMins(accessTokenExpiration));
//				accessTokenPayload.setTokenId(MDC.get("transactionId"));
				DriverReg details=driverRegRepo.findByEmailId(driverLoginReq.getEmailId());
//				response.setAuthorization(jwtUtility.generateAccessToken(accessTokenPayload,privateKeyService.getPrivateKey(DateUtil.currentDateTruncated())));
//				response.setExpirtyDate(accessTokenPayload.getExpiryDate());
				response.setRole(EnumUserRole.getStatus(details.getRole()));
				response.setUserId(details.getUserId());
				result.setLoginResponse(response);
			}
			else
			{
				throw new InvalidAccount(TransportErrorMessages.INVALID_ACCOUNT);
			}
		}
		else {
			sendOTP(driverLoginReq.getEmailId());
			TransportResult transRes=new TransportResult();
			transRes.setCode(TransprotSuccessCodes.SEND_OTP);
			transRes.setMessage(TransportSuccessMessages.SEND_OTP);
			result.setResult(transRes);
		}
		
		return result;
	}

	private String getDriverPwd(DriverLoginReq driverLoginReq) {
		DriverReg driverDetails=driverRegRepo.findByEmailId(driverLoginReq.getEmailId());
		return driverDetails.getPassword();
		
	}

	private boolean checkDriverMailId(String emailId) throws InvalidMaildId {
		return driverRegRepo.checkEmail(emailId);
		
	}

	@Override
	public OTPResponse validateDriverOTP(DriverOTPReq driverOtpreq) throws InvalidMaildId, TransportException, TransportDAOException, LoginFailedException, OTPExpiredException, InvalidOTP {
		
		LoginResponse response=new LoginResponse();
		OTPResponse otpResponse=new OTPResponse();
		TransportResult result=new TransportResult();
		checkUserExists(driverOtpreq.getEmailId());
		checkOtp(driverOtpreq.getEmailId(), driverOtpreq.getOtp());	
		//boolean reuslt=checkDriverMailExists(driverOtpreq.getEmailId());
//			LOG.debug("password : "+driverOtpreq.getPassword());
//			LOG.debug("Hashed password : "+getDriverPassword(driverOtpreq));
//			if (verifyPassword(driverOtpreq.getPassword(), getDriverPassword(driverOtpreq))) {
//			AccessTokenPayload accessTokenPayload = new AccessTokenPayload();
//			accessTokenPayload.setEmailId(driverOtpreq.getEmailId());
//			accessTokenPayload.setIssuedDate(DateUtil.currentDate());
//			accessTokenPayload.setExpiryDate(DateUtil.getDateAfterMins(accessTokenExpiration));
//			accessTokenPayload.setTokenId(MDC.get("transactionId"));
//			DriverReg details=driverRegRepo.findByEmailId(driverOtpreq.getEmailId());
//			response.setAuthorization(jwtUtility.generateAccessToken(accessTokenPayload,privateKeyService.getPrivateKey(DateUtil.currentDateTruncated())));
//			response.setExpirtyDate(accessTokenPayload.getExpiryDate());
//			response.setRole(EnumUserRole.getStatus(details.getRole()));
//			otpResponse.setLoginResponse(response);
//			}
			DriverReg driverReg=new DriverReg();
			driverReg.setEmailId(driverOtpreq.getEmailId());
			driverReg.setRole(EnumUserRole.DRIVER.getStatusId());
			String userRefId = UUIDUtil.randomUUID();
			driverReg.setUserId(userRefId);
			driverReg.setStatus(EnumAccountStatus.ACTIVE.getStatusId());
			driverReg.setPassword(encodePassword(driverOtpreq.getPassword()));
			try
			{
				driverRegRepo.save(driverReg);
				result.setCode(TransprotSuccessCodes.SUCCESSFULLY_REGISTERED);
				result.setMessage(TransportSuccessMessages.SUCCESSFULLY_REGISTERED);
				otpResponse.setResult(result);
			}
			catch(Exception ex)
			{
				LOG.debug("Error occured : "+ex.getMessage());
				TransportErrorResp errorResp=new TransportErrorResp();
				errorResp.setErrorCode(TransportErrorCodes.REGISTER_FAILED);
				errorResp.setErrorMessage(TransportErrorMessages.REGISTER_FAILED);
				otpResponse.setErrorResp(errorResp);
			}
		
			return otpResponse;
	}

	private String getDriverPassword(DriverOTPReq driverOtpreq) {
		DriverReg driverDetails=driverRegRepo.findByEmailId(driverOtpreq.getEmailId());
		return driverDetails.getPassword();
	}

	private boolean checkDriverMailExists(String emailId) {
		
		return driverRegRepo.checkEmail(emailId);
		
	}

	@Override
	public TransportResponse forgotPassword(ForgotPassword forgotPassword,HttpServletRequest httpReq) throws InvalidMaildId {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		if(checkDriverMailId(forgotPassword.getEmailId()))
		{
			String resetToken = generateToken();
			String resetURL = getResetURL(resetToken, httpReq);
			DriverReg driverDetails = driverRegRepo.findByEmailId(forgotPassword.getEmailId());
			emailService.resetMail(driverDetails.getEmailId(),resetURL);
			if (checkResetTokenExists(driverDetails.getEmailId())) {
				ResetToken details = resetTokenRepo.findByEmailId(driverDetails.getEmailId());
				details.setStatus("1");
				details.setResetToken(resetToken);
				resetTokenRepo.save(details);
			} else {
				saveResetToken(resetToken, driverDetails.getEmailId());
			}
			result.setCode(TransprotSuccessCodes.RESET_LINK_SENT_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.RESET_LINK_SENT_SUCCESSFULLY);
			response.setResult(result);
			return response;
		}
		else
		{
			throw new InvalidMaildId(TransportSuccessMessages.INVALID_MAIL_ID);
		}
		
	}

	private void saveResetToken(String resetToken, String emailId) {
		ResetToken resetTokenDetails = new ResetToken();
		resetTokenDetails.setResetToken(resetToken);
		resetTokenDetails.setEmailId(emailId);
		resetTokenDetails.setStatus("1");
		resetTokenRepo.save(resetTokenDetails);
		
	}

	private boolean checkResetTokenExists(String emailId) {
		return resetTokenRepo.checkTokenExistsByEmailId(emailId);
	}

	private String generateToken() {
		return UUID.randomUUID().toString();
	}
	private String getResetURL(String resetToken, HttpServletRequest request) {
		return resetURL + "/" + resetToken;
	}

	@Override
	public VanDetailsResp getVanDetails() {
		VanDetailsResp response=new VanDetailsResp();
		List<VanDetailsList> vansList=new ArrayList<VanDetailsList>();
		Iterable<VanDetails> vanDetailsList=vanDetailsRepo.findAll();
		for(VanDetails vanDetails:vanDetailsList)
		{
			VanDetailsList vanList=new VanDetailsList();
			vanList.setVanId(vanDetails.getId());
			vanList.setVanTypeName(vanDetails.getVanTypeName());
			vanList.setHeight(vanDetails.getHeight());
			vanList.setLength(vanDetails.getLength());
			vanList.setWidth(vanDetails.getWidth());
			vanList.setMaximumWeight(vanDetails.getMaximumWeight());
			vanList.setMinimumWeight(vanDetails.getMinimumWeight());
			vanList.setSeats(vanDetails.getNumberOfSeats());
			vansList.add(vanList);
		}
		response.setResult(vansList);
		return response;
	}

	@Override
	public DistanceResp getDistance(DistanceReq distanceReq) throws InvalidLocation {
		DistanceResp response=new DistanceResp();
		DistanceAndHours result=new DistanceAndHours();
		DistanceResponse distanceResp=distance(distanceReq);
		DecimalFormat dec = new DecimalFormat("#0.00");
		result.setDistance(dec.format(distanceResp.getDistance()));
		result.setTime(distanceResp.getTimeInseconds());
		response.setResult(result);
		return response;
	}

	private DistanceResponse distance(DistanceReq distanceReq) throws InvalidLocation {
		DistanceResponse response=new DistanceResponse();
		RestTemplate restTemplate = new RestTemplate();
		  HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity <String> entity = new HttpEntity<String>(headers);
		 String url_request=distacneURl+distanceReq.getPickUplatitude()+","+distanceReq.getPickUplongitude()+"&destinations="+distanceReq.getDeliveryLatitude()+","+distanceReq.getDeliveryLongitude()+"&key="+distanceAPIKey;
		 LOG.debug("url requ"+url_request);
		 try
		 {
			 String result=restTemplate.exchange(url_request, HttpMethod.GET, entity, String.class).getBody(); 
		      LOG.debug("Result "+ result);
		      JSONObject jsonResponse = new JSONObject(result);
		      JSONArray jsonArray=(JSONArray) jsonResponse.get("rows");
		      JSONObject jsoObj=(JSONObject) jsonArray.get(0);
		      JSONArray elements=(JSONArray) jsoObj.get("elements");
		      
		      JSONObject jsonObj=(JSONObject) elements.get(0);
		      String status=jsonObj.getString("status");
		      if(status.equals("OK"))
		      {
		    	  JSONObject durationObj=(JSONObject) jsonObj.get("duration");
		    	  int timeInSeconds=durationObj.getInt("value");
			      JSONObject distacneObj=(JSONObject) jsonObj.get("distance");
			      String time=durationObj.getString("text");
			      double distance=distacneObj.getDouble("value");
			      double distanceInmiles=convertMetersToMiles(distance);
			      response.setTimeInseconds(timeInSeconds);
			      response.setTime(time);
			      response.setDistance(distanceInmiles);
		      }
		      else
		      {
		    	  throw new InvalidLocation(TransportErrorMessages.INVALID_LOCATION);
		      }
		      
		 }
		 catch(InvalidLocation ex)
	 		{
			 throw new InvalidLocation(TransportErrorMessages.INVALID_LOCATION);
	 		}
		 catch(Exception ex) {
			 ex.printStackTrace();
	 			LOG.debug(ex.getMessage());
		 }
		 return response;

	}

	private double convertMetersToMiles(double distance) {
		return distance*0.000621371;
		
	}

	@Override
	public VehicleRegResp addVehicle(RegisterVehicle addVehicle, HttpServletRequest httpReq) throws TransportException, VehicleRegFailed, VehicleUploadFailed {
		TransportResult result = new TransportResult();
		VehicleRegResp response=new VehicleRegResp();
		LOG.debug("vehicle id"+addVehicle.getVehicleId());
		if(addVehicle.getVehicleId()==null)
		{
			String vehicleId=generateVehicleId();
			addVehicle.setVehicleId(vehicleId);
			saveVehicleDetails(addVehicle,commonUtil.getDrivrUserRefIdI(httpReq));
			result.setCode(TransprotSuccessCodes.VEHICLE_REG_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.VEHICLE_REG_SUCCESSFULLY);
			response.setResult(result);
			response.setVehicleId(addVehicle.getVehicleId());
		}
		else
		{
			saveVehicleDetails(addVehicle,commonUtil.getDrivrUserRefIdI(httpReq));
			result.setCode(TransprotSuccessCodes.VEHICLE_UPLOAD_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.VEHICLE_UPLOAD_SUCCESSFULLY);
			response.setResult(result);
		}
		
		
		
		return response;
	}
	
	private String generateVehicleId() {
		UUID uuid = UUID.randomUUID();
       String vehicleId="VH"+uuid;
       return vehicleId;
	}
	private boolean checkVehicleIdExists(String vehicleId) {
		return vehicleRepo.checkVehicleIdExists(vehicleId);
		
	}
	
private void saveVehicleDetails(RegisterVehicle addVehicle, String userId) throws TransportException, VehicleRegFailed, VehicleUploadFailed {
		
		boolean result=checkVehicleIdExists(addVehicle.getVehicleId());
		if(!result)
		{
			VehicleRegistration details=new VehicleRegistration();
			details.setUserId(userId);
			details.setBaseRange(addVehicle.getBaseRange());
			details.setVanSize(addVehicle.getVanSize());
			details.setVehicleRegNo(addVehicle.getVehicleRegNo());
			details.setNationalInsuranceNumber(addVehicle.getNationalInsuranceNumber());
			details.setVehicleId(addVehicle.getVehicleId());
			details.setLatittude(addVehicle.getLatittude());
			details.setLongitude(addVehicle.getLongitude());
			details.setStatus(EnumVehicleStatus.NOT_APPROVED.getStatusId());
			details.setBaseArea(addVehicle.getBaseArea());
			details.setVehicleStatus(EnumVehicleOperationStatus.ACTIVE.getStatusId());
			details.setDriverChargesPerHour(addVehicle.getDriverChargePerHour());     
			details.setChargePerMile(addVehicle.getChargePerMile());
			try
			{
				vehicleRepo.save(details);
			}
			catch(Exception ex)
			{
				throw new VehicleRegFailed(TransportErrorMessages.FAILED_TO_SAVE_VEHICLE_DETAILS);
			}
		}
		
		else
		{
			VehicleRegistration vehicleDetails=vehicleRepo.findByVehicleId(addVehicle.getVehicleId());
			vehicleDetails.setBaseRange(addVehicle.getBaseRange());
			vehicleDetails.setVanSize(addVehicle.getVanSize());
			vehicleDetails.setVehicleRegNo(addVehicle.getVehicleRegNo());
			vehicleDetails.setNationalInsuranceNumber(addVehicle.getNationalInsuranceNumber());
			vehicleDetails.setLatittude(addVehicle.getLatittude());
			vehicleDetails.setLongitude(addVehicle.getLongitude());
			vehicleDetails.setStatus(EnumVehicleStatus.NOT_APPROVED.getStatusId());
			vehicleDetails.setBaseArea(addVehicle.getBaseArea());
			vehicleDetails.setDriverChargesPerHour(addVehicle.getDriverChargePerHour());
			vehicleDetails.setChargePerMile(addVehicle.getChargePerMile());
			try
			{
				vehicleRepo.save(vehicleDetails);
			}
			catch(Exception ex)
			{
				LOG.debug("Error occured : "+ex.getMessage());
				throw new VehicleUploadFailed(TransportErrorMessages.FAILED_TO_UPLOAD_VEHICLE_DETAILS);
			}
			
		}
		
	}

@Override
public TransportResponse uploadFile(String vanImage, String motorInsurance, String goodsTransitCover, String liabilityInsurance,String vehicleId,
		HttpServletRequest request) {
	TransportResult result = new TransportResult();
	TransportResponse response = new TransportResponse();
	VehicleRegistration details=vehicleRepo.findByVehicleId(vehicleId);
	details.setVanImage(vanImage);
	details.setMotorInsurance(motorInsurance);
	details.setGoodsTransitCover(goodsTransitCover);
	details.setLiabilityInsurance(liabilityInsurance);
	details.setPath(directory);
	try
	{
		vehicleRepo.save(details);
		result.setCode(TransprotSuccessCodes.DOCUMENTS_UPLOADED_SUCCESSFULLY);
		result.setMessage(TransportSuccessMessages.DOCUMENTS_UPLOADED_SUCCESSFULLY);
		response.setResult(result);
		
	}
	catch(Exception ex)
	{
		ex.printStackTrace();
	}
	return response;
	
}

@Override
public TransportResponse uploadFile(MultipartFile file, MultipartFile file1, MultipartFile file2, MultipartFile file3,
		String vehicleId, HttpServletRequest request) throws FailedUpdateDocs {
	TransportResult result = new TransportResult();
	TransportResponse response = new TransportResponse();
	VehicleRegistration details=vehicleRepo.findByVehicleId(vehicleId);
	if(file!=null && file1!=null && file2!=null && file3!=null)
	{
		String vanImage = fileStorageService.storeFile(file);
		String motorInsurance = fileStorageService.storeFile(file1);
		String goodsTransitCover = fileStorageService.storeFile(file2);
		String liabilityInsurance = fileStorageService.storeFile(file3);
		details.setVanImage(vanImage);
		details.setMotorInsurance(motorInsurance);
		details.setGoodsTransitCover(goodsTransitCover);
		details.setLiabilityInsurance(liabilityInsurance);
		details.setPath(directory);
		try
		{
			vehicleRepo.save(details);
			result.setCode(TransprotSuccessCodes.DOCUMENTS_UPLOADED_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.DOCUMENTS_UPLOADED_SUCCESSFULLY);
			response.setResult(result);
			
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	else
	{
		if(file!=null)
		{
			String vanImage = fileStorageService.storeFile(file);
			details.setVanImage(vanImage);
		}
		if(file1!=null)
		{
			String motorInsurance = fileStorageService.storeFile(file1);
			details.setMotorInsurance(motorInsurance);
		}
		if(file2!=null)
		{
			String goodsTransitCover = fileStorageService.storeFile(file2);
			details.setGoodsTransitCover(goodsTransitCover);
		}
		if(file3!=null)
		{
			String liabilityInsurance = fileStorageService.storeFile(file3);
			details.setLiabilityInsurance(liabilityInsurance);
		}
		try
		{
			vehicleRepo.save(details);	
			result.setCode(TransprotSuccessCodes.DOCUMENTS_UPDATED_SUCCESSFULLY);
			result.setMessage(TransportSuccessMessages.DOCUMENTS_UPDATED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new FailedUpdateDocs(TransportErrorMessages.FAILED_TO_UPLOAD_DOCS);
		}
	}
	
	
	return response;
}

@Override
public EstimatedQutoeResp getEstimatedQuote(QuoteDetails quoteReq) throws IOException, InvalidLocation {
	EstimatedQutoeResp resp=new EstimatedQutoeResp();
	DistanceResponse price=calculateEsitmatedPrice(quoteReq);
	DecimalFormat dec = new DecimalFormat("#0.00");
	resp.setEstimatedPrice(dec.format(price.getPrice()));
	resp.setPickUpDate(quoteReq.getPickUpDate());
	resp.setPickUpTime(quoteReq.getPickUpTime());
	resp.setDistance(dec.format(price.getDistance()));
	resp.setBookingFee(dec.format(price.getBookingFee()));
	resp.setDiscount(dec.format(price.getDiscount()));
	resp.setTotalPrice(dec.format(price.getTotalPrice()));
	resp.setDriverCharges(dec.format(price.getDriverCharges()));
	resp.setHelpersCharges(dec.format(price.getHelpersCharges()));
	resp.setHoursNeededVehicle(price.getHoursNeededVehicle());
	resp.setMileageCharges(dec.format(price.getMileageCharges()));
	return resp;
}
private DistanceResponse calculateEsitmatedPrice(QuoteDetails quoteReq) throws IOException, InvalidLocation {
	
	DistanceReq req=new DistanceReq();
	req.setPickUplatitude(quoteReq.getPickupLatitude());
	req.setPickUplongitude(quoteReq.getPickupLongitude());
	req.setDeliveryLatitude(quoteReq.getDropLatitude());
	req.setDeliveryLongitude(quoteReq.getDropLongitude());
	DistanceResponse response=getTotalEstimatedPrice(quoteReq,req);
	return response;
}
private DistanceResponse getTotalEstimatedPrice(QuoteDetails quoteReq,DistanceReq req) throws IOException, InvalidLocation {
	DistanceResponse distanceResp=calculateDistance(req);
	double totalDistancePrice=getTotalDistancePrice(distanceResp,quoteReq);
	double totalDriverHoursPrice=getTotalDriverHoursPrice(distanceResp,quoteReq);
	double totalHelpersPrice=getTotalHelpersPrice(quoteReq.getHelpersCount());
	double bookingFee=getTotalBookingFee();
	double totalDiscountPrice=getTotalDiscountprice(totalDistancePrice,totalDriverHoursPrice,totalHelpersPrice);
	double estimatedQuotePrice=getTotalEstimatedQuotePrice(totalDistancePrice,totalDriverHoursPrice,totalHelpersPrice);
	double finalQuotePrice=discountQuotePrice(totalDiscountPrice,estimatedQuotePrice,bookingFee);
	distanceResp.setPrice(estimatedQuotePrice);
	distanceResp.setBookingFee(bookingFee);
	distanceResp.setDiscount(totalDiscountPrice);
	distanceResp.setTotalPrice(finalQuotePrice);
	distanceResp.setHelpersCharges(totalHelpersPrice);
	int minutesNeeded=quoteReq.getHoursNeeded();
	int hours=minutesNeeded/60;
	int minutes=minutesNeeded%60;
	distanceResp.setHoursNeededVehicle(hours+"hrs"+minutes+"minutes");
	distanceResp.setDriverCharges(totalDriverHoursPrice);
	distanceResp.setMileageCharges(totalDistancePrice);
	return distanceResp;
	}
private double getTotalDiscountprice(double totalDistancePrice, double totalDriverHoursPrice, double totalHelpersPrice) {
	double totalPrice=totalDistancePrice+totalDriverHoursPrice+totalHelpersPrice;
	Settings settingsDetails=settingsRepo.findByType("HelperPrice");
	double discountPricePercent=settingsDetails.getPromotionalDiscount();
	double discountPrice=totalPrice*discountPricePercent*0.01;
	return discountPrice;
}
private double discountQuotePrice(double totalDiscountPrice, double estimatedQuotePrice,double bookingFee) {
	double discountQuotePrice=estimatedQuotePrice-totalDiscountPrice+bookingFee;
	return discountQuotePrice;
}
private double getTotalEstimatedQuotePrice(double totalDistancePrice, double totalDriverHoursPrice,
		double totalHelpersPrice) {
	double totalEstimatedPrice=totalDistancePrice+totalDriverHoursPrice+totalHelpersPrice;
	return totalEstimatedPrice;
}
private double getTotalBookingFee() {
	Settings settingsDetails=settingsRepo.findByType("HelperPrice");
	double bookingFee=settingsDetails.getBookingFee();
	return bookingFee;
}
private double getTotalHelpersPrice(int helpersCount) {
	Settings settingsDetails=settingsRepo.findByType("HelperPrice");
	double totalHelpersPrice=settingsDetails.getPricePerDay()+helpersCount;
	return totalHelpersPrice;
}
private double getTotalDriverHoursPrice(DistanceResponse distanceResp, QuoteDetails quoteReq) {
	double driverCharges=getDriverCharges(quoteReq);
	int hoursNeeded=quoteReq.getHoursNeeded();
	int hours=hoursNeeded/60;
	int minutes=hoursNeeded%60;
	Double travelTimeInhours=new Double(hours);
	Double travelTimeInMinutes=new Double(minutes);
	double totalDriverHourCharges=travelTimeInhours*driverCharges;
	double totalDriverMinuteCharges=(travelTimeInMinutes/60)*driverCharges;
	double totaldriverCharges=totalDriverHourCharges+totalDriverMinuteCharges;
	return totaldriverCharges;
}
private double getTotalDistancePrice(DistanceResponse distanceResp, QuoteDetails quoteReq) {
	double pricePerMile=getMileCharges(quoteReq);
	double totalDistanceprice=distanceResp.getDistance()*pricePerMile;
	return totalDistanceprice;
}
private double getDriverCharges(QuoteDetails quoteReq) {
	VanDetails vanDetails=vanDetailsRepo.findById(quoteReq.getVanSize());
	if(getDayOfWeek(quoteReq).equals("Saturday") || getDayOfWeek(quoteReq).equals("Sunday"))
	{
		double driverCharges=vanDetails.getDriverChargePerHourWeekDays();
		return driverCharges;
	}
	else
	{
		double driverCharges=vanDetails.getDriverChargePerHourNormalDays();
		return driverCharges;
	}
	
}
private String getDayOfWeek(QuoteDetails quoteReq) {
	 SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week abbreviated
     return simpleDateformat.format(quoteReq.getPickUpDate());
}
private double getMileCharges(QuoteDetails quoteReq) {
	VanDetails vanDetails=vanDetailsRepo.findById(quoteReq.getVanSize());
	if(getDayOfWeek(quoteReq).equals("Saturday") || getDayOfWeek(quoteReq).equals("Sunday"))
	{
		double pricePerMile=vanDetails.getPricePerMileWeekDays();
		return pricePerMile;
	}
	else
	{
		double pricePerMile=vanDetails.getPricePerMileNormalDays();
		return pricePerMile;
	}
	
}
private double pricePerHelper() {
	return 10;
	
}

private DistanceResponse calculateDistance(DistanceReq distanceReq) throws InvalidLocation {
	DistanceResponse response=new DistanceResponse();
	RestTemplate restTemplate = new RestTemplate();
	  HttpHeaders headers = new HttpHeaders();
      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
      HttpEntity <String> entity = new HttpEntity<String>(headers);
	 String url_request=distacneURl+distanceReq.getPickUplatitude()+","+distanceReq.getPickUplongitude()+"&destinations="+distanceReq.getDeliveryLatitude()+","+distanceReq.getDeliveryLongitude()+"&key="+distanceAPIKey;
	 LOG.debug("url requ"+url_request);
	 try
	 {
		 String result=restTemplate.exchange(url_request, HttpMethod.GET, entity, String.class).getBody(); 
	      LOG.debug("Result "+ result);
	      JSONObject jsonResponse = new JSONObject(result);
	      JSONArray jsonArray=(JSONArray) jsonResponse.get("rows");
	      JSONObject jsoObj=(JSONObject) jsonArray.get(0);
	      JSONArray elements=(JSONArray) jsoObj.get("elements");
	      
	      JSONObject jsonObj=(JSONObject) elements.get(0);
	      String status=jsonObj.getString("status");
	      if(status.equals("OK"))
	      {
	    	  JSONObject durationObj=(JSONObject) jsonObj.get("duration");
	    	  int timeInSeconds=durationObj.getInt("value");
		      JSONObject distacneObj=(JSONObject) jsonObj.get("distance");
		      String time=durationObj.getString("text");
		      double distance=distacneObj.getDouble("value");
		      double distanceInmiles=convertMetersToMiles(distance);
		      response.setTimeInseconds(timeInSeconds);
		      response.setTime(time);
		      response.setDistance(distanceInmiles);
	      }
	      else
	      {
	    	  throw new InvalidLocation(TransportErrorMessages.INVALID_LOCATION);
	      }
	      
	 }
	 catch(InvalidLocation ex)
 		{
		 throw new InvalidLocation(TransportErrorMessages.INVALID_LOCATION);
 		}
	 catch(Exception ex) {
		 ex.printStackTrace();
 			LOG.debug(ex.getMessage());
	 }
	 return response;

}
}

