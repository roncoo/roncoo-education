package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignNavBar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.NavBarQO;
import com.roncoo.education.system.feign.vo.NavBarVO;
import com.roncoo.education.system.service.feign.biz.FeignNavBarBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 头部导航
 *
 * @author wuyun
 */
@RestController
public class FeignNavBarController extends BaseController implements IFeignNavBar {

    @Autowired
    private FeignNavBarBiz biz;

    @Override
    public Page<NavBarVO> listForPage(@RequestBody NavBarQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody NavBarQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody NavBarQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public NavBarVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
