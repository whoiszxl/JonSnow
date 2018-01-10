package com.whoiszxl.seckill.result;

/**
 * 作为json返回给前端的类
 * @author zxlvoid
 *
 * @param <T>
 */
public class Result<T> {

	private int code;
	private String msg;
	private T data;
	
	/**
	 * 成功时候调用的方法
	 * @param data
	 * @return
	 */
	public static <T> Result<T> success(T data){
		return new Result<T>(data);
	}
	
	/**
	 * 私有构造方法,返回一个成功的对象
	 * @param data
	 */
	private Result(T data) {
		this.code = 0;
		this.msg = "success";
		this.data = data;
	}
	
	/**
	 * 通过CodeMessage动态返回状态
	 * @param data
	 * @return
	 */
	public static <T> Result<T> build(CodeMessage data){
		return new Result<T>(data);
	}
	
	/**
	 * 通过codeMessage动态构建返回状态
	 * @param message
	 */
	private Result(CodeMessage message) {
		if(message == null) {
			return;
		}
		this.code = message.getCode();
		this.msg = message.getMsg();
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	
}
