package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogSmsDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSms;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminLogSmsEditReq;
import com.roncoo.education.user.service.admin.req.AdminLogSmsPageReq;
import com.roncoo.education.user.service.admin.req.AdminLogSmsSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLogSmsPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLogSmsViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户短信发送日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminLogSmsBiz extends BaseBiz {

    @NotNull
    private final LogSmsDao dao;

    /**
     * 用户短信发送日志分页
     *
     * @param req 用户短信发送日志分页查询参数
     * @return 用户短信发送日志分页查询结果
     */
    public Result<Page<AdminLogSmsPageResp>> page(AdminLogSmsPageReq req) {
        LogSmsExample example = new LogSmsExample();
        Criteria c = example.createCriteria();
        Page<LogSms> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminLogSmsPageResp> respPage = PageUtil.transform(page, AdminLogSmsPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 用户短信发送日志添加
     *
     * @param req 用户短信发送日志
     * @return 添加结果
     */
    public Result<String> save(AdminLogSmsSaveReq req) {
        LogSms record = BeanUtil.copyProperties(req, LogSms.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户短信发送日志查看
     *
     * @param id 主键ID
     * @return 用户短信发送日志
     */
    public Result<AdminLogSmsViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminLogSmsViewResp.class));
    }

    /**
     * 用户短信发送日志修改
     *
     * @param req 用户短信发送日志修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminLogSmsEditReq req) {
        LogSms record = BeanUtil.copyProperties(req, LogSms.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户短信发送日志删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
