package com.example.task;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.stereotype.Component;

@Component
public class ExampleTask {

//	@Scheduled(cron="0/5 * *  * * ? ")
	public void sayHello() {
		System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}
