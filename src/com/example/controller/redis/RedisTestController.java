//package com.example.controller.redis;
//
//import java.util.concurrent.TimeUnit;
//
//import javax.annotation.PostConstruct;
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.commons.lang.RandomStringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@Controller
//public class RedisTestController {
//	@Autowired
//	private StringRedisTemplate stringRedisTemplate;
//
//	@PostConstruct
//	public void get() {
//
//		new Thread() {
//			@Override
//			public void run() {
//				while (true) {
//					String val = stringRedisTemplate.boundListOps("orderId").rightPop(30, TimeUnit.SECONDS);
//					if (val != null) {
//						System.out.println(val);
//					}
//				}
//			}
//		}.start();
//
//	}
//
//	@ResponseBody
//	@RequestMapping(value = "/setRedisVal")
//	public String set(HttpServletRequest request, Model model, String orderId) {
//		stringRedisTemplate.opsForList().leftPush("orderId", RandomStringUtils.randomAlphanumeric(12));
//
//		return "sucess";
//	}
//
//}
