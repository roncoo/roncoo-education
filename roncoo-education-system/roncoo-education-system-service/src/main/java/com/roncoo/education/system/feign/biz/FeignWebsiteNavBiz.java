package com.roncoo.education.system.feign.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Base;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.WebsiteNavArticleDao;
import com.roncoo.education.system.dao.WebsiteNavDao;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNav;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavArticle;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample;
import com.roncoo.education.system.dao.impl.mapper.entity.WebsiteNavExample.Criteria;
import com.roncoo.education.system.feign.interfaces.qo.WebsiteNavQO;
import com.roncoo.education.system.feign.interfaces.vo.WebsiteNavVO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Component
public class FeignWebsiteNavBiz {

    @Autowired
    private WebsiteNavDao dao;
    @Autowired
    private WebsiteNavArticleDao websiteNavArticleDao;

    public Page<WebsiteNavVO> listForPage(WebsiteNavQO qo) {
        WebsiteNavExample example = new WebsiteNavExample();
        Criteria c = example.createCriteria();
        if (StringUtils.isNotEmpty(qo.getNavName())) {
            c.andNavNameLike(PageUtil.rightLike(qo.getNavName()));
        } else {
            c.andParentIdEqualTo(Long.valueOf(0));
        }
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        } else {
            c.andStatusIdLessThan(Base.FREEZE);
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<WebsiteNav> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        Page<WebsiteNavVO> vo = PageUtil.transform(page, WebsiteNavVO.class);
        if (CollectionUtil.isNotEmpty(vo.getList())) {
            for (WebsiteNavVO websiteNavVO : vo.getList()) {
                websiteNav(websiteNavVO);
            }
        }
        return vo;
    }

    public WebsiteNavVO websiteNav(WebsiteNavVO websiteNavVO) {
        List<WebsiteNav> websiteNav = dao.listByParentId(websiteNavVO.getId());
        if (ObjectUtil.isNotNull(websiteNav)) {
            List<WebsiteNavVO> WebsiteNavVOList = PageUtil.copyList(websiteNav, WebsiteNavVO.class);
            websiteNavVO.setWebsiteNavList(WebsiteNavVOList);
            for (WebsiteNavVO websiteNavV2 : WebsiteNavVOList) {
                WebsiteNavArticle websiteNavArticle = websiteNavArticleDao.getByNavId(websiteNavV2.getId());
                if (ObjectUtil.isNotNull(websiteNavArticle)) {
                    websiteNavV2.setIsArticle(1);
                } else {
                    websiteNavV2.setIsArticle(0);
                }
            }
        }
        return websiteNavVO;
    }

    public int save(WebsiteNavQO qo) {
        WebsiteNav record = BeanUtil.copyProperties(qo, WebsiteNav.class);
        return dao.save(record);
    }

    @Transactional
    public int deleteById(Long id) {
        int record = dao.deleteById(id);
        if (record < 1) {
            throw new BaseException("站点导航表删除失败");
        }
        WebsiteNavArticle websiteNavArticle = websiteNavArticleDao.getByNavId(id);
        if (ObjectUtil.isNotNull(websiteNavArticle)) {
            int result = websiteNavArticleDao.deleteById(websiteNavArticle.getId());
            if (result < 1) {
                throw new BaseException("站点导航文章表删除失败");
            }
        }
        return record;
    }

    public WebsiteNavVO getById(Long id) {
        WebsiteNav record = dao.getById(id);
        return BeanUtil.copyProperties(record, WebsiteNavVO.class);
    }

    @Transactional
    public int updateById(WebsiteNavQO qo) {
        WebsiteNav record = BeanUtil.copyProperties(qo, WebsiteNav.class);
        int result = dao.updateById(record);
        // 父级状态改变，更新所有子级状态
        if (qo.getStatusId() != null) {
            List<WebsiteNav> websiteNavList = dao.listByParentId(qo.getId());
            if (ObjectUtil.isNotNull(websiteNavList)) {
                for (WebsiteNav websiteNav : websiteNavList) {
                    websiteNav.setParentId(qo.getId());
                    websiteNav.setStatusId(qo.getStatusId());
                    dao.updateById(websiteNav);
                }
            }
        }
        return result;
    }

}
