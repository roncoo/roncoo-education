package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseVideoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public CourseVideoExample() {
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

        public Criteria andPeriodIdIsNull() {
            addCriterion("period_id is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIsNotNull() {
            addCriterion("period_id is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodIdEqualTo(Long value) {
            addCriterion("period_id =", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotEqualTo(Long value) {
            addCriterion("period_id <>", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdGreaterThan(Long value) {
            addCriterion("period_id >", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdGreaterThanOrEqualTo(Long value) {
            addCriterion("period_id >=", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLessThan(Long value) {
            addCriterion("period_id <", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdLessThanOrEqualTo(Long value) {
            addCriterion("period_id <=", value, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdIn(List<Long> values) {
            addCriterion("period_id in", values, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotIn(List<Long> values) {
            addCriterion("period_id not in", values, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdBetween(Long value1, Long value2) {
            addCriterion("period_id between", value1, value2, "periodId");
            return (Criteria) this;
        }

        public Criteria andPeriodIdNotBetween(Long value1, Long value2) {
            addCriterion("period_id not between", value1, value2, "periodId");
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

        public Criteria andVideoStatusIsNull() {
            addCriterion("video_status is null");
            return (Criteria) this;
        }

        public Criteria andVideoStatusIsNotNull() {
            addCriterion("video_status is not null");
            return (Criteria) this;
        }

        public Criteria andVideoStatusEqualTo(Integer value) {
            addCriterion("video_status =", value, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusNotEqualTo(Integer value) {
            addCriterion("video_status <>", value, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusGreaterThan(Integer value) {
            addCriterion("video_status >", value, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_status >=", value, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusLessThan(Integer value) {
            addCriterion("video_status <", value, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusLessThanOrEqualTo(Integer value) {
            addCriterion("video_status <=", value, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusIn(List<Integer> values) {
            addCriterion("video_status in", values, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusNotIn(List<Integer> values) {
            addCriterion("video_status not in", values, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusBetween(Integer value1, Integer value2) {
            addCriterion("video_status between", value1, value2, "videoStatus");
            return (Criteria) this;
        }

        public Criteria andVideoStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("video_status not between", value1, value2, "videoStatus");
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

        public Criteria andVideoOasIdIsNull() {
            addCriterion("video_oas_id is null");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdIsNotNull() {
            addCriterion("video_oas_id is not null");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdEqualTo(String value) {
            addCriterion("video_oas_id =", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdNotEqualTo(String value) {
            addCriterion("video_oas_id <>", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdGreaterThan(String value) {
            addCriterion("video_oas_id >", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdGreaterThanOrEqualTo(String value) {
            addCriterion("video_oas_id >=", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdLessThan(String value) {
            addCriterion("video_oas_id <", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdLessThanOrEqualTo(String value) {
            addCriterion("video_oas_id <=", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdLike(String value) {
            addCriterion("video_oas_id like", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdNotLike(String value) {
            addCriterion("video_oas_id not like", value, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdIn(List<String> values) {
            addCriterion("video_oas_id in", values, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdNotIn(List<String> values) {
            addCriterion("video_oas_id not in", values, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdBetween(String value1, String value2) {
            addCriterion("video_oas_id between", value1, value2, "videoOasId");
            return (Criteria) this;
        }

        public Criteria andVideoOasIdNotBetween(String value1, String value2) {
            addCriterion("video_oas_id not between", value1, value2, "videoOasId");
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