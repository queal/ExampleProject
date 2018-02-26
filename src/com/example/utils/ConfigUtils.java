package com.example.utils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConfigUtils {
	/** log */
	private static final Log log = LogFactory.getLog(ConfigUtils.class);

	private static Properties properties = new Properties();

	static {
		BufferedInputStream bis = null;
		try {
			bis = new BufferedInputStream(ConfigUtils.class.getResourceAsStream("/config.properties"));
			properties.load(bis);
		} catch (Exception e) {
			log.error("加载配置文件出错!", e);
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String get(String key) {
		return properties.getProperty(key);
	}

}
