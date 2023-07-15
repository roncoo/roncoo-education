package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.video.VodUtil;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.service.auth.req.AuthCourseSignReq;
import com.roncoo.education.course.service.auth.resp.AuthCourseSignResp;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.VodConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * AUTH-课程信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthCourseBiz extends BaseBiz {

    @NotNull
    private final CourseChapterPeriodDao periodDao;
    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final UserCourseDao userCourseDao;
    @NotNull
    private final UserStudyDao userStudyDao;

    @NotNull
    private final IFeignSysConfig feignSysConfig;

    public Result<AuthCourseSignResp> sign(AuthCourseSignReq req) {
        if (ObjectUtil.isNotEmpty(req.getCourseId()) && ObjectUtil.isEmpty(req.getPeriodId())) {
            // 若课程ID存在，则获取该课程的最新学习课时
            UserStudy userStudy = userStudyDao.getByCourseIdForLast(ThreadContext.userId(), req.getCourseId());
            if (ObjectUtil.isNotNull(userStudy)) {
                req.setPeriodId(userStudy.getPeriodId());
            } else {
                return Result.error("请选择要学习的课时");
            }
        }
        CourseChapterPeriod period = periodDao.getById(req.getPeriodId());
        if (ObjectUtil.isEmpty(period) || period.getStatusId().equals(StatusIdEnum.NO.getCode())) {
            return Result.error("该课时不存在或不可用");
        }
        if (ObjectUtil.isEmpty(period.getResourceId())) {
            return Result.error("该课时没设置资源");
        }
        Resource resource = resourceDao.getById(period.getResourceId());
        if (ObjectUtil.isEmpty(resource) || resource.getResourceType().equals(ResourceTypeEnum.DOC.getCode())) {
            return Result.error("资源类型不正确");
        }
        if (!resource.getVideoStatus().equals(VideoStatusEnum.SUCCES.getCode())) {
            return Result.error("资源暂不可用");
        }

        if (!check(period)) {
            return Result.error("没购买，不允许播放");
        }
        // 可以播放
        UserStudy userStudy = userStudyDao.getByPeriodIdAndUserId(req.getPeriodId(), ThreadContext.userId());
        if (ObjectUtil.isEmpty(userStudy)) {
            userStudy = new UserStudy();
            userStudy.setCourseId(period.getCourseId());
            userStudy.setChapterId(period.getChapterId());
            userStudy.setPeriodId(period.getId());
            userStudy.setUserId(ThreadContext.userId());
            userStudy.setResourceType(resource.getResourceType());
            userStudy.setCurrentDuration(0);
            userStudy.setCurrentPage(0);
            userStudy.setProgress(BigDecimal.ZERO);
            userStudyDao.save(userStudy);
        }

        AuthCourseSignResp resp = new AuthCourseSignResp();
        resp.setStartTime(userStudy.getProgress().multiply(new BigDecimal(resource.getVideoLength())).divide(BigDecimal.valueOf(100)).longValue());
        resp.setStudyId(userStudy.getId());
        resp.setPeriodId(req.getPeriodId());
        resp.setResourceId(resource.getId());
        resp.setVid(resource.getVideoVid());
        resp.setVodPlatform(resource.getVodPlatform());
        resp.setCurrentDuration(userStudy.getCurrentDuration());
        resp.setCurrentPage(userStudy.getCurrentPage());
        if (ResourceTypeEnum.VIDEO.getCode().equals(resource.getResourceType()) || ResourceTypeEnum.AUDIO.getCode().equals(resource.getResourceType())) {
            // 播放参数
            playConfig(req, resp);
        } else if (ResourceTypeEnum.DOC.getCode().equals(resource.getResourceType())) {
            docConfig(req, resp);
        }
        return Result.success(resp);
    }

    private void playConfig(AuthCourseSignReq req, AuthCourseSignResp resp) {
        VodPlayConfigReq playConfigReq = new VodPlayConfigReq();
        playConfigReq.setVid(resp.getVid());
        playConfigReq.setViewerId(ThreadContext.userId().toString());
        playConfigReq.setViewerIp(req.getClientIp());
        VodPlayConfigReq.VodAuthCode authCode = new VodPlayConfigReq.VodAuthCode();
        authCode.setUserId(ThreadContext.userId());
        authCode.setPeriodId(req.getPeriodId());
        playConfigReq.setVodAuthCode(authCode);

        // 视频云配置
        VodConfig vodConfig = feignSysConfig.getVod();
        vodConfig.setVodPlatform(resp.getVodPlatform());
        resp.setVodPlayConfig(VodUtil.getPlayConfig(vodConfig, playConfigReq));
    }

    private void docConfig(AuthCourseSignReq req, AuthCourseSignResp resp) {

    }

    private Boolean check(CourseChapterPeriod period) {
        UserCourse userCourse = userCourseDao.getByCourseIdAndUserId(period.getCourseId(), ThreadContext.userId());
        if (ObjectUtil.isEmpty(userCourse)) {
            if (period.getIsFree().equals(FreeEnum.FREE.getCode())) {
                // 免费课程不需要购买，直接可以观看
                userCourse = new UserCourse();
                userCourse.setUserId(ThreadContext.userId());
                userCourse.setCourseId(period.getCourseId());
                userCourse.setBuyType(BuyTypeEnum.FREE.getCode());
                userCourseDao.save(userCourse);
                return true;
            }
            return false;
        }
        return true;
    }
}
