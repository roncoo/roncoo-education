package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.service.admin.req.AdminUserCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseViewResp;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-课程用户关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUserCourseBiz extends BaseBiz {

    @NotNull
    private final UserCourseDao dao;

    /**
     * 课程用户关联表分页
     *
     * @param req 课程用户关联表分页查询参数
     * @return 课程用户关联表分页查询结果
     */
    public Result<Page<AdminUserCoursePageResp>> page(AdminUserCoursePageReq req) {
        UserCourseExample example = new UserCourseExample();
        Criteria c = example.createCriteria();
        Page<UserCourse> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUserCoursePageResp> respPage = PageUtil.transform(page, AdminUserCoursePageResp.class);
        return Result.success(respPage);
    }

    /**
     * 课程用户关联表添加
     *
     * @param req 课程用户关联表
     * @return 添加结果
     */
    public Result<String> save(AdminUserCourseSaveReq req) {
        UserCourse record = BeanUtil.copyProperties(req, UserCourse.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程用户关联表查看
     *
     * @param id 主键ID
     * @return 课程用户关联表
     */
    public Result<AdminUserCourseViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUserCourseViewResp.class));
    }

    /**
     * 课程用户关联表修改
     *
     * @param req 课程用户关联表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUserCourseEditReq req) {
        UserCourse record = BeanUtil.copyProperties(req, UserCourse.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程用户关联表删除
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
