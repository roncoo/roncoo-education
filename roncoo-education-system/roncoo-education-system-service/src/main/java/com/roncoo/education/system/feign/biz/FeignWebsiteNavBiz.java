package com.roncoo.education.system.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteNavDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavPageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavPageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 头部导航
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignWebsiteNavBiz extends BaseBiz {

    @NotNull
    private final WebsiteNavDao dao;

    public Page
            <WebsiteNavPageVO> page(WebsiteNavPageQO qo) {
        WebsiteNavExample example = new WebsiteNavExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<WebsiteNav> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteNavPageVO.class);
    }

    public int save(WebsiteNavSaveQO qo) {
        WebsiteNav record = BeanUtil.copyProperties(qo, WebsiteNav.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(WebsiteNavEditQO qo) {
        WebsiteNav record = BeanUtil.copyProperties(qo, WebsiteNav.class);
        return dao.updateById(record);
    }

    public WebsiteNavViewVO getById(Long id) {
        WebsiteNav record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteNavViewVO.class);
    }
}
