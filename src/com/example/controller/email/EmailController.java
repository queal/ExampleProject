package com.example.controller.email;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.upload.UploadExampleController;

@Controller
public class EmailController {
	private static final Log logger = LogFactory.getLog(UploadExampleController.class);
 
	@Autowired
	private JavaMailSender javaMailSender;

	@ResponseBody
	@RequestMapping(value = "/sendEmail")
	public String sendEmail(HttpServletRequest request, Model model) {

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setFrom("shoto_lee@126.com");
		mail.setTo("lih@newmiracle.net");
		
		String text = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "【测试邮件】";
		mail.setSubject(text);

		String body = "测试<a href='http://www.baidu.com' target='_blank' style='color:#df3434; font-weight:bold;'>百度</a>";
		mail.setText(body);
		javaMailSender.send(mail);

		return "sucess";
	}

}
