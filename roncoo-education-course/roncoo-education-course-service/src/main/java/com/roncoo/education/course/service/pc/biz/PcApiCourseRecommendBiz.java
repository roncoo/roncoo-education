package com.roncoo.education.course.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseCategoryDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.CourseRecommendDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseRecommend;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseRecommendExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseRecommendExample.Criteria;
import com.roncoo.education.course.service.pc.req.*;
import com.roncoo.education.course.service.pc.resq.CourseRecommendPageRESQ;
import com.roncoo.education.course.service.pc.resq.CourseRecommendViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 课程推荐
 */
@Component
public class PcApiCourseRecommendBiz {

    @Autowired
    private CourseCategoryDao courseCategoryDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseRecommendDao dao;

    public Result<Page<CourseRecommendPageRESQ>> listForPage(CourseRecommendPageREQ req) {
        CourseRecommendExample example = new CourseRecommendExample();
        Criteria c = example.createCriteria();
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        c.andCategoryIdEqualTo(req.getCategoryId());
        example.setOrderByClause("status_id desc, sort desc, id desc");
        Page<CourseRecommend> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<CourseRecommendPageRESQ> listForPage = PageUtil.transform(page, CourseRecommendPageRESQ.class);
        for (CourseRecommendPageRESQ resq : listForPage.getList()) {
            Course course = courseDao.getById(resq.getCourseId());
            if (ObjectUtil.isNotNull(course)) {
                resq.setCourseName(course.getCourseName());
            }
        }
        return Result.success(listForPage);
    }

    /**
     * 课程推荐添加
     *
     * @param req
     * @return
     */
    public Result<Integer> save(CourseRecommendSaveREQ req) {
        if (StringUtils.isEmpty(req.getCategoryId())) {
            return Result.error("分类ID不能为空");
        }
        if (StringUtils.isEmpty(req.getCourseId())) {
            return Result.error("课程ID不能为空");
        }
        // 查找推荐信息
        CourseCategory courseCategory = courseCategoryDao.getById(req.getCategoryId());
        if (ObjectUtil.isNull(courseCategory)) {
            return Result.error("找不到分类信息");
        }
        // 查找课程信息
        Course course = courseDao.getById(req.getCourseId());
        if (ObjectUtil.isNull(course) && !StatusIdEnum.YES.getCode().equals(course.getStatusId())) {
            return Result.error("找不到课程信息");
        }
        // 根据分类ID、课程ID获取推荐课程信息
        CourseRecommend courseRecommend = dao.getByCategoryIdAndCourseId(req.getCategoryId(), req.getCourseId());
        if (ObjectUtil.isNotNull(courseRecommend)) {
            return Result.error("课程已添加推荐");
        }

        // 校验分类下只能添加5课推荐课程
        List<CourseRecommend> list = dao.listByCategoryIdAndStatusId(req.getCategoryId(), StatusIdEnum.YES.getCode());
        if (list.size() >= 5) {
            return Result.error("课程只展示5个");
        }
        CourseRecommend record = BeanUtil.copyProperties(req, CourseRecommend.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 课程推荐删除
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(CourseRecommendDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 课程推荐更新
     *
     * @param req
     * @return
     */
    public Result<Integer> update(CourseRecommendUpdateREQ req) {
        CourseRecommend record = BeanUtil.copyProperties(req, CourseRecommend.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
    }

    /**
     * 课程推荐查看
     *
     * @param req
     * @return
     */
    public Result<CourseRecommendViewRESQ> view(CourseRecommendViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        CourseRecommend record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到推荐信息");
        }
        return Result.success(BeanUtil.copyProperties(record, CourseRecommendViewRESQ.class));
    }

}
