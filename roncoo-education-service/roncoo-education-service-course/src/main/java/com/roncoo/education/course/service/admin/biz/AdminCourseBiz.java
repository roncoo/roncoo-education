package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.FreeEnum;
import com.roncoo.education.common.core.enums.PutawayEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.elasticsearch.EsCourse;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseViewResp;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-课程信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCourseBiz extends BaseBiz {

    @NotNull
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @NotNull
    private final IFeignLecturer feignLecturer;

    @NotNull
    private final CourseDao dao;
    @NotNull
    private final CategoryDao categoryDao;

    /**
     * 课程信息分页
     *
     * @param req 课程信息分页查询参数
     * @return 课程信息分页查询结果
     */
    public Result<Page<AdminCoursePageResp>> page(AdminCoursePageReq req) {
        CourseExample example = new CourseExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getCourseName())) {
            c.andCourseNameLike(PageUtil.rightLike(req.getCourseName()));
        }
        example.setOrderByClause("course_sort asc, id desc");
        Page<Course> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCoursePageResp> respPage = PageUtil.transform(page, AdminCoursePageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> lecturerIdList = respPage.getList().stream().map(AdminCoursePageResp::getLecturerId).collect(Collectors.toList());
            Map<Long, String> lecturerNameMap = feignLecturer.listByIds(lecturerIdList);

            List<Long> categoryIdList = respPage.getList().stream().map(AdminCoursePageResp::getCategoryId).collect(Collectors.toList());
            Map<Long, String> categoryNameMap = categoryDao.listByIds(categoryIdList).stream().collect(Collectors.toMap(Category::getId, Category::getCategoryName));

            for (AdminCoursePageResp resp : respPage.getList()) {
                resp.setLecturerName(lecturerNameMap.get(resp.getLecturerId()));
                resp.setCategoryName(categoryNameMap.get(resp.getCategoryId()));
            }
        }
        return Result.success(respPage);
    }

    /**
     * 课程信息添加
     *
     * @param req 课程信息
     * @return 添加结果
     */
    public Result<String> save(AdminCourseSaveReq req) {
        if (req.getCoursePrice().compareTo(BigDecimal.ZERO) > 0) {
            req.setIsFree(FreeEnum.CHARGE.getCode());
        } else {
            req.setIsFree(FreeEnum.FREE.getCode());
        }
        if (req.getCoursePrice().compareTo(req.getRulingPrice()) > 0) {
            req.setRulingPrice(req.getCoursePrice());
        }
        Course record = BeanUtil.copyProperties(req, Course.class);
        if (dao.save(record) > 0) {
            EsCourse esCourse = BeanUtil.copyProperties(record, EsCourse.class);
            elasticsearchRestTemplate.index(new IndexQueryBuilder().withObject(esCourse).build(), IndexCoordinates.of(EsCourse.COURSE));
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
        if (ObjectUtil.isNotNull(req.getCoursePrice()) && req.getCoursePrice().compareTo(BigDecimal.ZERO) > 0) {
            req.setIsFree(FreeEnum.CHARGE.getCode());
        } else {
            req.setIsFree(FreeEnum.FREE.getCode());
        }
        if (ObjectUtil.isNotNull(req.getCoursePrice()) && req.getCoursePrice().compareTo(req.getRulingPrice()) > 0) {
            req.setRulingPrice(req.getCoursePrice());
        }
        Course record = BeanUtil.copyProperties(req, Course.class);
        if (dao.updateById(record) > 0) {
            EsCourse esCourse = BeanUtil.copyProperties(record, EsCourse.class);
            elasticsearchRestTemplate.index(new IndexQueryBuilder().withObject(esCourse).build(), IndexCoordinates.of(EsCourse.COURSE));
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
            if (ObjectUtil.isNotNull(elasticsearchRestTemplate)) {
                elasticsearchRestTemplate.delete(id.toString(), EsCourse.class);
            }
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    public Result<String> syncEs() {
        // 获取全部课程
        CourseExample example = new CourseExample();
        example.createCriteria().andIsPutawayEqualTo(PutawayEnum.UP.getCode()).andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        List<Course> courseList = dao.listByExample(example);
        if (CollUtil.isNotEmpty(courseList)) {
            List<IndexQuery> queries = new ArrayList<>();
            for (Course course : courseList) {
                EsCourse esCourse = BeanUtil.copyProperties(course, EsCourse.class);
                queries.add(new IndexQueryBuilder().withObject(esCourse).build());
            }
            // 更新es
            elasticsearchRestTemplate.indexOps(EsCourse.class).delete();
            elasticsearchRestTemplate.bulkIndex(queries, IndexCoordinates.of(EsCourse.COURSE));
        }
        return Result.success("操作成功");
    }
}
