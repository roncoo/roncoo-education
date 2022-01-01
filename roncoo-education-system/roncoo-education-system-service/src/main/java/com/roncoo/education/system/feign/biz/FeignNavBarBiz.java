package com.roncoo.education.system.feign.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Base;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.NavBarDao;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBar;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBarExample;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBarExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.NavBarQO;
import com.roncoo.education.system.feign.interfaces.vo.NavBarVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Component
public class FeignNavBarBiz {

    @Autowired
    private NavBarDao dao;

    public Page<NavBarVO> listForPage(NavBarQO qo) {
        NavBarExample example = new NavBarExample();
        Criteria c = example.createCriteria();
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        } else {
            c.andStatusIdLessThan(Base.FREEZE);
        }
        if (StringUtils.isNotEmpty(qo.getNavTitle())) {
            c.andNavTitleLike(PageUtil.rightLike(qo.getNavTitle()));
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc  ");
        Page<NavBar> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, NavBarVO.class);
    }

    public int save(NavBarQO qo) {
        if (StringUtils.isEmpty(qo.getNavUrl())) {
            throw new BaseException("导航链接不能为空");
        }
        if (StringUtils.isEmpty(qo.getTarget())) {
            throw new BaseException("跳转方式不能为空");
        }
        NavBar navBar = dao.getByNavUrl(qo.getNavUrl());
        if (ObjectUtil.isNotNull(navBar)) {
            throw new BaseException("已经添加该导航");
        }
        NavBar record = BeanUtil.copyProperties(qo, NavBar.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public NavBarVO getById(Long id) {
        NavBar record = dao.getById(id);
        return BeanUtil.copyProperties(record, NavBarVO.class);
    }

    public int updateById(NavBarQO qo) {
        if (qo.getStatusId() == null && StringUtils.isEmpty(qo.getTarget())) {
            throw new BaseException("跳转方式不能为空");
        }
        NavBar record = BeanUtil.copyProperties(qo, NavBar.class);
        return dao.updateById(record);
    }

}
