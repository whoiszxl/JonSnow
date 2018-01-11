package com.whoiszxl.seckill.redis;

/**
 * 提供redis key前缀的接口
 * @author whoiszxl
 *
 */
public interface KeyPrefix {

	/**
	 * 失效时间 
	 * @return
	 */
	public int expireSeconds();
	
	/**
	 * 获取前缀
	 * @return
	 */
	public String getPrefix();
}
