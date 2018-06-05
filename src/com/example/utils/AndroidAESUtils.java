package com.example.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * key必须是16bytes
 * 
 * @author Queal
 *
 */
public class AndroidAESUtils {
	private static final String KEY_ALGORITHM = "AES";
	private static final String SECURE_ALGORITHM = "AES/CBC/PKCS5Padding";

	public static String encrypt(String key, String content) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(SECURE_ALGORITHM);
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(key.getBytes()));
			byte[] result = cipher.doFinal(byteContent);
			return Base64.encodeBase64String(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String key, String content) {
		try {
			SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);
			Cipher cipher = Cipher.getInstance(SECURE_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(key.getBytes()));
			return new String(cipher.doFinal(Base64.decodeBase64(content)), "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
		String key = "1234567890123456";
		String content = "abc";
		String enCodeCotent;
		
		enCodeCotent = encrypt(key, content);
		System.out.println(enCodeCotent);
		
		System.out.println(decrypt(key, "9Hnvri1B0jIn9h5nX87ZXA=="));
	}
}
