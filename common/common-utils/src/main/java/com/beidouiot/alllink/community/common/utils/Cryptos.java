package com.beidouiot.alllink.community.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class Cryptos {
	private static final String AES = "AES";
	private static final String AES_CBC = "AES/CBC/PKCS5Padding";
	private static final String AES_ECB = "AES/ECB/PKCS7Padding";
	private static final String HMACSHA1 = "HmacSHA1";
	private static final String HMACSHA256 = "HmacSHA256";
	private static final String SHA256 = "SHA-256";
	private static final String SHA1 = "SHA-1";
	private static final int DEFAULT_HMACSHA1_KEYSIZE = 160; // RFC2401
	private static final int DEFAULT_AES_KEYSIZE = 128;
	private static final int DEFAULT_IVSIZE = 16;
	private static final String ENCODING = "UTF-8";

	private static SecureRandom random = new SecureRandom();

	// -- HMAC-SHA1 funciton --//
	/**
	 * 使用HMAC-SHA1进行消息签名, 返回字节数组,长度为20字节.
	 * 
	 * @param input 原始输入字符数组
	 * @param key   HMAC-SHA1密钥
	 * @return HMAC-SHA1加密后的数组
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static byte[] hmacSha1(byte[] input, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(key, HMACSHA1);
		Mac mac = Mac.getInstance(HMACSHA1);
		mac.init(secretKey);
		return mac.doFinal(input);

	}

	/**
	 * 使用HMAC-SHA256进行消息签名, 返回字节数组,长度为20字节.
	 * 
	 * @param input 原始输入字符数组
	 * @param key   HMAC-SHA256密钥
	 * @return HMAC-SHA256加密后的数组
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static byte[] hmacSha256(byte[] input, byte[] key) throws NoSuchAlgorithmException, InvalidKeyException {
		SecretKey secretKey = new SecretKeySpec(key, HMACSHA256);
		Mac mac = Mac.getInstance(HMACSHA256);
		mac.init(secretKey);
		return mac.doFinal(input);

	}
	
	/**
	 * 使用SHA1进行消息签名, 返回字节数组,长度为20字节.
	 * 
	 * @param input 原始输入字符数组
	 * @param key   SHA1密钥
	 * @return SHA1加密后的数组
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] sha1(byte[] input) throws NoSuchAlgorithmException {
		MessageDigest messageDigest;
		messageDigest = MessageDigest.getInstance(SHA1);
		messageDigest.update(input);
		return messageDigest.digest();

	}
	
	/**
	 * 使用SHA256进行消息签名, 返回字节数组,长度为20字节.
	 * 
	 * @param input 原始输入字符数组
	 * @param key   SHA256密钥
	 * @return SHA256加密后的数组
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] sha256(byte[] input) throws NoSuchAlgorithmException {
		MessageDigest messageDigest;
		messageDigest = MessageDigest.getInstance(SHA256);
		messageDigest.update(input);
		return messageDigest.digest();

	}

	/**
	 * 使用 HMAC-SHA1 签名方法对data进行签名
	 * 
	 * @param data 被签名的字符串
	 * @param key  密钥
	 * @return 签名后的字符串
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String generateHmacSha1(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException {
		return Encodes.encodeHex(hmacSha1(data.getBytes(), key.getBytes()));
	}

	/**
	 * 使用 HMAC-SHA1 签名方法对data进行签名(签名后的字符串是Hmacsha1后做Base64加密后所得)
	 * 
	 * @param data 被签名的字符串
	 * @param key  密钥
	 * @return 签名后的字符串(签名后的字符串是Hmacsha1后做Base64加密后所得)
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String generateBase64HmacSha1(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException {
		return new String(Base64.encodeBase64(hmacSha1(data.getBytes(), key.getBytes())));
	}

	/**
	 * 使用 HMAC-SHA256 签名方法对data进行签名
	 * 
	 * @param data 待加密字符串
	 * @param key  加密key
	 * @return 对字符串HamcSHA256后的字符串
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String generateHmacSha256(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException {
		return Encodes.encodeHex(hmacSha256(data.getBytes(), key.getBytes()));
	}

	/**
	 * 使用 HMAC-SHA256 签名方法对data进行签名(签名后的字符串是HmacSHA256后做Base64加密后所得)
	 * 
	 * @param data 待加密字符串
	 * @param key  加密key
	 * @return 对字符串HamcSHA256后再Base64的字符串
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String generateBase64HmacSha256(String data, String key) throws InvalidKeyException, NoSuchAlgorithmException {
		return new String(Base64.encodeBase64(hmacSha256(data.getBytes(), key.getBytes())));
	}

	/**
	 * 使用 SHA1 签名方法对data进行签名
	 * 
	 * @param data 待加密字符串
	 * @param key  加密key
	 * @return 对字符串SHA1后的字符串
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String generateSha1(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return Encodes.encodeHex(sha1(data.getBytes(ENCODING)));

	}
	
	/**
	 * 使用 SHA256 签名方法对data进行签名
	 * 
	 * @param data 待加密字符串
	 * @return 对字符串SHA256后的字符串
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 */
	public static String generateSha256(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return Encodes.encodeHex(sha256(data.getBytes(ENCODING)));

	}

	/**
	 * 校验HMAC-SHA1签名是否正确.
	 * 
	 * @param expected 已存在的签名
	 * @param input    原始输入字符串
	 * @param key      密钥
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static boolean isMacValid(byte[] expected, byte[] input, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException {
		byte[] actual = hmacSha1(input, key);
		return Arrays.equals(expected, actual);
	}

	/**
	 * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节). HMAC-SHA1算法对密钥无特殊要求,
	 * RFC2401建议最少长度为160位(20字节).
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] generateHmacSha1Key() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA1);
		keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();

	}
	
	/**
	 * 生成HMAC-SHA1密钥,返回字节数组,长度为160位(20字节). HMAC-SHA1算法对密钥无特殊要求,
	 * 
	 * @return 返回HMAC-SHA1密钥
	 * @throws NoSuchAlgorithmException 
	 */
	public static String generateHmacSha1KeyStr() throws NoSuchAlgorithmException {
		return Encodes.encodeHex(generateHmacSha1Key());
	}

	/**
	 * 生成HMAC-SHA256密钥,返回字节数组,长度为160位(20字节). HMAC-SHA1算法对密钥无特殊要求,
	 * 
	 * @return 返回HMAC-SHA256密钥
	 * @throws NoSuchAlgorithmException 
	 */
	public static String generateHmacSha256KeyStr() throws NoSuchAlgorithmException {
		return Encodes.encodeHex(generateHmacSha1Key());
	}
	
	/**
	 * 生成HMAC-SHA256密钥,返回字节数组,长度为160位(20字节). HMAC-SHA1算法对密钥无特殊要求,
	 * RFC2401建议最少长度为160位(20字节).
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] generateHmacSha256Key() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(HMACSHA256);
		keyGenerator.init(DEFAULT_HMACSHA1_KEYSIZE);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();

	}



	// -- AES funciton --//
	/**
	 * 使用AES加密原始字符串.
	 * 
	 * @param input 原始输入字符数组
	 * @param key   符合AES要求的密钥
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static byte[] aesEncrypt(byte[] input, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		return aes(input, key, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 使用AES加密原始字符串.
	 * 
	 * @param input 原始输入字符数组
	 * @param key   符合AES要求的密钥
	 * @param iv    初始向量
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static byte[] aesEncrypt(byte[] input, byte[] key, byte[] iv) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		return aes(input, key, iv, Cipher.ENCRYPT_MODE);
	}

	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input Hex编码的加密字符串
	 * @param key   符合AES要求的密钥
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String aesDecrypt(byte[] input, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		byte[] decryptResult = aes(input, key, Cipher.DECRYPT_MODE);
		return new String(decryptResult);
	}

	/**
	 * 使用AES解密字符串, 返回原始字符串.
	 * 
	 * @param input Hex编码的加密字符串
	 * @param key   符合AES要求的密钥
	 * @param iv    初始向量
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String aesDecrypt(byte[] input, byte[] key, byte[] iv) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte[] decryptResult = aes(input, key, iv, Cipher.DECRYPT_MODE);
		return new String(decryptResult);
	}

	/**
	 * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
	 * 
	 * @param input 原始字节数组
	 * @param key   符合AES要求的密钥
	 * @param mode  Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] aes(byte[] input, byte[] key, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		SecretKey secretKey = new SecretKeySpec(key, AES);
		Cipher cipher = Cipher.getInstance(AES);
		cipher.init(mode, secretKey);
		return cipher.doFinal(input);

	}

	/**
	 * 使用AES加密或解密无编码的原始字节数组, 返回无编码的字节数组结果.
	 * 
	 * @param input 原始字节数组
	 * @param key   符合AES要求的密钥
	 * @param iv    初始向量
	 * @param mode  Cipher.ENCRYPT_MODE 或 Cipher.DECRYPT_MODE
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	private static byte[] aes(byte[] input, byte[] key, byte[] iv, int mode) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		SecretKey secretKey = new SecretKeySpec(key, AES);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		Cipher cipher = Cipher.getInstance(AES_CBC);
		cipher.init(mode, secretKey, ivSpec);
		return cipher.doFinal(input);

	}

	/**
	 * 生成AES密钥,返回字节数组, 默认长度为128位(16字节).
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] generateAesKey() throws NoSuchAlgorithmException {
		return generateAesKey(DEFAULT_AES_KEYSIZE);
	}

	/**
	 * 生成AES密钥,可选长度为128,192,256位.
	 * @throws NoSuchAlgorithmException 
	 */
	public static byte[] generateAesKey(int keysize) throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
		keyGenerator.init(keysize);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey.getEncoded();
	}

	/**
	 * 生成随机向量,默认大小为cipher.getBlockSize(), 16字节.
	 */
	public static byte[] generateIV() {
		byte[] bytes = new byte[DEFAULT_IVSIZE];
		random.nextBytes(bytes);
		return bytes;
	}
	
	/**
	 ** AES ECB模式加密
	 * @param content 待加密内容
	 * @param key 加密秘钥
	 * @return 加密字符串 16进制
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 */
	public static String aesEcbEncrypt(String content, String key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] crypted = null;
		
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), AES);
		Cipher cipher = Cipher.getInstance(AES_ECB);
		cipher.init(Cipher.ENCRYPT_MODE, skey);
		crypted = cipher.doFinal(content.getBytes());
		return Encodes.encodeHex(crypted).toUpperCase();

	}
	
	/**
	 * AES ECB模式界面
	 * @param input 待解密的内容
	 * @param key 解密秘钥
	 * @return
	 * @throws DecoderException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 */
	public static String aesEcbDdecrypt(String input, String key) throws IllegalBlockSizeException, BadPaddingException, DecoderException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		byte[] output = null;
		SecretKeySpec skey = new SecretKeySpec(key.getBytes(), AES);
		Cipher cipher = Cipher.getInstance(AES_ECB);
		cipher.init(Cipher.DECRYPT_MODE, skey);
		output = cipher.doFinal(Hex.decodeHex(input.toCharArray()));
		return new String(output);
	}
	
	/**
	 * 加盐散列
	 * @param content
	 * @param salt
	 * @return
	 * @throws NoSuchAlgorithmException
	 */
	 public static String sha256WithSalt(String content, String salt) throws NoSuchAlgorithmException {
		    MessageDigest digest = MessageDigest.getInstance(SHA256);
		    String forHashContent = content + salt;
		    digest.update(forHashContent.getBytes());
		    return Encodes.encodeHex(digest.digest());
		  }
}
