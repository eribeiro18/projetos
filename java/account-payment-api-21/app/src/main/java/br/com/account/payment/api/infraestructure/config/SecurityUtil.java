package br.com.account.payment.api.infraestructure.config;

import java.util.Base64;

import org.json.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {

	private static final String ENCRYPT_METHOD = "MD5";
	
	private SecurityUtil() {
	};

	public static String cryptPassword(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(password);
	}

	public static String getJwtUser(String token) {
		String[] jwt = token.split("\\.");
		JSONObject jsonObject = new JSONObject(new String(Base64.getUrlDecoder().decode(jwt[1])));
		return jsonObject.getString("sub");
	}

}