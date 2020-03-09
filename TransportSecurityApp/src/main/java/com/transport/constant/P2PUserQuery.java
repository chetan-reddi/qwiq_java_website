package com.transport.constant;

public interface P2PUserQuery {
	String SAVE_ACTIVATION_TOKEN = "insert into activation_token(email_id,activation_token) values(:emailId,:token)";
	String USER_MAIL_BY_ACTIVATION_TOKEN = "select email_id from activation_token where activation_token=:token";
	String USER_ACTIVATION = "update user_registration set user_account_status=2 where user_email_id=:email";
	String GET_PRIVATE_KEY = "select jwt_private_key from jwt_private_keys where jwt_private_key_date = :keyDate";
	String INSERT_PRIVATE_KEY = "insert into jwt_private_keys(jwt_private_key_date,jwt_private_key) values (:keyDate,:privateKey)";
	String DELETE_PRIVATE_KEY = "delete from jwt_private_keys where jwt_private_key_date = :keyDate";
	String USER_SAVE_SECRECT_KEY = "insert into user_secret_key (user_ref_id,secret_key) values(:userId,:secretKey)";
	String USER_SAVE_OTP = "insert into user_one_time_password(user_email,user_otp) values(:emailId,:otp)";
	String CHECK_OTP = "select count(user_email) as count from user_one_time_password where user_email=:emailId and user_otp=:otp";
	String DELETE_OTP = "delete from user_one_time_password where user_email=:emailId and user_otp=:otp";
	String INSERT_LOGGED_OUT_TOKEN = "insert into logged_out_tokens (logged_out_token_id,logged_out_token_expiry) values (:tokenId,:tokenExpiryDate)";
	String DELETE_USER_REFRESH_TOKEN = "delete from user_refresh_token where  user_ref_id = :userId";
	String RESET_TOKEN = "insert into reset_token(reset_token,user_mail) values(:resetToken,:emailId)";
	String CHECK_RESET_TOKEN = "select count(reset_token) count from reset_token where reset_token=:resetToken";
	String CHECK_RESET_TOKEN_EXPIRED = "select count(reset_token) count from reset_token where reset_token=:resetToken and TIMESTAMPDIFF(MINUTE, creation_date, current_timestamp())<=10";
	String DELETE_RESET_TOKEN_EXPIRED = "delete from reset_token where reset_token=:resetToken";
	String UPDATE_NEW_PASSWORD = "update user_registration set user_password=:newPassword where user_email_id=:emailId";
	String GET_EMAILID_BY_TOKEN = "select user_mail from reset_token where reset_token=:resetToken";
	String GET_LOGGED_OUT_TOKEN_COUNT = "select count(1) as count from logged_out_tokens where logged_out_token_id = :tokenId and logged_out_token_expiry > curdate()";
	String SAVE_KYC_DOCS_PHOTO = "insert into user_kyc_docs_details(user_ref_id,address_front_path,address_back_path,identity_proof_front_path,identity_proof_back_path,docs_path) values(:userRefId,:fileName,:fileName2,:fileName3,:fileName4,:path)";
	String UPDATE_KYC_STATUS = "update user_kyc_status set address_status=1,id_status=1 where user_ref_id=:userId";
	String SAVE_KYC_STATUS = "insert into user_kyc_status(user_ref_id,address_status,id_status) values(:userId,1,1)";
	String GET_ALL_ADVERTISEMETN_BY_ALL = "select * from buy_advertisement where country_code=:countryCode and currency_id=:currencyId and coin_type=:coinType and (:amount between minimum_transaction_limit and maximium_transaction_limit) and payment_method=:paymentId";
}