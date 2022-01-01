package com.roncoo.education.user.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.user.dao.PlatformDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.dao.impl.mapper.entity.PlatformExample;
import com.roncoo.education.user.dao.impl.mapper.entity.PlatformExample.Criteria;
import com.roncoo.education.user.feign.interfaces.qo.PlatformQO;
import com.roncoo.education.user.feign.interfaces.vo.PlatformVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 平台信息
 *
 * @author wujing
 */
@Component
public class FeignPlatformBiz {

    @Autowired
    private PlatformDao dao;

    public Page<PlatformVO> listForPage(PlatformQO qo) {
        PlatformExample example = new PlatformExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(qo.getClientName())) {
            c.andClientNameLike(PageUtil.rightLike(qo.getClientName()));
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Platform> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, PlatformVO.class);
    }

    public int save(PlatformQO qo) {
        Platform record = BeanUtil.copyProperties(qo, Platform.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public PlatformVO getById(Long id) {
        Platform record = dao.getById(id);
        return BeanUtil.copyProperties(record, PlatformVO.class);
    }

    public int updateById(PlatformQO qo) {
        Platform record = BeanUtil.copyProperties(qo, Platform.class);
        return dao.updateById(record);
    }

    public PlatformVO getByClientId(String clientId) {
        Platform record = dao.getByClientId(clientId);
        return BeanUtil.copyProperties(record, PlatformVO.class);
    }

}
