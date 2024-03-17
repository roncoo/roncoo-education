package com.roncoo.education.user.service.admin.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ConsumeTypeEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.MD5Util;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.UsersAccountConsumeDao;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
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
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

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

    @NotNull
    private final UsersAccountDao usersAccountDao;

    /**
     * 用户账户消费记录分页
     *
     * @param req 用户账户消费记录分页查询参数
     * @return 用户账户消费记录分页查询结果
     */
    public Result<Page<AdminUsersAccountConsumePageResp>> page(AdminUsersAccountConsumePageReq req) {
        UsersAccountConsumeExample example = new UsersAccountConsumeExample();
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getUserId())) {
            c.andUserIdEqualTo(req.getUserId());
        }
        example.setOrderByClause("id desc");
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
    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(AdminUsersAccountConsumeSaveReq req) {
        UsersAccount account = usersAccountDao.getByUserId(req.getUserId());
        if (ObjectUtil.isEmpty(account)) {
            /// 用户账户不存在，创建新账户
            account = new UsersAccount();
            account.setUserId(req.getUserId());
            account.setAvailableAmount(BigDecimal.ZERO);
            account.setFreezeAmount(BigDecimal.ZERO);
            account.setSign(MD5Util.md5(account.getUserId().toString(), account.getAvailableAmount().toPlainString(), account.getFreezeAmount().toPlainString()));
            usersAccountDao.save(account);
        }

        UsersAccountConsume record = BeanUtil.copyProperties(req, UsersAccountConsume.class);
        if (req.getConsumeType().equals(ConsumeTypeEnum.Consume.getCode())) {
            record.setBalanceAmount(account.getAvailableAmount().subtract(req.getConsumeAmount()));
        }
        if (req.getConsumeType().equals(ConsumeTypeEnum.Income.getCode())) {
            record.setBalanceAmount(account.getAvailableAmount().add(req.getConsumeAmount()));
        }
        if (dao.save(record) > 0) {
            account.setAvailableAmount(record.getBalanceAmount());
            account.setSign(MD5Util.md5(account.getUserId().toString(), account.getAvailableAmount().toPlainString(), account.getFreezeAmount().toPlainString()));
            usersAccountDao.updateById(account);
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
