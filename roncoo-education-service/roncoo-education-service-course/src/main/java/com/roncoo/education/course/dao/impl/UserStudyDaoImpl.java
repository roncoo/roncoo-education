package com.roncoo.education.course.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.common.jdbc.AbstractBaseJdbc;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.UserStudyMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudyExample;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程用户学习日志 服务实现类
 *
 * @author wujing
 * @date 2022-09-03
 */
@Repository
@RequiredArgsConstructor
public class UserStudyDaoImpl extends AbstractBaseJdbc implements UserStudyDao {

    @NotNull
    private final UserStudyMapper mapper;

    @Override
    public int save(UserStudy record) {
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
    public int updateById(UserStudy record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserStudy getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserStudy> page(int pageCurrent, int pageSize, UserStudyExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<UserStudy> listByExample(UserStudyExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UserStudyExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public UserStudy getByPeriodIdAndUserId(Long periodId, Long userId) {
        UserStudyExample example = new UserStudyExample();
        example.createCriteria().andPeriodIdEqualTo(periodId).andUserIdEqualTo(userId);
        List<UserStudy> list = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<UserStudy> listByUserIdAndCourseIdsForMax(Long userId, List<Long> courseIdList) {
        String sql = "select max(gmt_modified) as gmt_modified, course_id, chapter_id, period_id, progress from user_study where user_id=:USERID and course_id in (:COURSEIDS) GROUP BY course_id";
        Map<String, Object> map = new HashMap();
        map.put("USERID", userId);
        map.put("COURSEIDS", courseIdList);
        return namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(UserStudy.class));
    }

    @Override
    public UserStudy getByCourseIdForLast(Long userId, Long courseId) {
        UserStudyExample example = new UserStudyExample();
        example.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        example.setOrderByClause("gmt_modified desc");
        List<UserStudy> list = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<UserStudy> listByUserIdAndCourseIdsForSumProgress(Long userId, List<Long> courseIdList) {
        String sql = "select course_id, sum(progress) as progress from user_study where user_id=:USERID and course_id in (:COURSEIDS) GROUP BY course_id";
        Map<String, Object> map = new HashMap();
        map.put("USERID", userId);
        map.put("COURSEIDS", courseIdList);
        return namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(UserStudy.class));
    }

    @Override
    public List<UserStudy> listByCourseIdAndUserIdsForSumProgress(Long courseId, List<Long> userIdList) {
        String sql = "select user_id, sum(progress) as progress from user_study where course_id=:COURSEID and user_id in (:USERIDS) GROUP BY user_id";
        Map<String, Object> map = new HashMap();
        map.put("COURSEID", courseId);
        map.put("USERIDS", userIdList);
        return namedParameterJdbcTemplate.query(sql, map, new BeanPropertyRowMapper<>(UserStudy.class));
    }

    @Override
    public List<UserStudy> listByUserIdAndCourseId(Long userId, Long courseId) {
        UserStudyExample example = new UserStudyExample();
        example.createCriteria().andUserIdEqualTo(userId).andCourseIdEqualTo(courseId);
        return this.mapper.selectByExample(example);
    }

    @Override
    public int deleteByPeriodId(Long periodId) {
        UserStudyExample example = new UserStudyExample();
        example.createCriteria().andPeriodIdEqualTo(periodId);
        return this.mapper.deleteByExample(example);
    }
}
