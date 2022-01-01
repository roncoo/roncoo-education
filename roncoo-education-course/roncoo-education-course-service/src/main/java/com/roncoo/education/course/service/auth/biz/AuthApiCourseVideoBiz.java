package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.AuditStatusEnum;
import com.roncoo.education.common.core.enums.IsVideoEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.polyv.PolyvUtil;
import com.roncoo.education.common.core.tools.ArrayListUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseAuditDao;
import com.roncoo.education.course.dao.CourseChapterAuditDao;
import com.roncoo.education.course.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.dao.CourseVideoDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseVideo;
import com.roncoo.education.course.service.auth.bo.*;
import com.roncoo.education.course.service.auth.dto.AuthCourseVideoForListDTO;
import com.roncoo.education.course.service.auth.dto.AuthCourseVideoListDTO;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Component
public class AuthApiCourseVideoBiz extends BaseBiz {

    @Autowired
    private CourseVideoDao dao;
    @Autowired
    private CourseChapterPeriodAuditDao periodAuditDao;
    @Autowired
    private CourseChapterAuditDao chapterAuditDao;
    @Autowired
    private CourseAuditDao courseAuditDao;

    @Autowired
    private IFeignSys bossSys;

    /**
     * 章节视频库，课时视频库添加接口
     *
     * @param authCourseVideoSaveBo
     * @author wuyun
     */
    public Result<Integer> save(AuthCourseVideoSaveBO authCourseVideoSaveBO) {
        if (authCourseVideoSaveBO.getChapterId().equals(Long.valueOf(0))) {
            return Result.error("章节ID不能为空");
        }
        if (authCourseVideoSaveBO.getUserNo() == null) {
            return Result.error("useNo不能为空");
        }
        // 根据章节编号查找章节信息
        CourseChapterAudit chapterInfoAudit = chapterAuditDao.getById(authCourseVideoSaveBO.getChapterId());
        if (ObjectUtil.isNull(chapterInfoAudit)) {
            return Result.error("找不到章节信息");
        }
        CourseAudit courseAudit = courseAuditDao.getById(chapterInfoAudit.getCourseId());
        if (ObjectUtil.isNull(courseAudit)) {
            return Result.error("找不到课程信息");
        }
        if (!authCourseVideoSaveBO.getUserNo().equals(courseAudit.getLecturerUserNo())) {
            return Result.error("传入的useNo与该课程的讲师useNo不一致");
        }
        // 查找视频信息
        CourseVideo courseVideo = dao.getByVideoNo(authCourseVideoSaveBO.getVideoNo());
        if (ObjectUtil.isNull(courseVideo)) {
            return Result.error("找不到视频信息");
        }
        courseVideo.setCourseId(chapterInfoAudit.getCourseId());
        courseVideo.setChapterId(chapterInfoAudit.getId());
        courseVideo.setStatusId(StatusIdEnum.YES.getCode());
        int result = dao.updateById(courseVideo);
        if (result > 0) {
            return Result.success(result);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 章节视频库列出
     *
     * @param chapterId
     * @author wuyun
     */
    public Result<AuthCourseVideoListDTO> listByChapterId(AuthCourseVideoBO authCourseVideoBO) {
        // 查找该章节下可用的视频信息集合
        List<CourseVideo> courseVideoList = dao.listByChapterIdAndPeriodIdAndStatusId(authCourseVideoBO.getChapterId(), Long.valueOf(0), StatusIdEnum.YES.getCode());
        AuthCourseVideoListDTO dto = new AuthCourseVideoListDTO();
        if (CollectionUtil.isNotEmpty(courseVideoList)) {
            List<AuthCourseVideoForListDTO> dtoList = ArrayListUtil.copy(courseVideoList, AuthCourseVideoForListDTO.class);
            dto.setList(dtoList);
        }
        return Result.success(dto);
    }

    /**
     * 课时视频列出
     *
     * @param periodId
     * @author wuyun
     */
    public Result<AuthCourseVideoListDTO> listByPeriodId(AuthPeriodIdVideoBO authCourseVideoBO) {
        List<CourseVideo> courseVideoList = dao.listByPeriodIdAndStatusId(authCourseVideoBO.getPeriodId(), StatusIdEnum.YES.getCode());
        AuthCourseVideoListDTO dto = new AuthCourseVideoListDTO();
        if (CollectionUtil.isNotEmpty(courseVideoList)) {
            List<AuthCourseVideoForListDTO> dtoList = ArrayListUtil.copy(courseVideoList, AuthCourseVideoForListDTO.class);
            dto.setList(dtoList);
        }
        return Result.success(dto);
    }

    /**
     * 课时视频更新接口
     *
     * @param authCourseVideoUpdateBo
     * @author wuyun
     */
    @Transactional
    public Result<Integer> update(AuthCourseVideoUpdateBO authCourseVideoUpdateBO) {
        if (authCourseVideoUpdateBO.getPeriodId() == null) {
            return Result.error("periodId不能为空");
        }
        if (authCourseVideoUpdateBO.getUserNo() == null) {
            return Result.error("useNo不能为空");
        }
        CourseChapterPeriodAudit courseChapterPeriodAudit = periodAuditDao.getById(authCourseVideoUpdateBO.getPeriodId());
        if (ObjectUtil.isNull(courseChapterPeriodAudit)) {
            return Result.error("找不到课时信息");
        }
        CourseAudit course = courseAuditDao.getById(courseChapterPeriodAudit.getCourseId());
        if (ObjectUtil.isNull(course)) {
            return Result.error("找不到课程信息");
        }
        if (!authCourseVideoUpdateBO.getUserNo().equals(course.getLecturerUserNo())) {
            return Result.error("传入的useNo与该课程的讲师useNo不一致");
        }

        // 查找该课时下的所有视频信息集合更改为冻结状态
        List<CourseVideo> periodVideoInfoAuditList = dao.listByPeriodIdAndStatusId(authCourseVideoUpdateBO.getPeriodId(), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(periodVideoInfoAuditList)) {
            for (CourseVideo periodVideoInfoAudit : periodVideoInfoAuditList) {
                CourseVideo audit = new CourseVideo();
                audit.setId(periodVideoInfoAudit.getId());
                audit.setStatusId(FREEZE);
                dao.updateById(audit);
            }
        }

        // 如果视频编号不为空
        if (authCourseVideoUpdateBO.getVideoNo() != null) {
            // 根据视频编号课时编号查找课时视频信息
            CourseVideo courseVideo = dao.getByVideoNoAndPeriodId(authCourseVideoUpdateBO.getVideoNo(), authCourseVideoUpdateBO.getPeriodId());
            if (ObjectUtil.isNotNull(courseVideo)) {
                // 如果存在则更新为可用状态
                courseVideo.setStatusId(StatusIdEnum.YES.getCode());
                dao.updateById(courseVideo);
                // 更新课程、章节、课时
                updateCourseChapterPeriod(courseChapterPeriodAudit, courseVideo);

            } else {
                // 根据视频编号查找可用的课时视频信息
                CourseVideo infoAudit = dao.getByVideoNoAndStatusId(authCourseVideoUpdateBO.getVideoNo(), StatusIdEnum.YES.getCode());
                CourseVideo audit = BeanUtil.copyProperties(infoAudit, CourseVideo.class);
                audit.setPeriodId(authCourseVideoUpdateBO.getPeriodId());
                dao.save(audit);
                // 更新课程、章节、课时
                updateCourseChapterPeriod(courseChapterPeriodAudit, audit);

            }
            return Result.success(1);
        } else {
            return Result.success(1);
        }
    }

    // 更新课程、章节、课时
    private void updateCourseChapterPeriod(CourseChapterPeriodAudit courseChapterPeriodAudit, CourseVideo courseVideo) {
        Long videoNo = courseVideo.getVideoNo();
        String videoName = courseVideo.getVideoName();
        String videoLength = courseVideo.getVideoLength();
        String videovid = courseVideo.getVideoVid();

        // 更新课时审核信息为待审核状态
        courseChapterPeriodAudit.setIsVideo(IsVideoEnum.YES.getCode());
        courseChapterPeriodAudit.setVideoNo(videoNo);
        courseChapterPeriodAudit.setVideoName(videoName);
        courseChapterPeriodAudit.setVideoLength(videoLength);
        courseChapterPeriodAudit.setVideoVid(videovid);
        courseChapterPeriodAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        periodAuditDao.updateById(courseChapterPeriodAudit);
        // 更新章节审核信息为待审核状态
        CourseChapterAudit chapterAudit = new CourseChapterAudit();
        chapterAudit.setId(courseChapterPeriodAudit.getChapterId());
        chapterAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        chapterAuditDao.updateById(chapterAudit);
        // 更新课程审核信息为待审核状态
        CourseAudit courseAudit = new CourseAudit();
        courseAudit.setId(courseChapterPeriodAudit.getCourseId());
        courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
        courseAuditDao.updateById(courseAudit);
    }

    /**
     * 视频删除
     *
     * @param bo
     * @author wuyun
     */
    @Transactional
    public Result<Integer> udpateById(AuthCourseVideoDeleteBO authCourseVideoDeleteBO) {
        if (authCourseVideoDeleteBO.getId() == null) {
            return Result.error("id不能为空");
        }
        if (authCourseVideoDeleteBO.getUserNo() == null) {
            return Result.error("lecturerUserNo不能为空");
        }
        CourseVideo courseVideo = dao.getById(authCourseVideoDeleteBO.getId());
        if (ObjectUtil.isNull(courseVideo)) {
            return Result.error("找不到该视频");
        }
        CourseAudit course = courseAuditDao.getById(courseVideo.getCourseId());
        if (ObjectUtil.isNull(course)) {
            return Result.error("找不到课程信息");
        }
        if (!authCourseVideoDeleteBO.getUserNo().equals(course.getLecturerUserNo())) {
            return Result.error("传入的useNo与该课程的讲师useNo不一致");
        }
        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("未找到系统配置信息");
        }
        if (StringUtils.isEmpty(sys.getPolyvUseid()) || StringUtils.isEmpty(sys.getPolyvSecretkey())) {
            return Result.error("useid或secretkey未配置");
        }
        // 查找该视频是否被使用
        List<CourseVideo> courseVideoList = dao.listByVideoNoAndNotEqualPeriodIdAndStatusId(courseVideo.getVideoNo(), Long.valueOf(0), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(courseVideoList) && courseVideoList.size() >= 2) {
            return Result.error("该视频已被使用，请先删除");
        }
        // 更改为冻结状态
        courseVideo.setStatusId(FREEZE);
        int result = dao.updateById(courseVideo);
        if (result > 0) {
            // 删除保利威视的视频
            PolyvUtil.deleteFile(courseVideo.getVideoVid(), sys.getPolyvUseid(), sys.getPolyvSecretkey());
            if (!courseVideo.getPeriodId().equals(Long.valueOf(0))) {
                CourseChapterPeriodAudit periodInfoAudit = periodAuditDao.getById(courseVideo.getPeriodId());
                periodInfoAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
                periodAuditDao.updateById(periodInfoAudit);
            }
            CourseChapterAudit chapterInfo = chapterAuditDao.getById(courseVideo.getChapterId());
            chapterInfo.setAuditStatus(AuditStatusEnum.WAIT.getCode());
            chapterAuditDao.updateById(chapterInfo);

            CourseAudit courseAudit = new CourseAudit();
            courseAudit.setId(chapterInfo.getCourseId());
            courseAudit.setAuditStatus(AuditStatusEnum.WAIT.getCode());
            courseAuditDao.updateById(courseAudit);
            return Result.success(result);
        }

        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

}
