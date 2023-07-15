package com.roncoo.education.system.dao.impl.mapper.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WebsiteCarouselExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public WebsiteCarouselExample() {
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

        public Criteria andCarouselTitleIsNull() {
            addCriterion("carousel_title is null");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleIsNotNull() {
            addCriterion("carousel_title is not null");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleEqualTo(String value) {
            addCriterion("carousel_title =", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleNotEqualTo(String value) {
            addCriterion("carousel_title <>", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleGreaterThan(String value) {
            addCriterion("carousel_title >", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleGreaterThanOrEqualTo(String value) {
            addCriterion("carousel_title >=", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleLessThan(String value) {
            addCriterion("carousel_title <", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleLessThanOrEqualTo(String value) {
            addCriterion("carousel_title <=", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleLike(String value) {
            addCriterion("carousel_title like", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleNotLike(String value) {
            addCriterion("carousel_title not like", value, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleIn(List<String> values) {
            addCriterion("carousel_title in", values, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleNotIn(List<String> values) {
            addCriterion("carousel_title not in", values, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleBetween(String value1, String value2) {
            addCriterion("carousel_title between", value1, value2, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselTitleNotBetween(String value1, String value2) {
            addCriterion("carousel_title not between", value1, value2, "carouselTitle");
            return (Criteria) this;
        }

        public Criteria andCarouselImgIsNull() {
            addCriterion("carousel_img is null");
            return (Criteria) this;
        }

        public Criteria andCarouselImgIsNotNull() {
            addCriterion("carousel_img is not null");
            return (Criteria) this;
        }

        public Criteria andCarouselImgEqualTo(String value) {
            addCriterion("carousel_img =", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgNotEqualTo(String value) {
            addCriterion("carousel_img <>", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgGreaterThan(String value) {
            addCriterion("carousel_img >", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgGreaterThanOrEqualTo(String value) {
            addCriterion("carousel_img >=", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgLessThan(String value) {
            addCriterion("carousel_img <", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgLessThanOrEqualTo(String value) {
            addCriterion("carousel_img <=", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgLike(String value) {
            addCriterion("carousel_img like", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgNotLike(String value) {
            addCriterion("carousel_img not like", value, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgIn(List<String> values) {
            addCriterion("carousel_img in", values, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgNotIn(List<String> values) {
            addCriterion("carousel_img not in", values, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgBetween(String value1, String value2) {
            addCriterion("carousel_img between", value1, value2, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselImgNotBetween(String value1, String value2) {
            addCriterion("carousel_img not between", value1, value2, "carouselImg");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlIsNull() {
            addCriterion("carousel_url is null");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlIsNotNull() {
            addCriterion("carousel_url is not null");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlEqualTo(String value) {
            addCriterion("carousel_url =", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlNotEqualTo(String value) {
            addCriterion("carousel_url <>", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlGreaterThan(String value) {
            addCriterion("carousel_url >", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlGreaterThanOrEqualTo(String value) {
            addCriterion("carousel_url >=", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlLessThan(String value) {
            addCriterion("carousel_url <", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlLessThanOrEqualTo(String value) {
            addCriterion("carousel_url <=", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlLike(String value) {
            addCriterion("carousel_url like", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlNotLike(String value) {
            addCriterion("carousel_url not like", value, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlIn(List<String> values) {
            addCriterion("carousel_url in", values, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlNotIn(List<String> values) {
            addCriterion("carousel_url not in", values, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlBetween(String value1, String value2) {
            addCriterion("carousel_url between", value1, value2, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselUrlNotBetween(String value1, String value2) {
            addCriterion("carousel_url not between", value1, value2, "carouselUrl");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetIsNull() {
            addCriterion("carousel_target is null");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetIsNotNull() {
            addCriterion("carousel_target is not null");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetEqualTo(String value) {
            addCriterion("carousel_target =", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetNotEqualTo(String value) {
            addCriterion("carousel_target <>", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetGreaterThan(String value) {
            addCriterion("carousel_target >", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetGreaterThanOrEqualTo(String value) {
            addCriterion("carousel_target >=", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetLessThan(String value) {
            addCriterion("carousel_target <", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetLessThanOrEqualTo(String value) {
            addCriterion("carousel_target <=", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetLike(String value) {
            addCriterion("carousel_target like", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetNotLike(String value) {
            addCriterion("carousel_target not like", value, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetIn(List<String> values) {
            addCriterion("carousel_target in", values, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetNotIn(List<String> values) {
            addCriterion("carousel_target not in", values, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetBetween(String value1, String value2) {
            addCriterion("carousel_target between", value1, value2, "carouselTarget");
            return (Criteria) this;
        }

        public Criteria andCarouselTargetNotBetween(String value1, String value2) {
            addCriterion("carousel_target not between", value1, value2, "carouselTarget");
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

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(LocalDateTime value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(LocalDateTime value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(LocalDateTime value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(LocalDateTime value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(LocalDateTime value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(LocalDateTime value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<LocalDateTime> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<LocalDateTime> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(LocalDateTime value1, LocalDateTime value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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
