package com.bfmzdx.o2o.util.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * 微信请求校验工具类
 * @author YangMing
 * @date 2017年11月6日
 */
public class SignUtil {

	//与接口配置信息中的Token要保持一致
	private static String token = "o2o";
	/**
	 * 
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature, String timestamp, String nonce) {
		String[] arr = new String[]{token, timestamp, nonce};
		//将token、timestamp、nonce进行字典排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;
		
		try {
			md = MessageDigest.getInstance("SHA-1");
			//将三个参数字符串拼接成一个字符串进行sha-1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		content = null;
		//将sha1加密后的字符串可与signature对比，标识该请求来源于微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;
	}
	/**
	 * 将字节数组转换成十六进制字符串
	 * @param digest
	 * @return
	 */
	private static String byteToStr(byte[] digest) {
		String strDigest = "";
		for (int i = 0; i < digest.length; i++) {
			strDigest += byteToHexStr(digest[i]);
		}
		return strDigest;
	}
	/**
	 * 将字节转换成十六进制字符串
	 * @param b
	 * @return
	 */
	private static String byteToHexStr(byte mByte) {
		char[] digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		char[] tempArr = new char[2];
		tempArr[0] = digit[(mByte >>> 4) & 0X0F];
		tempArr[1] = digit[mByte & 0X0F];
		
		String s = new String(tempArr);
		return s;
	}
}
