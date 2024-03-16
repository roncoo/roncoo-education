package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersAccountConsumeDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsume;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsumeExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsumeExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumeEditReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumePageReq;
import com.roncoo.education.user.service.admin.req.AdminUsersAccountConsumeSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountConsumePageResp;
import com.roncoo.education.user.service.admin.resp.AdminUsersAccountConsumeViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-用户账户消费记录
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUsersAccountConsumeBiz extends BaseBiz {

    @NotNull
    private final UsersAccountConsumeDao dao;

    /**
     * 用户账户消费记录分页
     *
     * @param req 用户账户消费记录分页查询参数
     * @return 用户账户消费记录分页查询结果
     */
    public Result<Page<AdminUsersAccountConsumePageResp>> page(AdminUsersAccountConsumePageReq req) {
        UsersAccountConsumeExample example = new UsersAccountConsumeExample();
        Criteria c = example.createCriteria();
        Page<UsersAccountConsume> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUsersAccountConsumePageResp> respPage = PageUtil.transform(page, AdminUsersAccountConsumePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 用户账户消费记录添加
     *
     * @param req 用户账户消费记录
     * @return 添加结果
     */
    public Result<String> save(AdminUsersAccountConsumeSaveReq req) {
        UsersAccountConsume record = BeanUtil.copyProperties(req, UsersAccountConsume.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户账户消费记录查看
     *
     * @param id 主键ID
     * @return 用户账户消费记录
     */
    public Result<AdminUsersAccountConsumeViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUsersAccountConsumeViewResp.class));
    }

    /**
     * 用户账户消费记录修改
     *
     * @param req 用户账户消费记录修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUsersAccountConsumeEditReq req) {
        UsersAccountConsume record = BeanUtil.copyProperties(req, UsersAccountConsume.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 用户账户消费记录删除
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
