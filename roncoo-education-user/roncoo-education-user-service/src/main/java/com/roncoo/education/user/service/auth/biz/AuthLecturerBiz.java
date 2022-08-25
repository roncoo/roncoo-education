package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LecturerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-讲师信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthLecturerBiz extends BaseBiz {

    @NotNull
    private final LecturerDao dao;

}
