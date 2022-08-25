package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgUserDao;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserEditQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserPageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgUserSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgUserPageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgUserViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 站内信用户记录表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignMsgUserBiz extends BaseBiz {

    @NotNull
    private final MsgUserDao dao;

    public Page<MsgUserPageVO> page(MsgUserPageQO qo) {
        MsgUserExample example = new MsgUserExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<MsgUser> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, MsgUserPageVO.class);
    }

    public int save(MsgUserSaveQO qo) {
        MsgUser record = BeanUtil.copyProperties(qo, MsgUser.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(MsgUserEditQO qo) {
        MsgUser record = BeanUtil.copyProperties(qo, MsgUser.class);
        return dao.updateById(record);
    }

    public MsgUserViewVO getById(Long id) {
        MsgUser record = dao.getById(id);
        return BeanUtil.copyProperties(record, MsgUserViewVO.class);
    }
}
