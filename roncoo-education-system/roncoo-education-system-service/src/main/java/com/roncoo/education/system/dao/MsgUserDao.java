package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUserExample;

public interface MsgUserDao {
    int save(MsgUser record);

    int deleteById(Long id);

    int updateById(MsgUser record);

    MsgUser getById(Long id);

    Page<MsgUser> listForPage(int pageCurrent, int pageSize, MsgUserExample example);

    int deleteByMsgId(Long id);

    /**
     * 获得学员未读消息总条数
     *
     * @param userNo
     * @param isRead
     * @return
     * @author wuyun
     */
    int countByUserNoAndIsRead(Long userNo, Integer isRead);

}
