package com.roncoo.education.course.service.admin.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterEditReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterViewResp;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * ADMIN-章节信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCourseChapterBiz extends BaseBiz {

    @NotNull
    private final CourseChapterDao dao;

    /**
     * 章节信息分页
     *
     * @param req 章节信息分页查询参数
     * @return 章节信息分页查询结果
     */
    public Result<Page<AdminCourseChapterPageResp>> page(AdminCourseChapterPageReq req) {
        CourseChapterExample example = new CourseChapterExample();
        Criteria c = example.createCriteria();
        Page<CourseChapter> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCourseChapterPageResp> respPage = PageUtil.transform(page, AdminCourseChapterPageResp.class);
        return Result.success(respPage);
    }

    /**
     * 章节信息添加
     *
     * @param req 章节信息
     * @return 添加结果
     */
    public Result<String> save(AdminCourseChapterSaveReq req) {
        CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 章节信息查看
     *
     * @param id 主键ID
     * @return 章节信息
     */
    public Result<AdminCourseChapterViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminCourseChapterViewResp.class));
    }

    /**
     * 章节信息修改
     *
     * @param req 章节信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminCourseChapterEditReq req) {
        CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 章节信息删除
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
