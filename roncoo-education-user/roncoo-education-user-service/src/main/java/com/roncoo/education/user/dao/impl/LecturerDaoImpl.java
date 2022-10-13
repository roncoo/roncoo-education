package com.roncoo.education.user.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.user.dao.LecturerDao;
import com.roncoo.education.user.dao.impl.mapper.LecturerMapper;
import com.roncoo.education.user.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.dao.impl.mapper.entity.LecturerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LecturerDaoImpl implements LecturerDao {
    @Autowired
    private LecturerMapper lecturerMapper;

    @Override
    public int save(Lecturer record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.lecturerMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.lecturerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Lecturer record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.lecturerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Lecturer getById(Long id) {
        return this.lecturerMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Lecturer> page(int pageCurrent, int pageSize, LecturerExample example) {
        int count = this.lecturerMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Lecturer>(count, totalPage, pageCurrent, pageSize, this.lecturerMapper.selectByExampleWithBLOBs(example));
    }

    @Override
    public List<Lecturer> listByStatusId(Integer statusId) {
        LecturerExample example = new LecturerExample();
        LecturerExample.Criteria criteria = example.createCriteria();
        criteria.andStatusIdEqualTo(statusId);
        return this.lecturerMapper.selectByExample(example);
    }

    @Override
    public List<Lecturer> getByLecturerName(String LecturerName) {
        LecturerExample example = new LecturerExample();
        LecturerExample.Criteria criteria = example.createCriteria();
        criteria.andLecturerNameLike(PageUtil.rightLike(LecturerName));
        List<Lecturer> resultList = this.lecturerMapper.selectByExample(example);
        if (resultList.isEmpty()) {
            return null;
        }
        return resultList;
    }

    @Override
    public List<Lecturer> listByIds(List<Long> ids) {
        LecturerExample example = new LecturerExample();
        LecturerExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(ids);
        return this.lecturerMapper.selectByExample(example);
    }
}
