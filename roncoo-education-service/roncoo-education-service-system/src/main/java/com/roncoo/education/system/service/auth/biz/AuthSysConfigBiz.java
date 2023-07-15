package com.roncoo.education.system.service.auth.biz;

import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysConfigDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;

}
