package com.example.controller.imgcheck;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.druid.util.StringUtils;

@Controller
public class ImgCheckController {
	private static final Log logger = LogFactory.getLog(ImgCheckController.class);

	/**
	 * 这个方法一般放到MainController中,做为public方法使用
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getImgCheckCode")
	public String getImgCheckCode(HttpServletRequest request, Model model) {
		return "/image";
	}

	@RequestMapping(value = "/goImgCheckPage")
	public String goImgCheckPage(HttpServletRequest request, Model model) {
		return "/imgcheck/index";
	}

	@RequestMapping(value = "/doImgCheckAction")
	public String doImgCheckAction(HttpServletRequest request, Model model, String val1, String checkCode) {

		model.addAttribute("val1", val1);
		model.addAttribute("checkCode", checkCode);
		
		String sRand = (String) request.getSession().getAttribute("rand");
		if(StringUtils.equals(sRand, checkCode)){
			model.addAttribute("msg", "验证码正确");
		} else {
			model.addAttribute("msg", "验证码错误");
		}
		
		// 这里注意, 每次post提交之后, 都应该删除session中的验证码
		request.getSession().removeAttribute("rand");
		
		return "/imgcheck/index";
	}
}
