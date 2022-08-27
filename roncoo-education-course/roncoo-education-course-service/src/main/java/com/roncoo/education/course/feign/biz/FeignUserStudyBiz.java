package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudyExample;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudyExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.UserStudyEditQO;
import com.roncoo.education.course.feign.interfaces.qo.UserStudyPageQO;
import com.roncoo.education.course.feign.interfaces.qo.UserStudySaveQO;
import com.roncoo.education.course.feign.interfaces.vo.UserStudyPageVO;
import com.roncoo.education.course.feign.interfaces.vo.UserStudyViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 课程用户学习日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignUserStudyBiz extends BaseBiz {

    @NotNull
    private final UserStudyDao dao;

    public Page<UserStudyPageVO> page(UserStudyPageQO qo) {
        UserStudyExample example = new UserStudyExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<UserStudy> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserStudyPageVO.class);
    }

    public int save(UserStudySaveQO qo) {
        UserStudy record = BeanUtil.copyProperties(qo, UserStudy.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(UserStudyEditQO qo) {
        UserStudy record = BeanUtil.copyProperties(qo, UserStudy.class);
        return dao.updateById(record);
    }

    public UserStudyViewVO getById(Long id) {
        UserStudy record = dao.getById(id);
        return BeanUtil.copyProperties(record, UserStudyViewVO.class);
    }
}
