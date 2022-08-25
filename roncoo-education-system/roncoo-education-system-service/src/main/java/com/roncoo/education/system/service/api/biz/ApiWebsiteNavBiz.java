package com.roncoo.education.system.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.ArrayListUtil;
import com.roncoo.education.system.dao.WebsiteNavDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
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

    public Result<List<WebsiteNavResp>> list() {
        List<WebsiteNav> list = websiteNavDao.listByStatusId(StatusIdEnum.YES.getCode());
        return Result.success(ArrayListUtil.copy(list, WebsiteNavResp.class));
    }

}
