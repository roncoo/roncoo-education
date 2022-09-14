package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.BuyTypeEnum;
import com.roncoo.education.common.core.enums.FreeEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.service.auth.req.AuthCourseSignReq;
import com.roncoo.education.course.service.auth.resp.AuthCourseSignResp;
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
    private final UserCourseDao userCourseDao;
    @NotNull
    private final UserStudyDao userStudyDao;

    public Result<AuthCourseSignResp> sign(AuthCourseSignReq req) {
        CourseChapterPeriod period = periodDao.getById(req.getPeriodId());
        if (ObjectUtil.isEmpty(period) || period.getStatusId().equals(StatusIdEnum.NO.getCode())) {
            return Result.error("该课时不存在或不可用");
        }

        if (!play(period)) {
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
        String sign = "";

        AuthCourseSignResp resp = new AuthCourseSignResp();
        resp.setProgress(userStudy.getProgress());
        resp.setStudyId(userStudy.getId());
        resp.setResourceId(period.getResourceId());
        resp.setSign(sign);
        return Result.success(resp);
    }

    private Boolean play(CourseChapterPeriod period) {
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
