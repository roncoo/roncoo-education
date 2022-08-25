package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ZoneDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-专区
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthZoneBiz extends BaseBiz {

    @NotNull
    private final ZoneDao dao;

}
