package com.roncoo.education.system.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Base;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.NavEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.NavBarDao;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBar;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBarExample;
import com.roncoo.education.system.dao.impl.mapper.entity.NavBarExample.Criteria;
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.NavBarPageRESQ;
import com.roncoo.education.system.service.pc.resq.NavBarViewRESQ;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 头部导航
 */
@Component
public class PcApiNavBarBiz {

    @Autowired
    private NavBarDao dao;

    /**
     * 分页列表
     *
     * @param req
     * @return
     */
    public Result<Page<NavBarPageRESQ>> list(NavBarPageREQ req) {
        NavBarExample example = new NavBarExample();
        Criteria c = example.createCriteria();
        if (req.getStatusId() != null) {
            c.andStatusIdEqualTo(req.getStatusId());
        } else {
            c.andStatusIdLessThan(Base.FREEZE);
        }
        if (StringUtils.isNotEmpty(req.getNavTitle())) {
            c.andNavTitleLike(PageUtil.like(req.getNavTitle()));
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc  ");
        Page<NavBar> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, NavBarPageRESQ.class));
    }

    /**
     * 保存头部导航
     *
     * @param req
     * @return
     */
    public Result<Integer> save(NavBarSaveREQ req) {
        if (StringUtils.isEmpty(req.getNavUrl())) {
            return Result.error("导航链接不能为空");
        }
        if (StringUtils.isEmpty(req.getTarget())) {
            return Result.error("跳转方式不能为空");
        }
        NavBar navBar = dao.getByNavUrl(req.getNavUrl());
        if (ObjectUtil.isNotNull(navBar)) {
            return Result.error("已经添加该导航");
        }
        NavBar record = BeanUtil.copyProperties(req, NavBar.class);
        if (NavEnum.INDEX.getCode().equals(req.getNavUrl())) {
            record.setNavTitle(NavEnum.INDEX.getDesc());
        } else if (NavEnum.COURSE.getCode().equals(req.getNavUrl())) {
            record.setNavTitle(NavEnum.COURSE.getDesc());
        } else {
            record.setNavTitle(NavEnum.RECRUIT.getDesc());
        }
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    /**
     * 删除
     *
     * @param req
     * @return
     */
    public Result<Integer> delete(NavBarDeleteREQ req) {
        if (req.getId() == null) {
            return Result.error("");
        }
        NavBar record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到头部导航信息");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    public Result<Integer> update(NavBarUpdateREQ req) {
        if (req.getId() == null) {
            return Result.error("");
        }
        NavBar navBar = dao.getById(req.getId());
        if (ObjectUtil.isNull(navBar)) {
            return Result.error("找不到头部导航信息");
        }
        NavBar record = BeanUtil.copyProperties(req, NavBar.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    /**
     * 查找头部导航信息
     *
     * @param req
     * @return
     */
    public Result<NavBarViewRESQ> view(NavBarViewREQ req) {
        if (req.getId() == null) {
            return Result.error("");
        }
        NavBar record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到头部导航信息");
        }
        return Result.success(BeanUtil.copyProperties(record, NavBarViewRESQ.class));
    }

}
