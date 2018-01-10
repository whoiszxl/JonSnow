package com.whoiszxl.seckill.utils;

import java.util.UUID;

/**
 * 唯一值生成
 * @author whoiszxl
 *
 */
public class UUIDUtil {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "");
	}
}
