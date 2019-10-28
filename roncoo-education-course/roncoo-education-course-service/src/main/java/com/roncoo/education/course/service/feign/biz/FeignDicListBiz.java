package com.roncoo.education.course.service.feign.biz;

import com.roncoo.education.course.feign.qo.DicListQO;
import com.roncoo.education.course.feign.vo.DicListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.service.dao.DicListDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.DicList;
import com.roncoo.education.course.service.dao.impl.mapper.entity.DicListExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

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
