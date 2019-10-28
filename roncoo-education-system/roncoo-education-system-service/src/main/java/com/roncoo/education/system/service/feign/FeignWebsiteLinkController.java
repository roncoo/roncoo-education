package com.roncoo.education.system.service.feign;

import com.roncoo.education.system.feign.interfaces.IFeignWebsiteLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.system.feign.qo.WebsiteLinkQO;
import com.roncoo.education.system.feign.vo.WebsiteLinkVO;
import com.roncoo.education.system.service.feign.biz.FeignWebsiteLinkBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@RestController
public class FeignWebsiteLinkController extends BaseController implements IFeignWebsiteLink {

    @Autowired
    private FeignWebsiteLinkBiz biz;

    @Override
    public Page<WebsiteLinkVO> listForPage(@RequestBody WebsiteLinkQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody WebsiteLinkQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody WebsiteLinkQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public WebsiteLinkVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
