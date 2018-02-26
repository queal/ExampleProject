package com.example.controller.i18n;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

@Controller
public class I18nController {
	private static final Log logger = LogFactory.getLog(I18nController.class);
	@Autowired
	private CookieLocaleResolver resolver;
	@Autowired
	private ResourceBundleMessageSource resourceBundleMessageSource;
	

	@RequestMapping(value = "/goI18nPage")
	public String updateMoney(HttpServletRequest request, Model model) {

		// java 获取i18n语言的value
		// Locale locale =
		// RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		// System.out.println(locale.getLanguage());

		Locale locale = RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
		System.out.println(locale.getLanguage());
		
		String val = resourceBundleMessageSource.getMessage("welcome", null, locale);
		System.out.println(val);

		return "/i18n/index";
	}

	@RequestMapping(value = "/changeLanguage")
	public String changeLanguage(HttpServletRequest request, HttpServletResponse response, Model model,
			String language) {

		// 手动切换语言, 如不加, 按浏览器自动判断
		if (StringUtils.equals(language, "en")) {
			resolver.setLocale(request, response, Locale.ENGLISH);
		} else if (StringUtils.equals(language, "zh_CN")) {
			resolver.setLocale(request, response, Locale.CHINA);
		}

		return "/i18n/index";
	}

}
