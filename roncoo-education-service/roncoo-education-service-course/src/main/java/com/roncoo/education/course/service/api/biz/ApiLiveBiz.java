package com.roncoo.education.course.service.api.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.LiveDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

/**
 * API-直播信息
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
public class ApiLiveBiz extends BaseBiz {

    @NotNull
    private final LiveDao dao;

}
