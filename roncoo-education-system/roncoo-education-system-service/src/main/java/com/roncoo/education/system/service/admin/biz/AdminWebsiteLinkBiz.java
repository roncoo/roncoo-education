package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteLinkDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLink;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteLinkExample.Criteria;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteLinkSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteLinkPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteLinkViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-站点友情链接
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminWebsiteLinkBiz extends BaseBiz {

    @NotNull
    private final WebsiteLinkDao dao;

    /**
     * 站点友情链接分页
     *
     * @param req 站点友情链接分页查询参数
     * @return 站点友情链接分页查询结果
     */
    public Result<Page<AdminWebsiteLinkPageResp>> page(AdminWebsiteLinkPageReq req) {
        WebsiteLinkExample example = new WebsiteLinkExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getLinkName())) {
            c.andLinkNameLike(PageUtil.rightLike(req.getLinkName()));
        }
        example.setOrderByClause(" sort asc, id desc ");
        Page<WebsiteLink> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminWebsiteLinkPageResp> respPage = PageUtil.transform(page, AdminWebsiteLinkPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 站点友情链接添加
     *
     * @param req 站点友情链接
     * @return 添加结果
     */
    public Result<String> save(AdminWebsiteLinkSaveReq req) {
        WebsiteLink record = BeanUtil.copyProperties(req, WebsiteLink.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 站点友情链接查看
     *
     * @param id 主键ID
     * @return 站点友情链接
     */
    public Result<AdminWebsiteLinkViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminWebsiteLinkViewResp.class));
    }

    /**
     * 站点友情链接修改
     *
     * @param req 站点友情链接修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminWebsiteLinkEditReq req) {
        WebsiteLink record = BeanUtil.copyProperties(req, WebsiteLink.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 站点友情链接删除
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
