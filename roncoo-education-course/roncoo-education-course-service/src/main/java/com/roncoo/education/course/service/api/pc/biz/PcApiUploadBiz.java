/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.pc.biz;

import com.roncoo.education.course.service.dao.FileStorageDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.FileStorage;
import com.roncoo.education.system.feign.vo.SysVO;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.config.SystemUtil;
import com.roncoo.education.util.enums.FileStorageTypeEnum;
import com.roncoo.education.util.enums.FileTypeEnum;
import com.roncoo.education.util.enums.PlatformEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.IdWorker;
import com.roncoo.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 上传接口
 */
@Component
public class PcApiUploadBiz extends BaseBiz {

    @Autowired
    private IFeignSys bossSys;
    @Autowired
    private FileStorageDao fileStorageDao;

    /**
     * 上传图片接口
     */
    public Result<String> uploadPic(MultipartFile picFile) {
        if (ObjectUtil.isNotNull(picFile)) {
            // 获取系统配置信息
            SysVO sys = bossSys.getSys();
            if (ObjectUtil.isNull(sys)) {
                Result.error("未配置系统配置表");
            }
            Long fileNo = IdWorker.getId();
            // 1、上传到本地
            if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
                File pic = new File(SystemUtil.PIC_STORAGE_PATH + fileNo.toString() + "." + StrUtil.getSuffix(picFile.getOriginalFilename()));
                try {
                    // 判断文件目录是否存在，不存在就创建文件目录
                    if (!pic.getParentFile().exists()) {
                        pic.getParentFile().mkdirs();// 创建父级文件路径
                    }
                    picFile.transferTo(pic);
                    FileStorage fileStorage = new FileStorage();
                    fileStorage.setFileName(picFile.getOriginalFilename());
                    fileStorage.setFileNo(fileNo);
                    fileStorage.setFileSize(picFile.getSize());
                    fileStorage.setFileType(FileStorageTypeEnum.PICTURE.getCode());
                    fileStorage.setFileUrl(pic.toString());
                    fileStorageDao.save(fileStorage);
                    return Result.success(pic.toString());
                } catch (Exception e) {
                    logger.error("上传到本地失败", e);
                    return Result.error("上传文件出错，请重新上传");
                }
            }
            return Result.success(AliyunUtil.uploadPic(PlatformEnum.COURSE, picFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
        }
        return Result.error("请选择上传的图片");
    }

    /**
     * 上传文档接口
     */
    public Result<String> uploadDoc(MultipartFile docFile) {
        if (ObjectUtil.isNotNull(docFile)) {
            // 获取系统配置信息
            SysVO sys = bossSys.getSys();
            if (ObjectUtil.isNull(sys)) {
                Result.error("未配置系统配置表");
            }
            Long fileNo = IdWorker.getId();
            // 1、上传到本地
            if (sys.getFileType().equals(FileTypeEnum.LOCAL.getCode())) {
                File pic = new File(SystemUtil.DOC_STORAGE_PATH + fileNo.toString() + "." + StrUtil.getSuffix(docFile.getOriginalFilename()));
                try {
                    // 判断文件目录是否存在，不存在就创建文件目录
                    if (!pic.getParentFile().exists()) {
                        pic.getParentFile().mkdirs();// 创建父级文件路径
                    }
                    docFile.transferTo(pic);
                    FileStorage fileStorage = new FileStorage();
                    fileStorage.setFileName(docFile.getOriginalFilename());
                    fileStorage.setFileNo(fileNo);
                    fileStorage.setFileSize(docFile.getSize());
                    fileStorage.setFileType(FileStorageTypeEnum.DOC.getCode());
                    fileStorage.setFileUrl(pic.toString());
                    fileStorageDao.save(fileStorage);
                    return Result.success(pic.toString());
                } catch (Exception e) {
                    logger.error("上传到本地失败", e);
                    return Result.error("上传文件出错，请重新上传");
                }
            }
            return Result.success(AliyunUtil.uploadDoc(PlatformEnum.COURSE, docFile, BeanUtil.copyProperties(bossSys.getSys(), Aliyun.class)));
        }
        return Result.error("请选择上传的文件");

    }

}
