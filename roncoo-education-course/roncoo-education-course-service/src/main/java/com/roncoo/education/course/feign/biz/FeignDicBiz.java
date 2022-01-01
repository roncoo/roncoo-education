package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.DicDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Dic;
import com.roncoo.education.course.dao.impl.mapper.entity.DicExample;
import com.roncoo.education.course.feign.interfaces.qo.DicQO;
import com.roncoo.education.course.feign.interfaces.vo.DicVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据字典
 *
 * @author wujing
 */
@Component
public class FeignDicBiz {

    @Autowired
    private DicDao dao;

    public Page<DicVO> listForPage(DicQO qo) {
        DicExample example = new DicExample();
        example.setOrderByClause(" id desc ");
        Page<Dic> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, DicVO.class);
    }

    public int save(DicQO qo) {
        Dic record = BeanUtil.copyProperties(qo, Dic.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public DicVO getById(Long id) {
        Dic record = dao.getById(id);
        return BeanUtil.copyProperties(record, DicVO.class);
    }

    public int updateById(DicQO qo) {
        Dic record = BeanUtil.copyProperties(qo, Dic.class);
        return dao.updateById(record);
    }

}
