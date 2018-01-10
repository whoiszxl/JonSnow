package com.whoiszxl.seckill.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whoiszxl.seckill.entities.SeckillUser;

/**
 * 秒杀mybatis操作类
 * @author whoiszxl
 *
 */
@Mapper
public interface SeckillUserDao {
	
	@Select("select * from seckill_user where id = #{id}")
	public SeckillUser getById(@Param("id") long id);
	
	@Insert("insert into seckill_user(id,password,salt,nickname) values(#{id},#{password},#{salt},#{nickname})")
	public int insertUser(@Param("id") long id,@Param("password") String password,@Param("salt") String salt,@Param("nickname") String nickname);
}
