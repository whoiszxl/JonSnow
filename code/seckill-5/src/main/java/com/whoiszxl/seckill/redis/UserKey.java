package com.whoiszxl.seckill.redis;

/**
 * 用户模块的key前缀
 * @author whoiszxl
 *
 */
public class UserKey extends BasePrefix {

	public UserKey(String prefix) {
		super(prefix);
	}
	
	public static UserKey ID = new UserKey("id");
	
	public static UserKey USER_INFO = new UserKey("user_info");

}
