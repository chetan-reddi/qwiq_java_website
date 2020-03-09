package com.transport.constant;

public class TransportQuery {

	public static String GET_PRIVATE_KEY = "select jwt_private_key from jwt_private_keys where jwt_private_key_date = :keyDate";
	public static String INSERT_PRIVATE_KEY = "insert into jwt_private_keys(jwt_private_key_date,jwt_private_key) values (:keyDate,:privateKey)";
	public static String DELETE_PRIVATE_KEY = "delete from jwt_private_keys where jwt_private_key_date = :keyDate";
	public static String DELETE_USER_REFRESH_TOKEN = "delete from user_refresh_token where  user_id = :userId";
	public static String INSERT_LOGGED_OUT_TOKEN = "insert into logged_out_tokens (logged_out_token_id,logged_out_token_expiry) values (:tokenId,:tokenExpiryDate)";
	public static String GET_LOGGED_OUT_TOKEN_COUNT = "select count(1) as count from logged_out_tokens where logged_out_token_id = :tokenId and logged_out_token_expiry > curdate()";


}
