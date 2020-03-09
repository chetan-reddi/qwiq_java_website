package com.transport.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.transport.custom.annotations.ValidPassword;
@Entity
@Table(name="users")
public class DriverReg {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
	@Column(name="user_id")
	private String userId;
	@NotNull(message = "email should not be null")
	@NotEmpty
	@Email
	@Column(name="email_id")
	private String emailId;
	@NotNull(message = "password should not be null")
	@NotEmpty
	@ValidPassword
	@Column(name="password")
	private String password;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registered_date")
	private Date userCreatedDate;
	@Column(name="role")
	private int role;
	@Column(name="status")
	private int status;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Date getUserCreatedDate() {
		return userCreatedDate;
	}
	public void setUserCreatedDate(Date userCreatedDate) {
		this.userCreatedDate = userCreatedDate;
	}
}
