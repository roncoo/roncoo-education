package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.PutawayEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.elasticsearch.EsCourse;
import com.roncoo.education.common.elasticsearch.EsPageUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.api.req.ApiCoursePageReq;
import com.roncoo.education.course.service.api.resp.ApiCategoryResp;
import com.roncoo.education.course.service.api.resp.ApiCoursePageResp;
import com.roncoo.education.course.service.biz.req.CourseReq;
import com.roncoo.education.course.service.biz.resp.CourseChapterPeriodResp;
import com.roncoo.education.course.service.biz.resp.CourseChapterResp;
import com.roncoo.education.course.service.biz.resp.CourseLecturerResp;
import com.roncoo.education.course.service.biz.resp.CourseResp;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * API-课程信息
 *
 * @author wujing
 */
@Component
@CacheConfig(cacheNames = {"course"})
@RequiredArgsConstructor
public class ApiCourseBiz extends BaseBiz {

    @NotNull
    private final CourseDao dao;
    @NotNull
    private final CourseChapterDao chapterDao;
    @NotNull
    private final CourseChapterPeriodDao periodDao;
    @NotNull
    private final CategoryDao categoryDao;

    @NotNull
    private final IFeignLecturer feignLecturer;

    @NotNull
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    public Result<Page<ApiCoursePageResp>> searchForPage(ApiCoursePageReq req) {
        NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
        // 高亮字段
        nsb.withHighlightFields(new HighlightBuilder.Field("courseName").preTags("<mark>").postTags("</mark>"));
        // 分页
        nsb.withPageable(PageRequest.of(req.getPageCurrent() - 1, req.getPageSize()));
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if (ObjectUtil.isNotEmpty(req.getCategoryId())) {
            List<Long> categoryIdList = listCategoryId(req.getCategoryId());
            qb.must(QueryBuilders.termsQuery("categoryId", categoryIdList));
        }
        if (ObjectUtil.isNotEmpty(req.getIsFree())) {
            qb.must(QueryBuilders.termQuery("isFree", req.getIsFree()));
        }
        if (StringUtils.hasText(req.getCourseName())) {
            // 模糊查询multiMatchQuery，最佳字段best_fields
            qb.must(QueryBuilders.multiMatchQuery(req.getCourseName(), "courseName").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        } else {
            // 课程排序（courseSort）
            nsb.withSort(new FieldSortBuilder("courseSort").order(SortOrder.ASC));
            nsb.withSort(new FieldSortBuilder("id").order(SortOrder.DESC));
        }
        nsb.withQuery(qb);
        SearchHits<EsCourse> searchHits = elasticsearchRestTemplate.search(nsb.build(), EsCourse.class, IndexCoordinates.of(EsCourse.COURSE));
        return Result.success(EsPageUtil.transform(searchHits, req.getPageCurrent(), req.getPageSize(), ApiCoursePageResp.class));
    }

    private List<Long> listCategoryId(Long categoryId) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        List<Category> categories = categoryDao.listByExample(example);
        List<Long> idList = new ArrayList<>();
        // 需要查询的ID
        idList.add(categoryId);
        filter(idList, categories, categoryId);
        return idList;
    }

    private List<ApiCategoryResp> filter(List<Long> idList, List<Category> categories, Long categoryId) {
        List<Category> list = categories.stream().filter(item -> item.getParentId().compareTo(categoryId) == 0).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(list)) {
            idList.addAll(list.stream().map(Category::getId).collect(Collectors.toList()));
            List<ApiCategoryResp> resps = BeanUtil.copyProperties(list, ApiCategoryResp.class);
            for (ApiCategoryResp resp : resps) {
                resp.setList(filter(idList, categories, resp.getId()));
            }
            return resps;
        }
        return new ArrayList<>();
    }


    @Cacheable
    public Result<CourseResp> view(CourseReq req) {
        Course course = dao.getById(req.getCourseId());
        if (course == null) {
            return Result.error("找不到该课程信息");
        }
        if (!course.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            return Result.error("该课程已被禁用");
        }
        if (course.getIsPutaway().equals(PutawayEnum.DOWN.getCode())) {
            return Result.error("该课程已下架");
        }
        CourseResp courseResp = BeanUtil.copyProperties(course, CourseResp.class);
        // 获取讲师信息
        LecturerViewVO lecturerViewVO = feignLecturer.getById(course.getLecturerId());
        if (ObjectUtil.isNotEmpty(lecturerViewVO)) {
            courseResp.setLecturerResp(BeanUtil.copyProperties(lecturerViewVO, CourseLecturerResp.class));
        }
        // 章节信息
        List<CourseChapter> chapterList = chapterDao.listByCourseIdAndStatusId(course.getId(), StatusIdEnum.YES.getCode());
        if (CollUtil.isNotEmpty(chapterList)) {
            courseResp.setChapterRespList(BeanUtil.copyProperties(chapterList, CourseChapterResp.class));
            // 课时信息
            List<CourseChapterPeriod> periodList = periodDao.listByCourseIdAndStatusId(course.getId(), StatusIdEnum.YES.getCode());
            if (CollUtil.isNotEmpty(periodList)) {
                Map<Long, List<CourseChapterPeriod>> map = periodList.stream().collect(Collectors.groupingBy(CourseChapterPeriod::getChapterId, Collectors.toList()));
                for (CourseChapterResp chapterResp : courseResp.getChapterRespList()) {
                    chapterResp.setPeriodRespList(BeanUtil.copyProperties(map.get(chapterResp.getId()), CourseChapterPeriodResp.class));
                }
            }
        }
        return Result.success(courseResp);
    }

}
