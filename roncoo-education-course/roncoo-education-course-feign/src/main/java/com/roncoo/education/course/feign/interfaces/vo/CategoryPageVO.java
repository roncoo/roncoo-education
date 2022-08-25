package com.roncoo.education.course.feign.interfaces.vo;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 分类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
public class CategoryPageVO implements Serializable{

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
     * 修改时间
     */
    private LocalDateTime gmtModified;

    /**
     * 状态(1:正常，0:禁用)
     */
    private Integer statusId;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 父分类ID
     */
    private Long parentId;

    /**
     * 类型(1课程，2资源)
     */
    private Integer categoryType;

    /**
     * 名称
     */
    private String categoryName;

    /**
     * 备注
     */
    private String remark;
}
