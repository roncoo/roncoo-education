package com.roncoo.education.user.service.pc.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.dao.UserDao;
import com.roncoo.education.user.dao.impl.mapper.entity.User;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExample.Criteria;
import com.roncoo.education.user.service.pc.req.UserPageREQ;
import com.roncoo.education.user.service.pc.resq.UserPageRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户信息
 */
@Component
public class PcApiUserBiz {

    @Autowired
    private UserDao dao;

    /**
     * 用戶分页列出
     *
     * @param userPageREQ
     * @return
     */
    public Result<Page<UserPageRESQ>> listForPage(UserPageREQ req) {
        UserExample example = new UserExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileLike(PageUtil.like(req.getMobile()));
        }
        example.setOrderByClause(" status_id desc, id desc ");
        Page<User> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, UserPageRESQ.class));
    }
}
