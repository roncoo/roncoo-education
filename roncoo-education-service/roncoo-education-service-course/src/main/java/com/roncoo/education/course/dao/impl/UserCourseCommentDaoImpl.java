package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.UserCourseCommentDao;
import com.roncoo.education.course.dao.impl.mapper.UserCourseCommentMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseComment;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCommentExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 课程评论 服务实现类
 *
 * @author wujing
 * @date 2023-03-24
 */
@Repository
@RequiredArgsConstructor
public class UserCourseCommentDaoImpl implements UserCourseCommentDao {

    @NotNull
    private final UserCourseCommentMapper mapper;

    @Override
    public int save(UserCourseComment record) {
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
    public int updateById(UserCourseComment record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public UserCourseComment getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<UserCourseComment> page(int pageCurrent, int pageSize, UserCourseCommentExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<UserCourseComment> listByExample(UserCourseCommentExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(UserCourseCommentExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<UserCourseComment> listByCourseIdAndStatusId(Long courseId, Integer statusId) {
        UserCourseCommentExample example = new UserCourseCommentExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andStatusIdEqualTo(statusId);
        return this.mapper.selectByExample(example);
    }
}
