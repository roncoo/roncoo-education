package com.roncoo.education.user.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.tools.IdWorker;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.impl.mapper.UsersAccountMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 用户账户 服务实现类
 *
 * @author wujing
 * @date 2024-03-16
 */
@Repository
@RequiredArgsConstructor
public class UsersAccountDaoImpl implements UsersAccountDao {

    @NotNull
    private final UsersAccountMapper mapper;

    @Override
    public int save(UsersAccount record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.mapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(UsersAccount record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UsersAccount getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UsersAccount> page(int pageCurrent, int pageSize, UsersAccountExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<UsersAccount> listByExample(UsersAccountExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UsersAccountExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public UsersAccount getByUserId(Long userId) {
        UsersAccountExample example = new UsersAccountExample();
        example.createCriteria().andUserIdEqualTo(userId);
        List<UsersAccount> list = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<UsersAccount> listByUserIds(List<Long> userIds) {
        UsersAccountExample example = new UsersAccountExample();
        example.createCriteria().andUserIdIn(userIds);
        return this.mapper.selectByExample(example);
    }
}
