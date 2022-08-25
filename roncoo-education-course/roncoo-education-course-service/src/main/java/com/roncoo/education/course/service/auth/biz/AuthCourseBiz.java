package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

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

}
