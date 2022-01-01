package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.jdbc.AbstractBaseJdbc;
import com.roncoo.education.user.dao.UserDao;
import com.roncoo.education.user.dao.impl.mapper.UserMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.User;
import com.roncoo.education.user.dao.impl.mapper.entity.UserExample;
import com.roncoo.education.user.feign.interfaces.vo.UserExtMsgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends AbstractBaseJdbc implements UserDao {
    @Autowired
    private UserMapper userMapper;

    @Override
    public int save(User record) {
        record.setId(IdWorker.getId());
        return this.userMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(User record) {
        return this.userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public User getById(Long id) {
        return this.userMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<User> listForPage(int pageCurrent, int pageSize, UserExample example) {
        int count = this.userMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<User>(count, totalPage, pageCurrent, pageSize, this.userMapper.selectByExample(example));
    }

    @Override
    public User getByMobile(String mobile) {
        UserExample example = new UserExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<User> list = this.userMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public User getByUserNo(Long userNo) {
        UserExample example = new UserExample();
        example.createCriteria().andUserNoEqualTo(userNo);
        List<User> list = this.userMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public Page<UserExtMsgVO> pageByStatusIdForMsg(Integer statusId, int pageCurrent, int pageSize) {
        StringBuffer sql = new StringBuffer("select user_no as userNo ,mobile from user_ext where 1=1");
        if (statusId != null) {
            sql.append(" and status_id =").append(statusId);
        }
        return queryForPage(sql.toString(), pageCurrent, pageSize, UserExtMsgVO.class);
    }
}
