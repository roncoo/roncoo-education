/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Component
public class AuthUploadBiz extends BaseBiz {

    /**
     * 上传视频接口
     *
     * @author wuyun
     */
    public Result<String> uploadVideo(MultipartFile videoFile) {
        // 视频上传
        if (ObjectUtil.isNotEmpty(videoFile)) {
        }
        return Result.error("请选择上传的视频");
    }

    /**
     * 上传图片接口
     *
     * @author wuyun
     */
    public Result<String> uploadPic(MultipartFile picFile) {
        if (ObjectUtil.isNotNull(picFile)) {

        }
        return Result.error("请选择上传的图片");
    }

    /**
     * 上传文档接口
     *
     * @author wuyun
     */
    public Result<String> uploadDoc(MultipartFile docFile) {
        if (ObjectUtil.isNotNull(docFile)) {

        }
        return Result.error("请选择上传的文件");
    }

}
