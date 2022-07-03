package com.beidouiot.alllink.community.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;

/**
*
* @Description RSA生成秘钥对及加解密
* @author longww
* @date 2021年4月18日
*/

public class KeyPairGenUtil {
	
	private static final String ALGORITHM = "RSA";

	private static final int KEYSIZE = 1024;
	
	private static final String UTF8 = "UTF-8";
	
	private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
	
	/**
	 * 
	 * @return 
	 * @throws NoSuchAlgorithmException
	 */
	public static Map<String, String> generateKeyPair() throws NoSuchAlgorithmException {
		SecureRandom secureRandom = new SecureRandom();
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM); 
		keyPairGenerator.initialize(KEYSIZE,secureRandom);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		Key publicKey = keyPair.getPublic(); 
		Key privateKey = keyPair.getPrivate();  
		
		byte[] publicKeyBytes = publicKey.getEncoded();  
        byte[] privateKeyBytes = privateKey.getEncoded(); 
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put("PublicKey", Base64.encodeBase64String(publicKeyBytes));
        map.put("PrivateKey", Base64.encodeBase64String(privateKeyBytes));
        return map;
	}
	
	/**
	 * @Description 公钥加密
	 * @param plainText  明文
	 * @param publicKey  公钥
	 * @return 公钥加密后的Base64编码
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException 
	 */
	public static String encryptWithPublicKey(String plainText, String publicKey) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		PublicKey pubKey = KeyFactory.getInstance(ALGORITHM).generatePublic(encPubKeySpec);
		Cipher cipher = Cipher.getInstance(ALGORITHM); 
		cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
		byte[] plainTextEnByte = cipher.doFinal(plainText.getBytes(UTF8)); 
		return Base64.encodeBase64String(plainTextEnByte);
	}
	
	/**
	 * @Description 私钥加密
	 * @param plainText 明文
	 * @param privateKey 私钥
	 * @return 私钥加密后的Base64编码
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptWithPrivateKey(String plainText, String privateKey) throws InvalidKeySpecException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		PKCS8EncodedKeySpec encPriKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		PrivateKey prvKey = KeyFactory.getInstance(ALGORITHM).generatePrivate(encPriKeySpec);
		Cipher cipher = Cipher.getInstance(ALGORITHM); 
		cipher.init(Cipher.ENCRYPT_MODE, prvKey);  
		byte[] plainTextEnByte = cipher.doFinal(plainText.getBytes(UTF8));
		return Base64.encodeBase64String(plainTextEnByte);
	}
	
	/**
	 * @Description 公钥解密
	 * @param cipherText 密文
	 * @param publicKey 公钥
	 * @return 返回明文
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptWithPublicKey(String cipherText, String publicKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] encryptDataBytes = Base64.decodeBase64(cipherText.getBytes(UTF8));
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		PublicKey pubKey = KeyFactory.getInstance(ALGORITHM).generatePublic(encPubKeySpec);
		cipher.init(Cipher.DECRYPT_MODE, pubKey);
		return new String(cipher.doFinal(encryptDataBytes), UTF8);
	}
	
	/**
	 * @Description 私钥解密
	 * @param cipherText 密文
	 * @param privateKey 公钥
	 * @return 返回明文
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptWithPrivateKey(String cipherText, String privateKey) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		byte[] encryptDataBytes = Base64.decodeBase64(cipherText.getBytes(UTF8));
		PKCS8EncodedKeySpec encPriKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey prvKey = keyFactory .generatePrivate(encPriKeySpec);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, prvKey);  
		return new String(cipher.doFinal(encryptDataBytes), UTF8);
	}
	
	/**
	 * @Description SHA256withRSA签名
	 * @param source 签名内容
	 * @param privateKey 私钥
	 * @return 签名后的Base64字符串
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws InvalidKeySpecException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public static String signatureWithRSA(String source, String privateKey) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException, SignatureException, UnsupportedEncodingException {
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		PKCS8EncodedKeySpec encPriKeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey prvKey = keyFactory.generatePrivate(encPriKeySpec);
		signature.initSign(prvKey);
		signature.update(source.getBytes(UTF8));
		byte[] result = signature.sign();
		return Base64.encodeBase64String(result);
	}
	
	/**
	 * @Description SHA256withRSA验签
	 * @param source 待验签内容
	 * @param signStr Base64后的签名字符串
	 * @param publicKey 公钥
	 * @return 验证成功返回true，否则false
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 * @throws UnsupportedEncodingException
	 */
	public static boolean verifySignatureWithRSA(String source, String signStr, String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		X509EncodedKeySpec encPubKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		PublicKey pubKey = KeyFactory.getInstance(ALGORITHM).generatePublic(encPubKeySpec);
		signature.initVerify(pubKey);
		signature.update(source.getBytes(UTF8));
		boolean verify = signature.verify(Base64.decodeBase64(signStr));
		return verify;
	}
	
	
}
