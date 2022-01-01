package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.Msg;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgExample;

import java.util.Date;
import java.util.List;

public interface MsgDao {
    int save(Msg record);

    int deleteById(Long id);

    int updateById(Msg record);

    Msg getById(Long id);

    Page<Msg> listForPage(int pageCurrent, int pageSize, MsgExample example);

    /**
     * 定时发送检索需要发送的站内信
     *
     * @param statusId
     * @param isSend
     * @param isTimeSend
     * @param sendTime
     * @return
     * @author wuyun
     */
    List<Msg> listByStatusIdAndIsSendAndIsTimeSendAndSendTime(Integer statusId, Integer isSend, Integer isTimeSend, Date sendTime);
}
