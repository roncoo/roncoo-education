package com.roncoo.education.system.service.api.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.system.dao.WebsiteNavDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.service.api.dto.WebsiteNavDTO;
import com.roncoo.education.system.service.api.dto.WebsiteNavListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Component
public class ApiWebsiteNavBiz {

    @Autowired
    private WebsiteNavDao websiteNavDao;

    public Result<WebsiteNavListDTO> list() {
        WebsiteNavListDTO dto = new WebsiteNavListDTO();
        List<WebsiteNav> list = websiteNavDao.listByStatusIdAndParentId(StatusIdEnum.YES.getCode(), 0L);
        if (CollectionUtil.isNotEmpty(list)) {
            List<WebsiteNavDTO> websiteNavList = PageUtil.copyList(list, WebsiteNavDTO.class);
            for (WebsiteNavDTO webSiteNav : websiteNavList) {
                list = websiteNavDao.listByStatusIdAndParentId(StatusIdEnum.YES.getCode(), webSiteNav.getId());
                webSiteNav.setWebsiteNavList(PageUtil.copyList(list, WebsiteNavDTO.class));
            }
            dto.setWebsiteNavList(websiteNavList);
        }
        return Result.success(dto);
    }

}
