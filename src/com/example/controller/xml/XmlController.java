package com.example.controller.xml;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.controller.xml.pojo.XmlExampleData;
import com.thoughtworks.xstream.XStream;

@Controller
public class XmlController {
	private static final Log logger = LogFactory.getLog(XmlController.class);

	@ResponseBody
	@RequestMapping(value = "/getXml")
	public String getJson(HttpServletRequest request, Model model) {
		XmlExampleData exampleData = new XmlExampleData();
		exampleData.setFlag("1");
		exampleData.setMsg("success");

		XStream xStream = new XStream();
		xStream.ignoreUnknownElements();
		xStream.alias("xml", XmlExampleData.class);
		
		String xmlData = xStream.toXML(exampleData);
		XmlExampleData exampleData1 = (XmlExampleData) xStream.fromXML(xmlData);
		System.out.println(exampleData1);

		return xmlData;
	}

}
