package com.roncoo.education.common.elasticsearch;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 课程信息表
 *
 * @author wuyun
 */
@Document(indexName = EsCourse.COURSE)
@Data
@Accessors(chain = true)
public class EsCourse implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String COURSE = "rc_es_course";

    /**
     * 主键
     */
    @Id
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
     * 课程名称
     */
    private String courseName;
    /**
     * 课程封面
     */
    private String courseLogo;
    /**
     * 分类
     */
    private Long categoryId;
    /**
     * 是否免费(1:免费，0:收费)
     */
    private Integer isFree;
    /**
     * 原价
     */
    private BigDecimal rulingPrice;
    /**
     * 优惠价
     */
    private BigDecimal coursePrice;
    /**
     * 课程排序
     */
    private Integer courseSort;

}
