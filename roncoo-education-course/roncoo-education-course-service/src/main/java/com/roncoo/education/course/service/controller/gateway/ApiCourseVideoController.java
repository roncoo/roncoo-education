package com.roncoo.education.course.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.interfaces.gateway.ApiCourseVideo;
import com.roncoo.education.course.service.biz.gateway.ApiCourseVideoBiz;
import com.roncoo.education.util.base.BaseController;

/**
 * 课程视频信息 
 *
 * @author wuyun
 */
@RestController
public class ApiCourseVideoController extends BaseController implements ApiCourseVideo{

    @Autowired
    private ApiCourseVideoBiz biz;
    
}
