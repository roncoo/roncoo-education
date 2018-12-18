package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andLecturerUserNoIsNull() {
            addCriterion("lecturer_user_no is null");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoIsNotNull() {
            addCriterion("lecturer_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoEqualTo(Long value) {
            addCriterion("lecturer_user_no =", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoNotEqualTo(Long value) {
            addCriterion("lecturer_user_no <>", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoGreaterThan(Long value) {
            addCriterion("lecturer_user_no >", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoGreaterThanOrEqualTo(Long value) {
            addCriterion("lecturer_user_no >=", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoLessThan(Long value) {
            addCriterion("lecturer_user_no <", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoLessThanOrEqualTo(Long value) {
            addCriterion("lecturer_user_no <=", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoIn(List<Long> values) {
            addCriterion("lecturer_user_no in", values, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoNotIn(List<Long> values) {
            addCriterion("lecturer_user_no not in", values, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoBetween(Long value1, Long value2) {
            addCriterion("lecturer_user_no between", value1, value2, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoNotBetween(Long value1, Long value2) {
            addCriterion("lecturer_user_no not between", value1, value2, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andCategoryId1IsNull() {
            addCriterion("category_id1 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId1IsNotNull() {
            addCriterion("category_id1 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId1EqualTo(Long value) {
            addCriterion("category_id1 =", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1NotEqualTo(Long value) {
            addCriterion("category_id1 <>", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1GreaterThan(Long value) {
            addCriterion("category_id1 >", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1GreaterThanOrEqualTo(Long value) {
            addCriterion("category_id1 >=", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1LessThan(Long value) {
            addCriterion("category_id1 <", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1LessThanOrEqualTo(Long value) {
            addCriterion("category_id1 <=", value, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1In(List<Long> values) {
            addCriterion("category_id1 in", values, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1NotIn(List<Long> values) {
            addCriterion("category_id1 not in", values, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1Between(Long value1, Long value2) {
            addCriterion("category_id1 between", value1, value2, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId1NotBetween(Long value1, Long value2) {
            addCriterion("category_id1 not between", value1, value2, "categoryId1");
            return (Criteria) this;
        }

        public Criteria andCategoryId2IsNull() {
            addCriterion("category_id2 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId2IsNotNull() {
            addCriterion("category_id2 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId2EqualTo(Long value) {
            addCriterion("category_id2 =", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2NotEqualTo(Long value) {
            addCriterion("category_id2 <>", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2GreaterThan(Long value) {
            addCriterion("category_id2 >", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2GreaterThanOrEqualTo(Long value) {
            addCriterion("category_id2 >=", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2LessThan(Long value) {
            addCriterion("category_id2 <", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2LessThanOrEqualTo(Long value) {
            addCriterion("category_id2 <=", value, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2In(List<Long> values) {
            addCriterion("category_id2 in", values, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2NotIn(List<Long> values) {
            addCriterion("category_id2 not in", values, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2Between(Long value1, Long value2) {
            addCriterion("category_id2 between", value1, value2, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId2NotBetween(Long value1, Long value2) {
            addCriterion("category_id2 not between", value1, value2, "categoryId2");
            return (Criteria) this;
        }

        public Criteria andCategoryId3IsNull() {
            addCriterion("category_id3 is null");
            return (Criteria) this;
        }

        public Criteria andCategoryId3IsNotNull() {
            addCriterion("category_id3 is not null");
            return (Criteria) this;
        }

        public Criteria andCategoryId3EqualTo(Long value) {
            addCriterion("category_id3 =", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3NotEqualTo(Long value) {
            addCriterion("category_id3 <>", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3GreaterThan(Long value) {
            addCriterion("category_id3 >", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3GreaterThanOrEqualTo(Long value) {
            addCriterion("category_id3 >=", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3LessThan(Long value) {
            addCriterion("category_id3 <", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3LessThanOrEqualTo(Long value) {
            addCriterion("category_id3 <=", value, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3In(List<Long> values) {
            addCriterion("category_id3 in", values, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3NotIn(List<Long> values) {
            addCriterion("category_id3 not in", values, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3Between(Long value1, Long value2) {
            addCriterion("category_id3 between", value1, value2, "categoryId3");
            return (Criteria) this;
        }

        public Criteria andCategoryId3NotBetween(Long value1, Long value2) {
            addCriterion("category_id3 not between", value1, value2, "categoryId3");
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

        public Criteria andIntroduceIdIsNull() {
            addCriterion("introduce_id is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdIsNotNull() {
            addCriterion("introduce_id is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdEqualTo(Long value) {
            addCriterion("introduce_id =", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdNotEqualTo(Long value) {
            addCriterion("introduce_id <>", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdGreaterThan(Long value) {
            addCriterion("introduce_id >", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("introduce_id >=", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdLessThan(Long value) {
            addCriterion("introduce_id <", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdLessThanOrEqualTo(Long value) {
            addCriterion("introduce_id <=", value, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdIn(List<Long> values) {
            addCriterion("introduce_id in", values, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdNotIn(List<Long> values) {
            addCriterion("introduce_id not in", values, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdBetween(Long value1, Long value2) {
            addCriterion("introduce_id between", value1, value2, "introduceId");
            return (Criteria) this;
        }

        public Criteria andIntroduceIdNotBetween(Long value1, Long value2) {
            addCriterion("introduce_id not between", value1, value2, "introduceId");
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

        public Criteria andCourseOriginalIsNull() {
            addCriterion("course_original is null");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalIsNotNull() {
            addCriterion("course_original is not null");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalEqualTo(BigDecimal value) {
            addCriterion("course_original =", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalNotEqualTo(BigDecimal value) {
            addCriterion("course_original <>", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalGreaterThan(BigDecimal value) {
            addCriterion("course_original >", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("course_original >=", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalLessThan(BigDecimal value) {
            addCriterion("course_original <", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("course_original <=", value, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalIn(List<BigDecimal> values) {
            addCriterion("course_original in", values, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalNotIn(List<BigDecimal> values) {
            addCriterion("course_original not in", values, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_original between", value1, value2, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseOriginalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_original not between", value1, value2, "courseOriginal");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountIsNull() {
            addCriterion("course_discount is null");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountIsNotNull() {
            addCriterion("course_discount is not null");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountEqualTo(BigDecimal value) {
            addCriterion("course_discount =", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountNotEqualTo(BigDecimal value) {
            addCriterion("course_discount <>", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountGreaterThan(BigDecimal value) {
            addCriterion("course_discount >", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("course_discount >=", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountLessThan(BigDecimal value) {
            addCriterion("course_discount <", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("course_discount <=", value, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountIn(List<BigDecimal> values) {
            addCriterion("course_discount in", values, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountNotIn(List<BigDecimal> values) {
            addCriterion("course_discount not in", values, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_discount between", value1, value2, "courseDiscount");
            return (Criteria) this;
        }

        public Criteria andCourseDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("course_discount not between", value1, value2, "courseDiscount");
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

        public Criteria andPeriodTotalIsNull() {
            addCriterion("period_total is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalIsNotNull() {
            addCriterion("period_total is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalEqualTo(Integer value) {
            addCriterion("period_total =", value, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalNotEqualTo(Integer value) {
            addCriterion("period_total <>", value, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalGreaterThan(Integer value) {
            addCriterion("period_total >", value, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalGreaterThanOrEqualTo(Integer value) {
            addCriterion("period_total >=", value, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalLessThan(Integer value) {
            addCriterion("period_total <", value, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalLessThanOrEqualTo(Integer value) {
            addCriterion("period_total <=", value, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalIn(List<Integer> values) {
            addCriterion("period_total in", values, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalNotIn(List<Integer> values) {
            addCriterion("period_total not in", values, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalBetween(Integer value1, Integer value2) {
            addCriterion("period_total between", value1, value2, "periodTotal");
            return (Criteria) this;
        }

        public Criteria andPeriodTotalNotBetween(Integer value1, Integer value2) {
            addCriterion("period_total not between", value1, value2, "periodTotal");
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