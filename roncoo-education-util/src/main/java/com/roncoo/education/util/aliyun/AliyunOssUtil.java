/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.aliyun;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.roncoo.education.util.config.ConfigUtil;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.StrUtil;

/**
 * 阿里云OSS工具类
 * 
 * @author wujing
 */
public final class AliyunOssUtil {

	private AliyunOssUtil() {
	}

	public static void main(String[] args) {
//		File file = new File("C:\\Users\\wuyun\\Desktop\\TSvnPwd.exe");
//		System.out.println(uploadPic(PlatformEnum.COURSE, file));
	}

	private static OSSClient ossClient;

	private static OSSClient getOssClient() {
		if (ossClient == null) {
			ossClient = new OSSClient(ConfigUtil.ALIYUN_OSS_ENDPOINT, ConfigUtil.ALIYUN_ACCESSKEYID, ConfigUtil.ALIYUN_ACCESSKEYSECRET);
		}
		return ossClient;
	}

	/**
	 * 返回文件URl
	 * 
	 * @param platformEnum
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static String uploadPic(PlatformEnum platformEnum, MultipartFile file) {
		// 上传
		try {
			String name = file.getOriginalFilename();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			putObject(ConfigUtil.ALIYUN_OSS_BUCKET, filePath, file.getInputStream(), name.substring(name.lastIndexOf(".")));
			return ConfigUtil.ALIYUN_OSS_URL + filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回文件URl
	 * 
	 * @param platformEnum
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static String uploadPic(PlatformEnum platformEnum, File file) {
		// 上传
		try {
			String name = file.getName();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			putObject(ConfigUtil.ALIYUN_OSS_BUCKET, filePath, file, name.substring(name.lastIndexOf(".")));
			return ConfigUtil.ALIYUN_OSS_URL + filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回文件URl
	 * 
	 * @param platformEnum
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static String uploadDoc(PlatformEnum platformEnum, MultipartFile file, String fileName) {
		// 上传
		try {
			String name = file.getOriginalFilename();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			putObject(ConfigUtil.ALIYUN_OSS_BUCKET, filePath, file.getInputStream(), fileName + name.substring(name.lastIndexOf(".")));
			return ConfigUtil.ALIYUN_OSS_URL + filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回文件URl
	 * 
	 * @param platformEnum
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static String upload(PlatformEnum platformEnum, File file, String orgNo, String fileName) {
		if (StringUtils.isBlank(orgNo)) {
			throw new RuntimeException("orgNo不能为空");
		}
		// 上传
		try {
			String name = file.getName();
			String filePath = orgNo + "/" + platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			putObject(ConfigUtil.ALIYUN_OSS_BUCKET, filePath, file, fileName + name.substring(name.lastIndexOf(".")));
			return ConfigUtil.ALIYUN_OSS_URL + filePath;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 返回文件URl
	 * 
	 * @param platformEnum
	 * @param file
	 * @param fileName
	 * @return
	 */
	public static File download(String url) {
		String key = url.replace(ConfigUtil.ALIYUN_OSS_URL, "");
		File file = new File(ConfigUtil.PIC_PATH + url.substring(url.lastIndexOf("/")));
		if (file.exists()) {
			return file;
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		try {
			downloadObject(ConfigUtil.ALIYUN_OSS_BUCKET, key, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 根据url删除
	 * 
	 * @param url
	 * @return
	 */
	public static void delete(String url) {
		// 删除
		try {
			String filePath = url.replace(ConfigUtil.ALIYUN_OSS_URL, url);
			deleteObject(ConfigUtil.ALIYUN_OSS_BUCKET, filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件存储入OSS
	 * 
	 * @param bucketName
	 * @param key
	 * @param inputStream
	 * @return
	 */
	private static PutObjectResult putObject(String bucketName, String key, InputStream inputStream, String fileName) {
		ObjectMetadata meta = new ObjectMetadata();
		if (StringUtils.isNotBlank(fileName)) {
			meta.setContentDisposition("attachment;filename={}".replace("{}", fileName));
		}
		return getOssClient().putObject(bucketName, key, inputStream, meta);
	}

	/**
	 * 文件存储入OSS
	 * 
	 * @param bucketName
	 * @param key
	 * @param inputStream
	 * @return
	 */
	private static PutObjectResult putObject(String bucketName, String key, File file, String fileName) {
		ObjectMetadata meta = new ObjectMetadata();
		if (StringUtils.isNotBlank(fileName)) {
			meta.setContentDisposition("attachment;filename={}".replace("{}", fileName));
		}
		return getOssClient().putObject(bucketName, key, file, meta);
	}

	/**
	 * 删除Bucket中的Object
	 * 
	 * @param bucketName
	 * @param key
	 * @throws IOException
	 */
	private static void deleteObject(String bucketName, String key) throws IOException {
		getOssClient().deleteObject(bucketName, key);
	}

	/**
	 * 删除Bucket中的Object
	 * 
	 * @param bucketName
	 * @param key
	 * @throws IOException
	 */
	private static void downloadObject(String bucketName, String key, File file) throws IOException {
		getOssClient().getObject(new GetObjectRequest(bucketName, key), file);
	}

}
