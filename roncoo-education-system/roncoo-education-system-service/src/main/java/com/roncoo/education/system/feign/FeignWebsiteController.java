package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignWebsiteBiz;
import com.roncoo.education.system.feign.interfaces.IFeignWebsite;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
public class FeignWebsiteController extends BaseController implements IFeignWebsite {

    @Autowired
    private FeignWebsiteBiz biz;

    @Override
    public Page<WebsiteVO> listForPage(@RequestBody WebsiteQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody WebsiteQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody WebsiteQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public WebsiteVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public WebsiteVO getWebsite() {
        return biz.getWebsite();
    }

}
