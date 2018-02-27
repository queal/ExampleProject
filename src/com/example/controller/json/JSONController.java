package com.example.controller.json;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.example.controller.json.pojo.JSONExampleData;

@Controller
public class JSONController {
	private static final Log logger = LogFactory.getLog(JSONController.class);

	@ResponseBody
	@RequestMapping(value = "/getJson")
	public String getJson(HttpServletRequest request, Model model) {
		JSONExampleData exampleData = new JSONExampleData();
		exampleData.setFlag("1");
		exampleData.setMsg("success");

		String jsonData = JSON.toJSONString(exampleData);
		JSONExampleData exampleData1 = JSON.parseObject(jsonData,
				JSONExampleData.class);
		System.out.println(request.getSession().getId() + "," + exampleData1);

		return jsonData;
	}

	/*
	 * 
	 * {retCode: -1[0], retData: {pojo}}
	 * 
	 * {pojoJson}
	 */
}
