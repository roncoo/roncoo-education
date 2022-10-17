package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteNavDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample.Criteria;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteNavSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteNavPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteNavViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-头部导航
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminWebsiteNavBiz extends BaseBiz {

    @NotNull
    private final WebsiteNavDao dao;

    /**
     * 头部导航分页
     *
     * @param req 头部导航分页查询参数
     * @return 头部导航分页查询结果
     */
    public Result<Page<AdminWebsiteNavPageResp>> page(AdminWebsiteNavPageReq req) {
        WebsiteNavExample example = new WebsiteNavExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getNavTitle())) {
            c.andNavTitleLike(PageUtil.rightLike(req.getNavTitle()));
        }
        example.setOrderByClause("sort asc, id desc");
        Page<WebsiteNav> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminWebsiteNavPageResp> respPage = PageUtil.transform(page, AdminWebsiteNavPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 头部导航添加
     *
     * @param req 头部导航
     * @return 添加结果
     */
    public Result<String> save(AdminWebsiteNavSaveReq req) {
        WebsiteNav record = BeanUtil.copyProperties(req, WebsiteNav.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 头部导航查看
     *
     * @param id 主键ID
     * @return 头部导航
     */
    public Result<AdminWebsiteNavViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminWebsiteNavViewResp.class));
    }

    /**
     * 头部导航修改
     *
     * @param req 头部导航修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminWebsiteNavEditReq req) {
        WebsiteNav record = BeanUtil.copyProperties(req, WebsiteNav.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 头部导航删除
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
