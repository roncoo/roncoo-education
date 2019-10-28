package com.roncoo.education.system.service.feign.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oas.utils.StringUtil;
import com.roncoo.education.system.feign.qo.MsgUserQO;
import com.roncoo.education.system.feign.vo.MsgUserVO;
import com.roncoo.education.system.service.dao.MsgUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@Component
public class FeignMsgUserBiz {

	@Autowired
	private MsgUserDao dao;

	public Page<MsgUserVO> listForPage(MsgUserQO qo) {
		MsgUserExample example = new MsgUserExample();
		Criteria c = example.createCriteria();
		if (StringUtil.isNotEmpty(qo.getMsgTitle())) {
			c.andMsgTitleLike(PageUtil.rightLike(qo.getMsgTitle()));
		}
		if (StringUtil.isNotEmpty(qo.getMobile())) {
			c.andMobileLike(PageUtil.rightLike(qo.getMobile()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<MsgUser> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		return PageUtil.transform(page, MsgUserVO.class);
	}

	public int save(MsgUserQO qo) {
		MsgUser record = BeanUtil.copyProperties(qo, MsgUser.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public MsgUserVO getById(Long id) {
		MsgUser record = dao.getById(id);
		return BeanUtil.copyProperties(record, MsgUserVO.class);
	}

	public int updateById(MsgUserQO qo) {
		MsgUser record = BeanUtil.copyProperties(qo, MsgUser.class);
		return dao.updateById(record);
	}

}
