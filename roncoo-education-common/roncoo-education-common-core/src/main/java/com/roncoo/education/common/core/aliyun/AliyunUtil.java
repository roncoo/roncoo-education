package com.roncoo.education.common.core.aliyun;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.*;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.roncoo.education.common.core.config.SystemUtil;
import com.roncoo.education.common.core.enums.PlatformEnum;
import com.roncoo.education.common.core.tools.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Slf4j
public final class AliyunUtil {

    private AliyunUtil() {
    }

    public static String getUrlSign(Aliyun aliyun, String url, Date expires) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(aliyun.getAliyunOssBucket(), url.replace(aliyun.getAliyunOssUrl(), ""));
        generatePresignedUrlRequest.setExpiration(expires);
        return url + "?" + getOssClient(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).generatePresignedUrl(generatePresignedUrlRequest).getQuery();
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
            downloadObject(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), key, file);
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
            getOssClient(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).putObject(aliyun.getAliyunOssBucket(), filePath, file);
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
            getOssClient(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).putObject(aliyun.getAliyunOssBucket(), filePath, in);
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
            putObjectForFile(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), filePath, new FileInputStream(file), file.getName());
            return aliyun.getAliyunOssUrl() + filePath;
        } catch (Exception e) {
            log.error("上传失败", e);
            return "";
        }
    }

    public static String uploadVideo(PlatformEnum platformEnum, File file, Aliyun aliyun) {
        // 上传
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            String name = file.getName();
            String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
            putObjectForFile(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOasVault(), filePath, fileInputStream, file.getName());
            return aliyun.getAliyunOssUrl() + filePath;
        } catch (Exception e) {
            log.error("上传失败", e);
            return "";
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    log.error("上传失败", e);
                }
            }
        }
    }

    public static String uploadDoc(PlatformEnum platformEnum, MultipartFile file, Aliyun aliyun) {
        InputStream in = null;
        // 上传
        try {
            in = file.getInputStream();
            String name = file.getOriginalFilename();
            String filePath = platformEnum.name().toLowerCase() + "/" + StrUtil.get32UUID() + name.substring(name.lastIndexOf("."));
            putObjectForFile(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), filePath, in, file.getOriginalFilename());
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

    public static boolean sendMsg(String phone, String code, Aliyun aliyun) throws ClientException {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret());
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", aliyun.getSignName());
        request.putQueryParameter("TemplateCode", aliyun.getSmsCode());
        request.putQueryParameter("TemplateParam", "{\"code\":\"{code}\",\"product\":\"领课开源\"}".replace("{code}", code));
        CommonResponse response = acsClient.getCommonResponse(request);
        log.info("发送成功，返回报文={}", response.toString());
        return true;
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


    /**
     * 文件存储入OSS
     *
     * @param bucketName
     * @param key
     * @param inputStream
     */
    private static PutObjectResult putObjectForFile(String endpoint, String keyId, String keySecret, String bucketName, String key, InputStream inputStream, String fileName) {
        OSS ossClient = getOssClient(endpoint, keyId, keySecret);
        ObjectMetadata meta = new ObjectMetadata();
        if (StringUtils.hasText(fileName)) {
            meta.setContentDisposition("attachment;filename={}".replace("{}", fileName));
            meta.setObjectAcl(CannedAccessControlList.Private);
        }
        return ossClient.putObject(bucketName, key, inputStream, meta);
    }

    private static void downloadObject(String endpoint, String keyId, String keySecret, String bucketName, String key, File file) throws IOException {
        getOssClient(endpoint, keyId, keySecret).getObject(new GetObjectRequest(bucketName, key), file);
    }

    private static OSS getOssClient(String endpoint, String keyId, String keySecret) {
        return new OSSClientBuilder().build(endpoint, keyId, keySecret);
    }

    /**
     * 删除Bucket中的Object
     *
     * @param bucketName
     * @param key
     * @throws IOException
     */
    private static void deleteObject(String bucketName, String key, Aliyun aliyun) throws IOException {
        getOssClient(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).deleteObject(bucketName, key);
    }

}
