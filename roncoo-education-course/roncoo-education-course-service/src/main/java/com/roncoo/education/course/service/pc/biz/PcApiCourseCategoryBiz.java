package com.roncoo.education.course.service.pc.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseCategoryDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseCategoryExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseCategoryExample.Criteria;
import com.roncoo.education.course.service.pc.req.*;
import com.roncoo.education.course.service.pc.resq.CourseCategoryPageRESQ;
import com.roncoo.education.course.service.pc.resq.CourseCategoryViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程分类
 */
@Component
public class PcApiCourseCategoryBiz {

    @Autowired
    private CourseCategoryDao dao;

    /**
     * 课程分类-分页列出
     *
     * @param req
     * @return
     */
    public Result<Page<CourseCategoryPageRESQ>> listForPage(CourseCategoryPageREQ req) {
        CourseCategoryExample example = new CourseCategoryExample();
        Criteria c = example.createCriteria();
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        if (req.getCategoryType() != null) {
            c.andCategoryTypeEqualTo(req.getCategoryType());
        }
        if (req.getParentId() != null) {
            c.andParentIdEqualTo(req.getParentId());
        }
        if (StringUtils.hasText(req.getCategoryName())) {
            c.andCategoryNameLike(PageUtil.rightLike(req.getCategoryName()));
        } else {
            c.andFloorEqualTo(1);
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<CourseCategory> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<CourseCategoryPageRESQ> listForPage = PageUtil.transform(page, CourseCategoryPageRESQ.class);
        for (CourseCategoryPageRESQ resq : listForPage.getList()) {
            resq.setChildren(recursionList(resq.getId()));
        }
        return Result.success(listForPage);
    }

    public Result<Integer> save(CourseCategorySaveREQ req) {
        if (StringUtils.isEmpty(req.getParentId())) {
            return Result.error("父ID不能为空");
        }
        if (StringUtils.isEmpty(req.getFloor())) {
            return Result.error("层级不能为空");
        }
        if (StringUtils.isEmpty(req.getCategoryName())) {
            return Result.error("分类名称不能为空");
        }
        if (StringUtils.isEmpty(req.getCategoryType())) {
            return Result.error("分类类型不能为空");
        }

        if (req.getParentId() == 0 && req.getFloor() == 1) {
            req.setFloor(1);
        } else {
            CourseCategory parentCategory = dao.getById(req.getParentId());
            if (ObjectUtil.isNull(parentCategory)) {
                return Result.error("找不到父分类信息");
            }
            req.setFloor(req.getFloor() + 1);
        }
        CourseCategory record = BeanUtil.copyProperties(req, CourseCategory.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    public Result<Integer> delete(CourseCategoryDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        List<CourseCategory> list = dao.listByParentId(req.getId());
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.error("请先删除下级分类");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    public Result<Integer> update(CourseCategoryUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        CourseCategory record = BeanUtil.copyProperties(req, CourseCategory.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    public Result<CourseCategoryViewRESQ> view(CourseCategoryViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        CourseCategory parentCategory = dao.getById(req.getId());
        if (ObjectUtil.isNull(parentCategory)) {
            return Result.error("找不到父分类信息");
        }
        return Result.success(BeanUtil.copyProperties(parentCategory, CourseCategoryViewRESQ.class));
    }

    /**
     * 递归展示分类
     *
     * @author WY
     */
    private List<CourseCategoryPageRESQ> recursionList(Long parentId) {
        List<CourseCategoryPageRESQ> list = new ArrayList<>();
        List<CourseCategory> CourseCategoryList = dao.listByParentId(parentId);
        if (CollectionUtil.isNotEmpty(CourseCategoryList)) {
            for (CourseCategory courseCategory : CourseCategoryList) {
                CourseCategoryPageRESQ resq = BeanUtil.copyProperties(courseCategory, CourseCategoryPageRESQ.class);
                resq.setChildren(recursionList(courseCategory.getId()));
                list.add(resq);
            }
        }
        return list;
    }
}
