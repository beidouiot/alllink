package com.beidouiot.alllink.community.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;


public class Encodes {
	private static final String DEFAULT_URL_ENCODING = "UTF-8";
	private static final char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
	private static final String SHA1 = "SHA-1";
	private static final String MD5 = "MD5";

	private static SecureRandom random = new SecureRandom();
	
	/**
	 * 对输入字符串进行sha1散列.
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] sha1(byte[] input) throws NoSuchAlgorithmException {
		return digest(input, SHA1, null, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt) throws NoSuchAlgorithmException {
		return digest(input, SHA1, salt, 1);
	}

	public static byte[] sha1(byte[] input, byte[] salt, int iterations) throws NoSuchAlgorithmException {
		return digest(input, SHA1, salt, iterations);
	}

	/**
	 * 对字符串进行散列, 支持md5与sha1算法.
	 * @throws NoSuchAlgorithmException 
	 */
	private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance(algorithm);

		if (salt != null) {
			digest.update(salt);
		}

		byte[] result = digest.digest(input);

		for (int i = 1; i < iterations; i++) {
			digest.reset();
			result = digest.digest(result);
		}
		return result;
	}

	/**
	 * 生成随机的Byte[]作为salt.
	 * 
	 * @param numBytes byte数组的大小
	 */
	public static byte[] generateSalt(int numBytes) {
		Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes);

		byte[] bytes = new byte[numBytes];
		random.nextBytes(bytes);
		return bytes;
	}

	/**
	 * 对文件进行md5散列.
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] md5(InputStream input) throws IOException, NoSuchAlgorithmException {
		return digest(input, MD5);
	}

	/**
	 * 对文件进行sha1散列.
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] sha1(InputStream input) throws IOException, NoSuchAlgorithmException {
		return digest(input, SHA1);
	}

	private static byte[] digest(InputStream input, String algorithm) throws IOException, NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		int bufferLength = 8 * 1024;
		byte[] buffer = new byte[bufferLength];
		int read = input.read(buffer, 0, bufferLength);

		while (read > -1) {
			messageDigest.update(buffer, 0, read);
			read = input.read(buffer, 0, bufferLength);
		}

		return messageDigest.digest();

	}
	
	/**
	 * 字节数字转Hex编码，输出转换后的Hex进制字符串.
	 * 
	 * @param input 字节数字
	 * 	
	 * @return 输出转换后的Hex进制字符串
	 */
	public static String encodeHex(byte[] input) {
		return Hex.encodeHexString(input);
	}

	/**
	 * Hex解码.
	 * 
	 * @param input 16进制字符串
	 * 
	 * @return 输出转换后的字节数字
	 * @throws DecoderException 
	 */
	public static byte[] decodeHex(String input) throws DecoderException {
		return Hex.decodeHex(input.toCharArray());

	}


	
	/**
	 * Base64编码.
	 * @param input 字节数组
	 * @return 返回Base64后的字符串
	 */
	public static String encodeBase64(byte[] input) {
		String base64 = Base64.encodeBase64String(input);
		base64 = base64.replaceAll("\r", "");
        base64 = base64.replaceAll("\n", "");
		return base64;
	}

	/**
	 * Base64编码, URL安全(将Base64中的URL非法字符'+'和'/'转为'-'和'_', 见RFC3548).
	 * @param input 字节数组
	 * @return 返回Base64后的字符串
	 */
	public static String encodeUrlSafeBase64(byte[] input) {
		return Base64.encodeBase64URLSafeString(input);
	}

	/**
	 * Base64解码.
	 */
	public static byte[] decodeBase64(String input) {
		return Base64.decodeBase64(input);
	}

	/**
	 * Base62编码。
	 */
	public static String encodeBase62(byte[] input) {
		char[] chars = new char[input.length];
		for (int i = 0; i < input.length; i++) {
			chars[i] = BASE62[(input[i] & 0xFF) % BASE62.length];
		}
		return new String(chars);
	}

	/**
	 * URL 编码, Encode默认为UTF-8.
	 * @param part 代码编码的字符串（UTF-8编码）
	 * @return 编码后的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlEncode(String part) throws UnsupportedEncodingException {
		return URLEncoder.encode(part, DEFAULT_URL_ENCODING);
	}

	/**
	 * URL 解码, Encode默认为UTF-8.
	 * @param part 待解码的字符串（UTF-8编码）
	 * @return 解码后的字符串
	 * @throws UnsupportedEncodingException 
	 */
	public static String urlDecode(String part) throws UnsupportedEncodingException {
		return URLDecoder.decode(part, DEFAULT_URL_ENCODING);
	}
	
	/**
	 * 将字符串用MD5加密，并且将结果以16进制字符串形式输出
	 * 
	 * @param data
	 *            待加密的字符串
	 * @return 以16进制字符串输出加密结果
	 * @throws NoSuchAlgorithmException 
	 */
	public static final String encodeMD5(String data) throws NoSuchAlgorithmException {
		MessageDigest digest = MessageDigest.getInstance("MD5");
		digest.update(data.getBytes());
		return encodeHex(digest.digest());
	}
}
