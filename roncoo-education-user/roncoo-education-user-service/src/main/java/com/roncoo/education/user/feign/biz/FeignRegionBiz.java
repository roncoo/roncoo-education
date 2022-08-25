package com.roncoo.education.user.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.RegionDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Region;
import com.roncoo.education.user.dao.impl.mapper.entity.RegionExample;
import com.roncoo.education.user.dao.impl.mapper.entity.RegionExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.RegionEditQO;
import com.roncoo.education.user.feign.interfaces.qo.RegionPageQO;
import com.roncoo.education.user.feign.interfaces.qo.RegionSaveQO;
import com.roncoo.education.user.feign.interfaces.vo.RegionPageVO;
import com.roncoo.education.user.feign.interfaces.vo.RegionViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignRegionBiz extends BaseBiz {

    @NotNull
    private final RegionDao dao;

    public Page<RegionPageVO> page(RegionPageQO qo) {
        RegionExample example = new RegionExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Region> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, RegionPageVO.class);
    }

    public int save(RegionSaveQO qo) {
        Region record = BeanUtil.copyProperties(qo, Region.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(RegionEditQO qo) {
        Region record = BeanUtil.copyProperties(qo, Region.class);
        return dao.updateById(record);
    }

    public RegionViewVO getById(Long id) {
        Region record = dao.getById(id);
        return BeanUtil.copyProperties(record, RegionViewVO.class);
    }
}
