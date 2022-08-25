package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAdvDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdv;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdvExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdvExample.Criteria;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAdvEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAdvPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteAdvSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAdvPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteAdvViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-广告信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminWebsiteAdvBiz extends BaseBiz {

    @NotNull
    private final WebsiteAdvDao dao;

    /**
     * 广告信息分页
     *
     * @param req 广告信息分页查询参数
     * @return 广告信息分页查询结果
     */
    public Result
            <Page
                    <AdminWebsiteAdvPageResp>> page(AdminWebsiteAdvPageReq req) {
        WebsiteAdvExample example = new WebsiteAdvExample();
        Criteria c = example.createCriteria();
        Page<WebsiteAdv> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page
                <AdminWebsiteAdvPageResp> respPage = PageUtil.transform(page, AdminWebsiteAdvPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 广告信息添加
     *
     * @param req 广告信息
     * @return 添加结果
     */
    public Result
            <String> save(AdminWebsiteAdvSaveReq req) {
        WebsiteAdv record = BeanUtil.copyProperties(req, WebsiteAdv.class);
        if (dao.save(record) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }

    /**
     * 广告信息查看
     *
     * @param id 主键ID
     * @return 广告信息
     */
    public Result
            <AdminWebsiteAdvViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminWebsiteAdvViewResp.class));
    }

    /**
     * 广告信息修改
     *
     * @param req 广告信息修改对象
     * @return 修改结果
     */
    public Result
            <String> edit(AdminWebsiteAdvEditReq req) {
        WebsiteAdv record = BeanUtil.copyProperties(req, WebsiteAdv.class);
        if (dao.updateById(record) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }

    /**
     * 广告信息删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result
            <String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }
}
