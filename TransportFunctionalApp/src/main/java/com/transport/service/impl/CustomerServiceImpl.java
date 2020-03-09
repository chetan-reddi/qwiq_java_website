package com.transport.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.transport.bean.AccessToken;
import com.transport.bean.KYCDetails;
import com.transport.constant.EnumVehicleDocStatus;
import com.transport.constant.TransportConstant;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.constant.TransportSuccessMessages;
import com.transport.constant.TransprotSuccessCodes;
import com.transport.exception.KYCDocsNotUpload;
import com.transport.model.DriverReg;
import com.transport.repository.CustomerRepo;
import com.transport.repository.DriverProfileRepo;
import com.transport.repository.DriverRepo;
import com.transport.repository.KYCRepo;
import com.transport.response.KYCDocsResponse;
import com.transport.response.TransportErrorResp;
import com.transport.response.TransportResponse;
import com.transport.response.TransportResult;
import com.transport.service.CustomerService;
import com.transport.service.PrivateKeyService;
import com.transport.util.CommonUtil;
import com.transport.util.JWTUtility;
import com.transport.util.PasswordHashingUtility;


@Service
public class CustomerServiceImpl implements CustomerService {

	public static final Logger LOG = LoggerFactory.getLogger(CustomerServiceImpl.class);
	@Value("${accesstoken.expiration}")
	private int accessTokenExpiration;
	@Autowired
	PasswordHashingUtility passwordHashingUtility;
	@Autowired
	CustomerRepo customerRepo;
	@Autowired
	DriverRepo driverRepo;
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
	@Override
	public TransportResponse uploadDocs(KYCDetails kycDetails,HttpServletRequest httpReq) {
		TransportResult result = new TransportResult();
		TransportResponse response = new TransportResponse();
		AccessToken accessToken = (AccessToken) httpReq.getAttribute(TransportConstant.ACCESS_TOKEN_OBJ);
		LOG.debug("Access Token " + accessToken);
		String emailId = accessToken.getPayload().getEmailId().toString();
		DriverReg details=driverRepo.findByEmailId(emailId);
		// Check kyc already uploaded or not
				boolean uploadResult = kycRepo.checkKycUploaded(details.getUserId(),kycDetails.getVehicleId());
				if (!uploadResult) {
					// If kyc not uploaded. save kyc details
					kycDetails.setStatus(EnumVehicleDocStatus.PENDING.getStatusId());
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
						getKycDetails.setStatus(EnumVehicleDocStatus.PENDING.getStatusId());
						getKycDetails.setVehicleId(kycDetails.getVehicleId());
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
	public KYCDocsResponse getDocs(String vehicleId,HttpServletRequest httpReq) throws KYCDocsNotUpload {
		KYCDocsResponse response=new KYCDocsResponse();
		checkUserExists(commonUtil.getUserRefIdI(httpReq), vehicleId);
		KYCDetails kycDetails=kycRepo.findByUserRefIdAndVehicleId(commonUtil.getUserRefIdI(httpReq),vehicleId);
		response.setResult(kycDetails);
		return response;
	}
	private void checkUserExists(String userid,String vehicleId) throws KYCDocsNotUpload {
		System.out.println(vehicleId);
		System.out.println(userid);
		boolean result=kycRepo.checkKycUploaded(userid,vehicleId);
		if(!result)
		{
			throw new KYCDocsNotUpload(TransportErrorMessages.KYC_DOCS_NOT_UPLOADED);
		}
	}

}
