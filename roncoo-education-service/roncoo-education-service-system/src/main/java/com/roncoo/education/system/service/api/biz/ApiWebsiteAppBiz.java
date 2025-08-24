package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAppDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteApp;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAppExample;
import com.roncoo.education.system.service.api.req.ApiWebsiteAppReq;
import com.roncoo.education.system.service.api.resp.ApiWebsiteAppResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;

/**
 * API-app版本管理
 *
 * @author fengyw
 */
@Component
@RequiredArgsConstructor
public class ApiWebsiteAppBiz extends BaseBiz {

    @NotNull
    private final WebsiteAppDao dao;

    /**
     * @param req
     * @return
     */
    public Result<ApiWebsiteAppResp> check(ApiWebsiteAppReq req) {
        WebsiteAppExample example = new WebsiteAppExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode()).andAppTypeEqualTo(req.getAppType()).andPublishTimeLessThan(LocalDateTime.now());
        example.setOrderByClause("publish_time desc");
        List<WebsiteApp> appList = dao.listByExample(example);
        ApiWebsiteAppResp resp = new ApiWebsiteAppResp();
        resp.setNeedUpdate(false);
        if (appList.isEmpty()) {
            // 不需要更新
            return Result.success(resp);
        }
        if (compareVersion(req.getAppVersion(), appList.get(0).getAppVersion()) >= 0) {
            // 等于或者大于不用更新
            return Result.success(resp);
        }
        resp = BeanUtil.copyProperties(appList.get(0), ApiWebsiteAppResp.class);
        resp.setNeedUpdate(true);
        return Result.success(resp);
    }

    /**
     * 比较版本号
     *
     * @param version1 当前版本
     * @param version2 基准版本
     * @return -1当前版本较低 0版本一样 1当前版本较高
     */
    private static int compareVersion(String version1, String version2) {
        String[] array1 = version1.split("\\.");
        String[] array2 = version2.split("\\.");
        int max = Math.max(array1.length, array2.length);
        for (int i = 0; i < max; i++) {
            int a1 = i < array1.length ? Integer.parseInt(array1[i]) : 0;
            int a2 = i < array2.length ? Integer.parseInt(array2[i]) : 0;
            if (a1 == a2) {
                continue;
            }
            if (a1 > a2) {
                return 1;
            } else {
                return -1;
            }
        }
        return 0;
    }
}
