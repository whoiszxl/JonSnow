package com.whoiszxl.seckill.execption;

import com.whoiszxl.seckill.result.CodeMessage;

/**
 * 全局异常处理
 * @author whoiszxl
 *
 */
public class GlobalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CodeMessage cm;

	public GlobalException(CodeMessage cm) {
		super(cm.toString());
		this.cm = cm;
	}
	
	public CodeMessage getCm() {
		return cm;
	}

	

}
