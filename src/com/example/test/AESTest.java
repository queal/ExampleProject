package com.example.test;

import org.apache.commons.lang.RandomStringUtils;

import com.example.utils.AESUtils;

public class AESTest {

	public static void main(String[] args) throws Exception {
		String key = RandomStringUtils.randomAlphanumeric(32);
		String content = "123adssadsadsadsadfgq23123124e122312";
		
//		// req
//		{
//			apiCode:tradeOut()
//			data: {}
//		}
//		
//		// resp
//		{
//			apiKey:
//			data:{
//				retCode:{}
//				retData:{}
//			}
//		}

		String ret = AESUtils.encrypt(key, content);
		System.out.println("ret: " + ret);
		System.out.println("ret: " + AESUtils.decrypt(key, ret));
		System.out.println();
	}
}
