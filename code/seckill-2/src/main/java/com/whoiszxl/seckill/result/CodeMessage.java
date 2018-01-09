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
}
