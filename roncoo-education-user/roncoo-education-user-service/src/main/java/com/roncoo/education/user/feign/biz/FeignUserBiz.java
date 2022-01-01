package com.roncoo.education.user.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.UserDao;
import com.roncoo.education.user.dao.impl.mapper.entity.User;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExample;
import com.roncoo.education.user.feign.interfaces.qo.UserQO;
import com.roncoo.education.user.feign.interfaces.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Component
public class FeignUserBiz {

    @Autowired
    private UserDao dao;

    public Page<UserVO> listForPage(UserQO qo) {
        UserExample example = new UserExample();
        example.setOrderByClause(" id desc ");
        Page<User> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserVO.class);
    }

    public int save(UserQO qo) {
        User record = BeanUtil.copyProperties(qo, User.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public UserVO getById(Long id) {
        User record = dao.getById(id);
        return BeanUtil.copyProperties(record, UserVO.class);
    }

    public int updateById(UserQO qo) {
        User record = BeanUtil.copyProperties(qo, User.class);
        return dao.updateById(record);
    }

    public UserVO getByMobile(String mobile) {
        User record = dao.getByMobile(mobile);
        return BeanUtil.copyProperties(record, UserVO.class);
    }

    public UserVO getByUserNo(Long userNo) {
        User record = dao.getByUserNo(userNo);
        return BeanUtil.copyProperties(record, UserVO.class);
    }

}
