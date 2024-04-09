package com.roncoo.education.user.service.api.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.user.service.api.req.LecturerPageReq;
import com.roncoo.education.user.service.api.resp.LecturerPageResp;
import com.roncoo.education.user.service.api.resp.LecturerViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
    private final LecturerDao dao;

    @NotNull
    private final IFeignCourse feignCourse;

    @Cacheable
    public Result<Page<LecturerPageResp>> search(LecturerPageReq req) {
        LecturerExample example = new LecturerExample();
        LecturerExample.Criteria c = example.createCriteria();
        c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        if (StringUtils.hasText(req.getLecturerName())) {
            c.andLecturerNameLike(PageUtil.rightLike(req.getLecturerName()));
        }
        example.setOrderByClause("sort asc, id desc");
        Page<Lecturer> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<LecturerPageResp> respPage = PageUtil.transform(page, LecturerPageResp.class);
        return Result.success(respPage);
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
