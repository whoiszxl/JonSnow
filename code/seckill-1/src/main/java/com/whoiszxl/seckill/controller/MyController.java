package com.whoiszxl.seckill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.whoiszxl.seckill.result.CodeMessage;
import com.whoiszxl.seckill.result.Result;

/**
 * 测试thymeleaf和result类的控制器
 * @author zxlvoid
 *
 */
@Controller
@RequestMapping(value = "/my")
public class MyController {

	@RequestMapping("/result")
	@ResponseBody
	public Result<String> result(){
		return Result.success("请求成功");
	}
	
	@RequestMapping("/result2")
	@ResponseBody
	public Result<String> result2(){
		return Result.build(CodeMessage.SERVER_ERROR);
	}
	
	@RequestMapping("/thymeleaf")
    public String  thymeleaf(Model model) {
 		model.addAttribute("name", "wwwwj");
 		return "index";
    }
	
}
