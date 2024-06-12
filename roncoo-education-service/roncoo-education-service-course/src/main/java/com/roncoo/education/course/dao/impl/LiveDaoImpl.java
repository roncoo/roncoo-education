package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.LiveDao;
import com.roncoo.education.course.dao.impl.mapper.LiveMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.Live;
import com.roncoo.education.course.dao.impl.mapper.entity.LiveExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 直播信息 服务实现类
 *
 * @author fengyw
 * @date 2024-06-07
 */
@Repository
@RequiredArgsConstructor
public class LiveDaoImpl implements LiveDao {

    @NotNull
    private final LiveMapper mapper;

    @Override
    public int save(Live record) {
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
    public int updateById(Live record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Live getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Live> page(int pageCurrent, int pageSize, LiveExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<Live> listByExample(LiveExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(LiveExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<Live> listByIds(List<Long> liveIdList) {
        LiveExample example = new LiveExample();
        example.createCriteria().andIdIn(liveIdList);
        return this.mapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public Live getByChannelId(String channelId) {
        LiveExample example = new LiveExample();
        example.createCriteria().andChannelIdEqualTo(channelId);
        List<Live> list = this.mapper.selectByExampleWithBLOBs(example);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
