package com.roncoo.education.user.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.RegionDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Region;
import com.roncoo.education.user.dao.impl.mapper.entity.RegionExample;
import com.roncoo.education.user.dao.impl.mapper.entity.RegionExample.Criteria;
import com.roncoo.education.user.service.admin.req.AdminRegionEditReq;
import com.roncoo.education.user.service.admin.req.AdminRegionPageReq;
import com.roncoo.education.user.service.admin.req.AdminRegionSaveReq;
import com.roncoo.education.user.service.admin.resp.AdminRegionPageResp;
import com.roncoo.education.user.service.admin.resp.AdminRegionViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-行政区域表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminRegionBiz extends BaseBiz {

    @NotNull
    private final RegionDao dao;

    /**
     * 行政区域表分页
     *
     * @param req 行政区域表分页查询参数
     * @return 行政区域表分页查询结果
     */
    public Result<Page<AdminRegionPageResp>> page(AdminRegionPageReq req) {
        RegionExample example = new RegionExample();
        Criteria c = example.createCriteria();
        Page<Region> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminRegionPageResp> respPage = PageUtil.transform(page, AdminRegionPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 行政区域表添加
     *
     * @param req 行政区域表
     * @return 添加结果
     */
    public Result<String> save(AdminRegionSaveReq req) {
        Region record = BeanUtil.copyProperties(req, Region.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 行政区域表查看
     *
     * @param id 主键ID
     * @return 行政区域表
     */
    public Result<AdminRegionViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminRegionViewResp.class));
    }

    /**
     * 行政区域表修改
     *
     * @param req 行政区域表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminRegionEditReq req) {
        Region record = BeanUtil.copyProperties(req, Region.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 行政区域表删除
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
