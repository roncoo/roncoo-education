package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteNavDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.service.api.resp.ApiWebsiteNavResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Component
@CacheConfig(cacheNames = {"system"})
public class ApiWebsiteNavBiz {

    @Autowired
    private WebsiteNavDao websiteNavDao;

    @Cacheable
    public Result<List<ApiWebsiteNavResp>> list() {
        List<WebsiteNav> list = websiteNavDao.listByStatusId(StatusIdEnum.YES.getCode());
        return Result.success(BeanUtil.copyProperties(list, ApiWebsiteNavResp.class));
    }

}
