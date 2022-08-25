package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.MsgDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Msg;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgExample;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.MsgEditQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgPageQO;
import com.roncoo.education.user.feign.interfaces.qo.MsgSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.MsgPageVO;
import com.roncoo.education.user.feign.interfaces.vo.MsgViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 站内信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignMsgBiz extends BaseBiz {

    @NotNull
    private final MsgDao dao;

    public Page<MsgPageVO> page(MsgPageQO qo) {
        MsgExample example = new MsgExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Msg> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, MsgPageVO.class);
    }

    public int save(MsgSaveQO qo) {
        Msg record = BeanUtil.copyProperties(qo, Msg.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(MsgEditQO qo) {
        Msg record = BeanUtil.copyProperties(qo, Msg.class);
        return dao.updateById(record);
    }

    public MsgViewVO getById(Long id) {
        Msg record = dao.getById(id);
        return BeanUtil.copyProperties(record, MsgViewVO.class);
    }
}
