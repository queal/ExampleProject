package com.example.controller.page.pojo;

import java.util.concurrent.Executors;

import org.apache.catalina.Executor;

import com.example.pojo.QueryCondition;

public class PersonQueryCondition extends QueryCondition {

	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public static void main(String[] args) {
		new Thread(){
			public void run() {
				// TODO:
			};
		}.start();
		
	}
}
