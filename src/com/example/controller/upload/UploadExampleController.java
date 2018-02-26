package com.example.controller.upload;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.utils.QiNiuUtils;

@Controller
public class UploadExampleController {
	private static final Log logger = LogFactory.getLog(UploadExampleController.class);

	@RequestMapping(value = "/goUploadPage")
	public String goUploadPage(HttpServletRequest request, Model model) {
		return "/upload/index";
	}

	@RequestMapping(value = "/doUploadFile")
	public String doUploadFile(HttpServletRequest request, Model model, MultipartFile file, String key1) {
		// spring 入参的值 要和form的name匹配
		// 否则需要用 @RequestParam(form-name) 来转译, 但一般原则上不转译, 为了更快
		try {
			// 上传七牛文件服务器
			String fileName = RandomStringUtils.randomAlphanumeric(16);
			if (QiNiuUtils.upload(fileName, file.getBytes(), QiNiuUtils.BUCKET_NAME_MER_INFO)) {
				model.addAttribute("msg", "上传成功");

				model.addAttribute("fileName", fileName);
			} else {
				model.addAttribute("msg", "上传失败");
			}
			// TODO: fileName 存库, 存取对应的关系

		} catch (Exception e) {
			logger.error("上传文件出错!", e);
		}
		return "/upload/index";
	}

	@ResponseBody
	@RequestMapping(value = "/showUploadFile")
	public String showUploadFile(HttpServletRequest request, Model model, String fileName) {
		return QiNiuUtils.getPrivateDownloadUrl(fileName, QiNiuUtils.DOMAIN_URL_MER_INFO);
	}

}
