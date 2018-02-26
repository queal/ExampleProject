package com.example.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.methods.multipart.ByteArrayPartSource;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.util.IdleConnectionTimeoutThread;

public class HttpClientUtils {

	/** 连接超时时间 */
	private static final int defaultConnectionTimeout = 10000;
	/** 回应超时时间 */
	private static final int defaultSoTimeout = 30000;
	/** 闲置连接超时时间 */
	private static final int defaultIdleConnTimeout = 60000;
	private static final int defaultMaxConnPerHost = 30;
	private static final int defaultMaxTotalConn = 80;

	/** 默认等待HttpConnectionManager返回连接超时（只有在达到最大连接数时起作用）*/
	private static final long defaultHttpConnectionManagerTimeout = 3 * 1000;

	private static HttpConnectionManager connectionManager;

	static {
		connectionManager = new MultiThreadedHttpConnectionManager();
		connectionManager.getParams().setDefaultMaxConnectionsPerHost(defaultMaxConnPerHost);
		connectionManager.getParams().setMaxTotalConnections(defaultMaxTotalConn);

		IdleConnectionTimeoutThread ict = new IdleConnectionTimeoutThread();
		ict.addConnectionManager(connectionManager);
		ict.setConnectionTimeout(defaultIdleConnTimeout);

		ict.start();
	}

	public static HttpClient getHttpClient() {
		HttpClient httpClient = new HttpClient(connectionManager);
		httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(defaultConnectionTimeout);
		httpClient.getHttpConnectionManager().getParams().setSoTimeout(defaultSoTimeout);
		httpClient.getParams().setConnectionManagerTimeout(defaultHttpConnectionManagerTimeout);
		return httpClient;
	}

	public static String doPost(HttpClient httpClient, String url, List<NameValuePair> params) {
		String body = "";

		try {
			PostMethod httppost = new PostMethod(url);
			httppost.getParams().setContentCharset("utf-8");
			if (params != null) {
				NameValuePair[] p = new NameValuePair[params.size()];
				params.toArray(p);
				httppost.setRequestBody(p);
			}
			httpClient.executeMethod(httppost);

			if (httppost.getStatusCode() == HttpStatus.SC_OK) {
				byte[] bytes = httppost.getResponseBody();
				body = new String(bytes, "utf-8");
			}
			httppost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public static String doPostJson(HttpClient httpClient, String url, String json) {
		String body = "";
		try {
			PostMethod httppost = new PostMethod(url);
			StringRequestEntity entity = new StringRequestEntity(json, "application/json", "utf-8");
			httppost.setRequestEntity(entity);
			httpClient.executeMethod(httppost);

			if (httppost.getStatusCode() == HttpStatus.SC_OK) {
				byte[] bytes = httppost.getResponseBody();
				body = new String(bytes, "utf-8");
			}
			httppost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public static String doPostXml(HttpClient httpClient, String url, String xml) {
		String body = "";
		try {
			PostMethod httppost = new PostMethod(url);
			StringRequestEntity entity = new StringRequestEntity(xml, "text/xml", "utf-8");
			httppost.setRequestEntity(entity);
			httpClient.executeMethod(httppost);

			if (httppost.getStatusCode() == HttpStatus.SC_OK) {
				byte[] bytes = httppost.getResponseBody();
				body = new String(bytes, "utf-8");
			}
			httppost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public static String doGet(HttpClient httpClient, String url) {
		String body = "";
		try {
			GetMethod httpget = new GetMethod(url);
			httpget.getParams().setContentCharset("utf-8");
			httpClient.executeMethod(httpget);

			if (httpget.getStatusCode() == HttpStatus.SC_OK) {
				byte[] bytes = httpget.getResponseBody();
				body = new String(bytes, "utf-8");
			}
			httpget.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

	public static byte[] getFile(HttpClient httpClient, String url) {
		byte[] file = null;
		try {
			GetMethod httpget = new GetMethod(url);
			httpget.getParams().setContentCharset("utf-8");
			httpClient.executeMethod(httpget);

			if (httpget.getStatusCode() == HttpStatus.SC_OK) {
				file = httpget.getResponseBody();
			}
			httpget.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return file;
	}

	public static String uploadFile(HttpClient httpClient, String url, List<NameValuePair> params, String filename,
			byte[] data) {
		String body = "";
		try {
			PostMethod httppost = new PostMethod(url);
			httppost.getParams().setContentCharset("utf-8");

			List<Part> parts = new ArrayList<Part>();
			parts.add(new FilePart(filename, new ByteArrayPartSource(filename, data)));

			for (NameValuePair pair : params) {
				parts.add(new StringPart(pair.getName(), pair.getValue()));
			}

			Part[] _parts = new Part[parts.size()];
			parts.toArray(_parts);

			MultipartRequestEntity entity = new MultipartRequestEntity(_parts, httpClient.getParams());
			httppost.setRequestEntity(entity);
			httpClient.executeMethod(httppost);

			if (httppost.getStatusCode() == HttpStatus.SC_OK) {
				byte[] bytes = httppost.getResponseBody();
				body = new String(bytes, "utf-8");
			}
			httppost.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return body;
	}

}
