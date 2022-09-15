package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.polyv.PolyvVodUtil;
import com.roncoo.education.common.polyv.callback.CallbackVodAuthCode;
import com.roncoo.education.common.polyv.vod.PolyvSign;
import com.roncoo.education.common.polyv.vod.PolyvSignResponse;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.*;
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
    private final CourseDao dao;
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
            userStudy.setCourseId(period.getCourseId());
            userStudy.setUserId(ThreadContext.userId());
            userStudy.setProgress(BigDecimal.ZERO);
            userStudyDao.save(userStudy);
        }

        AuthCourseSignResp resp = new AuthCourseSignResp();
        resp.setProgress(userStudy.getProgress());
        resp.setStudyId(userStudy.getId());
        resp.setVid(resource.getVideoVid());
        // 播放参数
        polyvSign(req, resource, resp);
        return Result.success(resp);
    }

    private void polyvSign(AuthCourseSignReq req, Resource resource, AuthCourseSignResp resp) {
        PolyvSign polyvSign = new PolyvSign();
        polyvSign.setIp(req.getClientIp());
        polyvSign.setUserNo(ThreadContext.userId());
        polyvSign.setVid(resource.getVideoVid());
        VodConfig vodConfig = feignSysConfig.getVod();

        PolyvSignResponse polyvSignResponse = PolyvVodUtil.getSignForH5(polyvSign, vodConfig.getPolyvUserId(), vodConfig.getPolyvSecretKey());
        if (ObjectUtil.isEmpty(polyvSignResponse)) {
            throw new BaseException("系统繁忙，请重试");
        }
        resp.setTs(polyvSignResponse.getTs());
        resp.setSign(polyvSignResponse.getSign());
        resp.setToken(polyvSignResponse.getToken());

        // 获取code
        CallbackVodAuthCode authCode = new CallbackVodAuthCode();
        authCode.setPeriodId(req.getPeriodId());
        authCode.setShowText("领课开源");
        authCode.setUserId(ThreadContext.userId());
        resp.setCode(PolyvVodUtil.getPolyvCode(authCode));
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
