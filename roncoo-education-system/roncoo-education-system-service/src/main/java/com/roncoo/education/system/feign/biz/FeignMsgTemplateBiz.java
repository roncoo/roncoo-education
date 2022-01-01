package com.roncoo.education.system.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.MsgTemplateDao;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.system.feign.interfaces.qo.MsgTemplateQO;
import com.roncoo.education.system.feign.interfaces.vo.MsgTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息模板
 *
 * @author wuyun
 */
@Component
public class FeignMsgTemplateBiz {

    @Autowired
    private MsgTemplateDao dao;

    public Page<MsgTemplateVO> listForPage(MsgTemplateQO qo) {
        MsgTemplateExample example = new MsgTemplateExample();
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<MsgTemplate> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, MsgTemplateVO.class);
    }

    public int save(MsgTemplateQO qo) {
        MsgTemplate record = BeanUtil.copyProperties(qo, MsgTemplate.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public MsgTemplateVO getById(Long id) {
        MsgTemplate record = dao.getById(id);
        return BeanUtil.copyProperties(record, MsgTemplateVO.class);
    }

    public int updateById(MsgTemplateQO qo) {
        MsgTemplate record = BeanUtil.copyProperties(qo, MsgTemplate.class);
        return dao.updateById(record);
    }

}
