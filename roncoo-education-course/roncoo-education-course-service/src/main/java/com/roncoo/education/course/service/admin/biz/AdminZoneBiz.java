package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminZoneEditReq;
import com.roncoo.education.course.service.admin.req.AdminZonePageReq;
import com.roncoo.education.course.service.admin.req.AdminZoneSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminZonePageResp;
import com.roncoo.education.course.service.admin.resp.AdminZoneViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-专区
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminZoneBiz extends BaseBiz {

    @NotNull
    private final ZoneDao dao;

    /**
     * 专区分页
     *
     * @param req 专区分页查询参数
     * @return 专区分页查询结果
     */
    public Result<Page<AdminZonePageResp>> page(AdminZonePageReq req) {
        ZoneExample example = new ZoneExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getZoneName())) {
            c.andZoneNameLike(PageUtil.rightLike(req.getZoneName()));
        }
        example.setOrderByClause("sort asc, id desc");
        Page<Zone> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminZonePageResp> respPage = PageUtil.transform(page, AdminZonePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 专区添加
     *
     * @param req 专区
     * @return 添加结果
     */
    public Result<String> save(AdminZoneSaveReq req) {
        Zone record = BeanUtil.copyProperties(req, Zone.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 专区查看
     *
     * @param id 主键ID
     * @return 专区
     */
    public Result<AdminZoneViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminZoneViewResp.class));
    }

    /**
     * 专区修改
     *
     * @param req 专区修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminZoneEditReq req) {
        Zone record = BeanUtil.copyProperties(req, Zone.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 专区删除
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
