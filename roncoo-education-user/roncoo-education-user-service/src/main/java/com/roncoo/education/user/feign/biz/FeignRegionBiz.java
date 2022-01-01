package com.roncoo.education.user.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.RegionDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Region;
import com.roncoo.education.user.dao.impl.mapper.entity.RegionExample;
import com.roncoo.education.user.feign.interfaces.qo.RegionQO;
import com.roncoo.education.user.feign.interfaces.vo.RegionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Component
public class FeignRegionBiz {

    @Autowired
    private RegionDao dao;

    public Page<RegionVO> listForPage(RegionQO qo) {
        RegionExample example = new RegionExample();
        example.setOrderByClause(" id desc ");
        Page<Region> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, RegionVO.class);
    }

    public int save(RegionQO qo) {
        Region record = BeanUtil.copyProperties(qo, Region.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public RegionVO getById(Long id) {
        Region record = dao.getById(id);
        return BeanUtil.copyProperties(record, RegionVO.class);
    }

    public int updateById(RegionQO qo) {
        Region record = BeanUtil.copyProperties(qo, Region.class);
        return dao.updateById(record);
    }

}
