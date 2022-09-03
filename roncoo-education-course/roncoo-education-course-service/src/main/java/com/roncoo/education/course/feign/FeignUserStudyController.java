package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignUserStudyBiz;
import com.roncoo.education.course.feign.interfaces.IFeignUserStudy;
import com.roncoo.education.course.feign.interfaces.qo.UserStudyEditQO;
import com.roncoo.education.course.feign.interfaces.qo.UserStudyPageQO;
import com.roncoo.education.course.feign.interfaces.qo.UserStudySaveQO;
import com.roncoo.education.course.feign.interfaces.vo.UserStudyPageVO;
import com.roncoo.education.course.feign.interfaces.vo.UserStudyViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 课程用户学习日志
 *
 * @author wujing
 * @date 2022-09-03
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/user/study")
public class FeignUserStudyController extends BaseController implements IFeignUserStudy {

    @NotNull
    private final FeignUserStudyBiz biz;

    @Override
    public Page<UserStudyPageVO> page(@RequestBody UserStudyPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody UserStudySaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody UserStudyEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public UserStudyViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
