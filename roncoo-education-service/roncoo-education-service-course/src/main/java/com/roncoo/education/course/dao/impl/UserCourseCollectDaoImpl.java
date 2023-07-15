package com.roncoo.education.course.dao.impl;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.UserCourseCollectDao;
import com.roncoo.education.course.dao.impl.mapper.UserCourseCollectMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 课程收藏 服务实现类
 *
 * @author wujing
 * @date 2023-03-24
 */
@Repository
@RequiredArgsConstructor
public class UserCourseCollectDaoImpl implements UserCourseCollectDao {

    @NotNull
    private final UserCourseCollectMapper mapper;

    @Override
    public int save(UserCourseCollect record) {
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
    public int updateById(UserCourseCollect record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserCourseCollect getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserCourseCollect> page(int pageCurrent, int pageSize, UserCourseCollectExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<UserCourseCollect> listByExample(UserCourseCollectExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UserCourseCollectExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public UserCourseCollect getByCouserIdAndUserId(Long courseId, Long userId) {
        UserCourseCollectExample example = new UserCourseCollectExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andUserIdEqualTo(userId);
        List<UserCourseCollect> list = this.mapper.selectByExample(example);
        if (CollUtil.isNotEmpty(list)) {
            return list.get(0);
        }
        return null;
    }
}
