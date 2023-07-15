package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgUserDao;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminMsgUserEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgUserPageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgUserSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgUserPageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgUserViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-站内信用户记录表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminMsgUserBiz extends BaseBiz {

    @NotNull
    private final MsgUserDao dao;

    /**
     * 站内信用户记录表分页
     *
     * @param req 站内信用户记录表分页查询参数
     * @return 站内信用户记录表分页查询结果
     */
    public Result<Page<AdminMsgUserPageResp>> page(AdminMsgUserPageReq req) {
        MsgUserExample example = new MsgUserExample();
        Criteria c = example.createCriteria();
        Page<MsgUser> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminMsgUserPageResp> respPage = PageUtil.transform(page, AdminMsgUserPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 站内信用户记录表添加
     *
     * @param req 站内信用户记录表
     * @return 添加结果
     */
    public Result<String> save(AdminMsgUserSaveReq req) {
        MsgUser record = BeanUtil.copyProperties(req, MsgUser.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 站内信用户记录表查看
     *
     * @param id 主键ID
     * @return 站内信用户记录表
     */
    public Result<AdminMsgUserViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminMsgUserViewResp.class));
    }

    /**
     * 站内信用户记录表修改
     *
     * @param req 站内信用户记录表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminMsgUserEditReq req) {
        MsgUser record = BeanUtil.copyProperties(req, MsgUser.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 站内信用户记录表删除
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
