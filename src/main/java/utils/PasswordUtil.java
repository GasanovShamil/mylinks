package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtil {

	private static final String salt = "gdhfg798241628003365812Ldtkqsdaz";
	
	public static String getHash(String password) {
		MessageDigest md = null;
		String fortpass = password + salt;
		StringBuffer sb;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(fortpass.getBytes());

		byte[] digest = md.digest();
		sb = new StringBuffer();
		for (byte b : digest) {
			sb.append(String.format("%02x", b & 0xff));
		}
		return sb.toString();
	}
}