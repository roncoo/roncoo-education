package com.roncoo.education.course.service.api.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserStudyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * API-课程用户学习日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class ApiUserStudyBiz extends BaseBiz {

    @NotNull
    private final UserStudyDao dao;

}
