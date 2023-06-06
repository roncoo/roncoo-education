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
     * 图片上传
     *
     * @return 图片url
     */
    String uploadDoc(MultipartFile file, Upload upload);
}
