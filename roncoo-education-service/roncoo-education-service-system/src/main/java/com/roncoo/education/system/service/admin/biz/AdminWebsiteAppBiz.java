package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAppDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteApp;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAppExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAppExample.Criteria;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAppEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAppPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAppSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAppPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAppViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

/**
 * ADMIN-app版本管理
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
public class AdminWebsiteAppBiz extends BaseBiz {

    @NotNull
    private final WebsiteAppDao dao;

    /**
     * app版本管理分页
     *
     * @param req app版本管理分页查询参数
     * @return app版本管理分页查询结果
     */
    public Result<Page<AdminWebsiteAppPageResp>> page(AdminWebsiteAppPageReq req) {
        WebsiteAppExample example = new WebsiteAppExample();
        example.setOrderByClause("publish_time desc");
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getAppType())) {
            c.andAppTypeEqualTo(req.getAppType());
        }
        Page<WebsiteApp> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminWebsiteAppPageResp> respPage = PageUtil.transform(page, AdminWebsiteAppPageResp.class);
        return Result.success(respPage);
    }

    /**
     * app版本管理添加
     *
     * @param req app版本管理
     * @return 添加结果
     */
    public Result<String> save(AdminWebsiteAppSaveReq req) {
        WebsiteApp record = BeanUtil.copyProperties(req, WebsiteApp.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * app版本管理查看
     *
     * @param id 主键ID
     * @return app版本管理
     */
    public Result<AdminWebsiteAppViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminWebsiteAppViewResp.class));
    }

    /**
     * app版本管理修改
     *
     * @param req app版本管理修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminWebsiteAppEditReq req) {
        WebsiteApp record = BeanUtil.copyProperties(req, WebsiteApp.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * app版本管理删除
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
