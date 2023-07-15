package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.ZoneCourseDao;
import com.roncoo.education.course.dao.impl.mapper.ZoneCourseMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 专区课程关联表 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class ZoneCourseDaoImpl implements ZoneCourseDao {

    @NotNull
    private final ZoneCourseMapper mapper;

    @Override
    public int save(ZoneCourse record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.mapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(ZoneCourse record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public ZoneCourse getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<ZoneCourse> page(int pageCurrent, int pageSize, ZoneCourseExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<ZoneCourse> listByExample(ZoneCourseExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(ZoneCourseExample example) {
        return this.mapper.countByExample(example);
    }
}
