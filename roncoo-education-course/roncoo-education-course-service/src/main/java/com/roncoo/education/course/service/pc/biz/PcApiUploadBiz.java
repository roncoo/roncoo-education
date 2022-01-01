/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.config.SystemUtil;
import com.roncoo.education.common.core.enums.FileClassifyEnum;
import com.roncoo.education.common.core.enums.FileTypeEnum;
import com.roncoo.education.common.core.enums.PlatformEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.core.tools.StrUtil;
import com.roncoo.education.course.dao.FileStorageDao;
import com.roncoo.education.course.dao.impl.mapper.entity.FileStorage;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
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
                    fileStorage.setfileClassify(FileClassifyEnum.PICTURE.getCode());
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
                    fileStorage.setfileClassify(FileClassifyEnum.DOC.getCode());
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
