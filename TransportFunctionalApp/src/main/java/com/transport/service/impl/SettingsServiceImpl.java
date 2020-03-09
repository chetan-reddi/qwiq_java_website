package com.transport.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.transport.bean.ChangePassword;
import com.transport.constant.TransportErrorCodes;
import com.transport.constant.TransportErrorMessages;
import com.transport.constant.TransprotSuccessCodes;
import com.transport.exception.FaileToChangePassword;
import com.transport.exception.FailedToSaveDetails;
import com.transport.exception.InvalidPassword;
import com.transport.exception.NoProfileDetails;
import com.transport.model.DriverReg;
import com.transport.model.ProfileDetails;
import com.transport.repository.DriverRepo;
import com.transport.repository.ProfileRepo;
import com.transport.response.ProfileDet;
import com.transport.response.ProfiledetailsResp;
import com.transport.response.TransportResponse;
import com.transport.response.TransportResult;
import com.transport.service.SettingsService;
import com.transport.util.CommonUtil;
import com.transport.util.PasswordHashingUtility;

@Service
public class SettingsServiceImpl implements SettingsService
{
	@Autowired
	ProfileRepo profileRepo;
	@Autowired
	CommonUtil commonUtil;
	@Autowired
	DriverRepo driverRepo;
	@Autowired
	PasswordHashingUtility passwordHashingUtility;
	@Override
	public TransportResponse save(ProfileDetails profileReq, HttpServletRequest httpReq) throws FailedToSaveDetails {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
		boolean checkExists=checkDriverExists(userRefId);
		if(!checkExists)
		{
			profileReq.setUserId(userRefId);
			try
			{
				profileRepo.save(profileReq);
				result.setCode(TransportErrorCodes.PROFILE_DETAILS_SAVED_SUCCESSFULLY);
				result.setMessage(TransportErrorMessages.PROFILE_DETAILS_SAVED_SUCCESSFULLY);
				response.setResult(result);
			}
			catch(Exception ex)
			{
				throw new FailedToSaveDetails(TransportErrorMessages.FAILED_TO_SAVE_PROFILE);
			}
		}
		else
		{
			ProfileDetails details=profileRepo.findByUserId(userRefId);
			details.setFirstName(profileReq.getFirstName());
			details.setLastName(profileReq.getLastName());
			details.setPhoneNumber(profileReq.getPhoneNumber());
			try
			{
				profileRepo.save(details);
				result.setCode(TransportErrorCodes.PROFILE_DETAILS_UPDATED_SUCCESSFULLY);
				result.setMessage(TransportErrorMessages.PROFILE_DETAILS_UPDATED_SUCCESSFULLY);
				response.setResult(result);
			}
			catch(Exception ex)
			{
				throw new FailedToSaveDetails(TransportErrorMessages.FAILED_TO_SAVE_PROFILE);
			}
		}
		
		return response;
	}
	private boolean checkDriverExists(String userRefId) {
		return profileRepo.checkDriverExists(userRefId);
		
	}
	@Override
	public ProfiledetailsResp getProfileDetails(HttpServletRequest httpReq) throws NoProfileDetails {
		
		ProfiledetailsResp response=new ProfiledetailsResp();
		ProfileDet profile=new ProfileDet();
		String userRefId=commonUtil.getDrivrUserRefIdI(httpReq);
		boolean result=checkDriverExists(userRefId);
		if(result)
		{
			ProfileDetails details=profileRepo.findByUserId(userRefId);
			profile.setFirstName(details.getFirstName());
			profile.setLastName(details.getLastName());
			profile.setPhoneNumber(details.getPhoneNumber());
			profile.setAdditionalStopCharge(details.getAdditionalStopCharge());
			profile.setAdditionalTimeChargePerHour(details.getAdditionalTimeChargePerHour());
			profile.setHelperChargePerHour(details.getHelperChargePerHour());
			profile.setOutOfWorkingChargePperhour(details.getOutOfWorkingChargePperhour());
			response.setResult(profile);
		}
		else
		{
			throw new NoProfileDetails(TransportErrorMessages.UPLOAD_PROFILE_DETAILS);
		}
		
		
		return response;
	}
	@Override
	public TransportResponse changePassword(ChangePassword changePwd, HttpServletRequest httpRequest) throws InvalidPassword, FaileToChangePassword {
		TransportResponse response=new TransportResponse();
		TransportResult result=new TransportResult();
		String userRefId=commonUtil.getDrivrUserRefIdI(httpRequest);
		DriverReg details=driverRepo.findByUserId(userRefId);
		checkOldPwd(changePwd.getOldPassword(),details.getEmailId());
		String hashedPwd=encodePassword(changePwd.getNewPassword());
		details.setPassword(hashedPwd);
		try
		{
			driverRepo.save(details);
			result.setCode(TransprotSuccessCodes.PASSWORD_CHANGED_SUCCESSFULLY);
			result.setMessage(TransportErrorMessages.PASSWORD_CHANGED_SUCCESSFULLY);
			response.setResult(result);
		}
		catch(Exception ex)
		{
			throw new FaileToChangePassword(TransportErrorMessages.FAILED_TO_CHANGE_PASSWORD);
		}
		return response;
		
	}
	private String encodePassword(String password) {
		return passwordHashingUtility.getPasswordHash(password);
	}

	private void checkOldPwd(String oldPassword, String emailId) throws InvalidPassword {
		DriverReg userDetails = driverRepo.findByEmailId(emailId);
		boolean passwordVerified = verifyPassword(oldPassword, userDetails.getPassword());
		if (!passwordVerified) {
			throw new InvalidPassword(TransportErrorMessages.WRONG_OLD_PASSWORD);
		}
		
	}
	private boolean verifyPassword(String oldPassword, String hashedPassword) {
			if (passwordHashingUtility.verifyPassword(oldPassword, hashedPassword)) {
				return true;
			}
			return false;
	
	}

}
