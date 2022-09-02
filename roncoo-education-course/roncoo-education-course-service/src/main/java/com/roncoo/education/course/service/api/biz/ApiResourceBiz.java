package com.roncoo.education.course.service.api.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ResourceDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * API-课程视频信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class ApiResourceBiz extends BaseBiz {

    @NotNull
    private final ResourceDao dao;

}
