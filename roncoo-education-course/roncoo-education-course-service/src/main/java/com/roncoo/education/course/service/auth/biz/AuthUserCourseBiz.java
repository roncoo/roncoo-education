package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserCourseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程用户关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthUserCourseBiz extends BaseBiz {

    @NotNull
    private final UserCourseDao dao;

}
