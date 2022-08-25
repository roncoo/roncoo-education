package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.RegionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-行政区域表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthRegionBiz extends BaseBiz {

    @NotNull
    private final RegionDao dao;

}
