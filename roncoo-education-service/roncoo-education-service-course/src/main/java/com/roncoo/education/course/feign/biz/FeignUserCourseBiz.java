package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.enums.BuyTypeEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseBindingQO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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

    //@Transactional(rollbackFor = Exception.class) // 使用了全局事务，这里不能再使用本地事务
    public int binding(UserCourseBindingQO qo) {
        UserCourse record = BeanUtil.copyProperties(qo, UserCourse.class);
        if (dao.save(record) > 0) {
            if (record.getBuyType().equals(BuyTypeEnum.BUY.getCode())) {
                // 课程购买数+1
                courseDao.addCountBuy(1, qo.getCourseId());
            }
            // 课程学习数+1
            courseDao.addCountStudy(1, qo.getCourseId());
            return 1;
        }
        return 0;
    }
}
