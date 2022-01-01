package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.config.SystemUtil;
import com.roncoo.education.common.core.enums.IsFreeEnum;
import com.roncoo.education.common.core.enums.IsPayEnum;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.polyv.PolyvCode;
import com.roncoo.education.common.core.polyv.PolyvSign;
import com.roncoo.education.common.core.polyv.PolyvSignResult;
import com.roncoo.education.common.core.polyv.PolyvUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.*;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.api.dto.CourseChapterDTO;
import com.roncoo.education.course.service.api.dto.CourseChapterPeriodDTO;
import com.roncoo.education.course.service.api.dto.CourseIntroduceDTO;
import com.roncoo.education.course.service.auth.bo.AuthCourseSignBO;
import com.roncoo.education.course.service.auth.bo.AuthCourseViewBO;
import com.roncoo.education.course.service.auth.dto.AuthCourseSignDTO;
import com.roncoo.education.course.service.auth.dto.AuthCourseViewDTO;
import com.roncoo.education.course.service.auth.dto.AuthLecturerDTO;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class AuthApiCourseBiz extends BaseBiz {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseChapterDao courseChapterDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;
    @Autowired
    private CourseIntroduceDao courseIntroduceDao;
    @Autowired
    private CourseUserStudyDao courseUserStudyDao;
    @Autowired
    private CourseUserStudyLogDao courseUserStudyLogDao;
    @Autowired
    private IFeignLecturer bossLecturer;
    @Autowired
    private OrderInfoDao orderInfoDao;

    @Autowired
    private IFeignSys bossSys;

    public Result<AuthCourseSignDTO> sign(AuthCourseSignBO authCourseSignBO) {
        if (ObjectUtil.isNull(authCourseSignBO.getUserNo())) {
            return Result.error("userNo不能为空");
        }
        if (ObjectUtil.isNull(authCourseSignBO.getPeriodId())) {
            return Result.error("periodId不能为空");
        }
        if (StringUtils.isEmpty(authCourseSignBO.getVideoVid())) {
            return Result.error("videoVid不能为空");
        }

        // 课时信息
        CourseChapterPeriod courseChapterPeriod = courseChapterPeriodDao.getById(authCourseSignBO.getPeriodId());
        if (StringUtils.isEmpty(courseChapterPeriod)) {
            return Result.error("找不到课时信息");
        }
        // 章节信息
        CourseChapter courseChapter = courseChapterDao.getById(courseChapterPeriod.getChapterId());
        if (ObjectUtil.isNull(courseChapter)) {
            return Result.error("找不到章节信息");
        }
        // 课程信息
        Course course = courseDao.getById(courseChapterPeriod.getCourseId());
        if (StringUtils.isEmpty(course)) {
            return Result.error("找不到课程信息");
        }

        // 免费：课时免费，章节免费，课程免费
        if (IsFreeEnum.FREE.getCode().equals(courseChapterPeriod.getIsFree()) || IsFreeEnum.FREE.getCode().equals(courseChapter.getIsFree()) || IsFreeEnum.FREE.getCode().equals(course.getIsFree())) {
            AuthCourseSignDTO authCourseSignDTO = getSgin(authCourseSignBO);
            callbackExecutor.execute(new StudyLog(authCourseSignBO, courseChapterPeriod, course, courseChapter));
            return Result.success(authCourseSignDTO);
        }

        // 收费：订单是否存在并且判断订单是否支付成功
        OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(authCourseSignBO.getUserNo(), courseChapterPeriod.getCourseId());
        if (orderInfo == null || !OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
            return Result.error("收费课程，请先购买");
        }

        // 成功
        AuthCourseSignDTO authCourseSignDTO = getSgin(authCourseSignBO);
        callbackExecutor.execute(new StudyLog(authCourseSignBO, courseChapterPeriod, course, courseChapter));
        return Result.success(authCourseSignDTO);
    }

    public Result<AuthCourseViewDTO> view(AuthCourseViewBO authCourseViewBO) {
        if (StringUtils.isEmpty(authCourseViewBO.getCourseId())) {
            return Result.error("courseId不能为空");
        }
        if (StringUtils.isEmpty(authCourseViewBO.getUserNo())) {
            return Result.error("userNo不能为空");
        }

        // 查询课程信息
        Course course = courseDao.getById(authCourseViewBO.getCourseId());
        if (course == null) {
            return Result.error("找不到该课程");
        }
        AuthCourseViewDTO dto = BeanUtil.copyProperties(course, AuthCourseViewDTO.class);

        // 查询课程介绍
        CourseIntroduce courseIntroduce = courseIntroduceDao.getById(course.getIntroduceId());
        dto.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());

        // 先假设为收费课程且用户未付款
        dto.setIsPay(IsPayEnum.NO.getCode());
        // 查询订单号，查看用户是否购买了课程，是否存在订单号
        OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(authCourseViewBO.getUserNo(), authCourseViewBO.getCourseId());
        if (ObjectUtil.isNull(orderInfo)) {
            // 未购买或者没支付情况
            dto.setIsPay(IsPayEnum.NO.getCode());
        } else if (OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
            // 订单状态为已支付
            dto.setIsPay(IsPayEnum.YES.getCode());
        }
        // 如果课程为免费课程则设置为已付费
        if (IsFreeEnum.FREE.getCode().equals(course.getIsFree())) {
            dto.setIsPay(IsPayEnum.YES.getCode());
        }

        // 此处用于测试支付课程，无论怎样都是未购买
        if (SystemUtil.TEST_COURSE.equals(course.getId().toString())) {
            dto.setIsPay(IsPayEnum.NO.getCode());
        }

        // 查询讲师信息
        LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(dto.getLecturerUserNo());
        dto.setLecturer(BeanUtil.copyProperties(lecturerVO, AuthLecturerDTO.class));

        // 查询章节信息
        List<CourseChapter> courseChapterList = courseChapterDao.listByCourseIdAndStatusId(authCourseViewBO.getCourseId(), StatusIdEnum.YES.getCode());
        // 如果为空就直接返回
        if (courseChapterList.isEmpty()) {
            return Result.success(dto);
        }
        dto.setChapterList(PageUtil.copyList(courseChapterList, CourseChapterDTO.class));

        // 课时信息
        for (CourseChapterDTO courseChapterDTO : dto.getChapterList()) {
            List<CourseChapterPeriod> courseChapterPeriodList = courseChapterPeriodDao.listByChapterIdAndStatusId(courseChapterDTO.getId(), StatusIdEnum.YES.getCode());
            courseChapterDTO.setPeriodList(PageUtil.copyList(courseChapterPeriodList, CourseChapterPeriodDTO.class));
        }

        return Result.success(dto);
    }

    /**
     * 获取播放sign值
     *
     * @param bo
     * @return
     */
    private AuthCourseSignDTO getSgin(AuthCourseSignBO authCourseSignBO) {
        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            try {
                throw new Exception("找不到系统配置信息");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (StringUtils.isEmpty(sys.getPolyvUseid()) || StringUtils.isEmpty(sys.getPolyvSecretkey())) {
            try {
                throw new Exception("useid或secretkey未配置");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        PolyvSign polyvSign = new PolyvSign();
        polyvSign.setIp(authCourseSignBO.getIp());
        polyvSign.setUserNo(authCourseSignBO.getUserNo());
        polyvSign.setVid(authCourseSignBO.getVideoVid());
        PolyvSignResult signResult = PolyvUtil.getSignForH5(polyvSign, sys.getPolyvUseid(), sys.getPolyvSecretkey());
        AuthCourseSignDTO dto = BeanUtil.copyProperties(signResult, AuthCourseSignDTO.class);
        PolyvCode polyvCode = new PolyvCode();
        polyvCode.setPeriodNo(authCourseSignBO.getPeriodId());
        polyvCode.setUserNo(authCourseSignBO.getUserNo());
        dto.setCode(PolyvUtil.getPolyvCode(polyvCode));
        return dto;
    }

    class StudyLog implements Runnable {
        private AuthCourseSignBO authCourseSignBO;
        private CourseChapterPeriod courseChapterPeriod;
        private Course course;
        private CourseChapter courseChapter;

        public StudyLog(AuthCourseSignBO authCourseSignBO, CourseChapterPeriod courseChapterPeriod, Course course, CourseChapter courseChapter) {
            this.authCourseSignBO = authCourseSignBO;
            this.courseChapterPeriod = courseChapterPeriod;
            this.courseChapter = courseChapter;
            this.course = course;
        }

        @Override
        public void run() {
            // 更新学习人数
            updateCount(course);

            // 学习日志与统计
            studyCount(authCourseSignBO, courseChapterPeriod, course, courseChapter);
        }

        /**
         * 更新学习人数
         */
        private Integer updateCount(Course course) {
            Course record = new Course();
            record.setId(course.getId());
            record.setCountStudy(course.getCountStudy() + 1);
            return courseDao.updateById(record);
        }

        private void studyCount(AuthCourseSignBO authCourseSignBO, CourseChapterPeriod courseChapterPeriod, Course course, CourseChapter courseChapter) {
            // 查找课程用户关联表
            CourseUserStudy courseUserStudy = courseUserStudyDao.getByUserNoAndCourseId(authCourseSignBO.getUserNo(), courseChapterPeriod.getCourseId());
            // 如果不存在记录
            if (null == courseUserStudy) {
                courseUserStudy = new CourseUserStudy();
                courseUserStudy.setCourseId(course.getId());
                courseUserStudy.setUserNo(authCourseSignBO.getUserNo());
                courseUserStudy.setPeriodTotal(course.getPeriodTotal());
                courseUserStudy.setPeriodStudy(1);
                courseUserStudyDao.save(courseUserStudy);

                CourseUserStudyLog courseUserStudyLog = new CourseUserStudyLog();
                courseUserStudyLog.setPeriodId(courseChapterPeriod.getId());
                courseUserStudyLog.setPeriodName(courseChapterPeriod.getPeriodName());
                courseUserStudyLog.setChapterId(courseChapterPeriod.getChapterId());
                courseUserStudyLog.setChapterName(courseChapter.getChapterName());
                courseUserStudyLog.setCourseId(courseChapterPeriod.getCourseId());
                courseUserStudyLog.setCourseName(course.getCourseName());
                courseUserStudyLog.setUserNo(authCourseSignBO.getUserNo());
                courseUserStudyLogDao.save(courseUserStudyLog);
            } else {
                CourseUserStudyLog courseUserStudyLog = courseUserStudyLogDao.getByUserNoAndPeriodId(authCourseSignBO.getUserNo(), courseChapterPeriod.getId());
                if (courseUserStudyLog == null) {
                    // 记录
                    courseUserStudyLog = new CourseUserStudyLog();
                    courseUserStudyLog.setPeriodId(courseChapterPeriod.getId());
                    courseUserStudyLog.setPeriodName(courseChapterPeriod.getPeriodName());
                    courseUserStudyLog.setChapterId(courseChapterPeriod.getChapterId());
                    courseUserStudyLog.setChapterName(courseChapter.getChapterName());
                    courseUserStudyLog.setCourseId(courseChapterPeriod.getCourseId());
                    courseUserStudyLog.setCourseName(course.getCourseName());
                    courseUserStudyLog.setUserNo(authCourseSignBO.getUserNo());
                    courseUserStudyLogDao.save(courseUserStudyLog);

                    courseUserStudy.setPeriodTotal(course.getPeriodTotal());
                    courseUserStudy.setPeriodStudy(courseUserStudy.getPeriodStudy() + 1);
                    courseUserStudyDao.updateById(courseUserStudy);
                } else {
                    courseUserStudy.setPeriodTotal(course.getPeriodTotal());
                    courseUserStudy.setPeriodStudy(courseUserStudy.getPeriodStudy() + 1);
                    courseUserStudyDao.updateById(courseUserStudy);
                }
            }
        }
    }

}
