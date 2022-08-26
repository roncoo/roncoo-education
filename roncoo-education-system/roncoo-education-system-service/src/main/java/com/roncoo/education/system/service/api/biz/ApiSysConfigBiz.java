package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import com.roncoo.education.system.service.api.resp.ApiSysConfigWebsiteResp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AUTH-系统配置
 *
 * @author wujing
 */
@Component
@CacheConfig(cacheNames = {"system"})
@RequiredArgsConstructor
public class ApiSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;
    @NotNull
    private final CacheRedis cacheRedis;

    @Cacheable
    public Result<ApiSysConfigWebsiteResp> website() {
        SysConfigExample example = new SysConfigExample();
        List<SysConfig> sysConfigs = dao.listByExample(example);
        Map<String, String> map = sysConfigs.stream().collect(Collectors.toMap(SysConfig::getConfigKey, SysConfig::getConfigValue));
        return Result.success(BeanUtil.objToBean(map, ApiSysConfigWebsiteResp.class));
    }
}
