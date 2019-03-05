package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.system.common.bean.qo.MsgUserQO;
import com.roncoo.education.system.common.bean.vo.MsgUserVO;
import com.roncoo.education.util.base.Page;

/**
 * 站内信用户记录表 
 *
 * @author wuyun
 */
public interface BossMsgUser {
	
	@RequestMapping(value = "/boss/system/msgUser/listForPage")
	Page<MsgUserVO> listForPage(@RequestBody MsgUserQO qo);
	
	@RequestMapping(value = "/boss/system/msgUser/save")
	int save(@RequestBody MsgUserQO qo);
	
	@RequestMapping(value = "/boss/system/msgUser/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/msgUser/updateById")
	int updateById(@RequestBody MsgUserQO qo);
	
	@RequestMapping(value = "/boss/system/msgUser/getById")
	MsgUserVO getById(@RequestBody Long id);
	
}
