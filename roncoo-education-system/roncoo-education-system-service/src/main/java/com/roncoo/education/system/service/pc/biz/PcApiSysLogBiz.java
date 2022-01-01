package com.roncoo.education.system.service.pc.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.system.dao.SysLogDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample.Criteria;
import com.roncoo.education.system.service.pc.req.SysLogPageREQ;
import com.roncoo.education.system.service.pc.resq.SysLogPageRESQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Component
public class PcApiSysLogBiz {

    @Autowired
    private SysLogDao sysLogDao;

    public Result<Page<SysLogPageRESQ>> list(SysLogPageREQ req) {
        SysLogExample example = new SysLogExample();
        Criteria c = example.createCriteria();
        if (StringUtils.isNotEmpty(req.getRealName())) {
            c.andRealNameLike(PageUtil.like(req.getRealName()));
        }
        if (StringUtils.isNotEmpty(req.getBeginDate())) {
            c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginDate(), "yyyy-MM-dd"));
        }
        if (StringUtils.isNotEmpty(req.getEndDate())) {
            c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndDate(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause(" id desc ");
        Page<SysLog> page = sysLogDao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, SysLogPageRESQ.class));
    }

}
