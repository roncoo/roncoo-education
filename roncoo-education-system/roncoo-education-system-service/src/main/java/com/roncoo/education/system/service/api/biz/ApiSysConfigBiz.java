package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysConfigDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * API-系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class ApiSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;

}
