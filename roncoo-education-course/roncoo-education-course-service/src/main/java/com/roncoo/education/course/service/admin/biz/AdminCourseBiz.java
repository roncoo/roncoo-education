package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.service.admin.req.AdminCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseViewResp;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCourseBiz extends BaseBiz {

    @NotNull
    private final CourseDao dao;

    /**
     * 课程信息分页
     *
     * @param req 课程信息分页查询参数
     * @return 课程信息分页查询结果
     */
    public Result<Page<AdminCoursePageResp>> page(AdminCoursePageReq req) {
        CourseExample example = new CourseExample();
        Criteria c = example.createCriteria();
        Page<Course> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCoursePageResp> respPage = PageUtil.transform(page, AdminCoursePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 课程信息添加
     *
     * @param req 课程信息
     * @return 添加结果
     */
    public Result<String> save(AdminCourseSaveReq req) {
        Course record = BeanUtil.copyProperties(req, Course.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程信息查看
     *
     * @param id 主键ID
     * @return 课程信息
     */
    public Result<AdminCourseViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminCourseViewResp.class));
    }

    /**
     * 课程信息修改
     *
     * @param req 课程信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminCourseEditReq req) {
        Course record = BeanUtil.copyProperties(req, Course.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程信息删除
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
