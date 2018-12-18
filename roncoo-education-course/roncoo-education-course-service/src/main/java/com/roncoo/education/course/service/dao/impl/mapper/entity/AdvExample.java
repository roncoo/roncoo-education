package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public AdvExample() {
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

        public Criteria andAdvTitleIsNull() {
            addCriterion("adv_title is null");
            return (Criteria) this;
        }

        public Criteria andAdvTitleIsNotNull() {
            addCriterion("adv_title is not null");
            return (Criteria) this;
        }

        public Criteria andAdvTitleEqualTo(String value) {
            addCriterion("adv_title =", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleNotEqualTo(String value) {
            addCriterion("adv_title <>", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleGreaterThan(String value) {
            addCriterion("adv_title >", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleGreaterThanOrEqualTo(String value) {
            addCriterion("adv_title >=", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleLessThan(String value) {
            addCriterion("adv_title <", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleLessThanOrEqualTo(String value) {
            addCriterion("adv_title <=", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleLike(String value) {
            addCriterion("adv_title like", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleNotLike(String value) {
            addCriterion("adv_title not like", value, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleIn(List<String> values) {
            addCriterion("adv_title in", values, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleNotIn(List<String> values) {
            addCriterion("adv_title not in", values, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleBetween(String value1, String value2) {
            addCriterion("adv_title between", value1, value2, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvTitleNotBetween(String value1, String value2) {
            addCriterion("adv_title not between", value1, value2, "advTitle");
            return (Criteria) this;
        }

        public Criteria andAdvImgIsNull() {
            addCriterion("adv_img is null");
            return (Criteria) this;
        }

        public Criteria andAdvImgIsNotNull() {
            addCriterion("adv_img is not null");
            return (Criteria) this;
        }

        public Criteria andAdvImgEqualTo(String value) {
            addCriterion("adv_img =", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgNotEqualTo(String value) {
            addCriterion("adv_img <>", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgGreaterThan(String value) {
            addCriterion("adv_img >", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgGreaterThanOrEqualTo(String value) {
            addCriterion("adv_img >=", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgLessThan(String value) {
            addCriterion("adv_img <", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgLessThanOrEqualTo(String value) {
            addCriterion("adv_img <=", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgLike(String value) {
            addCriterion("adv_img like", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgNotLike(String value) {
            addCriterion("adv_img not like", value, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgIn(List<String> values) {
            addCriterion("adv_img in", values, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgNotIn(List<String> values) {
            addCriterion("adv_img not in", values, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgBetween(String value1, String value2) {
            addCriterion("adv_img between", value1, value2, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvImgNotBetween(String value1, String value2) {
            addCriterion("adv_img not between", value1, value2, "advImg");
            return (Criteria) this;
        }

        public Criteria andAdvUrlIsNull() {
            addCriterion("adv_url is null");
            return (Criteria) this;
        }

        public Criteria andAdvUrlIsNotNull() {
            addCriterion("adv_url is not null");
            return (Criteria) this;
        }

        public Criteria andAdvUrlEqualTo(String value) {
            addCriterion("adv_url =", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotEqualTo(String value) {
            addCriterion("adv_url <>", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlGreaterThan(String value) {
            addCriterion("adv_url >", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlGreaterThanOrEqualTo(String value) {
            addCriterion("adv_url >=", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlLessThan(String value) {
            addCriterion("adv_url <", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlLessThanOrEqualTo(String value) {
            addCriterion("adv_url <=", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlLike(String value) {
            addCriterion("adv_url like", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotLike(String value) {
            addCriterion("adv_url not like", value, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlIn(List<String> values) {
            addCriterion("adv_url in", values, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotIn(List<String> values) {
            addCriterion("adv_url not in", values, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlBetween(String value1, String value2) {
            addCriterion("adv_url between", value1, value2, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvUrlNotBetween(String value1, String value2) {
            addCriterion("adv_url not between", value1, value2, "advUrl");
            return (Criteria) this;
        }

        public Criteria andAdvTargetIsNull() {
            addCriterion("adv_target is null");
            return (Criteria) this;
        }

        public Criteria andAdvTargetIsNotNull() {
            addCriterion("adv_target is not null");
            return (Criteria) this;
        }

        public Criteria andAdvTargetEqualTo(String value) {
            addCriterion("adv_target =", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetNotEqualTo(String value) {
            addCriterion("adv_target <>", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetGreaterThan(String value) {
            addCriterion("adv_target >", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetGreaterThanOrEqualTo(String value) {
            addCriterion("adv_target >=", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetLessThan(String value) {
            addCriterion("adv_target <", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetLessThanOrEqualTo(String value) {
            addCriterion("adv_target <=", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetLike(String value) {
            addCriterion("adv_target like", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetNotLike(String value) {
            addCriterion("adv_target not like", value, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetIn(List<String> values) {
            addCriterion("adv_target in", values, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetNotIn(List<String> values) {
            addCriterion("adv_target not in", values, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetBetween(String value1, String value2) {
            addCriterion("adv_target between", value1, value2, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvTargetNotBetween(String value1, String value2) {
            addCriterion("adv_target not between", value1, value2, "advTarget");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIsNull() {
            addCriterion("adv_location is null");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIsNotNull() {
            addCriterion("adv_location is not null");
            return (Criteria) this;
        }

        public Criteria andAdvLocationEqualTo(Integer value) {
            addCriterion("adv_location =", value, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationNotEqualTo(Integer value) {
            addCriterion("adv_location <>", value, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationGreaterThan(Integer value) {
            addCriterion("adv_location >", value, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationGreaterThanOrEqualTo(Integer value) {
            addCriterion("adv_location >=", value, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationLessThan(Integer value) {
            addCriterion("adv_location <", value, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationLessThanOrEqualTo(Integer value) {
            addCriterion("adv_location <=", value, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationIn(List<Integer> values) {
            addCriterion("adv_location in", values, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationNotIn(List<Integer> values) {
            addCriterion("adv_location not in", values, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationBetween(Integer value1, Integer value2) {
            addCriterion("adv_location between", value1, value2, "advLocation");
            return (Criteria) this;
        }

        public Criteria andAdvLocationNotBetween(Integer value1, Integer value2) {
            addCriterion("adv_location not between", value1, value2, "advLocation");
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

        public Criteria andBeginTimeEqualTo(Date value) {
            addCriterion("begin_time =", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotEqualTo(Date value) {
            addCriterion("begin_time <>", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThan(Date value) {
            addCriterion("begin_time >", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("begin_time >=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThan(Date value) {
            addCriterion("begin_time <", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeLessThanOrEqualTo(Date value) {
            addCriterion("begin_time <=", value, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeIn(List<Date> values) {
            addCriterion("begin_time in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotIn(List<Date> values) {
            addCriterion("begin_time not in", values, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeBetween(Date value1, Date value2) {
            addCriterion("begin_time between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andBeginTimeNotBetween(Date value1, Date value2) {
            addCriterion("begin_time not between", value1, value2, "beginTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andPlatShowIsNull() {
            addCriterion("plat_show is null");
            return (Criteria) this;
        }

        public Criteria andPlatShowIsNotNull() {
            addCriterion("plat_show is not null");
            return (Criteria) this;
        }

        public Criteria andPlatShowEqualTo(Integer value) {
            addCriterion("plat_show =", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowNotEqualTo(Integer value) {
            addCriterion("plat_show <>", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowGreaterThan(Integer value) {
            addCriterion("plat_show >", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("plat_show >=", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowLessThan(Integer value) {
            addCriterion("plat_show <", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowLessThanOrEqualTo(Integer value) {
            addCriterion("plat_show <=", value, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowIn(List<Integer> values) {
            addCriterion("plat_show in", values, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowNotIn(List<Integer> values) {
            addCriterion("plat_show not in", values, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowBetween(Integer value1, Integer value2) {
            addCriterion("plat_show between", value1, value2, "platShow");
            return (Criteria) this;
        }

        public Criteria andPlatShowNotBetween(Integer value1, Integer value2) {
            addCriterion("plat_show not between", value1, value2, "platShow");
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