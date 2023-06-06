package com.roncoo.education.common.upload.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author fengyw
 */
@Slf4j
@Component(value = "local")
public class LocalUploadImpl implements UploadFace {

    public static final String LOCALPATH_IMAGES = System.getProperty("user.dir") + "/files/images/";
    public static final String PATH_IMAGES = "/system/images/";

    public static final String LOCALPATH_DOCS = System.getProperty("user.dir") + "/files/docs/";
    public static final String PATH_DOCS = "/system/docs/";

    @Override
    public String uploadPic(MultipartFile file, Upload upload) {
        String fileName = IdUtil.simpleUUID() + "." + FileUtil.getSuffix(file.getOriginalFilename());
        try {
            file.transferTo(FileUtil.file(FileUtil.mkdir(LOCALPATH_IMAGES), fileName));
        } catch (IOException e) {
            log.error("本地上传错误", e);
            return "";
        }
        return upload.getWebsiteDomain() + "gateway" + PATH_IMAGES + fileName;
    }

    @Override
    public String uploadDoc(MultipartFile file, Upload upload) {
        String fileName = IdUtil.simpleUUID() + "." + FileUtil.getSuffix(file.getOriginalFilename());
        try {
            file.transferTo(FileUtil.file(FileUtil.mkdir(LOCALPATH_DOCS), fileName));
        } catch (IOException e) {
            log.error("本地上传错误", e);
            return "";
        }
        return upload.getWebsiteDomain() + "gateway" + PATH_DOCS + fileName;
    }

}
