package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.dao.SysLogDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLog;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysLogExample.Criteria;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.admin.req.AdminSysLogPageReq;
import com.roncoo.education.system.service.admin.resp.AdminSysLogPageResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 后台操作日志表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminSysLogBiz {

    @NotNull
    private final SysLogDao sysLogDao;

    @NotNull
    private final SysUserDao sysUserDao;

    public Result<Page<AdminSysLogPageResp>> list(AdminSysLogPageReq req) {
        SysLogExample example = new SysLogExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysLog> page = sysLogDao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminSysLogPageResp> resp = PageUtil.transform(page, AdminSysLogPageResp.class);
        if (CollUtil.isNotEmpty(resp.getList())) {
            List<Long> userIds = resp.getList().stream().map(AdminSysLogPageResp::getUserId).collect(Collectors.toList());
            List<SysUser> sysUsers = sysUserDao.listByIds(userIds);
            Map<Long, SysUser> map = sysUsers.stream().collect(Collectors.toMap(SysUser::getId, sysUser -> sysUser));
            for (AdminSysLogPageResp res : resp.getList()) {
                res.setRealName(map.get(res.getUserId()).getRealName());
            }
        }
        return Result.success(resp);
    }

}
