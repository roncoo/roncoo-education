package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.upload.UploadFace;
import com.roncoo.education.common.video.LiveUtil;
import com.roncoo.education.common.video.VodUtil;
import com.roncoo.education.common.video.req.LiveWatchReq;
import com.roncoo.education.common.video.req.VodPlayConfigReq;
import com.roncoo.education.course.dao.*;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.auth.req.AuthCourseSignReq;
import com.roncoo.education.course.service.auth.resp.AuthCourseSignResp;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.DocConfig;
import com.roncoo.education.system.feign.interfaces.vo.VideoConfig;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

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
    private final CourseChapterDao chapterDao;
    @NotNull
    private final CourseDao courseDao;
    @NotNull
    private final LiveDao liveDao;
    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final UserCourseDao userCourseDao;
    @NotNull
    private final UserStudyDao userStudyDao;
    @NotNull
    private final Map<String, UploadFace> uploadFaceMap;

    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final IFeignUsers feignUsers;

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

        // 课程购买校验
        if (!check(period)) {
            return Result.error("请购买该课程");
        }

        AuthCourseSignResp resp = new AuthCourseSignResp();
        resp.setPeriodId(req.getPeriodId());
        resp.setPeriodType(period.getPeriodType());

        // 直播类型
        if (period.getPeriodType().equals(PeriodTypeEnum.LIVE.getCode())) {
            Live live = liveDao.getById(period.getLiveId());
            if (ObjectUtil.isEmpty(live)) {
                return Result.error("该直播不存在");
            }
            if (live.getLiveStatus().equals(LiveStatusEnum.WAITING.getCode())) {
                return Result.error("该直播没开始，请稍后再试");
            }

            // 获取直播观看地址
            viewConfig(req.getIsPc(), live.getChannelId(), resp);
            return Result.success(resp);
        }

        // 资源类型
        Resource resource = resourceDao.getById(period.getResourceId());
        if (ObjectUtil.isEmpty(resource)) {
            return Result.error("该资源不存在");
        }
        if ((resource.getResourceType().equals(ResourceTypeEnum.VIDEO.getCode()) || resource.getResourceType().equals(ResourceTypeEnum.AUDIO.getCode())) && !resource.getVideoStatus().equals(VideoStatusEnum.SUCCES.getCode())) {
            return Result.error("资源处理中，暂不能学习");
        }

        // 资源学习记录
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

        resp.setResourceId(resource.getId());
        resp.setResourceType(resource.getResourceType());
        resp.setVid(resource.getVideoVid());
        resp.setVodPlatform(resource.getVodPlatform());
        resp.setStoragePlatform(resource.getStoragePlatform());
        resp.setStudyId(userStudy.getId());
        resp.setCurrentDuration(userStudy.getCurrentDuration());
        resp.setCurrentPage(userStudy.getCurrentPage());

        if (ResourceTypeEnum.VIDEO.getCode().equals(resource.getResourceType()) || ResourceTypeEnum.AUDIO.getCode().equals(resource.getResourceType())) {
            // 音视频
            playConfig(req, resp);
        } else if (ResourceTypeEnum.DOC.getCode().equals(resource.getResourceType())) {
            // 文档
            docConfig(resource, resp);
        } else if (ResourceTypeEnum.PIC.getCode().equals(resource.getResourceType())) {
            // 图片
            picConfig(resource, resp);
        }
        return Result.success(resp);
    }

    private void viewConfig(Boolean isPc, String channelNo, AuthCourseSignResp resp) {
        LiveWatchReq liveWatchReq = new LiveWatchReq();
        liveWatchReq.setChannelId(channelNo);
        liveWatchReq.setAppWatch(!isPc);
        UsersVO usersVO = feignUsers.getById(ThreadContext.userId());
        liveWatchReq.setUserId(usersVO.getId());
        liveWatchReq.setUserName(usersVO.getNickname());
        liveWatchReq.setUserAvatar(usersVO.getUserHead());

        // 视频云配置
        VideoConfig videoConfig = feignSysConfig.getVideo();
        videoConfig.setVodPlatform(resp.getVodPlatform());
        resp.setLiveViewConfig(LiveUtil.getLiveWatchUrl(videoConfig, liveWatchReq));
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
        VideoConfig videoConfig = feignSysConfig.getVideo();
        videoConfig.setVodPlatform(resp.getVodPlatform());
        resp.setVodPlayConfig(VodUtil.getPlayConfig(videoConfig, playConfigReq));
    }

    private void docConfig(Resource resource, AuthCourseSignResp resp) {
        DocConfig docConfig = feignSysConfig.getDoc();
        UploadFace uploadFace = uploadFaceMap.get(StoragePlatformEnum.byCode(resource.getStoragePlatform()).getMode());
        resp.setDocStudyConfig(uploadFace.getPreviewConfig(resource.getResourceUrl(), 300, docConfig));
    }

    private void picConfig(Resource resource, AuthCourseSignResp resp) {
        DocConfig docConfig = feignSysConfig.getDoc();
        UploadFace uploadFace = uploadFaceMap.get(StoragePlatformEnum.byCode(resource.getStoragePlatform()).getMode());
        resp.setPicStudyConfig(uploadFace.getPreviewConfig(resource.getResourceUrl(), 300, docConfig));
    }

    /**
     * @param period
     * @return true 可以观看，false 不能观看
     */
    private Boolean check(CourseChapterPeriod period) {
        UserCourse userCourse = userCourseDao.getByCourseIdAndUserId(period.getCourseId(), ThreadContext.userId());
        if (ObjectUtil.isNotEmpty(userCourse)) {
            // 已经购买
            return true;
        }
        Course course = courseDao.getById(period.getCourseId());
        if (course.getIsFree().equals(FreeEnum.FREE.getCode())) {
            // 课程免费
            userCourse = new UserCourse();
            userCourse.setUserId(ThreadContext.userId());
            userCourse.setCourseId(period.getCourseId());
            userCourse.setBuyType(BuyTypeEnum.FREE.getCode());
            userCourseDao.save(userCourse);
            return true;
        }
        CourseChapter courseChapter = chapterDao.getById(period.getChapterId());
        if (courseChapter.getIsFree().equals(FreeEnum.FREE.getCode())) {
            // 章节免费
            return true;
        }
        if (period.getIsFree().equals(FreeEnum.FREE.getCode())) {
            // 课时免费
            return true;
        }
        // 不能观看
        return false;
    }
}
