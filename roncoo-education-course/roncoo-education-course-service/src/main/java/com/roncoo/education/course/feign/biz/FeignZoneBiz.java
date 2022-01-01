package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.ZoneQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 专区
 *
 * @author wujing
 */
@Component
public class FeignZoneBiz {

    @Autowired
    private ZoneDao dao;

    public Page<ZoneVO> listForPage(ZoneQO qo) {
        ZoneExample example = new ZoneExample();
        Criteria c = example.createCriteria();
        if (qo.getZoneLocation() != null) {
            c.andZoneLocationEqualTo(qo.getZoneLocation());
        }
        if (StringUtils.hasText(qo.getZoneName())) {
            c.andZoneNameEqualTo(qo.getZoneName());
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Zone> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, ZoneVO.class);
    }

    public int save(ZoneQO qo) {
        Zone record = BeanUtil.copyProperties(qo, Zone.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public ZoneVO getById(Long id) {
        Zone record = dao.getById(id);
        return BeanUtil.copyProperties(record, ZoneVO.class);
    }

    public int updateById(ZoneQO qo) {
        Zone record = BeanUtil.copyProperties(qo, Zone.class);
        return dao.updateById(record);
    }

    public List<ZoneVO> listAllZone() {
        List<Zone> zones = dao.listAllZone();
        return BeanUtil.copyProperties(zones, ZoneVO.class);
    }

}
