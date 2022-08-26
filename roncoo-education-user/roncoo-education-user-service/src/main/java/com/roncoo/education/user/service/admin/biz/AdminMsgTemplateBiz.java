package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgTemplateDao;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplateExample;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplateExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminMsgTemplateEditReq;
import com.roncoo.education.user.service.admin.req.AdminMsgTemplatePageReq;
import com.roncoo.education.user.service.admin.req.AdminMsgTemplateSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminMsgTemplatePageResp;
import com.roncoo.education.user.service.admin.resp.AdminMsgTemplateViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-消息模板
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminMsgTemplateBiz extends BaseBiz {

    @NotNull
    private final MsgTemplateDao dao;

    /**
     * 消息模板分页
     *
     * @param req 消息模板分页查询参数
     * @return 消息模板分页查询结果
     */
    public Result<Page<AdminMsgTemplatePageResp>> page(AdminMsgTemplatePageReq req) {
        MsgTemplateExample example = new MsgTemplateExample();
        Criteria c = example.createCriteria();
        Page<MsgTemplate> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminMsgTemplatePageResp> respPage = PageUtil.transform(page, AdminMsgTemplatePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 消息模板添加
     *
     * @param req 消息模板
     * @return 添加结果
     */
    public Result<String> save(AdminMsgTemplateSaveReq req) {
        MsgTemplate record = BeanUtil.copyProperties(req, MsgTemplate.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 消息模板查看
     *
     * @param id 主键ID
     * @return 消息模板
     */
    public Result<AdminMsgTemplateViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminMsgTemplateViewResp.class));
    }

    /**
     * 消息模板修改
     *
     * @param req 消息模板修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminMsgTemplateEditReq req) {
        MsgTemplate record = BeanUtil.copyProperties(req, MsgTemplate.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 消息模板删除
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
