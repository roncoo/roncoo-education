package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.LiveChannelDao;
import com.roncoo.education.course.dao.impl.mapper.LiveChannelMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveChannel;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveChannelExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 直播频道信息 服务实现类
 *
 * @author fengyw
 * @date 2024-06-08
 */
@Repository
@RequiredArgsConstructor
public class LiveChannelDaoImpl implements LiveChannelDao {

    @NotNull
    private final LiveChannelMapper mapper;

    @Override
    public int save(LiveChannel record) {
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
    public int updateById(LiveChannel record) {
        record.setGmtCreate(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public LiveChannel getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<LiveChannel> page(int pageCurrent, int pageSize, LiveChannelExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<LiveChannel> listByExample(LiveChannelExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(LiveChannelExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public LiveChannel getByCourseId(Long courseId) {
        LiveChannelExample example = new LiveChannelExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        List<LiveChannel> list = this.mapper.selectByExample(example);
        return list.isEmpty() ? null : list.get(0);
    }
}
