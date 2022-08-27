package com.roncoo.education.course.feign.interfaces.qo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程用户学习日志
 *
 * @author wujing
 * @date 2022-08-27
 */
@Data
@Accessors(chain = true)
public class UserStudyEditQO implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 创建时间
     */
    private LocalDateTime gmtCreate;

    /**
     * 课程ID
     */
    private Long courseId;

    /**
     * 章节ID
     */
    private Long chapterId;

    /**
     * 课时ID
     */
    private Long periodId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 进度(百分比)
     */
    private BigDecimal progress;

}
