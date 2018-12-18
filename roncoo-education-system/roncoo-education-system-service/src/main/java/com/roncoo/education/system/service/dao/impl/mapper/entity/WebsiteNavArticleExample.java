package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebsiteNavArticleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public WebsiteNavArticleExample() {
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

        public Criteria andNavIdIsNull() {
            addCriterion("nav_id is null");
            return (Criteria) this;
        }

        public Criteria andNavIdIsNotNull() {
            addCriterion("nav_id is not null");
            return (Criteria) this;
        }

        public Criteria andNavIdEqualTo(Long value) {
            addCriterion("nav_id =", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotEqualTo(Long value) {
            addCriterion("nav_id <>", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdGreaterThan(Long value) {
            addCriterion("nav_id >", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdGreaterThanOrEqualTo(Long value) {
            addCriterion("nav_id >=", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdLessThan(Long value) {
            addCriterion("nav_id <", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdLessThanOrEqualTo(Long value) {
            addCriterion("nav_id <=", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdIn(List<Long> values) {
            addCriterion("nav_id in", values, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotIn(List<Long> values) {
            addCriterion("nav_id not in", values, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdBetween(Long value1, Long value2) {
            addCriterion("nav_id between", value1, value2, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotBetween(Long value1, Long value2) {
            addCriterion("nav_id not between", value1, value2, "navId");
            return (Criteria) this;
        }

        public Criteria andArtTitleIsNull() {
            addCriterion("art_title is null");
            return (Criteria) this;
        }

        public Criteria andArtTitleIsNotNull() {
            addCriterion("art_title is not null");
            return (Criteria) this;
        }

        public Criteria andArtTitleEqualTo(String value) {
            addCriterion("art_title =", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotEqualTo(String value) {
            addCriterion("art_title <>", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleGreaterThan(String value) {
            addCriterion("art_title >", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleGreaterThanOrEqualTo(String value) {
            addCriterion("art_title >=", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleLessThan(String value) {
            addCriterion("art_title <", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleLessThanOrEqualTo(String value) {
            addCriterion("art_title <=", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleLike(String value) {
            addCriterion("art_title like", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotLike(String value) {
            addCriterion("art_title not like", value, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleIn(List<String> values) {
            addCriterion("art_title in", values, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotIn(List<String> values) {
            addCriterion("art_title not in", values, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleBetween(String value1, String value2) {
            addCriterion("art_title between", value1, value2, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtTitleNotBetween(String value1, String value2) {
            addCriterion("art_title not between", value1, value2, "artTitle");
            return (Criteria) this;
        }

        public Criteria andArtPicIsNull() {
            addCriterion("art_pic is null");
            return (Criteria) this;
        }

        public Criteria andArtPicIsNotNull() {
            addCriterion("art_pic is not null");
            return (Criteria) this;
        }

        public Criteria andArtPicEqualTo(String value) {
            addCriterion("art_pic =", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicNotEqualTo(String value) {
            addCriterion("art_pic <>", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicGreaterThan(String value) {
            addCriterion("art_pic >", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicGreaterThanOrEqualTo(String value) {
            addCriterion("art_pic >=", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicLessThan(String value) {
            addCriterion("art_pic <", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicLessThanOrEqualTo(String value) {
            addCriterion("art_pic <=", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicLike(String value) {
            addCriterion("art_pic like", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicNotLike(String value) {
            addCriterion("art_pic not like", value, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicIn(List<String> values) {
            addCriterion("art_pic in", values, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicNotIn(List<String> values) {
            addCriterion("art_pic not in", values, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicBetween(String value1, String value2) {
            addCriterion("art_pic between", value1, value2, "artPic");
            return (Criteria) this;
        }

        public Criteria andArtPicNotBetween(String value1, String value2) {
            addCriterion("art_pic not between", value1, value2, "artPic");
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