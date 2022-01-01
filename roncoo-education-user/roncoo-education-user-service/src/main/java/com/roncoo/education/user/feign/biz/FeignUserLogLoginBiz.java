package com.roncoo.education.user.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.UserLogLoginDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogLoginExample;
import com.roncoo.education.user.feign.interfaces.qo.UserLogLoginQO;
import com.roncoo.education.user.feign.interfaces.vo.UserLogLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户错误登录日志
 *
 * @author wujing
 */
@Component
public class FeignUserLogLoginBiz {

    @Autowired
    private UserLogLoginDao dao;

    public Page<UserLogLoginVO> listForPage(UserLogLoginQO qo) {
        UserLogLoginExample example = new UserLogLoginExample();
        example.setOrderByClause(" id desc ");
        Page<UserLogLogin> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserLogLoginVO.class);
    }

    public int save(UserLogLoginQO qo) {
        UserLogLogin record = BeanUtil.copyProperties(qo, UserLogLogin.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public UserLogLoginVO getById(Long id) {
        UserLogLogin record = dao.getById(id);
        return BeanUtil.copyProperties(record, UserLogLoginVO.class);
    }

    public int updateById(UserLogLoginQO qo) {
        UserLogLogin record = BeanUtil.copyProperties(qo, UserLogLogin.class);
        return dao.updateById(record);
    }

}
