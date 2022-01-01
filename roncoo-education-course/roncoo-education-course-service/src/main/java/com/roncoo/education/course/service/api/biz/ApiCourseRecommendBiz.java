package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.CourseRecommendDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseRecommend;
import com.roncoo.education.course.service.api.bo.CourseRecommendBO;
import com.roncoo.education.course.service.api.dto.CourseRecommendDTO;
import com.roncoo.education.course.service.api.dto.CourseRecommendListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程推荐
 *
 * @author kyh
 */
@Component
public class ApiCourseRecommendBiz {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    private CourseRecommendDao dao;

    /**
     * 根据分类ID获取推荐课程信息
     *
     * @param courseRecommendBO
     * @author kyh
     */
    public Result<CourseRecommendListDTO> list(CourseRecommendBO bo) {
        List<CourseRecommend> list = dao.listByCategoryIdAndStatusId(bo.getCategoryId(), StatusIdEnum.YES.getCode());
        CourseRecommendListDTO dto = new CourseRecommendListDTO();
        if (CollectionUtil.isEmpty(list)) {
            return Result.success(dto);
        }
        List<CourseRecommendDTO> listDTO = new ArrayList<>();
        for (CourseRecommend courseRecommend : list) {
            Course course = courseDao.getById(courseRecommend.getCategoryId());
            if (ObjectUtil.isNotNull(course)) {
                CourseRecommendDTO courseRecommendDTO = BeanUtil.copyProperties(course, CourseRecommendDTO.class);
                listDTO.add(courseRecommendDTO);
            }
        }
        dto.setList(listDTO);
        return Result.success(dto);
    }

}
