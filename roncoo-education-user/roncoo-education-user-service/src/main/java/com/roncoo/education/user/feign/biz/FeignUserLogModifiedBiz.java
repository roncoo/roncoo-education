package com.roncoo.education.user.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.UserLogModifiedDao;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogModified;
import com.roncoo.education.user.dao.impl.mapper.entity.UserLogModifiedExample;
import com.roncoo.education.user.feign.interfaces.qo.UserLogModifiedQO;
import com.roncoo.education.user.feign.interfaces.vo.UserLogModifiedVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户修改日志
 *
 * @author wujing
 */
@Component
public class FeignUserLogModifiedBiz {

    @Autowired
    private UserLogModifiedDao dao;

    public Page<UserLogModifiedVO> listForPage(UserLogModifiedQO qo) {
        UserLogModifiedExample example = new UserLogModifiedExample();
        example.setOrderByClause(" id desc ");
        Page<UserLogModified> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserLogModifiedVO.class);
    }

    public int save(UserLogModifiedQO qo) {
        UserLogModified record = BeanUtil.copyProperties(qo, UserLogModified.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public UserLogModifiedVO getById(Long id) {
        UserLogModified record = dao.getById(id);
        return BeanUtil.copyProperties(record, UserLogModifiedVO.class);
    }

    public int updateById(UserLogModifiedQO qo) {
        UserLogModified record = BeanUtil.copyProperties(qo, UserLogModified.class);
        return dao.updateById(record);
    }

}
