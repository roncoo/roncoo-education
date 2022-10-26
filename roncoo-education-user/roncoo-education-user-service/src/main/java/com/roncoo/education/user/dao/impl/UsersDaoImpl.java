package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.jdbc.AbstractBaseJdbc;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.UsersMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersDaoImpl extends AbstractBaseJdbc implements UsersDao {
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public int save(Users record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.usersMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.usersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Users record) {
        return this.usersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Users getById(Long id) {
        return this.usersMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Users> page(int pageCurrent, int pageSize, UsersExample example) {
        int count = this.usersMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Users>(count, totalPage, pageCurrent, pageSize, this.usersMapper.selectByExample(example));
    }

    @Override
    public Users getByMobile(String mobile) {
        UsersExample example = new UsersExample();
        example.createCriteria().andMobileEqualTo(mobile);
        List<Users> list = this.usersMapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Users> listByIds(List<Long> userIdList) {
        UsersExample example = new UsersExample();
        example.createCriteria().andIdIn(userIdList);
        return this.usersMapper.selectByExample(example);
    }
}
