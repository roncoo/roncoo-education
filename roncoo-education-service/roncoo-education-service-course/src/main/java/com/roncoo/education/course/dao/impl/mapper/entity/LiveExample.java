package com.roncoo.education.course.dao.impl.mapper.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LiveExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public LiveExample() {
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

        public Criteria andLiveNameIsNull() {
            addCriterion("live_name is null");
            return (Criteria) this;
        }

        public Criteria andLiveNameIsNotNull() {
            addCriterion("live_name is not null");
            return (Criteria) this;
        }

        public Criteria andLiveNameEqualTo(String value) {
            addCriterion("live_name =", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameNotEqualTo(String value) {
            addCriterion("live_name <>", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameGreaterThan(String value) {
            addCriterion("live_name >", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameGreaterThanOrEqualTo(String value) {
            addCriterion("live_name >=", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameLessThan(String value) {
            addCriterion("live_name <", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameLessThanOrEqualTo(String value) {
            addCriterion("live_name <=", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameLike(String value) {
            addCriterion("live_name like", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameNotLike(String value) {
            addCriterion("live_name not like", value, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameIn(List<String> values) {
            addCriterion("live_name in", values, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameNotIn(List<String> values) {
            addCriterion("live_name not in", values, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameBetween(String value1, String value2) {
            addCriterion("live_name between", value1, value2, "liveName");
            return (Criteria) this;
        }

        public Criteria andLiveNameNotBetween(String value1, String value2) {
            addCriterion("live_name not between", value1, value2, "liveName");
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

        public Criteria andLiveModelIsNull() {
            addCriterion("live_model is null");
            return (Criteria) this;
        }

        public Criteria andLiveModelIsNotNull() {
            addCriterion("live_model is not null");
            return (Criteria) this;
        }

        public Criteria andLiveModelEqualTo(Integer value) {
            addCriterion("live_model =", value, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelNotEqualTo(Integer value) {
            addCriterion("live_model <>", value, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelGreaterThan(Integer value) {
            addCriterion("live_model >", value, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_model >=", value, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelLessThan(Integer value) {
            addCriterion("live_model <", value, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelLessThanOrEqualTo(Integer value) {
            addCriterion("live_model <=", value, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelIn(List<Integer> values) {
            addCriterion("live_model in", values, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelNotIn(List<Integer> values) {
            addCriterion("live_model not in", values, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelBetween(Integer value1, Integer value2) {
            addCriterion("live_model between", value1, value2, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveModelNotBetween(Integer value1, Integer value2) {
            addCriterion("live_model not between", value1, value2, "liveModel");
            return (Criteria) this;
        }

        public Criteria andLiveDelayIsNull() {
            addCriterion("live_delay is null");
            return (Criteria) this;
        }

        public Criteria andLiveDelayIsNotNull() {
            addCriterion("live_delay is not null");
            return (Criteria) this;
        }

        public Criteria andLiveDelayEqualTo(Integer value) {
            addCriterion("live_delay =", value, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayNotEqualTo(Integer value) {
            addCriterion("live_delay <>", value, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayGreaterThan(Integer value) {
            addCriterion("live_delay >", value, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_delay >=", value, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayLessThan(Integer value) {
            addCriterion("live_delay <", value, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayLessThanOrEqualTo(Integer value) {
            addCriterion("live_delay <=", value, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayIn(List<Integer> values) {
            addCriterion("live_delay in", values, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayNotIn(List<Integer> values) {
            addCriterion("live_delay not in", values, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayBetween(Integer value1, Integer value2) {
            addCriterion("live_delay between", value1, value2, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andLiveDelayNotBetween(Integer value1, Integer value2) {
            addCriterion("live_delay not between", value1, value2, "liveDelay");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNull() {
            addCriterion("begin_time is null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIsNotNull() {
            addCriterion("begin_time is not null");
            return (Criteria) this;
        }

        public Criteria andBeginTimeEqualTo(LocalDateTime value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(LocalDateTime value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(LocalDateTime value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(LocalDateTime value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<LocalDateTime> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<LocalDateTime> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andLiveDurationIsNull() {
            addCriterion("live_duration is null");
            return (Criteria) this;
        }

        public Criteria andLiveDurationIsNotNull() {
            addCriterion("live_duration is not null");
            return (Criteria) this;
        }

        public Criteria andLiveDurationEqualTo(Integer value) {
            addCriterion("live_duration =", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationNotEqualTo(Integer value) {
            addCriterion("live_duration <>", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationGreaterThan(Integer value) {
            addCriterion("live_duration >", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_duration >=", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationLessThan(Integer value) {
            addCriterion("live_duration <", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationLessThanOrEqualTo(Integer value) {
            addCriterion("live_duration <=", value, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationIn(List<Integer> values) {
            addCriterion("live_duration in", values, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationNotIn(List<Integer> values) {
            addCriterion("live_duration not in", values, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationBetween(Integer value1, Integer value2) {
            addCriterion("live_duration between", value1, value2, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveDurationNotBetween(Integer value1, Integer value2) {
            addCriterion("live_duration not between", value1, value2, "liveDuration");
            return (Criteria) this;
        }

        public Criteria andLiveStatusIsNull() {
            addCriterion("live_status is null");
            return (Criteria) this;
        }

        public Criteria andLiveStatusIsNotNull() {
            addCriterion("live_status is not null");
            return (Criteria) this;
        }

        public Criteria andLiveStatusEqualTo(Integer value) {
            addCriterion("live_status =", value, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusNotEqualTo(Integer value) {
            addCriterion("live_status <>", value, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusGreaterThan(Integer value) {
            addCriterion("live_status >", value, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_status >=", value, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusLessThan(Integer value) {
            addCriterion("live_status <", value, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusLessThanOrEqualTo(Integer value) {
            addCriterion("live_status <=", value, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusIn(List<Integer> values) {
            addCriterion("live_status in", values, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusNotIn(List<Integer> values) {
            addCriterion("live_status not in", values, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusBetween(Integer value1, Integer value2) {
            addCriterion("live_status between", value1, value2, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andLiveStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("live_status not between", value1, value2, "liveStatus");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveIsNull() {
            addCriterion("playback_save is null");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveIsNotNull() {
            addCriterion("playback_save is not null");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveEqualTo(Integer value) {
            addCriterion("playback_save =", value, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveNotEqualTo(Integer value) {
            addCriterion("playback_save <>", value, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveGreaterThan(Integer value) {
            addCriterion("playback_save >", value, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveGreaterThanOrEqualTo(Integer value) {
            addCriterion("playback_save >=", value, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveLessThan(Integer value) {
            addCriterion("playback_save <", value, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveLessThanOrEqualTo(Integer value) {
            addCriterion("playback_save <=", value, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveIn(List<Integer> values) {
            addCriterion("playback_save in", values, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveNotIn(List<Integer> values) {
            addCriterion("playback_save not in", values, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveBetween(Integer value1, Integer value2) {
            addCriterion("playback_save between", value1, value2, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andPlaybackSaveNotBetween(Integer value1, Integer value2) {
            addCriterion("playback_save not between", value1, value2, "playbackSave");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNull() {
            addCriterion("resource_id is null");
            return (Criteria) this;
        }

        public Criteria andResourceIdIsNotNull() {
            addCriterion("resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andResourceIdEqualTo(Long value) {
            addCriterion("resource_id =", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotEqualTo(Long value) {
            addCriterion("resource_id <>", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThan(Long value) {
            addCriterion("resource_id >", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdGreaterThanOrEqualTo(Long value) {
            addCriterion("resource_id >=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThan(Long value) {
            addCriterion("resource_id <", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdLessThanOrEqualTo(Long value) {
            addCriterion("resource_id <=", value, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdIn(List<Long> values) {
            addCriterion("resource_id in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotIn(List<Long> values) {
            addCriterion("resource_id not in", values, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdBetween(Long value1, Long value2) {
            addCriterion("resource_id between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andResourceIdNotBetween(Long value1, Long value2) {
            addCriterion("resource_id not between", value1, value2, "resourceId");
            return (Criteria) this;
        }

        public Criteria andLivePlatformIsNull() {
            addCriterion("live_platform is null");
            return (Criteria) this;
        }

        public Criteria andLivePlatformIsNotNull() {
            addCriterion("live_platform is not null");
            return (Criteria) this;
        }

        public Criteria andLivePlatformEqualTo(Integer value) {
            addCriterion("live_platform =", value, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformNotEqualTo(Integer value) {
            addCriterion("live_platform <>", value, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformGreaterThan(Integer value) {
            addCriterion("live_platform >", value, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("live_platform >=", value, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformLessThan(Integer value) {
            addCriterion("live_platform <", value, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformLessThanOrEqualTo(Integer value) {
            addCriterion("live_platform <=", value, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformIn(List<Integer> values) {
            addCriterion("live_platform in", values, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformNotIn(List<Integer> values) {
            addCriterion("live_platform not in", values, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformBetween(Integer value1, Integer value2) {
            addCriterion("live_platform between", value1, value2, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andLivePlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("live_platform not between", value1, value2, "livePlatform");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNull() {
            addCriterion("channel_id is null");
            return (Criteria) this;
        }

        public Criteria andChannelIdIsNotNull() {
            addCriterion("channel_id is not null");
            return (Criteria) this;
        }

        public Criteria andChannelIdEqualTo(String value) {
            addCriterion("channel_id =", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotEqualTo(String value) {
            addCriterion("channel_id <>", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThan(String value) {
            addCriterion("channel_id >", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_id >=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThan(String value) {
            addCriterion("channel_id <", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLessThanOrEqualTo(String value) {
            addCriterion("channel_id <=", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdLike(String value) {
            addCriterion("channel_id like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotLike(String value) {
            addCriterion("channel_id not like", value, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdIn(List<String> values) {
            addCriterion("channel_id in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotIn(List<String> values) {
            addCriterion("channel_id not in", values, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdBetween(String value1, String value2) {
            addCriterion("channel_id between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelIdNotBetween(String value1, String value2) {
            addCriterion("channel_id not between", value1, value2, "channelId");
            return (Criteria) this;
        }

        public Criteria andChannelPwdIsNull() {
            addCriterion("channel_pwd is null");
            return (Criteria) this;
        }

        public Criteria andChannelPwdIsNotNull() {
            addCriterion("channel_pwd is not null");
            return (Criteria) this;
        }

        public Criteria andChannelPwdEqualTo(String value) {
            addCriterion("channel_pwd =", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdNotEqualTo(String value) {
            addCriterion("channel_pwd <>", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdGreaterThan(String value) {
            addCriterion("channel_pwd >", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdGreaterThanOrEqualTo(String value) {
            addCriterion("channel_pwd >=", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdLessThan(String value) {
            addCriterion("channel_pwd <", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdLessThanOrEqualTo(String value) {
            addCriterion("channel_pwd <=", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdLike(String value) {
            addCriterion("channel_pwd like", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdNotLike(String value) {
            addCriterion("channel_pwd not like", value, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdIn(List<String> values) {
            addCriterion("channel_pwd in", values, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdNotIn(List<String> values) {
            addCriterion("channel_pwd not in", values, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdBetween(String value1, String value2) {
            addCriterion("channel_pwd between", value1, value2, "channelPwd");
            return (Criteria) this;
        }

        public Criteria andChannelPwdNotBetween(String value1, String value2) {
            addCriterion("channel_pwd not between", value1, value2, "channelPwd");
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