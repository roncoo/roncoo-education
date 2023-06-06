package com.roncoo.education.common.core.aliyun;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * @author fengyw
 */
@Slf4j
public final class AliyunOssUtil {

    private final static String PREFIX = "education";

    private AliyunOssUtil() {
    }

    public static String getUrlSign(Aliyun aliyun, String url, Date expires) {
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(aliyun.getAliyunOssBucket(), url.replace(aliyun.getAliyunOssUrl(), ""));
        generatePresignedUrlRequest.setExpiration(expires);
        return url + "?" + getOssClient(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).generatePresignedUrl(generatePresignedUrlRequest).getQuery();
    }

    public static String uploadPic(File file, Aliyun aliyun) {
        // 上传
        try {
            String name = file.getName();
            String filePath = PREFIX + "/" + IdUtil.simpleUUID() + name.substring(name.lastIndexOf("."));
            getOssClient(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret()).putObject(aliyun.getAliyunOssBucket(), filePath, file);
            return aliyun.getAliyunOssUrl() + filePath;
        } catch (Exception e) {
            log.error("上传失败", e);
            return "";
        }
    }

    public static String uploadPic(MultipartFile file, Aliyun aliyun) {
        // 上传
        InputStream in = null;
        try {
            in = file.getInputStream();
            String name = file.getOriginalFilename();
            String filePath = PREFIX + "/" + IdUtil.simpleUUID() + name.substring(name.lastIndexOf("."));
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

    public static String uploadDoc(File file, Aliyun aliyun) {
        // 上传
        try {
            String name = file.getName();
            String filePath = PREFIX + "/" + IdUtil.simpleUUID() + name.substring(name.lastIndexOf("."));
            putObjectForFile(aliyun.getAliyunOssEndpoint(), aliyun.getAliyunAccessKeyId(), aliyun.getAliyunAccessKeySecret(), aliyun.getAliyunOssBucket(), filePath, new FileInputStream(file), file.getName());
            return aliyun.getAliyunOssUrl() + filePath;
        } catch (Exception e) {
            log.error("上传失败", e);
            return "";
        }
    }

    public static String uploadDoc(MultipartFile file, Aliyun aliyun) {
        InputStream in = null;
        // 上传
        try {
            in = file.getInputStream();
            String name = file.getOriginalFilename();
            String filePath = PREFIX + "/" + IdUtil.simpleUUID() + name.substring(name.lastIndexOf("."));
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
        meta.setContentEncoding("UTF-8");
        if (StringUtils.hasText(fileName)) {
            meta.setContentDisposition("attachment;filename={}".replace("{}", fileName));
            meta.setObjectAcl(CannedAccessControlList.Private);
        }
        return ossClient.putObject(bucketName, key, inputStream, meta);
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
