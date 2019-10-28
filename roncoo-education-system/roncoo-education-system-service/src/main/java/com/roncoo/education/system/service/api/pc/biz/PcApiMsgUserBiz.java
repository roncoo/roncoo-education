package com.roncoo.education.system.service.api.pc.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.aliyun.oas.utils.StringUtil;
import com.roncoo.education.system.common.req.MsgUserDeleteREQ;
import com.roncoo.education.system.common.req.MsgUserPageREQ;
import com.roncoo.education.system.common.req.MsgUserViewREQ;
import com.roncoo.education.system.common.resq.MsgUserPageRESQ;
import com.roncoo.education.system.common.resq.MsgUserViewRESQ;
import com.roncoo.education.system.service.dao.MsgUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 站内信用户记录
 *
 */
@Component
public class PcApiMsgUserBiz {

	@Autowired
	private MsgUserDao dao;

	public Result<Page<MsgUserPageRESQ>> list(MsgUserPageREQ req) {
		MsgUserExample example = new MsgUserExample();
		Criteria c = example.createCriteria();
		if (StringUtil.isNotEmpty(req.getMsgTitle())) {
			c.andMsgTitleLike(PageUtil.like(req.getMsgTitle()));
		}
		if (StringUtil.isNotEmpty(req.getMobile())) {
			c.andMobileLike(PageUtil.like(req.getMobile()));
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<MsgUser> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		return Result.success(PageUtil.transform(page, MsgUserPageRESQ.class));
	}

	public Result<Integer> delete(MsgUserDeleteREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		int result = dao.deleteById(req.getId());
		if (result < 0) {
			return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
		}
		return Result.success(result);
	}

	public Result<MsgUserViewRESQ> view(MsgUserViewREQ req) {
		if (StringUtils.isEmpty(req.getId())) {
			return Result.error("ID不能为空");
		}
		MsgUser record = dao.getById(req.getId());
		if (ObjectUtil.isNull(record)) {
			return Result.error("找不到用户消息记录");
		}
		return Result.success(BeanUtil.copyProperties(record, MsgUserViewRESQ.class));
	}

}
