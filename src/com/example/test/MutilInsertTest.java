package com.example.test;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.apache.commons.httpclient.HttpClient;

import com.example.utils.HttpClientUtils;

public class MutilInsertTest {

	public static void main(String[] args) {

		final HttpClient httpClient = HttpClientUtils.getHttpClient();
		Executor executor = Executors.newFixedThreadPool(20);

		while (true) {
			executor.execute(new Runnable() {

				@Override
				public void run() {
					HttpClientUtils.doGet(httpClient, "http://localhost:8080/ExampleProject/insertExist?name=Queal");

				}
			});
		}
	}

}
