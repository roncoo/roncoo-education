package com.roncoo.education.course.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.jdbc.AbstractBaseJdbc;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.CourseMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程信息 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class CourseDaoImpl extends AbstractBaseJdbc implements CourseDao {

    @NotNull
    private final CourseMapper mapper;

    @Override
    public int save(Course record) {
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
    public int updateById(Course record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Course getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Course> page(int pageCurrent, int pageSize, CourseExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExampleWithBLOBs(example));
    }

    @Override
    public List<Course> listByExample(CourseExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(CourseExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<Course> listByIds(List<Long> courseIds) {
        if (CollUtil.isEmpty(courseIds)) {
            return Collections.emptyList();
        }
        CourseExample example = new CourseExample();
        example.createCriteria().andIdIn(courseIds);
        example.setOrderByClause("field(id," + courseIds.stream().map(String::valueOf).collect(Collectors.joining(",")) + ")");
        return this.mapper.selectByExample(example);
    }

    @Override
    public void addCountBuy(int countBuy, Long id) {
        String sql = "update course set count_buy=count_buy+? where id=?";
        this.jdbcTemplate.update(sql, countBuy, id);
    }

    @Override
    public void addCountStudy(int countStudy, Long id) {
        String sql = "update course set count_study=count_study+? where id=?";
        this.jdbcTemplate.update(sql, countStudy, id);
    }
}
