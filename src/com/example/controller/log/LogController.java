package com.example.controller.log;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.upload.UploadExampleController;

@Controller
public class LogController {
	private static final Log logger = LogFactory.getLog(UploadExampleController.class);

	@ResponseBody
	@RequestMapping(value = "/logtest")
	public String updateMoney(HttpServletRequest request, Model model) {
		if(logger.isInfoEnabled()){
			logger.info("这是一个log实例");
		}

		// 这是一个错误的log实例
		try {
			Integer.parseInt("a");
		} catch (Exception e) {
			// 这里没有记录log
			e.printStackTrace();
		}

		// 这是一个错误的log实例
		try {
			Integer.parseInt("a");
		} catch (Exception e) {
			// e.toString, 异常被吞
			logger.error("转换出错!" + e);
		}

		// 这是一个正确的log实例
		try {
			Integer.parseInt("a");
		} catch (Exception e) {
			logger.error("转换出错!", e);
		}

		return "sucess";
	}

}
