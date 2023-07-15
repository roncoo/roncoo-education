package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.WebsiteCarouselDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarousel;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarouselExample;
import com.roncoo.education.system.service.api.resp.ApiWebsiteCarouselResp;
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
@RequiredArgsConstructor
@CacheConfig(cacheNames = {"system"})
public class ApiWebsiteCarouselBiz extends BaseBiz {

    @NotNull
    private final WebsiteCarouselDao dao;

    @Cacheable
    public Result<List<ApiWebsiteCarouselResp>> list() {
        WebsiteCarouselExample example = new WebsiteCarouselExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        example.setOrderByClause("sort asc, id desc");
        List<WebsiteCarousel> CarouselList = dao.listByExample(example);
        return Result.success(PageUtil.copyList(CarouselList, ApiWebsiteCarouselResp.class));
    }
}
