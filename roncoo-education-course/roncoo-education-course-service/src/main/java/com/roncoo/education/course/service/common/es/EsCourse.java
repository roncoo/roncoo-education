package com.roncoo.education.course.service.common.es;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * 课程信息表
 *
 * @author wuyun
 */
@Document(indexName = "es_course", type = "course")
public class EsCourse implements Serializable {

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
	 * 机构号
	 */
	private String orgNo;
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
	private String courseImg;
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
	private BigDecimal orgPrice;
	/**
	 * 优惠价
	 */
	private BigDecimal fabPrice;
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

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getCourseImg() {
		return courseImg;
	}

	public void setCourseImg(String courseImg) {
		this.courseImg = courseImg;
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

	public BigDecimal getOrgPrice() {
		return orgPrice;
	}

	public void setOrgPrice(BigDecimal orgPrice) {
		this.orgPrice = orgPrice;
	}

	public BigDecimal getFabPrice() {
		return fabPrice;
	}

	public void setFabPrice(BigDecimal fabPrice) {
		this.fabPrice = fabPrice;
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

	@Override
	public String toString() {
		return "EsCourse [id=" + id + ", gmtCreate=" + gmtCreate + ", gmtModified=" + gmtModified + ", orgNo=" + orgNo + ", sort=" + sort + ", lecturerName=" + lecturerName + ", courseNo=" + courseNo + ", courseName=" + courseName + ", courseImg=" + courseImg + ", courseSort=" + courseSort
				+ ", orgPrice=" + orgPrice + ", fabPrice=" + fabPrice + ", isFree=" + isFree + "]";
	}

}
