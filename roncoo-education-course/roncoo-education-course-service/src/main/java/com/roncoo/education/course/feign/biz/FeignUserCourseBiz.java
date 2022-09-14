package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.BuyTypeEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseBindingQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseEditQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.UserCoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.UserCourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;

/**
 * 课程用户关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignUserCourseBiz extends BaseBiz {

    @NotNull
    private final UserCourseDao dao;
    @NotNull
    private final CourseDao courseDao;

    public Page<UserCoursePageVO> page(UserCoursePageQO qo) {
        UserCourseExample example = new UserCourseExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<UserCourse> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, UserCoursePageVO.class);
    }

    public int save(UserCourseSaveQO qo) {
        UserCourse record = BeanUtil.copyProperties(qo, UserCourse.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(UserCourseEditQO qo) {
        UserCourse record = BeanUtil.copyProperties(qo, UserCourse.class);
        return dao.updateById(record);
    }

    public UserCourseViewVO getById(Long id) {
        UserCourse record = dao.getById(id);
        return BeanUtil.copyProperties(record, UserCourseViewVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public int binding(UserCourseBindingQO qo) {
        UserCourse record = BeanUtil.copyProperties(qo, UserCourse.class);
        if (dao.save(record) > 0) {
            if (record.getBuyType().equals(BuyTypeEnum.BUY.getCode())) {
                // 课程购买数+1
                courseDao.addCountBuy(1, qo.getCourseId());
            }
            courseDao.addCountStudy(1, qo.getCourseId());
            return 1;
        }
        return 0;
    }
}
