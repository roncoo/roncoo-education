package com.roncoo.education.system.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.SysConfigMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 系统配置 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class SysConfigDaoImpl implements SysConfigDao {

    @NotNull
    private final SysConfigMapper mapper;

    @Override
    public int save(SysConfig record) {
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
    public int updateById(SysConfig record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public SysConfig getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<SysConfig> page(int pageCurrent, int pageSize, SysConfigExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExampleWithBLOBs(example));
    }

    @Override
    public List<SysConfig> listByExample(SysConfigExample example) {
        return this.mapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public int countByExample(SysConfigExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public SysConfig getByConfigKey(String configKey) {
        SysConfigExample example = new SysConfigExample();
        example.createCriteria().andConfigKeyEqualTo(configKey);
        example.setOrderByClause("sort asc, id desc");
        List<SysConfig> configList = this.mapper.selectByExampleWithBLOBs(example);
        if (CollUtil.isNotEmpty(configList)) {
            return configList.get(0);
        }
        return null;
    }
}
