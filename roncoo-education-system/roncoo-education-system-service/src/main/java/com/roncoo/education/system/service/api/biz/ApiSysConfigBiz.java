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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        ApiSysConfigWebsiteResp resp = BeanUtil.objToBean(map, ApiSysConfigWebsiteResp.class);
        // 公安网备案号处理
        resp.setWebsitePrnNo(getNumeric(resp.getWebsitePrn()));
        return Result.success(resp);
    }

    /**
     * 从字符串获取数字
     * @param str
     * @return
     */
    private static String getNumeric(String str) {
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
