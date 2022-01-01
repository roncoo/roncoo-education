package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.AdvDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Adv;
import com.roncoo.education.course.dao.impl.mapper.entity.AdvExample;
import com.roncoo.education.course.dao.impl.mapper.entity.AdvExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.AdvQO;
import com.roncoo.education.course.feign.interfaces.vo.AdvVO;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 广告信息
 *
 * @author wujing
 */
@Component
public class FeignAdvBiz {

    @Autowired
    private AdvDao dao;

    @Autowired
    private IFeignSys feignSys;

    public Page<AdvVO> listForPage(AdvQO qo) {
        AdvExample example = new AdvExample();
        Criteria c = example.createCriteria();
        if (qo.getPlatShow() != null) {
            c.andPlatShowEqualTo(qo.getPlatShow());
        }
        if (StringUtils.hasText(qo.getAdvTitle())) {
            c.andAdvTitleEqualTo(qo.getAdvTitle());
        }
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        }
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        Page<Adv> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, AdvVO.class);
    }

    public int save(AdvQO qo) {
        Adv record = BeanUtil.copyProperties(qo, Adv.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        Adv adv = dao.getById(id);
        if (adv != null) {
            AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(feignSys.getSys(), Aliyun.class));
        }
        return dao.deleteById(id);
    }

    public AdvVO getById(Long id) {
        Adv record = dao.getById(id);
        return BeanUtil.copyProperties(record, AdvVO.class);
    }

    public int updateById(AdvQO qo) {
        Adv adv = dao.getById(qo.getId());
        if (StringUtils.hasText(qo.getAdvImg()) && !adv.getAdvImg().equals(qo.getAdvImg())) {
            AliyunUtil.delete(adv.getAdvImg(), BeanUtil.copyProperties(feignSys.getSys(), Aliyun.class));
        }
        Adv record = BeanUtil.copyProperties(qo, Adv.class);
        return dao.updateById(record);
    }

}
