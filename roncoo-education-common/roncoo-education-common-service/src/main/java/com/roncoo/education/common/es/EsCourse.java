package com.roncoo.education.common.es;

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
    private String courselog;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getCourseName() {
        return courseName;
    }

    public Long getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Long courseNo) {
        this.courseNo = courseNo;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getCourseSort() {
        return courseSort;
    }

    public void setCourseSort(Integer courseSort) {
        this.courseSort = courseSort;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getCourselog() {
        return courselog;
    }

    public void setCourselog(String courselog) {
        this.courselog = courselog;
    }

    public BigDecimal getCourseOriginal() {
        return courseOriginal;
    }

    public void setCourseOriginal(BigDecimal courseOriginal) {
        this.courseOriginal = courseOriginal;
    }

    public BigDecimal getCourseDiscount() {
        return courseDiscount;
    }

    public void setCourseDiscount(BigDecimal courseDiscount) {
        this.courseDiscount = courseDiscount;
    }

    @Override
    public String toString() {
        return "EsCourse [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", sort=" + sort + ", lecturerName=" + lecturerName + ", courseNo=" + courseNo + ", courselog=" + courselog + ", courseName=" + courseName + ", courseSort=" + courseSort + ", courseOriginal="
                + courseOriginal + ", courseDiscount=" + courseDiscount + ", isFree=" + isFree + "]";
    }

}
