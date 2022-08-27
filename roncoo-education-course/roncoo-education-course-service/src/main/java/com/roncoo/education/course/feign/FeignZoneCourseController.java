package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignZoneCourseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignZoneCourse;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseEditQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 专区课程关联表
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/zone/course")
public class FeignZoneCourseController extends BaseController implements IFeignZoneCourse {

    @NotNull
    private final FeignZoneCourseBiz biz;

    @Override
    public Page<ZoneCoursePageVO> page(@RequestBody ZoneCoursePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody ZoneCourseSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ZoneCourseEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public ZoneCourseViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
