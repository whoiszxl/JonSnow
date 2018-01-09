package com.whoiszxl.seckill.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.whoiszxl.seckill.entities.User;

/**
 * Mybatis通过注解方式
 * @author zxlvoid
 *
 */
@Mapper
public interface UserDao {

	/**
	 * 通过username查询到整条记录
	 * @param username
	 * @return User实体
	 */
	@Select("select username,password from xl_user where username = #{username}")
	public User getByUsername(@Param("username") String username);
	
	
	@Insert("insert into xl_user(username,password) values(#{username},#{password})")
	public int insert(User user); 
}
