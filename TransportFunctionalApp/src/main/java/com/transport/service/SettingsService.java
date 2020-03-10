package com.transport.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.transport.bean.ChangePassword;
import com.transport.exception.FaileToChangePassword;
import com.transport.exception.FailedToSaveDetails;
import com.transport.exception.InvalidPassword;
import com.transport.exception.NoProfileDetails;
import com.transport.model.ProfileDetails;
import com.transport.response.BlogResponse;
import com.transport.response.ProfiledetailsResp;
import com.transport.response.TransportResponse;
@Service
public interface SettingsService {

	TransportResponse save(ProfileDetails profileReq, HttpServletRequest httpReq) throws FailedToSaveDetails;

	ProfiledetailsResp getProfileDetails(HttpServletRequest httpReq) throws NoProfileDetails;

	TransportResponse changePassword(ChangePassword changePwd, HttpServletRequest request) throws InvalidPassword, FaileToChangePassword;

	BlogResponse getAllBlogs();

}
