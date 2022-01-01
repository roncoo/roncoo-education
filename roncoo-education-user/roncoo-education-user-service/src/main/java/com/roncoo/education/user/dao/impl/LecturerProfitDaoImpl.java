package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.LecturerProfitDao;
import com.roncoo.education.user.dao.impl.mapper.LecturerProfitMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfit;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerProfitExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LecturerProfitDaoImpl implements LecturerProfitDao {
    @Autowired
    private LecturerProfitMapper lecturerProfitMapper;

    public int save(LecturerProfit record) {
        record.setId(IdWorker.getId());
        return this.lecturerProfitMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.lecturerProfitMapper.deleteByPrimaryKey(id);
    }

    public int updateById(LecturerProfit record) {
        return this.lecturerProfitMapper.updateByPrimaryKeySelective(record);
    }

    public LecturerProfit getById(Long id) {
        return this.lecturerProfitMapper.selectByPrimaryKey(id);
    }

    public Page<LecturerProfit> listForPage(int pageCurrent, int pageSize, LecturerProfitExample example) {
        int count = this.lecturerProfitMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<LecturerProfit>(count, totalPage, pageCurrent, pageSize, this.lecturerProfitMapper.selectByExample(example));
    }
}
