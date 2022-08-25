package com.roncoo.education.system.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAdvDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdv;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdvExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdvExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvEditQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvPageQO;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteAdvSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteAdvPageVO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteAdvViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 广告信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignWebsiteAdvBiz extends BaseBiz {

    @NotNull
    private final WebsiteAdvDao dao;

    public Page
            <WebsiteAdvPageVO> page(WebsiteAdvPageQO qo) {
        WebsiteAdvExample example = new WebsiteAdvExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<WebsiteAdv> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, WebsiteAdvPageVO.class);
    }

    public int save(WebsiteAdvSaveQO qo) {
        WebsiteAdv record = BeanUtil.copyProperties(qo, WebsiteAdv.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(WebsiteAdvEditQO qo) {
        WebsiteAdv record = BeanUtil.copyProperties(qo, WebsiteAdv.class);
        return dao.updateById(record);
    }

    public WebsiteAdvViewVO getById(Long id) {
        WebsiteAdv record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteAdvViewVO.class);
    }
}
