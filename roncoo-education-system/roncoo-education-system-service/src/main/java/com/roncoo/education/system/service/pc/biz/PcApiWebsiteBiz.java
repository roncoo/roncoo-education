package com.roncoo.education.system.service.pc.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteDao;
import com.roncoo.education.system.dao.impl.mapper.entity.Website;
import com.roncoo.education.system.service.pc.req.WebsiteUpdateREQ;
import com.roncoo.education.system.service.pc.resq.WebsiteViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点信息
 */
@Component
public class PcApiWebsiteBiz {

    @Autowired
    private WebsiteDao dao;

    public Result<WebsiteViewRESQ> view() {
        Website website = dao.getWebsite();
        return Result.success(BeanUtil.copyProperties(website, WebsiteViewRESQ.class));
    }

    public Result<Integer> update(WebsiteUpdateREQ req) {
        Website record = BeanUtil.copyProperties(req, Website.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

}
