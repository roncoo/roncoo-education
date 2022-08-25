package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgTemplateDao;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplateExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplateEditQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplatePageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgTemplateSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgTemplatePageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgTemplateViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 消息模板
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignMsgTemplateBiz extends BaseBiz {

    @NotNull
    private final MsgTemplateDao dao;

    public Page<MsgTemplatePageVO> page(MsgTemplatePageQO qo) {
        MsgTemplateExample example = new MsgTemplateExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<MsgTemplate> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, MsgTemplatePageVO.class);
    }

    public int save(MsgTemplateSaveQO qo) {
        MsgTemplate record = BeanUtil.copyProperties(qo, MsgTemplate.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(MsgTemplateEditQO qo) {
        MsgTemplate record = BeanUtil.copyProperties(qo, MsgTemplate.class);
        return dao.updateById(record);
    }

    public MsgTemplateViewVO getById(Long id) {
        MsgTemplate record = dao.getById(id);
        return BeanUtil.copyProperties(record, MsgTemplateViewVO.class);
    }
}
