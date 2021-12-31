package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.IsHfield;
import com.roncoo.education.common.core.enums.IsPutawayEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.SqlUtil;
import com.roncoo.education.common.es.EsCourse;
import com.roncoo.education.common.es.EsPageUtil;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.CourseIntroduceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.course.service.api.bo.CourseInfoPageBO;
import com.roncoo.education.course.service.api.bo.CourseInfoSearchBO;
import com.roncoo.education.course.service.api.bo.CourseVideoBO;
import com.roncoo.education.course.service.api.dto.*;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder.Field;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class ApiCourseBiz {

    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseIntroduceDao courseIntroduceDao;
    @Autowired
    private CourseChapterDao courseChapterDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;
    @Autowired
    private IFeignLecturer bossLecturer;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    /**
     * 课程详情接口
     *
     * @param courseView
     * @return
     */
    public Result<CourseViewDTO> view(CourseVideoBO courseVideoBO) {
        if (courseVideoBO.getCourseId() == null) {
            return Result.error("课程ID不能为空");
        }
        // 课程信息
        Course course = courseDao.getById(courseVideoBO.getCourseId());
        if (course == null) {
            return Result.error("找不到该课程信息");
        }
        CourseViewDTO data = BeanUtil.copyProperties(course, CourseViewDTO.class);

        // 课程介绍
        CourseIntroduce courseIntroduce = courseIntroduceDao.getById(data.getIntroduceId());
        if (!StringUtils.isEmpty(courseIntroduce)) {
            data.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());
        }

        // 讲师信息
        LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(data.getLecturerUserNo());
        if (StringUtils.isEmpty(lecturerVO)) {
            return Result.error("根据讲师用户编号没找到对应的讲师信息!");
        }
        data.setLecturer(BeanUtil.copyProperties(lecturerVO, LecturerDTO.class));

        // 章节信息
        List<CourseChapter> courseChapterList = courseChapterDao.listByCourseIdAndStatusId(courseVideoBO.getCourseId(), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(courseChapterList)) {
            data.setChapterList(PageUtil.copyList(courseChapterList, CourseChapterDTO.class));
        }

        // 课时信息
        if (CollectionUtil.isNotEmpty(data.getChapterList())) {
            for (CourseChapterDTO courseChapterDTO : data.getChapterList()) {
                List<CourseChapterPeriod> courseChapterPeriodList = courseChapterPeriodDao.listByChapterIdAndStatusId(courseChapterDTO.getId(), StatusIdEnum.YES.getCode());
                courseChapterDTO.setPeriodList(PageUtil.copyList(courseChapterPeriodList, CourseChapterPeriodDTO.class));
            }
        }
        return Result.success(data);
    }

    /**
     * 课程信息列表接口
     *
     * @param courseInfoPageBO
     * @return
     * @author wuyun
     */
    public Result<Page<CourseInfoPageDTO>> list(CourseInfoPageBO courseInfoPageBO) {
        CourseExample example = new CourseExample();
        Criteria c = example.createCriteria();
        c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        c.andIsPutawayEqualTo(IsPutawayEnum.YES.getCode());
        if (!StringUtils.isEmpty(courseInfoPageBO.getCategoryId1())) {
            c.andCategoryId1EqualTo(courseInfoPageBO.getCategoryId1());
        }
        if (!StringUtils.isEmpty(courseInfoPageBO.getCategoryId2())) {
            c.andCategoryId2EqualTo(courseInfoPageBO.getCategoryId2());
        }
        if (!StringUtils.isEmpty(courseInfoPageBO.getCategoryId3())) {
            c.andCategoryId3EqualTo(courseInfoPageBO.getCategoryId3());
        }
        if (!StringUtils.isEmpty(courseInfoPageBO.getIsFree())) {
            c.andIsFreeEqualTo(courseInfoPageBO.getIsFree());
        }
        if (!StringUtils.isEmpty(courseInfoPageBO.getCourseName())) {
            c.andCourseNameLike(PageUtil.rightLike(SqlUtil.checkSql(courseInfoPageBO.getCourseName())));
        }
        example.setOrderByClause(" sort desc, id desc ");
        Page<Course> page = courseDao.listForPage(courseInfoPageBO.getPageCurrent(), courseInfoPageBO.getPageSize(), example);
        return Result.success(PageUtil.transform(page, CourseInfoPageDTO.class));
    }

    /**
     * 课程搜索列表接口
     *
     * @param bo
     * @author wuyun
     */
    public Result<Page<CourseInfoSearchPageDTO>> searchList(CourseInfoSearchBO bo) {
        if (bo.getPageCurrent() <= 0) {
            bo.setPageCurrent(1);
        }
        if (bo.getPageSize() <= 0) {
            bo.setPageSize(20);
        }

        if (StringUtils.isEmpty(bo.getCourseName())) {
            return Result.success(new Page<CourseInfoSearchPageDTO>());
        }

        Field hfield = null;
        if (bo.getIsHfield() != null && bo.getIsHfield().equals(IsHfield.YES.getCode())) {
            hfield = new HighlightBuilder.Field("courseName").preTags("<mark>").postTags("</mark>");
        }

        NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
        if (bo.getIsHfield() != null && bo.getIsHfield().equals(IsHfield.YES.getCode())) {
            nsb.withHighlightFields(hfield);// 高亮字段
        }
        nsb.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));// 评分排序（_source）
        nsb.withSort(new FieldSortBuilder("courseSort").order(SortOrder.DESC));// 课程排序（courseSort）
        nsb.withPageable(PageRequest.of(bo.getPageCurrent() - 1, bo.getPageSize()));
        // 复合查询，外套boolQuery
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        // 模糊查询multiMatchQuery，最佳字段best_fields
        qb.must(QueryBuilders.multiMatchQuery(bo.getCourseName(), "courseName", "lecturerName").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        nsb.withQuery(qb);
        SearchHits<EsCourse> searchHits = elasticsearchRestTemplate.search(nsb.build(), EsCourse.class, IndexCoordinates.of(EsCourse.COURSE));
        return Result.success(EsPageUtil.transform(searchHits, bo.getPageCurrent(), bo.getPageSize(), CourseInfoSearchPageDTO.class));
    }
}
