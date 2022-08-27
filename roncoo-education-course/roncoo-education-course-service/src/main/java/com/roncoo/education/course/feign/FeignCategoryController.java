package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignCategoryBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCategory;
import com.roncoo.education.course.feign.interfaces.qo.CategoryEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CategoryPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CategorySaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CategoryPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CategoryViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 分类
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/category")
public class FeignCategoryController extends BaseController implements IFeignCategory {

    @NotNull
    private final FeignCategoryBiz biz;

    @Override
    public Page<CategoryPageVO> page(@RequestBody CategoryPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody CategorySaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CategoryEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CategoryViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
