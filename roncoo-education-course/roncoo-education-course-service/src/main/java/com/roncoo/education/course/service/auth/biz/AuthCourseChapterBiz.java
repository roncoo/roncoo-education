package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-章节信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthCourseChapterBiz extends BaseBiz {

    @NotNull
    private final CourseChapterDao dao;

}
