package com.roncoo.education.common.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * 上传功能
 *
 * @author fengyw
 */
public interface UploadFace {

    /**
     * 图片上传
     *
     * @return 图片url
     */
    String uploadPic(MultipartFile file, Upload upload);

    /**
     * 文档上传
     *
     * @return 文档url
     */
    String uploadDoc(MultipartFile file, Upload upload, Boolean isPublicRead);


    /**
     * 获取下载地址
     */
    String getDownloadUrl(String docUrl, int expireSeconds, Upload upload);

    /**
     * 获取预览地址
     *
     * @return 预览地址
     */
    String getPreviewConfig(String docUrl, int expireSeconds, Upload upload);

}
