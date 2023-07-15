package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Msg;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgExample;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminMsgEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgPageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgPageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-站内信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminMsgBiz extends BaseBiz {

    @NotNull
    private final MsgDao dao;

    /**
     * 站内信息表分页
     *
     * @param req 站内信息表分页查询参数
     * @return 站内信息表分页查询结果
     */
    public Result<Page<AdminMsgPageResp>> page(AdminMsgPageReq req) {
        MsgExample example = new MsgExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause("sort asc, id desc");
        Page<Msg> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminMsgPageResp> respPage = PageUtil.transform(page, AdminMsgPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 站内信息表添加
     *
     * @param req 站内信息表
     * @return 添加结果
     */
    public Result<String> save(AdminMsgSaveReq req) {
        Msg record = BeanUtil.copyProperties(req, Msg.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 站内信息表查看
     *
     * @param id 主键ID
     * @return 站内信息表
     */
    public Result<AdminMsgViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminMsgViewResp.class));
    }

    /**
     * 站内信息表修改
     *
     * @param req 站内信息表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminMsgEditReq req) {
        Msg record = BeanUtil.copyProperties(req, Msg.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 站内信息表删除
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
