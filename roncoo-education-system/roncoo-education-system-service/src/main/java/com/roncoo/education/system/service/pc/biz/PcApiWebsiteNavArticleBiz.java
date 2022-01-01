package com.roncoo.education.system.service.pc.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.service.pc.req.WebsiteNavArticleSaveREQ;
import com.roncoo.education.system.service.pc.req.WebsiteNavArticleUpdateREQ;
import com.roncoo.education.system.service.pc.req.WebsiteNavArticleViewREQ;
import com.roncoo.education.system.service.pc.resq.WebsiteNavArticleViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 站点导航文章
 */
@Component
public class PcApiWebsiteNavArticleBiz {

    @Autowired
    private WebsiteNavArticleDao dao;

    public Result<WebsiteNavArticleViewRESQ> view(WebsiteNavArticleViewREQ req) {
        if (req.getNavId() == null) {
            return Result.error("NavId不能为空");
        }
        WebsiteNavArticle record = dao.getByNavId(req.getNavId());
        return Result.success(BeanUtil.copyProperties(record, WebsiteNavArticleViewRESQ.class));
    }

    public Result<Integer> save(WebsiteNavArticleSaveREQ req) {
        if (req.getNavId() == null) {
            return Result.error("NavId不能为空");
        }
        WebsiteNavArticle record = BeanUtil.copyProperties(req, WebsiteNavArticle.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    public Result<Integer> update(WebsiteNavArticleUpdateREQ req) {
        if (req.getId() == null) {
            return Result.error("Id不能为空");
        }
        WebsiteNavArticle record = BeanUtil.copyProperties(req, WebsiteNavArticle.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

}
