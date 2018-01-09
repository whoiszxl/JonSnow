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
import com.whoiszxl.seckill.vo.RegisterVo;

/**
 * 登录控制器
 * @author whoiszxl
 *
 */
@Controller
@RequestMapping("/register")
public class RegisterController {

	private static Logger log = LoggerFactory.getLogger(RegisterController.class);
	
	@Autowired
	private ISeckillService seckillService;
	
	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping("/to_register")
	public String toLogin() {
		return "register";
	}
	
	@RequestMapping("/do_register")
    @ResponseBody
    public Result<Boolean> doLogin(HttpServletResponse response, @Valid RegisterVo registerVo) {
    	log.info(registerVo.toString());
    	//登录
    	seckillService.register(response, registerVo);
    	return Result.success(true);
    }
}
