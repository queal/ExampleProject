package com.example.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

public class QiNiuUtils {
	private static final Log logger = LogFactory.getLog(QiNiuUtils.class);

	public static final String BUCKET_NAME_MER_INFO = ConfigUtils.get("qiniu_bucket_name_merinfo");
	public static final String BUCKET_NAME_EXCEL = ConfigUtils.get("qiniu_bucket_name_excel");
	public static final String BUCKET_NAME_UEDITOR = ConfigUtils.get("qiniu_bucket_name_ueditor");

	public static final String DOMAIN_URL_MER_INFO = ConfigUtils.get("qiniu_domain_url_merinfo");
	public static final String DOMAIN_URL_EXCEL = ConfigUtils.get("qiniu_domain_url_excel");
	public static final String DOMAIN_URL_UEDITOR = ConfigUtils.get("qiniu_domain_url_ueditor");
	
	
	private static final String ACCESS_KEY = ConfigUtils.get("qiniu_access_key");
	private static final String SECRET_KEY = ConfigUtils.get("qiniu_secret_key");

	private static Auth auth;
	private static UploadManager uploadManager;

	static {
		auth = Auth.create(ACCESS_KEY, SECRET_KEY);
		uploadManager = new UploadManager();
	}

	public static boolean upload(String fileName, byte[] file, String bucketName) {
		boolean isSucs = false;
		try {
			Response response = uploadManager.put(file, fileName, auth.uploadToken(bucketName, fileName));
			if (200 == response.statusCode) {
				isSucs = true;
			}
		} catch (QiniuException e) {
			logger.error("七牛上传文件出错!fileName:" + fileName + ",bucketName:" + bucketName, e);
		}
		return isSucs;
	}

	public static String getPrivateDownloadUrl(String fileName, String bucketName) {
		return auth.privateDownloadUrl(bucketName + fileName, 3600);
	}

	public static void delete(String fileName, String bucketName) {
		try {
			BucketManager bucketManager = new BucketManager(auth);
		
			bucketManager.delete(bucketName, fileName);
		} catch (QiniuException e) {
			logger.error("七牛删除文件出错!fileName:" + fileName + ",bucketName:" + bucketName, e);
		}
	}
	
	public static boolean upload(String fileName, String fileUrl, String bucketName) {
		boolean isSucs = false;
		try {
			BucketManager bucketManager = new BucketManager(auth);
			DefaultPutRet ret = bucketManager.fetch(fileUrl, bucketName, fileName);
			if(StringUtils.equals(ret.key, fileName)) {
				isSucs = true;
			} 
		} catch (QiniuException e) {
			logger.error("七牛上传文件出错!fileName:" + fileName + ",bucketName:" + bucketName, e);
		}
		return isSucs;
	}

}
