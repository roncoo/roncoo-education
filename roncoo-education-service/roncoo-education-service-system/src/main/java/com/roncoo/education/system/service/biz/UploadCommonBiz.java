/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.system.service.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StoragePlatformEnum;
import com.roncoo.education.common.core.tools.DocUtil;
import com.roncoo.education.common.core.tools.FileUtils;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import com.roncoo.education.system.service.biz.resp.UploadDocResp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Slf4j
@Component
public class UploadCommonBiz {

    @Autowired
    private Map<String, UploadFace> uploadFaceMap;

    @Autowired
    private SysConfigCommonBiz sysConfigCommonBiz;

    public Result<String> uploadPic(MultipartFile picFile) {
        if (!FileUtils.isPic(picFile)) {
            return Result.error("目前只支持：{}，请选择图片上传".replace("{}", FileUtils.PIC_TYPE_MAP));
        }

        Upload upload = sysConfigCommonBiz.getSysConfig(Upload.class);
        if (ObjectUtil.isNotEmpty(upload) || StringUtils.isEmpty(upload.getStoragePlatform())) {
            UploadFace uploadFace = uploadFaceMap.get(StoragePlatformEnum.byCode(Integer.valueOf(upload.getStoragePlatform())).getMode());
            if (ObjectUtil.isEmpty(uploadFace)) {
                return Result.error("暂不支持该类型");
            }
            String fileUrl = uploadFace.uploadPic(picFile, upload);
            return Result.success(fileUrl);
        }
        return Result.error("上传参数没配置");
    }

    public Result<UploadDocResp> uploadDoc(MultipartFile docFile) {
        if (!FileUtils.isDoc(docFile)) {
            return Result.error("目前只支持：{}，请选择文件上传".replace("{}", FileUtils.DOC_TYPE_MAP));
        }

        UploadDocResp resp = new UploadDocResp();
        Upload upload = sysConfigCommonBiz.getSysConfig(Upload.class);
        resp.setStoragePlatform(upload.getStoragePlatform());
        if (ObjectUtil.isNotEmpty(upload) || StringUtils.isEmpty(upload.getStoragePlatform())) {
            UploadFace uploadFace = uploadFaceMap.get(StoragePlatformEnum.byCode(upload.getStoragePlatform()).getMode());
            if (ObjectUtil.isEmpty(uploadFace)) {
                return Result.error("暂不支持该类型");
            }
            resp.setDocUrl(uploadFace.uploadDoc(docFile, upload));
            try {
                resp.setPageCount(DocUtil.getDocPageCount(docFile.getOriginalFilename(), docFile.getInputStream()));
                return Result.success(resp);
            } catch (Exception e) {
                log.error("文档错误", e);
                return Result.error("文档错误");
            }
        }
        return Result.error("系统异常，请重试");
    }

}
