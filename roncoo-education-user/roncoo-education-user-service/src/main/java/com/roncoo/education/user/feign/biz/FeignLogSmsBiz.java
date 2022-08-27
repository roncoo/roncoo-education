package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSms;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LogSmsSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LogSmsPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LogSmsViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 用户短信发送日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignLogSmsBiz extends BaseBiz {

    @NotNull
    private final LogSmsDao dao;

    public Page<LogSmsPageVO> page(LogSmsPageQO qo) {
        LogSmsExample example = new LogSmsExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<LogSms> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, LogSmsPageVO.class);
    }

    public int save(LogSmsSaveQO qo) {
        LogSms record = BeanUtil.copyProperties(qo, LogSms.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(LogSmsEditQO qo) {
        LogSms record = BeanUtil.copyProperties(qo, LogSms.class);
        return dao.updateById(record);
    }

    public LogSmsViewVO getById(Long id) {
        LogSms record = dao.getById(id);
        return BeanUtil.copyProperties(record, LogSmsViewVO.class);
    }
}
