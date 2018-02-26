package com.example.ws.impl;

import javax.jws.WebService;

import com.example.ws.HelloWsService;

@WebService(endpointInterface = "com.example.ws.HelloWsService")
public class HelloWsServiceImpl implements HelloWsService {
	
	public String sayHi(String text, String t1) {
		System.out.println("sayHi called");
		return "Hello " + text;
	}
}
