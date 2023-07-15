package com.roncoo.education.system.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.system.dao.WebsiteCarouselDao;
import com.roncoo.education.system.dao.impl.mapper.WebsiteCarouselMapper;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarousel;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteCarouselExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 广告信息 服务实现类
 *
 * @author wujing
 * @date 2022-09-16
 */
@Repository
@RequiredArgsConstructor
public class WebsiteCarouselDaoImpl implements WebsiteCarouselDao {

    @NotNull
    private final WebsiteCarouselMapper mapper;

    @Override
    public int save(WebsiteCarousel record) {
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
    public int updateById(WebsiteCarousel record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public WebsiteCarousel getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<WebsiteCarousel> page(int pageCurrent, int pageSize, WebsiteCarouselExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<WebsiteCarousel> listByExample(WebsiteCarouselExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(WebsiteCarouselExample example) {
        return this.mapper.countByExample(example);
    }
}
