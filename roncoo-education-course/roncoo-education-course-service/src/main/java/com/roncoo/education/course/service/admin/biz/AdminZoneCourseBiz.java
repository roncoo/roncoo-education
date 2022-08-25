package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.service.admin.req.AdminZoneCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminZoneCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminZoneCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminZoneCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminZoneCourseViewResp;
import com.roncoo.education.course.dao.ZoneCourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-专区课程关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminZoneCourseBiz extends BaseBiz {

    @NotNull
    private final ZoneCourseDao dao;

    /**
     * 专区课程关联表分页
     *
     * @param req 专区课程关联表分页查询参数
     * @return 专区课程关联表分页查询结果
     */
    public Result<Page<AdminZoneCoursePageResp>> page(AdminZoneCoursePageReq req) {
        ZoneCourseExample example = new ZoneCourseExample();
        Criteria c = example.createCriteria();
        Page<ZoneCourse> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminZoneCoursePageResp> respPage = PageUtil.transform(page, AdminZoneCoursePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 专区课程关联表添加
     *
     * @param req 专区课程关联表
     * @return 添加结果
     */
    public Result<String> save(AdminZoneCourseSaveReq req) {
        ZoneCourse record = BeanUtil.copyProperties(req, ZoneCourse.class);
        if (dao.save(record) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }

    /**
     * 专区课程关联表查看
     *
     * @param id 主键ID
     * @return 专区课程关联表
     */
    public Result<AdminZoneCourseViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminZoneCourseViewResp.class));
    }

    /**
     * 专区课程关联表修改
     *
     * @param req 专区课程关联表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminZoneCourseEditReq req) {
        ZoneCourse record = BeanUtil.copyProperties(req, ZoneCourse.class);
        if (dao.updateById(record) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }

    /**
     * 专区课程关联表删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success(toI18nSuccess());
        }
        return Result.error(toI18nFail());
    }
}
