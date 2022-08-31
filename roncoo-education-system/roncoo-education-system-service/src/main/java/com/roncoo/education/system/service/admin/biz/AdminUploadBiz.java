/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StoragePlatformEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Slf4j
@Component
public class AdminUploadBiz {

    @Autowired
    private Map<String, UploadFace> uploadFaceMap;

    @Autowired
    private SysConfigDao sysConfigDao;

    public Result<String> uploadPic(MultipartFile picFile) {
        Upload upload = getUploadForSysConfig();
        if(ObjectUtil.isNotEmpty(upload)|| StringUtils.isEmpty(upload.getStoragePlatform())){
            UploadFace uploadFace =  uploadFaceMap.get(StoragePlatformEnum.getByCode(Integer.valueOf(upload.getStoragePlatform())).getMode());
            String fileUrl = uploadFace.uploadPic(picFile, upload);
            return Result.success(fileUrl);
        }
        return Result.error("上传参数配置");
    }

    public Result<String> uploadDoc(MultipartFile docFile) {
        return null;
    }

    private Upload getUploadForSysConfig() {
        SysConfigExample example = new SysConfigExample();
        List<SysConfig> sysConfigs = sysConfigDao.listByExample(example);
        Map<String, String> map = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        return BeanUtil.objToBean(map, Upload.class);
    }
}
