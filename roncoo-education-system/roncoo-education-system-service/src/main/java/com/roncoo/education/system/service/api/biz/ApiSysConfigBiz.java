package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.service.api.resp.ApiSysConfigWebsiteResp;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final SysConfigCommonBiz sysConfigCommonBiz;

    @Cacheable
    public Result<ApiSysConfigWebsiteResp> website() {
        ApiSysConfigWebsiteResp resp = sysConfigCommonBiz.getSysConfig(ApiSysConfigWebsiteResp.class);
        // 公安网备案号处理
        resp.setWebsitePrnNo(getNumeric(resp.getWebsitePrn()));
        return Result.success(resp);
    }

    /**
     * 从字符串获取数字
     *
     * @param str
     * @return
     */
    private static String getNumeric(String str) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }
}
