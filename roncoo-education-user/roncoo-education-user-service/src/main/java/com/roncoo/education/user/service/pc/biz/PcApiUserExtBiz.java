package com.roncoo.education.user.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.user.dao.UserExtDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExtExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExtExample.Criteria;
import com.roncoo.education.user.service.pc.req.UserExtPageREQ;
import com.roncoo.education.user.service.pc.req.UserExtUpdateREQ;
import com.roncoo.education.user.service.pc.req.UserExtViewREQ;
import com.roncoo.education.user.service.pc.resq.UserExtPageRESQ;
import com.roncoo.education.user.service.pc.resq.UserExtViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 用户教育信息
 */
@Component
public class PcApiUserExtBiz {

    @Autowired
    private UserExtDao dao;

    /**
     * 用戶教育分页列出
     *
     * @param req
     * @return
     */
    public Result<Page<UserExtPageRESQ>> listForPage(UserExtPageREQ req) {
        UserExtExample example = new UserExtExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileLike(PageUtil.rightLike(req.getMobile()));
        }
        if (StringUtils.hasText(req.getNickname())) {
            c.andNicknameLike(PageUtil.like(req.getNickname()));
        }
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        if (StringUtils.hasText(req.getBeginGmtCreate())) {
            c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginGmtCreate(), "yyyy-MM-dd"));
        }
        if (StringUtils.hasText(req.getEndGmtCreate())) {
            c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndGmtCreate(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause(" status_id desc, id desc ");
        Page<UserExt> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, UserExtPageRESQ.class));
    }

    /**
     * 用户教育更新
     *
     * @param req
     * @return
     */
    public Result<Integer> update(UserExtUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("主键不能为空");
        }
        UserExt userExt = dao.getById(req.getId());
        if (ObjectUtil.isNull(userExt)) {
            return Result.error("找不到用户信息");
        }
        UserExt record = BeanUtil.copyProperties(req, UserExt.class);
        int results = dao.updateById(record);
        if (results < 0) {
            return Result.error(ResultEnum.USER_UPDATE_FAIL);
        }
        return Result.success(results);
    }

    /**
     * 查找用户信息
     *
     * @param req
     * @return
     */
    public Result<UserExtViewRESQ> view(UserExtViewREQ req) {
        UserExt record = new UserExt();
        if (req.getId() != null) {
            record = dao.getById(req.getId());
        }
        if (req.getOrderUserNo() != null) {
            record = dao.getByUserNo(req.getOrderUserNo());
        }
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到用户信息");
        }
        return Result.success(BeanUtil.copyProperties(record, UserExtViewRESQ.class));
    }

}
