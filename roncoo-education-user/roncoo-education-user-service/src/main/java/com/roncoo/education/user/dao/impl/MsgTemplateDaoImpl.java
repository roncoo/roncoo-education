package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.MsgTemplateDao;
import com.roncoo.education.user.dao.impl.mapper.MsgTemplateMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplateExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 消息模板 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class MsgTemplateDaoImpl implements MsgTemplateDao {

    @NotNull
    private final MsgTemplateMapper mapper;

    @Override
    public int save(MsgTemplate record) {
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
    public int updateById(MsgTemplate record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public MsgTemplate getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<MsgTemplate> page(int pageCurrent, int pageSize, MsgTemplateExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<MsgTemplate> listByExample(MsgTemplateExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(MsgTemplateExample example) {
        return this.mapper.countByExample(example);
    }
}
