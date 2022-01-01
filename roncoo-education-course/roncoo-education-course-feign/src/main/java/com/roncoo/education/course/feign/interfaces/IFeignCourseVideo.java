package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseVideoQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseVideoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseVideo {

    @RequestMapping(value = "/feign/course/courseVideo/listForPage")
    Page<CourseVideoVO> listForPage(@RequestBody CourseVideoQO qo);

    @RequestMapping(value = "/feign/course/courseVideo/save")
    int save(@RequestBody CourseVideoQO qo);

    @RequestMapping(value = "/feign/course/courseVideo/deleteById")
    int deleteById(@RequestBody Long id);

    @RequestMapping(value = "/feign/course/courseVideo/updateById")
    int updateById(@RequestBody CourseVideoQO qo);

    @RequestMapping(value = "/feign/course/courseVideo/getById")
    CourseVideoVO getById(@RequestBody Long id);

    /**
     * 定时任务-视频处理
     *
     * @param targetFile
     * @author wuyun
     */
    @RequestMapping(value = "/feign/course/courseVideo/gethandleScheduledTasksById")
    void handleScheduledTasks(@RequestBody File targetFile);

}
