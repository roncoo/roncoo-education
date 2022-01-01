package com.roncoo.education.system.feign.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.core.tools.ArrayListUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.system.dao.MsgDao;
import com.roncoo.education.system.dao.MsgUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.Msg;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgExample;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgExample.Criteria;
import com.roncoo.education.system.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.feign.interfaces.qo.MsgQO;
import com.roncoo.education.system.feign.interfaces.vo.MsgPushVO;
import com.roncoo.education.system.feign.interfaces.vo.MsgVO;
import com.roncoo.education.user.feign.interfaces.IFeignUserExt;
import com.roncoo.education.user.feign.interfaces.vo.UserExtMsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Component
public class FeignMsgBiz extends BaseBiz {

    @Autowired
    private MsgDao dao;
    @Autowired
    private MsgUserDao msgUserDao;

    @Autowired
    private IFeignUserExt bossUserExt;

    @Autowired
    private CacheRedis cacheRedis;

    public Page<MsgVO> listForPage(MsgQO qo) {
        MsgExample example = new MsgExample();
        Criteria c = example.createCriteria();
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        } else {
            c.andStatusIdLessThan(Constants.FREEZE);
        }
        if (StringUtils.hasText(qo.getMsgTitle())) {
            c.andMsgTitleLike(PageUtil.rightLike(qo.getMsgTitle()));
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Msg> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, MsgVO.class);
    }

    public int save(MsgQO qo) {
        Msg record = BeanUtil.copyProperties(qo, Msg.class);
        return dao.save(record);
    }

    @Transactional
    public int deleteById(Long id) {
        msgUserDao.deleteByMsgId(id);
        int result = dao.deleteById(id);
        if (result < 1) {
            throw new BaseException("更新表失败");
        }
        return result;
    }

    public MsgVO getById(Long id) {
        Msg record = dao.getById(id);
        return BeanUtil.copyProperties(record, MsgVO.class);
    }

    public int updateById(MsgQO qo) {
        Msg record = BeanUtil.copyProperties(qo, Msg.class);
        return dao.updateById(record);
    }

    @Transactional
    public int pushByManual(Long id) {
        // 获得模板
        Msg msg = dao.getById(id);
        if (msg == null) {
            throw new BaseException("查找msg失败");
        }
        final MsgPushVO msgPush = BeanUtil.copyProperties(msg, MsgPushVO.class);
        // 刷新站内信
        updateMsg(id);
        callbackExecutor.execute(new Runnable() {
            @Override
            public void run() {
                pushToUserByMsgPush(msgPush);
            }
        });
        return 1;
    }

    private void pushToUserByMsgPush(MsgPushVO msgPush) {
        // 获取缓存的条数
        int num = getCacheNum();
        for (int i = 1; i < num + 1; i++) {
            List<UserExtMsgVO> list = cacheRedis.list(RedisPreEnum.SYS_MSG_SEND.getCode() + "_" + i, UserExtMsgVO.class);
            if (CollectionUtil.isNotEmpty(list)) {
                // 批量生成
                for (UserExtMsgVO vo : list) {
                    saveMsgUser(msgPush, vo);
                }
            }
        }

    }

    private int getCacheNum() {
        boolean flag = cacheRedis.hasKey(RedisPreEnum.SYS_MSG_SEND_NUM.getCode());
        if (!flag) {// 找不到，去缓存用户信息
            bossUserExt.cachUserForMsg();
        }
        int num = cacheRedis.get(RedisPreEnum.SYS_MSG_SEND_NUM.getCode(), int.class);
        return num;
    }

    private void updateMsg(Long id) {
        Msg msgNew = new Msg();
        msgNew.setId(id);
        msgNew.setIsSend(HasNoticeEnum.YES.getCode());
        dao.updateById(msgNew);
    }

    private void saveMsgUser(MsgPushVO msg, UserExtMsgVO vo) {
        MsgUser record = new MsgUser();
        record.setStatusId(StatusIdEnum.YES.getCode());
        record.setMsgId(msg.getId());
        record.setMsgTitle(msg.getMsgTitle());
        record.setUserNo(vo.getUserNo());
        record.setMobile(vo.getMobile());
        msgUserDao.save(record);
    }

    /**
     * 定时器任务：推送站内信到用户
     *
     * @return
     */
    @Transactional
    public int push() {
        List<Msg> list = dao.listByStatusIdAndIsSendAndIsTimeSendAndSendTime(StatusIdEnum.YES.getCode(), IsSendEnum.NO.getCode(), IsTimeSendEnum.YES.getCode(), new Date());
        List<MsgPushVO> msgList = ArrayListUtil.copy(list, MsgPushVO.class);
        if (CollectionUtil.isNotEmpty(msgList)) {
            for (MsgPushVO vo : msgList) {
                // 进行推送前，将当前站内信推送状态置为已通知
                updateMsg(vo.getId());

                callbackExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        pushToUserByMsgPush(vo);
                    }
                });
            }
            return 1;
        }
        return 0;
    }

}
