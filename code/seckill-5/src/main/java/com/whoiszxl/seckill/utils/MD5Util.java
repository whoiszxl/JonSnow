package com.whoiszxl.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 密码加密工具
 * @author whoiszxl
 *
 */
public class MD5Util {
	
	/**
	 * 将字符串格式化
	 * @param src
	 * @return
	 */
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}
	
	/**
	 * 输入转form时候需要用的salt盐
	 */
	private static final String salt = "wang23j445214";
	
	/**
	 * 输入密码转form的一次加密密码
	 * @param inputPass
	 * @return
	 */
	public static String inputPassToFormPass(String inputPass) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + inputPass +salt.charAt(5) + salt.charAt(4);
		return md5(str);
	}
	
	/**
	 * 将form数据转换到存到数据库的数据
	 * @param formPass form来的password
	 * @param salt 随机盐
	 * @return 最终加密密码
	 */
	public static String formPassToDBPass(String formPass, String salt) {
		String str = ""+salt.charAt(0)+salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
		return md5(str);
	}
	
	/**
	 * 将输入的密码直接转换成存入数据库的密码
	 * @param inputPass
	 * @param saltDB
	 * @return
	 */
	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	
}
