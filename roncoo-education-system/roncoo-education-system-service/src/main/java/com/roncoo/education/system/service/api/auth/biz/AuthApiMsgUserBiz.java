package com.roncoo.education.system.service.api.auth.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.system.common.bo.MsgReadBO;
import com.roncoo.education.system.common.bo.MsgUserBO;
import com.roncoo.education.system.common.bo.MsgViewBO;
import com.roncoo.education.system.common.dto.MsgDTO;
import com.roncoo.education.system.common.dto.MsgReadDTO;
import com.roncoo.education.system.common.dto.MsgUserDTO;
import com.roncoo.education.system.service.dao.MsgDao;
import com.roncoo.education.system.service.dao.MsgUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Msg;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.IsDefaultEnum;
import com.roncoo.education.util.enums.IsReadEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 站内信用户记录表
 *
 * @author wuyun
 */
@Component
public class AuthApiMsgUserBiz {

	@Autowired
	private MsgUserDao dao;
	@Autowired
	private MsgDao msgDao;

	/**
	 * 站内信分页列表接口
	 *
	 * @param msgUserBO
	 * @return
	 * @author wuyun
	 */
	public Result<Page<MsgUserDTO>> list(MsgUserBO bo) {
		if (bo.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		MsgUserExample example = new MsgUserExample();
		Criteria c = example.createCriteria();
		c.andUserNoEqualTo(bo.getUserNo());
		c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
		if (bo.getIsRead() != null) {
			c.andIsReadEqualTo(bo.getIsRead());
		}
		example.setOrderByClause(" status_id desc, sort desc, id desc ");
		Page<MsgUser> page = dao.listForPage(bo.getPageCurrent(), bo.getPageSize(), example);
		return Result.success(PageUtil.transform(page, MsgUserDTO.class));
	}

	/**
	 * 用户查看站内信内容
	 *
	 * @param MsgViewBO
	 * @return
	 * @author wuyun
	 */
	public Result<MsgDTO> readMsg(MsgViewBO bo) {
		if (bo.getId() == null) {
			return Result.error("id不能为空");
		}
		MsgUser record = dao.getById(bo.getId());
		if (record == null) {
			return Result.error("找不到站内信息");
		}
		// 未阅读，则刷新阅读状态
		if (IsDefaultEnum.NO.getCode().equals(record.getIsRead())) {
			record.setIsRead(IsDefaultEnum.YES.getCode());
			dao.updateById(record);
		}
		// 返回消息实体
		Msg msg = msgDao.getById(record.getMsgId());
		if (msg == null) {
			return Result.error("查询msg有误");
		}
		return Result.success(BeanUtil.copyProperties(msg, MsgDTO.class));
	}

	/**
	 * 用户站内信未读条数
	 *
	 * @param msgReadBO
	 * @return
	 * @author wuyun
	 */
	public Result<MsgReadDTO> getNumOfUnReadMsg(MsgReadBO bo) {
		if (bo.getUserNo() == null) {
			return Result.error("用户编号不能为空");
		}
		int num = dao.countByUserNoAndIsRead(bo.getUserNo(), IsReadEnum.NO.getCode());
		MsgReadDTO dto = new MsgReadDTO();
		dto.setNum(num);
		return Result.success(dto);
	}

}
