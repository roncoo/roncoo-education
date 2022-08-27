package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.ZoneEditQO;
import com.roncoo.education.course.feign.interfaces.qo.ZonePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.ZonePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 专区
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignZoneBiz extends BaseBiz {

    @NotNull
    private final ZoneDao dao;

    public Page<ZonePageVO> page(ZonePageQO qo) {
        ZoneExample example = new ZoneExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Zone> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, ZonePageVO.class);
    }

    public int save(ZoneSaveQO qo) {
        Zone record = BeanUtil.copyProperties(qo, Zone.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(ZoneEditQO qo) {
        Zone record = BeanUtil.copyProperties(qo, Zone.class);
        return dao.updateById(record);
    }

    public ZoneViewVO getById(Long id) {
        Zone record = dao.getById(id);
        return BeanUtil.copyProperties(record, ZoneViewVO.class);
    }
}
