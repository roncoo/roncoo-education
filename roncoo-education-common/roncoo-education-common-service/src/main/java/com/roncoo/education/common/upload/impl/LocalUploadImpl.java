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

    public static final String LOCALPATH = System.getProperty("user.dir") + "/files/images/";
    public static final String PATH = "/system/images/";

    @Override
    public String uploadPic(MultipartFile file, Upload upload) {
        String fileName =  IdUtil.simpleUUID() +"."+ FileUtil.getSuffix(file.getOriginalFilename());
        try {
            file.transferTo(FileUtil.file(LOCALPATH +fileName));
        } catch (IOException e) {
            log.error("本地上传错误", e);
            return "";
        }
        return upload.getWebsiteDomain() + "gateway/system" + PATH + fileName;
    }

}
