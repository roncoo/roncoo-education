/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service.dao;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysLogExample;

public interface SysLogDao {
	int save(SysLog record);

	int deleteById(Long id);

	int updateById(SysLog record);

	SysLog getById(Long id);

	Page<SysLog> listForPage(int pageCurrent, int pageSize, SysLogExample example);
}
