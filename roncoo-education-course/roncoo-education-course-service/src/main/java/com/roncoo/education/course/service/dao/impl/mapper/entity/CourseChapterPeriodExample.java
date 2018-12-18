package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseChapterPeriodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public CourseChapterPeriodExample() {
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
        this.limitStart=limitStart;
    }

    public int getLimitStart() {
        return limitStart;
    }

    public void setPageSize(int pageSize) {
        this.pageSize=pageSize;
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

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
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

        public Criteria andGmtModifiedEqualTo(Date value) {
            addCriterion("gmt_modified =", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotEqualTo(Date value) {
            addCriterion("gmt_modified <>", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThan(Date value) {
            addCriterion("gmt_modified >", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modified >=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThan(Date value) {
            addCriterion("gmt_modified <", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modified <=", value, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedIn(List<Date> values) {
            addCriterion("gmt_modified in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotIn(List<Date> values) {
            addCriterion("gmt_modified not in", values, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedBetween(Date value1, Date value2) {
            addCriterion("gmt_modified between", value1, value2, "gmtModified");
            return (Criteria) this;
        }

        public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
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

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Long value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Long value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Long value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Long value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Long value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Long> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Long> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Long value1, Long value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Long value1, Long value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNull() {
            addCriterion("chapter_id is null");
            return (Criteria) this;
        }

        public Criteria andChapterIdIsNotNull() {
            addCriterion("chapter_id is not null");
            return (Criteria) this;
        }

        public Criteria andChapterIdEqualTo(Long value) {
            addCriterion("chapter_id =", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotEqualTo(Long value) {
            addCriterion("chapter_id <>", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThan(Long value) {
            addCriterion("chapter_id >", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdGreaterThanOrEqualTo(Long value) {
            addCriterion("chapter_id >=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThan(Long value) {
            addCriterion("chapter_id <", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdLessThanOrEqualTo(Long value) {
            addCriterion("chapter_id <=", value, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdIn(List<Long> values) {
            addCriterion("chapter_id in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotIn(List<Long> values) {
            addCriterion("chapter_id not in", values, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdBetween(Long value1, Long value2) {
            addCriterion("chapter_id between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andChapterIdNotBetween(Long value1, Long value2) {
            addCriterion("chapter_id not between", value1, value2, "chapterId");
            return (Criteria) this;
        }

        public Criteria andPeriodNameIsNull() {
            addCriterion("period_name is null");
            return (Criteria) this;
        }

        public Criteria andPeriodNameIsNotNull() {
            addCriterion("period_name is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodNameEqualTo(String value) {
            addCriterion("period_name =", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameNotEqualTo(String value) {
            addCriterion("period_name <>", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameGreaterThan(String value) {
            addCriterion("period_name >", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameGreaterThanOrEqualTo(String value) {
            addCriterion("period_name >=", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameLessThan(String value) {
            addCriterion("period_name <", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameLessThanOrEqualTo(String value) {
            addCriterion("period_name <=", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameLike(String value) {
            addCriterion("period_name like", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameNotLike(String value) {
            addCriterion("period_name not like", value, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameIn(List<String> values) {
            addCriterion("period_name in", values, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameNotIn(List<String> values) {
            addCriterion("period_name not in", values, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameBetween(String value1, String value2) {
            addCriterion("period_name between", value1, value2, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodNameNotBetween(String value1, String value2) {
            addCriterion("period_name not between", value1, value2, "periodName");
            return (Criteria) this;
        }

        public Criteria andPeriodDescIsNull() {
            addCriterion("period_desc is null");
            return (Criteria) this;
        }

        public Criteria andPeriodDescIsNotNull() {
            addCriterion("period_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodDescEqualTo(String value) {
            addCriterion("period_desc =", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescNotEqualTo(String value) {
            addCriterion("period_desc <>", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescGreaterThan(String value) {
            addCriterion("period_desc >", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescGreaterThanOrEqualTo(String value) {
            addCriterion("period_desc >=", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescLessThan(String value) {
            addCriterion("period_desc <", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescLessThanOrEqualTo(String value) {
            addCriterion("period_desc <=", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescLike(String value) {
            addCriterion("period_desc like", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescNotLike(String value) {
            addCriterion("period_desc not like", value, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescIn(List<String> values) {
            addCriterion("period_desc in", values, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescNotIn(List<String> values) {
            addCriterion("period_desc not in", values, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescBetween(String value1, String value2) {
            addCriterion("period_desc between", value1, value2, "periodDesc");
            return (Criteria) this;
        }

        public Criteria andPeriodDescNotBetween(String value1, String value2) {
            addCriterion("period_desc not between", value1, value2, "periodDesc");
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

        public Criteria andPeriodOriginalIsNull() {
            addCriterion("period_original is null");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalIsNotNull() {
            addCriterion("period_original is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalEqualTo(BigDecimal value) {
            addCriterion("period_original =", value, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalNotEqualTo(BigDecimal value) {
            addCriterion("period_original <>", value, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalGreaterThan(BigDecimal value) {
            addCriterion("period_original >", value, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("period_original >=", value, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalLessThan(BigDecimal value) {
            addCriterion("period_original <", value, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("period_original <=", value, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalIn(List<BigDecimal> values) {
            addCriterion("period_original in", values, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalNotIn(List<BigDecimal> values) {
            addCriterion("period_original not in", values, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_original between", value1, value2, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodOriginalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_original not between", value1, value2, "periodOriginal");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountIsNull() {
            addCriterion("period_discount is null");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountIsNotNull() {
            addCriterion("period_discount is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountEqualTo(BigDecimal value) {
            addCriterion("period_discount =", value, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountNotEqualTo(BigDecimal value) {
            addCriterion("period_discount <>", value, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountGreaterThan(BigDecimal value) {
            addCriterion("period_discount >", value, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("period_discount >=", value, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountLessThan(BigDecimal value) {
            addCriterion("period_discount <", value, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("period_discount <=", value, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountIn(List<BigDecimal> values) {
            addCriterion("period_discount in", values, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountNotIn(List<BigDecimal> values) {
            addCriterion("period_discount not in", values, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_discount between", value1, value2, "periodDiscount");
            return (Criteria) this;
        }

        public Criteria andPeriodDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("period_discount not between", value1, value2, "periodDiscount");
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

        public Criteria andIsDocIsNull() {
            addCriterion("is_doc is null");
            return (Criteria) this;
        }

        public Criteria andIsDocIsNotNull() {
            addCriterion("is_doc is not null");
            return (Criteria) this;
        }

        public Criteria andIsDocEqualTo(Integer value) {
            addCriterion("is_doc =", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocNotEqualTo(Integer value) {
            addCriterion("is_doc <>", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocGreaterThan(Integer value) {
            addCriterion("is_doc >", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_doc >=", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocLessThan(Integer value) {
            addCriterion("is_doc <", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocLessThanOrEqualTo(Integer value) {
            addCriterion("is_doc <=", value, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocIn(List<Integer> values) {
            addCriterion("is_doc in", values, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocNotIn(List<Integer> values) {
            addCriterion("is_doc not in", values, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocBetween(Integer value1, Integer value2) {
            addCriterion("is_doc between", value1, value2, "isDoc");
            return (Criteria) this;
        }

        public Criteria andIsDocNotBetween(Integer value1, Integer value2) {
            addCriterion("is_doc not between", value1, value2, "isDoc");
            return (Criteria) this;
        }

        public Criteria andDocNameIsNull() {
            addCriterion("doc_name is null");
            return (Criteria) this;
        }

        public Criteria andDocNameIsNotNull() {
            addCriterion("doc_name is not null");
            return (Criteria) this;
        }

        public Criteria andDocNameEqualTo(String value) {
            addCriterion("doc_name =", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotEqualTo(String value) {
            addCriterion("doc_name <>", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameGreaterThan(String value) {
            addCriterion("doc_name >", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameGreaterThanOrEqualTo(String value) {
            addCriterion("doc_name >=", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameLessThan(String value) {
            addCriterion("doc_name <", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameLessThanOrEqualTo(String value) {
            addCriterion("doc_name <=", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameLike(String value) {
            addCriterion("doc_name like", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotLike(String value) {
            addCriterion("doc_name not like", value, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameIn(List<String> values) {
            addCriterion("doc_name in", values, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotIn(List<String> values) {
            addCriterion("doc_name not in", values, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameBetween(String value1, String value2) {
            addCriterion("doc_name between", value1, value2, "docName");
            return (Criteria) this;
        }

        public Criteria andDocNameNotBetween(String value1, String value2) {
            addCriterion("doc_name not between", value1, value2, "docName");
            return (Criteria) this;
        }

        public Criteria andDocUrlIsNull() {
            addCriterion("doc_url is null");
            return (Criteria) this;
        }

        public Criteria andDocUrlIsNotNull() {
            addCriterion("doc_url is not null");
            return (Criteria) this;
        }

        public Criteria andDocUrlEqualTo(String value) {
            addCriterion("doc_url =", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotEqualTo(String value) {
            addCriterion("doc_url <>", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlGreaterThan(String value) {
            addCriterion("doc_url >", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlGreaterThanOrEqualTo(String value) {
            addCriterion("doc_url >=", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlLessThan(String value) {
            addCriterion("doc_url <", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlLessThanOrEqualTo(String value) {
            addCriterion("doc_url <=", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlLike(String value) {
            addCriterion("doc_url like", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotLike(String value) {
            addCriterion("doc_url not like", value, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlIn(List<String> values) {
            addCriterion("doc_url in", values, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotIn(List<String> values) {
            addCriterion("doc_url not in", values, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlBetween(String value1, String value2) {
            addCriterion("doc_url between", value1, value2, "docUrl");
            return (Criteria) this;
        }

        public Criteria andDocUrlNotBetween(String value1, String value2) {
            addCriterion("doc_url not between", value1, value2, "docUrl");
            return (Criteria) this;
        }

        public Criteria andIsVideoIsNull() {
            addCriterion("is_video is null");
            return (Criteria) this;
        }

        public Criteria andIsVideoIsNotNull() {
            addCriterion("is_video is not null");
            return (Criteria) this;
        }

        public Criteria andIsVideoEqualTo(String value) {
            addCriterion("is_video =", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoNotEqualTo(String value) {
            addCriterion("is_video <>", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoGreaterThan(String value) {
            addCriterion("is_video >", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoGreaterThanOrEqualTo(String value) {
            addCriterion("is_video >=", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLessThan(String value) {
            addCriterion("is_video <", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLessThanOrEqualTo(String value) {
            addCriterion("is_video <=", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoLike(String value) {
            addCriterion("is_video like", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoNotLike(String value) {
            addCriterion("is_video not like", value, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoIn(List<String> values) {
            addCriterion("is_video in", values, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoNotIn(List<String> values) {
            addCriterion("is_video not in", values, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoBetween(String value1, String value2) {
            addCriterion("is_video between", value1, value2, "isVideo");
            return (Criteria) this;
        }

        public Criteria andIsVideoNotBetween(String value1, String value2) {
            addCriterion("is_video not between", value1, value2, "isVideo");
            return (Criteria) this;
        }

        public Criteria andVideoNoIsNull() {
            addCriterion("video_no is null");
            return (Criteria) this;
        }

        public Criteria andVideoNoIsNotNull() {
            addCriterion("video_no is not null");
            return (Criteria) this;
        }

        public Criteria andVideoNoEqualTo(Long value) {
            addCriterion("video_no =", value, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoNotEqualTo(Long value) {
            addCriterion("video_no <>", value, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoGreaterThan(Long value) {
            addCriterion("video_no >", value, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoGreaterThanOrEqualTo(Long value) {
            addCriterion("video_no >=", value, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoLessThan(Long value) {
            addCriterion("video_no <", value, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoLessThanOrEqualTo(Long value) {
            addCriterion("video_no <=", value, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoIn(List<Long> values) {
            addCriterion("video_no in", values, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoNotIn(List<Long> values) {
            addCriterion("video_no not in", values, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoBetween(Long value1, Long value2) {
            addCriterion("video_no between", value1, value2, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNoNotBetween(Long value1, Long value2) {
            addCriterion("video_no not between", value1, value2, "videoNo");
            return (Criteria) this;
        }

        public Criteria andVideoNameIsNull() {
            addCriterion("video_name is null");
            return (Criteria) this;
        }

        public Criteria andVideoNameIsNotNull() {
            addCriterion("video_name is not null");
            return (Criteria) this;
        }

        public Criteria andVideoNameEqualTo(String value) {
            addCriterion("video_name =", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotEqualTo(String value) {
            addCriterion("video_name <>", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameGreaterThan(String value) {
            addCriterion("video_name >", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameGreaterThanOrEqualTo(String value) {
            addCriterion("video_name >=", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameLessThan(String value) {
            addCriterion("video_name <", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameLessThanOrEqualTo(String value) {
            addCriterion("video_name <=", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameLike(String value) {
            addCriterion("video_name like", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotLike(String value) {
            addCriterion("video_name not like", value, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameIn(List<String> values) {
            addCriterion("video_name in", values, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotIn(List<String> values) {
            addCriterion("video_name not in", values, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameBetween(String value1, String value2) {
            addCriterion("video_name between", value1, value2, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoNameNotBetween(String value1, String value2) {
            addCriterion("video_name not between", value1, value2, "videoName");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIsNull() {
            addCriterion("video_length is null");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIsNotNull() {
            addCriterion("video_length is not null");
            return (Criteria) this;
        }

        public Criteria andVideoLengthEqualTo(String value) {
            addCriterion("video_length =", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotEqualTo(String value) {
            addCriterion("video_length <>", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthGreaterThan(String value) {
            addCriterion("video_length >", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthGreaterThanOrEqualTo(String value) {
            addCriterion("video_length >=", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLessThan(String value) {
            addCriterion("video_length <", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLessThanOrEqualTo(String value) {
            addCriterion("video_length <=", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLike(String value) {
            addCriterion("video_length like", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotLike(String value) {
            addCriterion("video_length not like", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIn(List<String> values) {
            addCriterion("video_length in", values, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotIn(List<String> values) {
            addCriterion("video_length not in", values, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthBetween(String value1, String value2) {
            addCriterion("video_length between", value1, value2, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotBetween(String value1, String value2) {
            addCriterion("video_length not between", value1, value2, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoVidIsNull() {
            addCriterion("video_vid is null");
            return (Criteria) this;
        }

        public Criteria andVideoVidIsNotNull() {
            addCriterion("video_vid is not null");
            return (Criteria) this;
        }

        public Criteria andVideoVidEqualTo(String value) {
            addCriterion("video_vid =", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidNotEqualTo(String value) {
            addCriterion("video_vid <>", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidGreaterThan(String value) {
            addCriterion("video_vid >", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidGreaterThanOrEqualTo(String value) {
            addCriterion("video_vid >=", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidLessThan(String value) {
            addCriterion("video_vid <", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidLessThanOrEqualTo(String value) {
            addCriterion("video_vid <=", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidLike(String value) {
            addCriterion("video_vid like", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidNotLike(String value) {
            addCriterion("video_vid not like", value, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidIn(List<String> values) {
            addCriterion("video_vid in", values, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidNotIn(List<String> values) {
            addCriterion("video_vid not in", values, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidBetween(String value1, String value2) {
            addCriterion("video_vid between", value1, value2, "videoVid");
            return (Criteria) this;
        }

        public Criteria andVideoVidNotBetween(String value1, String value2) {
            addCriterion("video_vid not between", value1, value2, "videoVid");
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