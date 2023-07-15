package com.roncoo.education.course.dao.impl.mapper.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CourseExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public CourseExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitStart(int limitStart) {
        this.limitStart = limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(LocalDateTime value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(LocalDateTime value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(LocalDateTime value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(LocalDateTime value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<LocalDateTime> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<LocalDateTime> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNull() {
            addCriterion("gmt_modified is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIsNotNull() {
            addCriterion("gmt_modified is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(LocalDateTime value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(LocalDateTime value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<LocalDateTime> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<LocalDateTime> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("gmt_modified not between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNull() {
            addCriterion("status_id is null");
            return (Criteria) this;
        }

        public Criteria andStatusIdIsNotNull() {
            addCriterion("status_id is not null");
            return (Criteria) this;
        }

        public Criteria andStatusIdEqualTo(Integer value) {
            addCriterion("status_id =", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotEqualTo(Integer value) {
            addCriterion("status_id <>", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThan(Integer value) {
            addCriterion("status_id >", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("status_id >=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThan(Integer value) {
            addCriterion("status_id <", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdLessThanOrEqualTo(Integer value) {
            addCriterion("status_id <=", value, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdIn(List<Integer> values) {
            addCriterion("status_id in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotIn(List<Integer> values) {
            addCriterion("status_id not in", values, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdBetween(Integer value1, Integer value2) {
            addCriterion("status_id between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andStatusIdNotBetween(Integer value1, Integer value2) {
            addCriterion("status_id not between", value1, value2, "statusId");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andLecturerIdIsNull() {
            addCriterion("lecturer_id is null");
            return (Criteria) this;
        }

        public Criteria andLecturerIdIsNotNull() {
            addCriterion("lecturer_id is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerIdEqualTo(Long value) {
            addCriterion("lecturer_id =", value, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdNotEqualTo(Long value) {
            addCriterion("lecturer_id <>", value, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdGreaterThan(Long value) {
            addCriterion("lecturer_id >", value, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("lecturer_id >=", value, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdLessThan(Long value) {
            addCriterion("lecturer_id <", value, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdLessThanOrEqualTo(Long value) {
            addCriterion("lecturer_id <=", value, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdIn(List<Long> values) {
            addCriterion("lecturer_id in", values, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdNotIn(List<Long> values) {
            addCriterion("lecturer_id not in", values, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdBetween(Long value1, Long value2) {
            addCriterion("lecturer_id between", value1, value2, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andLecturerIdNotBetween(Long value1, Long value2) {
            addCriterion("lecturer_id not between", value1, value2, "lecturerId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNull() {
            addCriterion("category_id is null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIsNotNull() {
            addCriterion("category_id is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryIdEqualTo(Long value) {
            addCriterion("category_id =", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotEqualTo(Long value) {
            addCriterion("category_id <>", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThan(Long value) {
            addCriterion("category_id >", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("category_id >=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThan(Long value) {
            addCriterion("category_id <", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("category_id <=", value, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdIn(List<Long> values) {
            addCriterion("category_id in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotIn(List<Long> values) {
            addCriterion("category_id not in", values, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdBetween(Long value1, Long value2) {
            addCriterion("category_id between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("category_id not between", value1, value2, "categoryId");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseLogoIsNull() {
            addCriterion("course_logo is null");
            return (Criteria) this;
        }

        public Criteria andCourseLogoIsNotNull() {
            addCriterion("course_logo is not null");
            return (Criteria) this;
        }

        public Criteria andCourseLogoEqualTo(String value) {
            addCriterion("course_logo =", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotEqualTo(String value) {
            addCriterion("course_logo <>", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoGreaterThan(String value) {
            addCriterion("course_logo >", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoGreaterThanOrEqualTo(String value) {
            addCriterion("course_logo >=", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoLessThan(String value) {
            addCriterion("course_logo <", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoLessThanOrEqualTo(String value) {
            addCriterion("course_logo <=", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoLike(String value) {
            addCriterion("course_logo like", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotLike(String value) {
            addCriterion("course_logo not like", value, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoIn(List<String> values) {
            addCriterion("course_logo in", values, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotIn(List<String> values) {
            addCriterion("course_logo not in", values, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoBetween(String value1, String value2) {
            addCriterion("course_logo between", value1, value2, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andCourseLogoNotBetween(String value1, String value2) {
            addCriterion("course_logo not between", value1, value2, "courseLogo");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNull() {
            addCriterion("is_free is null");
            return (Criteria) this;
        }

        public Criteria andIsFreeIsNotNull() {
            addCriterion("is_free is not null");
            return (Criteria) this;
        }

        public Criteria andIsFreeEqualTo(Integer value) {
            addCriterion("is_free =", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotEqualTo(Integer value) {
            addCriterion("is_free <>", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThan(Integer value) {
            addCriterion("is_free >", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_free >=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThan(Integer value) {
            addCriterion("is_free <", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeLessThanOrEqualTo(Integer value) {
            addCriterion("is_free <=", value, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeIn(List<Integer> values) {
            addCriterion("is_free in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotIn(List<Integer> values) {
            addCriterion("is_free not in", values, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeBetween(Integer value1, Integer value2) {
            addCriterion("is_free between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andIsFreeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_free not between", value1, value2, "isFree");
            return (Criteria) this;
        }

        public Criteria andRulingPriceIsNull() {
            addCriterion("ruling_price is null");
            return (Criteria) this;
        }

        public Criteria andRulingPriceIsNotNull() {
            addCriterion("ruling_price is not null");
            return (Criteria) this;
        }

        public Criteria andRulingPriceEqualTo(BigDecimal value) {
            addCriterion("ruling_price =", value, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceNotEqualTo(BigDecimal value) {
            addCriterion("ruling_price <>", value, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceGreaterThan(BigDecimal value) {
            addCriterion("ruling_price >", value, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("ruling_price >=", value, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceLessThan(BigDecimal value) {
            addCriterion("ruling_price <", value, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("ruling_price <=", value, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceIn(List<BigDecimal> values) {
            addCriterion("ruling_price in", values, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceNotIn(List<BigDecimal> values) {
            addCriterion("ruling_price not in", values, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ruling_price between", value1, value2, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andRulingPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("ruling_price not between", value1, value2, "rulingPrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceIsNull() {
            addCriterion("course_price is null");
            return (Criteria) this;
        }

        public Criteria andCoursePriceIsNotNull() {
            addCriterion("course_price is not null");
            return (Criteria) this;
        }

        public Criteria andCoursePriceEqualTo(BigDecimal value) {
            addCriterion("course_price =", value, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceNotEqualTo(BigDecimal value) {
            addCriterion("course_price <>", value, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceGreaterThan(BigDecimal value) {
            addCriterion("course_price >", value, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("course_price >=", value, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceLessThan(BigDecimal value) {
            addCriterion("course_price <", value, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("course_price <=", value, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceIn(List<BigDecimal> values) {
            addCriterion("course_price in", values, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceNotIn(List<BigDecimal> values) {
            addCriterion("course_price not in", values, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_price between", value1, value2, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andCoursePriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_price not between", value1, value2, "coursePrice");
            return (Criteria) this;
        }

        public Criteria andIsPutawayIsNull() {
            addCriterion("is_putaway is null");
            return (Criteria) this;
        }

        public Criteria andIsPutawayIsNotNull() {
            addCriterion("is_putaway is not null");
            return (Criteria) this;
        }

        public Criteria andIsPutawayEqualTo(Integer value) {
            addCriterion("is_putaway =", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayNotEqualTo(Integer value) {
            addCriterion("is_putaway <>", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayGreaterThan(Integer value) {
            addCriterion("is_putaway >", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_putaway >=", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayLessThan(Integer value) {
            addCriterion("is_putaway <", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayLessThanOrEqualTo(Integer value) {
            addCriterion("is_putaway <=", value, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayIn(List<Integer> values) {
            addCriterion("is_putaway in", values, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayNotIn(List<Integer> values) {
            addCriterion("is_putaway not in", values, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayBetween(Integer value1, Integer value2) {
            addCriterion("is_putaway between", value1, value2, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andIsPutawayNotBetween(Integer value1, Integer value2) {
            addCriterion("is_putaway not between", value1, value2, "isPutaway");
            return (Criteria) this;
        }

        public Criteria andCourseSortIsNull() {
            addCriterion("course_sort is null");
            return (Criteria) this;
        }

        public Criteria andCourseSortIsNotNull() {
            addCriterion("course_sort is not null");
            return (Criteria) this;
        }

        public Criteria andCourseSortEqualTo(Integer value) {
            addCriterion("course_sort =", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortNotEqualTo(Integer value) {
            addCriterion("course_sort <>", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortGreaterThan(Integer value) {
            addCriterion("course_sort >", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("course_sort >=", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortLessThan(Integer value) {
            addCriterion("course_sort <", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortLessThanOrEqualTo(Integer value) {
            addCriterion("course_sort <=", value, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortIn(List<Integer> values) {
            addCriterion("course_sort in", values, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortNotIn(List<Integer> values) {
            addCriterion("course_sort not in", values, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortBetween(Integer value1, Integer value2) {
            addCriterion("course_sort between", value1, value2, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCourseSortNotBetween(Integer value1, Integer value2) {
            addCriterion("course_sort not between", value1, value2, "courseSort");
            return (Criteria) this;
        }

        public Criteria andCountBuyIsNull() {
            addCriterion("count_buy is null");
            return (Criteria) this;
        }

        public Criteria andCountBuyIsNotNull() {
            addCriterion("count_buy is not null");
            return (Criteria) this;
        }

        public Criteria andCountBuyEqualTo(Integer value) {
            addCriterion("count_buy =", value, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyNotEqualTo(Integer value) {
            addCriterion("count_buy <>", value, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyGreaterThan(Integer value) {
            addCriterion("count_buy >", value, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_buy >=", value, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyLessThan(Integer value) {
            addCriterion("count_buy <", value, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyLessThanOrEqualTo(Integer value) {
            addCriterion("count_buy <=", value, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyIn(List<Integer> values) {
            addCriterion("count_buy in", values, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyNotIn(List<Integer> values) {
            addCriterion("count_buy not in", values, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyBetween(Integer value1, Integer value2) {
            addCriterion("count_buy between", value1, value2, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountBuyNotBetween(Integer value1, Integer value2) {
            addCriterion("count_buy not between", value1, value2, "countBuy");
            return (Criteria) this;
        }

        public Criteria andCountStudyIsNull() {
            addCriterion("count_study is null");
            return (Criteria) this;
        }

        public Criteria andCountStudyIsNotNull() {
            addCriterion("count_study is not null");
            return (Criteria) this;
        }

        public Criteria andCountStudyEqualTo(Integer value) {
            addCriterion("count_study =", value, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyNotEqualTo(Integer value) {
            addCriterion("count_study <>", value, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyGreaterThan(Integer value) {
            addCriterion("count_study >", value, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyGreaterThanOrEqualTo(Integer value) {
            addCriterion("count_study >=", value, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyLessThan(Integer value) {
            addCriterion("count_study <", value, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyLessThanOrEqualTo(Integer value) {
            addCriterion("count_study <=", value, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyIn(List<Integer> values) {
            addCriterion("count_study in", values, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyNotIn(List<Integer> values) {
            addCriterion("count_study not in", values, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyBetween(Integer value1, Integer value2) {
            addCriterion("count_study between", value1, value2, "countStudy");
            return (Criteria) this;
        }

        public Criteria andCountStudyNotBetween(Integer value1, Integer value2) {
            addCriterion("count_study not between", value1, value2, "countStudy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
