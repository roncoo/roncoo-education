package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ResourceExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.ResourceEditQO;
import com.roncoo.education.course.feign.interfaces.qo.ResourcePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ResourceSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.ResourcePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ResourceViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 课程视频信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignResourceBiz extends BaseBiz {

    @NotNull
    private final ResourceDao dao;

    public Page<ResourcePageVO> page(ResourcePageQO qo) {
        ResourceExample example = new ResourceExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Resource> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, ResourcePageVO.class);
    }

    public int save(ResourceSaveQO qo) {
        Resource record = BeanUtil.copyProperties(qo, Resource.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(ResourceEditQO qo) {
        Resource record = BeanUtil.copyProperties(qo, Resource.class);
        return dao.updateById(record);
    }

    public ResourceViewVO getById(Long id) {
        Resource record = dao.getById(id);
        return BeanUtil.copyProperties(record, ResourceViewVO.class);
    }
}
