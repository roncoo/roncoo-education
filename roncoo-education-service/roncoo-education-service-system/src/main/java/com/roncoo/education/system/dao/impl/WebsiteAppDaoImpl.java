package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.tools.IdWorker;
import com.roncoo.education.system.dao.WebsiteAppDao;
import com.roncoo.education.system.dao.impl.mapper.WebsiteAppMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteApp;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteAppExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * app版本管理 服务实现类
 *
 * @author fengyw
 * @date 2024-06-07
 */
@Repository
@RequiredArgsConstructor
public class WebsiteAppDaoImpl implements WebsiteAppDao {

    @NotNull
    private final WebsiteAppMapper mapper;

    @Override
    public int save(WebsiteApp record) {
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
    public int updateById(WebsiteApp record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public WebsiteApp getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<WebsiteApp> page(int pageCurrent, int pageSize, WebsiteAppExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<WebsiteApp> listByExample(WebsiteAppExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(WebsiteAppExample example) {
        return this.mapper.countByExample(example);
    }
}
