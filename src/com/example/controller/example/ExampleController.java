package com.example.controller.example;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.alibaba.fastjson.JSON;
import com.example.ibatis.model.Person;
import com.example.service.person.PersonService;
import com.example.websocket.handler.EchoTestWebSocket;

@Controller
public class ExampleController {
	private static final Log logger = LogFactory.getLog(ExampleController.class);
	private static final Log loggerExm = LogFactory.getLog("loggerExm");

	@Autowired
	private PersonService personService;

	private static Executor executor = Executors.newFixedThreadPool(20);

	@ResponseBody
	@RequestMapping(value = "/updateMoney")
	public String updateMoney(HttpServletRequest request, Model model) {

		for (int i = 0; i < 1000; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					logger.info(personService.updateMoney(1, "20"));
				}
			});
		}

		return "sucess";
	}

	@ResponseBody
	@RequestMapping(value = "/loggerExm")
	public String loggerExm(HttpServletRequest request, Model model) {
		loggerExm.info("loggerExm content");

		return "sucess";
	}

	@ResponseBody
	@RequestMapping(value = "/insertExist")
	public String insertExist(HttpServletRequest request, Model model, String name) {
		try {
			Person record = new Person();
			record.setName(name);
			record.setAge(10);
			record.setMoney(new BigDecimal(10));
			record.setRegTime(new Date());
			personService.insertExist(record);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sucess";
	}

	@ResponseBody
	@RequestMapping(value = "/t26")
	public String t26(HttpServletRequest request, Model model, String name) {
		try {
			Person person = personService.selectByPrimaryKey(26);
			Date d = person.getRegTime();
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			System.out.println(c.get(Calendar.YEAR));
			System.out.println(c.get(Calendar.MONTH));
			System.out.println(c.get(Calendar.DATE));
			System.out.println(c.get(Calendar.HOUR));
			System.out.println(c.get(Calendar.MINUTE));
			System.out.println(c.get(Calendar.SECOND));
			System.out.println(c.get(Calendar.MILLISECOND));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "sucess";
	}

	@ResponseBody
	@RequestMapping(value = "/ttb")
	public String ttb(HttpServletRequest request, Model model) {
		List<String> data = new ArrayList<String>();

		for (int i = 0; i < 50; i++) {
			data.add(RandomStringUtils.randomAlphanumeric(6));
		}

		String ret = JSON.toJSONString(data);
		System.out.println(ret);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return ret;
	}

	@RequestMapping(value = "/websocket")
	public String websocket(HttpServletRequest request, Model model) {
		return "websocket/index";
	}

	@ResponseBody
	@RequestMapping(value = "/websockettest")
	public String websockettest(HttpServletRequest request, Model model) {
		try {
			for (WebSocketSession webSocketSession : EchoTestWebSocket.webSession.values()) {
				if (webSocketSession.isOpen()) {
					webSocketSession
							.sendMessage(new TextMessage("��ð�, ��֤����Ϣ�������_" + RandomStringUtils.randomAlphanumeric(16)));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "sucess";
	}
}
