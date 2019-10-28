package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignDic;
import com.roncoo.education.course.feign.qo.DicQO;
import com.roncoo.education.course.feign.vo.DicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignDicBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 数据字典
 *
 * @author wujing
 */
@RestController
public class FeignDicController extends BaseController implements IFeignDic {

    @Autowired
    private FeignDicBiz biz;

    @Override
    public Page<DicVO> listForPage(@RequestBody DicQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody DicQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody DicQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public DicVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
