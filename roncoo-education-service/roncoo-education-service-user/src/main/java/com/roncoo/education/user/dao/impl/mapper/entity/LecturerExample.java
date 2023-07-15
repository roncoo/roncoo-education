package com.roncoo.education.user.dao.impl.mapper.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LecturerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public LecturerExample() {
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

        public Criteria andLecturerNameIsNull() {
            addCriterion("lecturer_name is null");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIsNotNull() {
            addCriterion("lecturer_name is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerNameEqualTo(String value) {
            addCriterion("lecturer_name =", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotEqualTo(String value) {
            addCriterion("lecturer_name <>", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameGreaterThan(String value) {
            addCriterion("lecturer_name >", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_name >=", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLessThan(String value) {
            addCriterion("lecturer_name <", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLessThanOrEqualTo(String value) {
            addCriterion("lecturer_name <=", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameLike(String value) {
            addCriterion("lecturer_name like", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotLike(String value) {
            addCriterion("lecturer_name not like", value, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameIn(List<String> values) {
            addCriterion("lecturer_name in", values, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotIn(List<String> values) {
            addCriterion("lecturer_name not in", values, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameBetween(String value1, String value2) {
            addCriterion("lecturer_name between", value1, value2, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerNameNotBetween(String value1, String value2) {
            addCriterion("lecturer_name not between", value1, value2, "lecturerName");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileIsNull() {
            addCriterion("lecturer_mobile is null");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileIsNotNull() {
            addCriterion("lecturer_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileEqualTo(String value) {
            addCriterion("lecturer_mobile =", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileNotEqualTo(String value) {
            addCriterion("lecturer_mobile <>", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileGreaterThan(String value) {
            addCriterion("lecturer_mobile >", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_mobile >=", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileLessThan(String value) {
            addCriterion("lecturer_mobile <", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileLessThanOrEqualTo(String value) {
            addCriterion("lecturer_mobile <=", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileLike(String value) {
            addCriterion("lecturer_mobile like", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileNotLike(String value) {
            addCriterion("lecturer_mobile not like", value, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileIn(List<String> values) {
            addCriterion("lecturer_mobile in", values, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileNotIn(List<String> values) {
            addCriterion("lecturer_mobile not in", values, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileBetween(String value1, String value2) {
            addCriterion("lecturer_mobile between", value1, value2, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerMobileNotBetween(String value1, String value2) {
            addCriterion("lecturer_mobile not between", value1, value2, "lecturerMobile");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionIsNull() {
            addCriterion("lecturer_position is null");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionIsNotNull() {
            addCriterion("lecturer_position is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionEqualTo(String value) {
            addCriterion("lecturer_position =", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionNotEqualTo(String value) {
            addCriterion("lecturer_position <>", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionGreaterThan(String value) {
            addCriterion("lecturer_position >", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_position >=", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionLessThan(String value) {
            addCriterion("lecturer_position <", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionLessThanOrEqualTo(String value) {
            addCriterion("lecturer_position <=", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionLike(String value) {
            addCriterion("lecturer_position like", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionNotLike(String value) {
            addCriterion("lecturer_position not like", value, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionIn(List<String> values) {
            addCriterion("lecturer_position in", values, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionNotIn(List<String> values) {
            addCriterion("lecturer_position not in", values, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionBetween(String value1, String value2) {
            addCriterion("lecturer_position between", value1, value2, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerPositionNotBetween(String value1, String value2) {
            addCriterion("lecturer_position not between", value1, value2, "lecturerPosition");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadIsNull() {
            addCriterion("lecturer_head is null");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadIsNotNull() {
            addCriterion("lecturer_head is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadEqualTo(String value) {
            addCriterion("lecturer_head =", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadNotEqualTo(String value) {
            addCriterion("lecturer_head <>", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadGreaterThan(String value) {
            addCriterion("lecturer_head >", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_head >=", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadLessThan(String value) {
            addCriterion("lecturer_head <", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadLessThanOrEqualTo(String value) {
            addCriterion("lecturer_head <=", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadLike(String value) {
            addCriterion("lecturer_head like", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadNotLike(String value) {
            addCriterion("lecturer_head not like", value, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadIn(List<String> values) {
            addCriterion("lecturer_head in", values, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadNotIn(List<String> values) {
            addCriterion("lecturer_head not in", values, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadBetween(String value1, String value2) {
            addCriterion("lecturer_head between", value1, value2, "lecturerHead");
            return (Criteria) this;
        }

        public Criteria andLecturerHeadNotBetween(String value1, String value2) {
            addCriterion("lecturer_head not between", value1, value2, "lecturerHead");
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
