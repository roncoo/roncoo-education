package com.roncoo.education.system.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.MsgUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.system.service.pc.req.MsgUserDeleteREQ;
import com.roncoo.education.system.service.pc.req.MsgUserPageREQ;
import com.roncoo.education.system.service.pc.req.MsgUserViewREQ;
import com.roncoo.education.system.service.pc.resq.MsgUserPageRESQ;
import com.roncoo.education.system.service.pc.resq.MsgUserViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 站内信用户记录
 */
@Component
public class PcApiMsgUserBiz {

    @Autowired
    private MsgUserDao dao;

    public Result<Page<MsgUserPageRESQ>> list(MsgUserPageREQ req) {
        MsgUserExample example = new MsgUserExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMsgTitle())) {
            c.andMsgTitleLike(PageUtil.like(req.getMsgTitle()));
        }
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileLike(PageUtil.like(req.getMobile()));
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<MsgUser> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, MsgUserPageRESQ.class));
    }

    public Result<Integer> delete(MsgUserDeleteREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        int result = dao.deleteById(req.getId());
        if (result < 0) {
            return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
        }
        return Result.success(result);
    }

    public Result<MsgUserViewRESQ> view(MsgUserViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        MsgUser record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到用户消息记录");
        }
        return Result.success(BeanUtil.copyProperties(record, MsgUserViewRESQ.class));
    }

}
