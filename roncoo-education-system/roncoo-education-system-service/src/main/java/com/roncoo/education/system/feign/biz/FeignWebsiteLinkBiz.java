package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteLinkDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteLinkQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteLinkVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Component
public class FeignWebsiteLinkBiz {

    @Autowired
    private WebsiteLinkDao dao;

    public Page<WebsiteLinkVO> listForPage(WebsiteLinkQO qo) {
        WebsiteLinkExample example = new WebsiteLinkExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(qo.getLinkName())) {
            c.andLinkNameLike(PageUtil.rightLike(qo.getLinkName()));
        }
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<WebsiteLink> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteLinkVO.class);
    }

    public int save(WebsiteLinkQO qo) {
        WebsiteLink record = BeanUtil.copyProperties(qo, WebsiteLink.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public WebsiteLinkVO getById(Long id) {
        WebsiteLink record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteLinkVO.class);
    }

    public int updateById(WebsiteLinkQO qo) {
        WebsiteLink record = BeanUtil.copyProperties(qo, WebsiteLink.class);
        return dao.updateById(record);
    }

}
