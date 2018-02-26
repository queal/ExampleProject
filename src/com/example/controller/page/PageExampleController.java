package com.example.controller.page;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controller.page.pojo.PersonQueryCondition;
import com.example.service.person.PersonService;

@Controller
public class PageExampleController {
	private static final Log logger = LogFactory.getLog(PageExampleController.class);

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/queryPersonList")
	public String queryPersonList(HttpServletRequest request, Model model, PersonQueryCondition condition) {
		condition.setAge(3);
		condition.setPageSize(1);
		personService.queryPersonList(condition);
		
		return "/upload/index";
	}

}
