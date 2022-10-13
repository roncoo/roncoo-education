package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.MsgDao;
import com.roncoo.education.user.dao.impl.mapper.MsgMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.Msg;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 站内信息表 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class MsgDaoImpl implements MsgDao {

    @NotNull
    private final MsgMapper mapper;

    @Override
    public int save(Msg record) {
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
    public int updateById(Msg record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Msg getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Msg> page(int pageCurrent, int pageSize, MsgExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<Msg> listByExample(MsgExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(MsgExample example) {
        return this.mapper.countByExample(example);
    }
}
