package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignWebsiteLinkBiz;
import com.roncoo.education.system.feign.interfaces.IFeignWebsiteLink;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteLinkQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteLinkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
