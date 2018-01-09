package com.whoiszxl.seckill.redis;

public abstract class BasePrefix implements KeyPrefix {
	
	private int expireSeconds;
	private String prefix;
	
	/**
	 * 构造创建对象，0为永不过期
	 * @param prefix
	 */
	public BasePrefix(String prefix) {
		this(0,prefix);
	}
	
	/**
	 * 传入有效时间和前缀构造基础前缀类
	 * @param expireSeconds
	 * @param prefix
	 */
	public BasePrefix( int expireSeconds, String prefix) {
		this.expireSeconds = expireSeconds;
		this.prefix = prefix;
	}
	
	@Override
	public int expireSeconds() {
		return expireSeconds;
	}

	/**
	 * 返回当前类名+冒号+前缀的一个rediskey前缀
	 */
	@Override
	public String getPrefix() {
		String className = getClass().getSimpleName();
		return className + ":" + prefix;
	}

	
}
