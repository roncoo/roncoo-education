package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.UsersEditQO;
import com.roncoo.education.user.feign.interfaces.qo.UsersPageQO;
import com.roncoo.education.user.feign.interfaces.qo.UsersSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.UsersPageVO;
import com.roncoo.education.user.feign.interfaces.vo.UsersViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 用户信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignUsersBiz extends BaseBiz {

    @NotNull
    private final UsersDao dao;

    public Page<UsersPageVO> page(UsersPageQO qo) {
        UsersExample example = new UsersExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Users> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UsersPageVO.class);
    }

    public int save(UsersSaveQO qo) {
        Users record = BeanUtil.copyProperties(qo, Users.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(UsersEditQO qo) {
        Users record = BeanUtil.copyProperties(qo, Users.class);
        return dao.updateById(record);
    }

    public UsersViewVO getById(Long id) {
        Users record = dao.getById(id);
        return BeanUtil.copyProperties(record, UsersViewVO.class);
    }
}
