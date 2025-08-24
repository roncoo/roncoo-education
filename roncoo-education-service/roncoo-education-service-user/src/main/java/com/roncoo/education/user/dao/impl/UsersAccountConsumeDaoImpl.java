package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.tools.IdWorker;
import com.roncoo.education.user.dao.UsersAccountConsumeDao;
import com.roncoo.education.user.dao.impl.mapper.UsersAccountConsumeMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsume;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsumeExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 用户账户消费记录 服务实现类
 *
 * @author wujing
 * @date 2024-03-17
 */
@Repository
@RequiredArgsConstructor
public class UsersAccountConsumeDaoImpl implements UsersAccountConsumeDao {

    @NotNull
    private final UsersAccountConsumeMapper mapper;

    @Override
    public int save(UsersAccountConsume record) {
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
    public int updateById(UsersAccountConsume record) {
        record.setGmtCreate(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UsersAccountConsume getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UsersAccountConsume> page(int pageCurrent, int pageSize, UsersAccountConsumeExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<UsersAccountConsume> listByExample(UsersAccountConsumeExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UsersAccountConsumeExample example) {
        return this.mapper.countByExample(example);
    }
}
