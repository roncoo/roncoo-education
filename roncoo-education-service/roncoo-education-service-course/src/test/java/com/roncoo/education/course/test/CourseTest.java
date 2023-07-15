package com.roncoo.education.course.test;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.course.job.CourseJob;
import com.roncoo.education.course.service.api.biz.ApiCourseBiz;
import com.roncoo.education.course.service.api.req.ApiCoursePageReq;
import com.roncoo.education.course.service.biz.req.CourseReq;
import com.roncoo.education.course.service.biz.resp.CourseResp;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CourseTest extends BaseTest {

    @Autowired
    private ApiCourseBiz courseBiz;

    @Autowired
    private CourseJob courseJob;

    @Test
    public void view() {
        CourseReq req = new CourseReq();
        req.setCourseId(1080759557655564289L);
        Result<CourseResp> result = courseBiz.view(req);
        log.info("course={}", result.getData());
    }

    @Test
    public void es() {
        courseJob.course();
    }

    @Test
    public void search() {
        ApiCoursePageReq req = new ApiCoursePageReq();
        req.setCategoryId(1080756158205726721L);
        //req.setIsFree(1);
        System.out.println(JSUtil.toJsonString(courseBiz.searchForPage(req)));
    }
}
