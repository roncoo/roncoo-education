package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.LiveLogDao;
import com.roncoo.education.course.dao.impl.mapper.LiveLogMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveLog;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveLogExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 直播开播日志 服务实现类
 *
 * @author fengyw
 * @date 2024-06-08
 */
@Repository
@RequiredArgsConstructor
public class LiveLogDaoImpl implements LiveLogDao {

    @NotNull
    private final LiveLogMapper mapper;

    @Override
    public int save(LiveLog record) {
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
    public int updateById(LiveLog record) {
        record.setGmtCreate(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public LiveLog getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<LiveLog> page(int pageCurrent, int pageSize, LiveLogExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<LiveLog> listByExample(LiveLogExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(LiveLogExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public LiveLog getBySessionId(String sessionId) {
        LiveLogExample example = new LiveLogExample();
        example.createCriteria().andSessionIdEqualTo(sessionId);
        example.setOrderByClause("id desc");
        List<LiveLog> list = this.mapper.selectByExample(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
