package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.DicListDao;
import com.roncoo.education.course.dao.impl.mapper.entity.DicList;
import com.roncoo.education.course.dao.impl.mapper.entity.DicListExample;
import com.roncoo.education.course.feign.interfaces.qo.DicListQO;
import com.roncoo.education.course.feign.interfaces.vo.DicListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 数据字典明细表
 *
 * @author wujing
 */
@Component
public class FeignDicListBiz {

    @Autowired
    private DicListDao dao;

    public Page<DicListVO> listForPage(DicListQO qo) {
        DicListExample example = new DicListExample();
        example.setOrderByClause(" id desc ");
        Page<DicList> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, DicListVO.class);
    }

    public int save(DicListQO qo) {
        DicList record = BeanUtil.copyProperties(qo, DicList.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public DicListVO getById(Long id) {
        DicList record = dao.getById(id);
        return BeanUtil.copyProperties(record, DicListVO.class);
    }

    public int updateById(DicListQO qo) {
        DicList record = BeanUtil.copyProperties(qo, DicList.class);
        return dao.updateById(record);
    }

}
