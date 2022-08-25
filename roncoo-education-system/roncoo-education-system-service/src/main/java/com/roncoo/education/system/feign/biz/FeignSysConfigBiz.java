package com.roncoo.education.system.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfigExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigEditQO;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigPageQO;
import com.roncoo.education.system.feign.interfaces.qo.SysConfigSaveQO;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigPageVO;
import com.roncoo.education.system.feign.interfaces.vo.SysConfigViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 系统配置
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignSysConfigBiz extends BaseBiz {

    @NotNull
    private final SysConfigDao dao;

    public Page
            <SysConfigPageVO> page(SysConfigPageQO qo) {
        SysConfigExample example = new SysConfigExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<SysConfig> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, SysConfigPageVO.class);
    }

    public int save(SysConfigSaveQO qo) {
        SysConfig record = BeanUtil.copyProperties(qo, SysConfig.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(SysConfigEditQO qo) {
        SysConfig record = BeanUtil.copyProperties(qo, SysConfig.class);
        return dao.updateById(record);
    }

    public SysConfigViewVO getById(Long id) {
        SysConfig record = dao.getById(id);
        return BeanUtil.copyProperties(record, SysConfigViewVO.class);
    }
}
