package com.transport.bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.transport.custom.annotations.ValidPassword;

public class DriverLoginReq {

	@NotNull
	@NotEmpty
	@Email
	private String emailId;
	@NotNull
	@NotEmpty
	@ValidPassword
	private String password;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
