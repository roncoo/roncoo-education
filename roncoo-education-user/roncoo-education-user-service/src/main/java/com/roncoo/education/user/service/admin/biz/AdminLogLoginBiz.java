package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.service.admin.req.AdminLogLoginEditReq;
import com.roncoo.education.user.service.admin.req.AdminLogLoginPageReq;
import com.roncoo.education.user.service.admin.req.AdminLogLoginSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminLogLoginPageResp;
import com.roncoo.education.user.service.admin.resp.AdminLogLoginViewResp;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户登录日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminLogLoginBiz extends BaseBiz {

    @NotNull
    private final LogLoginDao dao;

    /**
     * 用户登录日志分页
     *
     * @param req 用户登录日志分页查询参数
     * @return 用户登录日志分页查询结果
     */
    public Result<Page<AdminLogLoginPageResp>> page(AdminLogLoginPageReq req) {
        LogLoginExample example = new LogLoginExample();
        Criteria c = example.createCriteria();
        Page<LogLogin> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminLogLoginPageResp> respPage = PageUtil.transform(page, AdminLogLoginPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 用户登录日志添加
     *
     * @param req 用户登录日志
     * @return 添加结果
     */
    public Result<String> save(AdminLogLoginSaveReq req) {
        LogLogin record = BeanUtil.copyProperties(req, LogLogin.class);
        if (dao.save(record) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }

    /**
     * 用户登录日志查看
     *
     * @param id 主键ID
     * @return 用户登录日志
     */
    public Result<AdminLogLoginViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminLogLoginViewResp.class));
    }

    /**
     * 用户登录日志修改
     *
     * @param req 用户登录日志修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminLogLoginEditReq req) {
        LogLogin record = BeanUtil.copyProperties(req, LogLogin.class);
        if (dao.updateById(record) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }

    /**
     * 用户登录日志删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }
}
