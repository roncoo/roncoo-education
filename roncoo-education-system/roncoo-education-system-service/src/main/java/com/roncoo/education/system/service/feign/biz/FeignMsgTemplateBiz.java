package com.roncoo.education.system.service.feign.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.feign.qo.MsgTemplateQO;
import com.roncoo.education.system.feign.vo.MsgTemplateVO;
import com.roncoo.education.system.service.dao.MsgTemplateDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

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
