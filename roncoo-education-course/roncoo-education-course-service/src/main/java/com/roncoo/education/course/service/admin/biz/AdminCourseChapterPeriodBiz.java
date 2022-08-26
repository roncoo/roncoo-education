package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodEditReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodPageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPeriodSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-课时信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCourseChapterPeriodBiz extends BaseBiz {

    @NotNull
    private final CourseChapterPeriodDao dao;

    /**
     * 课时信息分页
     *
     * @param req 课时信息分页查询参数
     * @return 课时信息分页查询结果
     */
    public Result<Page<AdminCourseChapterPeriodPageResp>> page(AdminCourseChapterPeriodPageReq req) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        Criteria c = example.createCriteria();
        Page<CourseChapterPeriod> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCourseChapterPeriodPageResp> respPage = PageUtil.transform(page, AdminCourseChapterPeriodPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 课时信息添加
     *
     * @param req 课时信息
     * @return 添加结果
     */
    public Result<String> save(AdminCourseChapterPeriodSaveReq req) {
        CourseChapterPeriod record = BeanUtil.copyProperties(req, CourseChapterPeriod.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课时信息查看
     *
     * @param id 主键ID
     * @return 课时信息
     */
    public Result<AdminCourseChapterPeriodViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminCourseChapterPeriodViewResp.class));
    }

    /**
     * 课时信息修改
     *
     * @param req 课时信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminCourseChapterPeriodEditReq req) {
        CourseChapterPeriod record = BeanUtil.copyProperties(req, CourseChapterPeriod.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课时信息删除
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
