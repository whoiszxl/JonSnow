package com.whoiszxl.seckill.result;

/**
 * 传入Result中构建返回信息的类
 * @author zxlvoid
 *
 */
public class CodeMessage {
	
	private int code;
	private String msg;
	
	//通用异常
	public static CodeMessage SUCCESS = new CodeMessage(0, "request success");
	public static CodeMessage SERVER_ERROR = new CodeMessage(10000, "The Server Run Error.");
	public static CodeMessage BIND_ERROR = new CodeMessage(10001, "参数校验异常：%s");
	
	//登录模块 5002XX
	public static CodeMessage SESSION_ERROR = new CodeMessage(500210, "Session不存在或者已经失效");
	public static CodeMessage PASSWORD_EMPTY = new CodeMessage(500211, "登录密码不能为空");
	public static CodeMessage MOBILE_EMPTY = new CodeMessage(500212, "手机号不能为空");
	public static CodeMessage MOBILE_ERROR = new CodeMessage(500213, "手机号格式错误");
	public static CodeMessage MOBILE_NOT_EXIST = new CodeMessage(500214, "手机号不存在");
	public static CodeMessage PASSWORD_ERROR = new CodeMessage(500215, "密码错误");
	
	//注册模块 5003XX
	public static CodeMessage MOBILE_EXIST = new CodeMessage(500310, "手机号已被注册");
	
	
	public CodeMessage(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMsg() {
		return msg;
	}
	
	public CodeMessage fillArgs(Object... args) {
		int code = this.code;
		String message = String.format(this.msg, args);
		return new CodeMessage(code, message);
	}
}
