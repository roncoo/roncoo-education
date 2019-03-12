package com.roncoo.education.util.aliyun;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oas.OASFactory;
import com.aliyun.oas.ease.ArchiveManager;
import com.aliyun.oas.model.common.ServiceCredentials;
import com.aliyun.oas.model.result.UploadResult;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.StrUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class AliyunUtil {

	private AliyunUtil() {
	}

	public static String getUrlSign(Aliyun aliyun, String url, Date expires) {
		GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(aliyun.getAliyunOssBucket(), url.replace(aliyun.getAliyunOssUrl(), ""));
		generatePresignedUrlRequest.setExpiration(expires);
		return url + "?" + getOssClient(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).generatePresignedUrl(generatePresignedUrlRequest).getQuery();
	}

	public static File download(Aliyun aliyun, String url) {
		String key = check(url, aliyun.getAliyunOssUrl());

		File file = new File(SystemUtil.PIC_PATH + url.substring(url.lastIndexOf("/")));
		if (file.exists()) {
			return file;
		}
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		try {
			downloadObject(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), key, file);
		} catch (IOException e) {
			log.error("上传失败", e);
		}
		return file;
	}

	public static String uploadPic(PlatformEnum platformEnum, File file, Aliyun aliyun) {
		// 上传
		try {
			String name = file.getName();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			getOssClient(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).putObject(aliyun.getAliyunOssBucket(), filePath, file);
			return aliyun.getAliyunOssUrl() + filePath;
		} catch (Exception e) {
			log.error("上传失败", e);
			return "";
		}
	}

	public static String uploadPic(PlatformEnum platformEnum, MultipartFile file, Aliyun aliyun) {
		// 上传
		InputStream in = null;
		try {
			in = file.getInputStream();
			String name = file.getOriginalFilename();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			getOssClient(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).putObject(aliyun.getAliyunOssBucket(), filePath, in);
			return aliyun.getAliyunOssUrl() + filePath;
		} catch (Exception e) {
			log.error("上传失败", e);
			return "";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	public static String uploadDoc(PlatformEnum platformEnum, File file, Aliyun aliyun) {
		// 上传
		try {
			String name = file.getName();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			putObjectForFile(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), filePath, new FileInputStream(file), file.getName());
			return aliyun.getAliyunOssUrl() + filePath;
		} catch (Exception e) {
			log.error("上传失败", e);
			return "";
		}
	}

	public static String uploadDoc(PlatformEnum platformEnum, MultipartFile file, Aliyun aliyun) {
		InputStream in = null;
		// 上传
		try {
			in = file.getInputStream();
			String name = file.getOriginalFilename();
			String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
			putObjectForFile(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), filePath, in, file.getOriginalFilename());
			return aliyun.getAliyunOssUrl() + filePath;
		} catch (Exception e) {
			log.error("上传失败", e);
			return "";
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
				}
			}
		}
	}

	/**
	 * 根据url删除
	 * 
	 * @param url
	 */
	public static void delete(String url, Aliyun aliyun) {
		try {
			String filePath = url.replace(aliyun.getAliyunOssUrl(), "");
			deleteObject(aliyun.getAliyunOssBucket(), filePath, aliyun);
		} catch (Exception e) {
			log.error("上传失败", e);
		}
	}

	public static String uploadOAS(File file, Aliyun aliyun) {
		// 上传文件
		try {
			UploadResult uploadResult = getArchiveManager(aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), SystemUtil.ALIYUN_OAS_URL).upload(aliyun.getAliyunOasVault(), file);
			return uploadResult.getArchiveId();
		} catch (Exception e) {
			log.error("上传到OAS失败，会忽略不再上传", e);
		}
		return "";
	}

	public static boolean sendMsg(String phone, String code, Aliyun aliyun) throws ClientException {
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化ascClient需要的几个参数
		// 初始化ascClient,暂时不支持多region（请勿修改）
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret());
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", "Dysmsapi", "dysmsapi.aliyuncs.com");
		IAcsClient acsClient = new DefaultAcsClient(profile);
		// 组装请求对象
		SendSmsRequest request = new SendSmsRequest();
		// 使用post提交
		request.setMethod(MethodType.POST);
		// 必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
		request.setPhoneNumbers(phone);
		// 必填:短信签名-可在短信控制台中找到
		request.setSignName(aliyun.getSignName());
		// 必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(aliyun.getSmsCode());
		// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		// 友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		request.setTemplateParam("{\"code\":\"{code}\",\"product\":\"领课开源\"}".replace("{code}", code));
		// 可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
		// request.setSmsUpExtendCode("90997");
		// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		// request.setOutId("yourOutId");
		// 请求失败这里会抛ClientException异常
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			log.debug(sendSmsResponse.getCode());
			return true;
		} else {
			log.error("发送失败，code={},message={}", sendSmsResponse.getCode(), sendSmsResponse.getMessage());
			return false;
		}
	}

	private static String check(String url, String ossUrl) {
		String key = url.replace(ossUrl, "");
		if (key.startsWith("http")) {
			if (ossUrl.startsWith("https")) {
				ossUrl = "http" + ossUrl.substring(5, ossUrl.length());
			} else {
				ossUrl = "https" + ossUrl.substring(4, ossUrl.length());
			}
		}
		return key.replace(ossUrl, "");
	}

	private static ArchiveManager getArchiveManager(String keyId, String keySecret, String oasUrl) {
		// Access Key 配置
		ServiceCredentials credentials = new ServiceCredentials(keyId, keySecret);
		// 通过工厂类获得archiveManager接口
		return OASFactory.archiveManagerFactory(credentials, oasUrl);
	}

	/**
	 * 文件存储入OSS
	 * 
	 * @param bucketName
	 * @param key
	 * @param inputStream
	 */
	private static PutObjectResult putObjectForFile(String endpoint, String keyId, String keySecret, String bucketName, String key, InputStream inputStream, String fileName) {
		OSSClient ossClient = getOssClient(endpoint, keyId, keySecret);
		ObjectMetadata meta = new ObjectMetadata();
		if (StringUtils.isNotBlank(fileName)) {
			meta.setContentDisposition("attachment;filename={}".replace("{}", fileName));
			meta.setObjectAcl(CannedAccessControlList.Private);
		}
		return ossClient.putObject(bucketName, key, inputStream, meta);
	}

	private static void downloadObject(String endpoint, String keyId, String keySecret, String bucketName, String key, File file) throws IOException {
		getOssClient(endpoint, keyId, keySecret).getObject(new GetObjectRequest(bucketName, key), file);
	}

	private static OSSClient getOssClient(String endpoint, String keyId, String keySecret) {
		return new OSSClient(endpoint, keyId, keySecret);
	}

	/**
	 * 删除Bucket中的Object
	 * 
	 * @param bucketName
	 * @param key
	 * @throws IOException
	 */
	private static void deleteObject(String bucketName, String key, Aliyun aliyun) throws IOException {
		getOssClient(SystemUtil.ALIYUN_OSS_ENDPOINT, aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).deleteObject(bucketName, key);
	}

}
