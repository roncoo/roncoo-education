package com.roncoo.education.system.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.feign.biz.FeignWebsiteNavArticleBiz;
import com.roncoo.education.system.feign.interfaces.IFeignWebsiteNavArticle;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavArticleQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavArticleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@RestController
public class FeignWebsiteNavArticleController extends BaseController implements IFeignWebsiteNavArticle {

    @Autowired
    private FeignWebsiteNavArticleBiz biz;

    @Override
    public Page<WebsiteNavArticleVO> listForPage(@RequestBody WebsiteNavArticleQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody WebsiteNavArticleQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@RequestBody Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody WebsiteNavArticleQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public WebsiteNavArticleVO getById(@RequestBody Long id) {
        return biz.getById(id);
    }

    @Override
    public WebsiteNavArticleVO getByNavId(@PathVariable(value = "navId") Long navId) {
        return biz.getByNavId(navId);
    }

}
