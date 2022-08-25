package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.LogSmsDao;
import com.roncoo.education.user.dao.impl.mapper.LogSmsMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSms;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 用户短信发送日志 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class LogSmsDaoImpl implements LogSmsDao {

    @NotNull
    private final LogSmsMapper mapper;

    @Override
    public int save(LogSms record) {
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
    public int updateById(LogSms record) {
        record.setGmtCreate(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public LogSms getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<LogSms> page(int pageCurrent, int pageSize, LogSmsExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<LogSms> listByExample(LogSmsExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(LogSmsExample example) {
        return this.mapper.countByExample(example);
    }
}
