package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignAdv;
import com.roncoo.education.course.feign.qo.AdvQO;
import com.roncoo.education.course.feign.vo.AdvVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignAdvBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 广告信息
 *
 * @author wujing
 */
@RestController
public class FeignAdvController extends BaseController implements IFeignAdv {

    @Autowired
    private FeignAdvBiz biz;

    @Override
    public Page<AdvVO> listForPage(@RequestBody AdvQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody AdvQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody AdvQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public AdvVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
