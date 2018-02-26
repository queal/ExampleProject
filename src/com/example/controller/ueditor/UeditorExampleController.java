package com.example.controller.ueditor;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.example.controller.ueditor.pojo.Config;
import com.example.controller.ueditor.pojo.UploadImageResponse;
import com.example.utils.QiNiuUtils;

@Controller
public class UeditorExampleController {
	private static final Log logger = LogFactory.getLog(UeditorExampleController.class);

	@RequestMapping(value = "/goUeditorPage")
	public String goUeditorPage(HttpServletRequest request, Model model) {
		return "/ueditor/index";
	}

	@ResponseBody
	@RequestMapping(value = "/ueditorAction/service")
	public String config(HttpServletRequest request, Model model, String action) throws Exception {

		if (StringUtils.equals(action, "config")) {
			Config config = new Config();
			config.setImageUrl("/ueditorAction/service");
			config.setImagePath(QiNiuUtils.DOMAIN_URL_UEDITOR);

			return JSON.toJSONString(config);
		} else if (StringUtils.equals(action, "uploadimage")) {
			MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
			MultipartFile mulFile = mulRequest.getFile("upfile");

			byte[] imgData = mulFile.getBytes();

			String fileName = RandomStringUtils.randomAlphanumeric(16);
			if (QiNiuUtils.upload(fileName, imgData, QiNiuUtils.BUCKET_NAME_UEDITOR)) {
				UploadImageResponse response = new UploadImageResponse();
				response.setTitle(fileName);
				response.setOriginal(fileName);
				response.setUrl(QiNiuUtils.DOMAIN_URL_UEDITOR + fileName);
				return JSON.toJSONString(response);
			}
		}

		System.out.println(action);

		return null;
	}

}
