package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteAdvDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdv;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAdvExample;
import com.roncoo.education.system.service.api.resp.ApiWebsiteAdvResp;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * API-广告信息
 *
 * @author wujing
 */
@Component
@CacheConfig(cacheNames = {"system"})
@RequiredArgsConstructor
public class ApiWebsiteAdvBiz extends BaseBiz {

    @NotNull
    private final WebsiteAdvDao dao;

    @Cacheable
    public Result<List<ApiWebsiteAdvResp>> list() {
        WebsiteAdvExample example = new WebsiteAdvExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        List<WebsiteAdv> advList = dao.listByExample(example);
        return Result.success(PageUtil.copyList(advList, ApiWebsiteAdvResp.class));
    }
}
