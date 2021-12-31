package com.roncoo.education.system.service.auth.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.MsgDao;
import com.roncoo.education.system.dao.impl.mapper.entity.Msg;
import com.roncoo.education.system.service.auth.bo.MsgViewBO;
import com.roncoo.education.system.service.auth.dto.MsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Component
public class AuthApiMsgBiz {

    @Autowired
    private MsgDao dao;

    public Result<MsgDTO> view(MsgViewBO bo) {
        if (bo.getId() == null) {
            return Result.error("id不能为空");
        }
        Msg msg = dao.getById(bo.getId());
        if (ObjectUtils.isEmpty(msg)) {
            return Result.error("查询错误");
        }
        return Result.success(BeanUtil.copyProperties(msg, MsgDTO.class));
    }
}
