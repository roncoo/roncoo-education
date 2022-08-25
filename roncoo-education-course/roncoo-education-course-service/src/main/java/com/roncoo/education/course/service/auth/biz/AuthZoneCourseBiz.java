package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ZoneCourseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-专区课程关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthZoneCourseBiz extends BaseBiz {

    @NotNull
    private final ZoneCourseDao dao;

}
