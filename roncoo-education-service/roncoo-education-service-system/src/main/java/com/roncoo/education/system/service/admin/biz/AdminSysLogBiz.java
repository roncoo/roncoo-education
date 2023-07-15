package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.dao.SysLogDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample.Criteria;
import com.roncoo.education.system.service.admin.req.AdminSysLogPageReq;
import com.roncoo.education.system.service.admin.resp.AdminSysLogPageResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Component
public class AdminSysLogBiz {

    @Autowired
    private SysLogDao sysLogDao;

    public Result<Page<AdminSysLogPageResp>> list(AdminSysLogPageReq req) {
        SysLogExample example = new SysLogExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getRealName())) {
            c.andRealNameLike(PageUtil.like(req.getRealName()));
        }
        example.setOrderByClause(" id desc ");
        Page<SysLog> page = sysLogDao.page(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, AdminSysLogPageResp.class));
    }

}
