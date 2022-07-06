package com.roncoo.education.common.es;

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
    public static final String COURSE = "edu_course";
    private static final long serialVersionUID = 1L;

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
     * 排序
     */
    private Integer sort;
    /**
     * 讲师名称
     */
    private String lecturerName;
    /**
     * 课程编号
     */
    private Long courseNo;
    /**
     * 课程封面
     */
    private String courseLogo;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程排序
     */
    private Integer courseSort;
    /**
     * 原价
     */
    private BigDecimal courseOriginal;
    /**
     * 优惠价
     */
    private BigDecimal courseDiscount;
    /**
     * 是否免费(1:免费，0:收费)
     */
    private Integer isFree;

    @Override
    public String toString() {
        return "EsCourse [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", sort=" + sort + ", lecturerName=" + lecturerName + ", courseNo=" + courseNo + ", courseLogo=" + courseLogo + ", courseName=" + courseName + ", courseSort=" + courseSort + ", courseOriginal="
                + courseOriginal + ", courseDiscount=" + courseDiscount + ", isFree=" + isFree + "]";
    }

}
