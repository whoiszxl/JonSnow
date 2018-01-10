package com.whoiszxl.seckill.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whoiszxl.seckill.result.Result;
import com.whoiszxl.seckill.service.ISeckillService;
import com.whoiszxl.seckill.vo.LoginVo;

/**
 * 登录控制器
 * @author whoiszxl
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ISeckillService seckillService;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/to_login")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
    	log.info(loginVo.toString());
    	//登录
    	seckillService.login(response, loginVo);
    	return Result.success(true);
    }
}
