package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginEditQO;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginPageQO;
import com.roncoo.education.user.feign.interfaces.qo.LogLoginSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.LogLoginPageVO;
import com.roncoo.education.user.feign.interfaces.vo.LogLoginViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 用户登录日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignLogLoginBiz extends BaseBiz {

    @NotNull
    private final LogLoginDao dao;

    public Page<LogLoginPageVO> page(LogLoginPageQO qo) {
        LogLoginExample example = new LogLoginExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<LogLogin> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, LogLoginPageVO.class);
    }

    public int save(LogLoginSaveQO qo) {
        LogLogin record = BeanUtil.copyProperties(qo, LogLogin.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(LogLoginEditQO qo) {
        LogLogin record = BeanUtil.copyProperties(qo, LogLogin.class);
        return dao.updateById(record);
    }

    public LogLoginViewVO getById(Long id) {
        LogLogin record = dao.getById(id);
        return BeanUtil.copyProperties(record, LogLoginViewVO.class);
    }
}
