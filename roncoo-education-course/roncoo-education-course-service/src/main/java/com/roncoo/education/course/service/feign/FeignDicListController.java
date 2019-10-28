package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignDicList;
import com.roncoo.education.course.feign.qo.DicListQO;
import com.roncoo.education.course.feign.vo.DicListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignDicListBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典明细表
 *
 * @author wujing
 */
@RestController
public class FeignDicListController extends BaseController implements IFeignDicList {

    @Autowired
    private FeignDicListBiz biz;

    @Override
    public Page<DicListVO> listForPage(@RequestBody DicListQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody DicListQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody DicListQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public DicListVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
