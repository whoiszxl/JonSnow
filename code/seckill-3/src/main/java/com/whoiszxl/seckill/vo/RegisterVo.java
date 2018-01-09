package com.whoiszxl.seckill.vo;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.whoiszxl.seckill.validators.IsMobile;

/**
 * 登录参数接收vo类
 * @author whoiszxl
 *
 */
public class RegisterVo {

	@NotNull
	@IsMobile
	private String mobile;
	
	@NotNull
	private String password;
	
	@NotNull
	@Length(min=1,max=20)
	private String nickname;
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "RegisterVo [mobile=" + mobile + ", password=" + password + ", nickname=" + nickname + "]";
	}
	
	
}
