package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteDao;
import com.roncoo.education.system.dao.impl.mapper.entity.Website;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteExample;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Component
public class FeignWebsiteBiz {

    @Autowired
    private WebsiteDao dao;

    public Page<WebsiteVO> listForPage(WebsiteQO qo) {
        WebsiteExample example = new WebsiteExample();
        example.setOrderByClause(" id desc ");
        Page<Website> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteVO.class);
    }

    public int save(WebsiteQO qo) {
        Website record = BeanUtil.copyProperties(qo, Website.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public WebsiteVO getById(Long id) {
        Website record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteVO.class);
    }

    public int updateById(WebsiteQO qo) {
        Website record = BeanUtil.copyProperties(qo, Website.class);
        return dao.updateById(record);
    }

    public WebsiteVO getWebsite() {
        Website website = dao.getWebsite();
        return BeanUtil.copyProperties(website, WebsiteVO.class);
    }

}
