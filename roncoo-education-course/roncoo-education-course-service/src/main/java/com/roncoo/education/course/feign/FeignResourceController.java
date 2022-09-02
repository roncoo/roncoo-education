package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignResourceBiz;
import com.roncoo.education.course.feign.interfaces.IFeignResource;
import com.roncoo.education.course.feign.interfaces.qo.ResourceEditQO;
import com.roncoo.education.course.feign.interfaces.qo.ResourcePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ResourceSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.ResourcePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ResourceViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 课程视频信息
 *
 * @author wujing
 * @date 2022-09-02
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/resource")
public class FeignResourceController extends BaseController implements IFeignResource {

    @NotNull
    private final FeignResourceBiz biz;

    @Override
    public Page<ResourcePageVO> page(@RequestBody ResourcePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody ResourceSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ResourceEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public ResourceViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
