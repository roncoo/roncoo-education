package com.roncoo.education.course.feign.interfaces.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseCategoryVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
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
     * 分类类型(1课程，2资源)
     */
    private Integer categoryType;
    /**
     * 分类名称
     */
    private String categoryName;
    /**
     * 层级
     */
    private Integer floor;
    /**
     * 备注
     */
    private String remark;

    private List<CourseCategoryVO> list;
}
