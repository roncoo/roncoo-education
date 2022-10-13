package com.roncoo.education.course.feign.interfaces.qo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程用户关联表
 *
 * @author wujing
 * @date 2022-08-27
 */
@Data
@Accessors(chain = true)
public class UserCourseBindingQO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 购买类型(1支付，2免费)
     */
    private Integer buyType;

}
