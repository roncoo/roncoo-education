package com.roncoo.education.user.service.api.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.elasticsearch.EsLecturer;
import com.roncoo.education.common.elasticsearch.EsPageUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.api.req.LecturerPageReq;
import com.roncoo.education.user.service.api.resp.LecturerPageResp;
import com.roncoo.education.user.service.api.resp.LecturerViewResp;
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

/**
 * 用户基本信息
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"user"})
public class ApiLecturerBiz extends BaseBiz {

    @NotNull
    private final ElasticsearchRestTemplate elasticsearchRestTemplate;

    @NotNull
    private final LecturerDao dao;

    @NotNull
    private final IFeignCourse feignCourse;

    @Cacheable
    public Result<Page<LecturerPageResp>> search(LecturerPageReq req) {
        NativeSearchQueryBuilder nsb = new NativeSearchQueryBuilder();
        // 高亮字段
        nsb.withHighlightFields(new HighlightBuilder.Field("lecturerName").preTags("<mark>").postTags("</mark>"));
        // 分页
        nsb.withPageable(PageRequest.of(req.getPageCurrent() - 1, req.getPageSize()));
        BoolQueryBuilder qb = QueryBuilders.boolQuery();
        if (StringUtils.hasText(req.getLecturerName())) {
            // 模糊查询multiMatchQuery，最佳字段best_fields
            qb.must(QueryBuilders.multiMatchQuery(req.getLecturerName(), "lecturerName").type(MultiMatchQueryBuilder.Type.BEST_FIELDS));
        } else {
            // 讲师排序（sort）
            nsb.withSorts(new FieldSortBuilder("sort").order(SortOrder.ASC));
            nsb.withSorts(new FieldSortBuilder("id").order(SortOrder.DESC));
        }
        qb.must(QueryBuilders.termQuery("statusId", StatusIdEnum.YES.getCode()));
        nsb.withQuery(qb);
        SearchHits<EsLecturer> searchHits = elasticsearchRestTemplate.search(nsb.build(), EsLecturer.class, IndexCoordinates.of(EsLecturer.LECTURER));
        return Result.success(EsPageUtil.transform(searchHits, req.getPageCurrent(), req.getPageSize(), LecturerPageResp.class));
    }

    @Cacheable
    public Result<LecturerViewResp> view(Long id) {
        Lecturer lecturer = dao.getById(id);
        if (ObjectUtil.isNull(lecturer) || !lecturer.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            return Result.error("讲师不存在或者已经禁用");
        }
        LecturerViewResp resp = BeanUtil.copyProperties(lecturer, LecturerViewResp.class);
        resp.setCourseViewVO(feignCourse.listByLecturerId(lecturer.getId()));
        return Result.success(resp);
    }
}
