package com.roncoo.education.course.feign.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.PlatformEnum;
import com.roncoo.education.common.core.enums.VideoStatusEnum;
import com.roncoo.education.common.core.polyv.PolyvUtil;
import com.roncoo.education.common.core.polyv.UploadFile;
import com.roncoo.education.common.core.polyv.UploadFileResult;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.StrUtil;
import com.roncoo.education.course.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.CourseVideoDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseVideoExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseVideoQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseVideoVO;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Component
public class FeignCourseVideoBiz {
    @Autowired
    private IFeignSys bossSys;
    @Autowired
    private CourseVideoDao dao;
    @Autowired
    private CourseChapterPeriodAuditDao courseChapterPeriodAuditDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;

    public Page<CourseVideoVO> listForPage(CourseVideoQO qo) {
        CourseVideoExample example = new CourseVideoExample();
        example.setOrderByClause(" id desc ");
        Page<CourseVideo> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseVideoVO.class);
    }

    public int save(CourseVideoQO qo) {
        CourseVideo record = BeanUtil.copyProperties(qo, CourseVideo.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseVideoVO getById(Long id) {
        CourseVideo record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseVideoVO.class);
    }

    public int updateById(CourseVideoQO qo) {
        CourseVideo record = BeanUtil.copyProperties(qo, CourseVideo.class);
        return dao.updateById(record);
    }

    /**
     * 定时任务-视频处理
     *
     * @param targetFile
     * @author wuyun
     */
    public void handleScheduledTasks(File targetFile) {
        Long videoNo = Long.valueOf(StrUtil.getPrefix(targetFile.getName()));
        List<CourseVideo> list = dao.listByVideoNo(videoNo);

        // 1、异步上传到保利威视
        UploadFile uploadFile = new UploadFile();
        CourseVideo courseVideo = list.get(0);
        uploadFile.setTitle(courseVideo.getVideoName());
        uploadFile.setDesc(courseVideo.getVideoName());
        uploadFile.setTag(courseVideo.getVideoName());
        uploadFile.setCataid(1L);

        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            throw new BaseException("找不到系统配置信息");
        }
        if (StringUtils.isEmpty(sys.getPolyvWritetoken())) {
            throw new BaseException("writetoken没配置");
        }
        UploadFileResult result = PolyvUtil.uploadFile(targetFile, uploadFile, sys.getPolyvWritetoken());

        if (ObjectUtil.isNotNull(result)) {
            // 2、异步上传到阿里云
            String videoOasId = AliyunUtil.uploadVideo(PlatformEnum.COURSE, targetFile, BeanUtil.copyProperties(sys, Aliyun.class));
            if (CollectionUtil.isNotEmpty(list)) {
                for (CourseVideo info : list) {
                    // 上传
                    info.setVideoLength(result.getDuration());
                    info.setVideoVid(result.getVid());
                    info.setVideoOasId(videoOasId);
                    info.setVideoStatus(VideoStatusEnum.SUCCES.getCode());
                    dao.updateById(info);
                }
            }

            // 更新课时审核表视频信息
            List<CourseChapterPeriodAudit> periodAuditList = courseChapterPeriodAuditDao.listByVideoNo(videoNo);
            if (CollectionUtil.isNotEmpty(periodAuditList)) {
                for (CourseChapterPeriodAudit periodAudit : periodAuditList) {
                    periodAudit.setVideoLength(result.getDuration());
                    periodAudit.setVideoVid(result.getVid());
                    courseChapterPeriodAuditDao.updateById(periodAudit);
                }
            }
            // 更新课时视频信息
            List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByVideoNo(videoNo);
            if (CollectionUtil.isNotEmpty(periodList)) {
                for (CourseChapterPeriod period : periodList) {
                    period.setVideoLength(result.getDuration());
                    period.setVideoVid(result.getVid());
                    courseChapterPeriodDao.updateById(period);
                }
            }


        }
        // 成功删除本地文件
        targetFile.delete();
    }

}
