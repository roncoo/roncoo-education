package com.roncoo.education.user.service.auth.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Msg;
import com.roncoo.education.user.service.auth.req.AuthMsgReq;
import com.roncoo.education.user.service.auth.resp.AuthMsgResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;

/**
 * AUTH-站内信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthMsgBiz extends BaseBiz {

    @NotNull
    private final MsgDao dao;

    public Result<AuthMsgResp> get(AuthMsgReq req) {
        if (req.getId() == null) {
            return Result.error("id不能为空");
        }
        Msg msg = dao.getById(req.getId());
        if (ObjectUtils.isEmpty(msg)) {
            return Result.error("查询错误");
        }
        return Result.success(BeanUtil.copyProperties(msg, AuthMsgResp.class));
    }
}
