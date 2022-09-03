package com.roncoo.education.course.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.impl.mapper.UserCourseMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 课程用户关联表 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class UserCourseDaoImpl implements UserCourseDao {

    @NotNull
    private final UserCourseMapper mapper;

    @Override
    public int save(UserCourse record) {
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
    public int updateById(UserCourse record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserCourse getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserCourse> page(int pageCurrent, int pageSize, UserCourseExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<UserCourse> listByExample(UserCourseExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UserCourseExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public UserCourse getByCourseIdAndUserId(Long courseId, Long userId) {
        UserCourseExample example = new UserCourseExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andUserIdEqualTo(userId);
        List<UserCourse> list = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
