package com.transport.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.transport.bean.DistanceReq;
import com.transport.bean.DriverLoginReq;
import com.transport.bean.DriverOTPReq;
import com.transport.bean.ForgotPassword;
import com.transport.bean.KYCDetails;
import com.transport.bean.LoginRequest;
import com.transport.bean.QuoteDetails;
import com.transport.bean.RegisterVehicle;
import com.transport.bean.SetPassword;
import com.transport.bean.UserOTPReq;
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
import com.transport.model.UserRegistration;
import com.transport.response.DistanceResp;
import com.transport.response.DriverLoginResponse;
import com.transport.response.EstimatedQutoeResp;
import com.transport.response.LoginResult;
import com.transport.response.OTPResponse;
import com.transport.response.TransportResponse;
import com.transport.response.VanDetailsResp;
import com.transport.response.VehicleRegResp;

@Service
public interface CustomerService {

	public TransportResponse registerCustomer(UserRegistration customerReq) throws MailExistsException, UserNameExistsException, TransportException;

	public TransportResponse loginCustomer(LoginRequest loginReq) throws  InvalidMaildId, LoginFailedException, TransportException, TransportDAOException, InvalidAccount, SendOTPFailed;

	public TransportResponse registerDriver(UserRegistration driverReg) throws MailExistsException, TransportException;

	public TransportResponse setPassword(SetPassword setPassword) throws PasswordMismatchException;

	public TransportResponse uploadDocs(KYCDetails kycDetails, HttpServletRequest httpReq);

	public TransportResponse resendOTP(UserOTPReq otpReq) throws InvalidMaildId, ResendOTPFailed, SendOTPFailed;

	public LoginResult validateOTP(UserOTPReq otpReq) throws InvalidMaildId, OTPExpiredException, InvalidOTP, TransportException, TransportDAOException;

	public DriverLoginResponse driverLogin(DriverLoginReq driverLoginReq) throws SendOTPFailed, LoginFailedException, InvalidMaildId, InvalidAccount, TransportException, TransportDAOException;

	public OTPResponse validateDriverOTP(DriverOTPReq driverOtpreq) throws InvalidMaildId, TransportException, TransportDAOException, LoginFailedException, OTPExpiredException, InvalidOTP;

	public TransportResponse forgotPassword(ForgotPassword forgotPassword,HttpServletRequest httpReq) throws InvalidMaildId;

	public VanDetailsResp getVanDetails();

	public DistanceResp getDistance(DistanceReq distanceReq) throws InvalidLocation;

	public VehicleRegResp addVehicle(RegisterVehicle addVehicle, HttpServletRequest httpReq) throws TransportException, VehicleRegFailed, VehicleUploadFailed;

	public TransportResponse uploadFile(String vanImage, String motorInsurance, String goodsTransitCover,
			String liabilityInsurance,String vehicleId,HttpServletRequest request);

	public TransportResponse uploadFile(MultipartFile file, MultipartFile file1, MultipartFile file2,
			MultipartFile file3, String vehicleId, HttpServletRequest request) throws FailedUpdateDocs;

	public EstimatedQutoeResp getEstimatedQuote(QuoteDetails quoteReq) throws IOException, InvalidLocation;

}
