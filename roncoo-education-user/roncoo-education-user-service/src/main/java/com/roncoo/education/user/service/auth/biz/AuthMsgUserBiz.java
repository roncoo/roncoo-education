package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ReadEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgDao;
import com.roncoo.education.user.dao.MsgUserDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Msg;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.user.service.auth.req.AuthMsgUserGetReq;
import com.roncoo.education.user.service.auth.req.AuthMsgUserPageReq;
import com.roncoo.education.user.service.auth.resp.AuthMsgResp;
import com.roncoo.education.user.service.auth.resp.AuthMsgUserResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-站内信用户记录表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthMsgUserBiz extends BaseBiz {

    @NotNull
    private final MsgUserDao dao;
    @NotNull
    private final MsgDao msgDao;

    /**
     * 站内信分页列表接口
     *
     * @param req
     * @return
     * @author wuyun
     */
    public Result<Page<AuthMsgUserResp>> list(AuthMsgUserPageReq req) {
        MsgUserExample example = new MsgUserExample();
        MsgUserExample.Criteria c = example.createCriteria();
        c.andUserIdEqualTo(ThreadContext.userId());
        c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        if (req.getIsRead() != null) {
            c.andIsReadEqualTo(req.getIsRead());
        }
        example.setOrderByClause(" sort asc, id desc ");
        Page<MsgUser> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, AuthMsgUserResp.class));
    }

    /**
     * 用户查看站内信内容
     *
     * @author wuyun
     */
    public Result<AuthMsgResp> readMsg(AuthMsgUserGetReq req) {
        if (req.getId() == null) {
            return Result.error("id不能为空");
        }
        MsgUser record = dao.getById(req.getId());
        if (record == null) {
            return Result.error("找不到站内信息");
        }
        if (!record.getUserId().equals(ThreadContext.userId())) {
            return Result.error("没权限读该信息");
        }

        // 未阅读，则刷新阅读状态
        if (ReadEnum.NO.getCode().equals(record.getIsRead())) {
            record.setIsRead(ReadEnum.READ.getCode());
            dao.updateById(record);
        }

        // 返回消息实体
        Msg msg = msgDao.getById(record.getMsgId());
        if (msg == null) {
            return Result.error("查询msg有误");
        }
        return Result.success(BeanUtil.copyProperties(msg, AuthMsgResp.class));
    }

}
