package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserStudyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程用户学习日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthUserStudyBiz extends BaseBiz {

    @NotNull
    private final UserStudyDao dao;

}
