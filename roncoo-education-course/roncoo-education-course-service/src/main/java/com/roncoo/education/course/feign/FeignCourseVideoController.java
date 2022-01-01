package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseVideoBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseVideo;
import com.roncoo.education.course.feign.interfaces.qo.CourseVideoQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@RestController
public class FeignCourseVideoController extends BaseController implements IFeignCourseVideo {

    @Autowired
    private FeignCourseVideoBiz biz;

    @Override
    public Page<CourseVideoVO> listForPage(@RequestBody CourseVideoQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseVideoQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseVideoQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseVideoVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

    @Override
    public void handleScheduledTasks(@RequestBody File targetFile) {
        biz.handleScheduledTasks(targetFile);
    }

}
