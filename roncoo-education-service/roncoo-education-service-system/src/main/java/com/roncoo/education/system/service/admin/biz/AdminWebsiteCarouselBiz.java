package com.roncoo.education.system.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteCarouselDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarousel;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarouselExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarouselExample.Criteria;
import com.roncoo.education.system.service.admin.req.AdminWebsiteCarouselEditReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteCarouselPageReq;
import com.roncoo.education.system.service.admin.req.AdminWebsiteCarouselSaveReq;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteCarouselPageResp;
import com.roncoo.education.system.service.admin.resp.AdminWebsiteCarouselViewResp;
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
public class AdminWebsiteCarouselBiz extends BaseBiz {

    @NotNull
    private final WebsiteCarouselDao dao;

    /**
     * 广告信息分页
     *
     * @param req 广告信息分页查询参数
     * @return 广告信息分页查询结果
     */
    public Result<Page<AdminWebsiteCarouselPageResp>> page(AdminWebsiteCarouselPageReq req) {
        WebsiteCarouselExample example = new WebsiteCarouselExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause("sort asc, id desc");
        Page<WebsiteCarousel> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminWebsiteCarouselPageResp> respPage = PageUtil.transform(page, AdminWebsiteCarouselPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 广告信息添加
     *
     * @param req 广告信息
     * @return 添加结果
     */
    public Result<String> save(AdminWebsiteCarouselSaveReq req) {
        WebsiteCarousel record = BeanUtil.copyProperties(req, WebsiteCarousel.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 广告信息查看
     *
     * @param id 主键ID
     * @return 广告信息
     */
    public Result<AdminWebsiteCarouselViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminWebsiteCarouselViewResp.class));
    }

    /**
     * 广告信息修改
     *
     * @param req 广告信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminWebsiteCarouselEditReq req) {
        WebsiteCarousel record = BeanUtil.copyProperties(req, WebsiteCarousel.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 广告信息删除
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
