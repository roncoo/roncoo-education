package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebsiteRecruitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public WebsiteRecruitExample() {
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

        public Criteria andRecruitTypeIsNull() {
            addCriterion("recruit_type is null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIsNotNull() {
            addCriterion("recruit_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeEqualTo(Integer value) {
            addCriterion("recruit_type =", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotEqualTo(Integer value) {
            addCriterion("recruit_type <>", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeGreaterThan(Integer value) {
            addCriterion("recruit_type >", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recruit_type >=", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeLessThan(Integer value) {
            addCriterion("recruit_type <", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeLessThanOrEqualTo(Integer value) {
            addCriterion("recruit_type <=", value, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeIn(List<Integer> values) {
            addCriterion("recruit_type in", values, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotIn(List<Integer> values) {
            addCriterion("recruit_type not in", values, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeBetween(Integer value1, Integer value2) {
            addCriterion("recruit_type between", value1, value2, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("recruit_type not between", value1, value2, "recruitType");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleIsNull() {
            addCriterion("recruit_title is null");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleIsNotNull() {
            addCriterion("recruit_title is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleEqualTo(String value) {
            addCriterion("recruit_title =", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleNotEqualTo(String value) {
            addCriterion("recruit_title <>", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleGreaterThan(String value) {
            addCriterion("recruit_title >", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleGreaterThanOrEqualTo(String value) {
            addCriterion("recruit_title >=", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleLessThan(String value) {
            addCriterion("recruit_title <", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleLessThanOrEqualTo(String value) {
            addCriterion("recruit_title <=", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleLike(String value) {
            addCriterion("recruit_title like", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleNotLike(String value) {
            addCriterion("recruit_title not like", value, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleIn(List<String> values) {
            addCriterion("recruit_title in", values, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleNotIn(List<String> values) {
            addCriterion("recruit_title not in", values, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleBetween(String value1, String value2) {
            addCriterion("recruit_title between", value1, value2, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitTitleNotBetween(String value1, String value2) {
            addCriterion("recruit_title not between", value1, value2, "recruitTitle");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoIsNull() {
            addCriterion("recruit_info is null");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoIsNotNull() {
            addCriterion("recruit_info is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoEqualTo(String value) {
            addCriterion("recruit_info =", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoNotEqualTo(String value) {
            addCriterion("recruit_info <>", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoGreaterThan(String value) {
            addCriterion("recruit_info >", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoGreaterThanOrEqualTo(String value) {
            addCriterion("recruit_info >=", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoLessThan(String value) {
            addCriterion("recruit_info <", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoLessThanOrEqualTo(String value) {
            addCriterion("recruit_info <=", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoLike(String value) {
            addCriterion("recruit_info like", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoNotLike(String value) {
            addCriterion("recruit_info not like", value, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoIn(List<String> values) {
            addCriterion("recruit_info in", values, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoNotIn(List<String> values) {
            addCriterion("recruit_info not in", values, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoBetween(String value1, String value2) {
            addCriterion("recruit_info between", value1, value2, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andRecruitInfoNotBetween(String value1, String value2) {
            addCriterion("recruit_info not between", value1, value2, "recruitInfo");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementIsNull() {
            addCriterion("entry_agreement is null");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementIsNotNull() {
            addCriterion("entry_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementEqualTo(String value) {
            addCriterion("entry_agreement =", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementNotEqualTo(String value) {
            addCriterion("entry_agreement <>", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementGreaterThan(String value) {
            addCriterion("entry_agreement >", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("entry_agreement >=", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementLessThan(String value) {
            addCriterion("entry_agreement <", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementLessThanOrEqualTo(String value) {
            addCriterion("entry_agreement <=", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementLike(String value) {
            addCriterion("entry_agreement like", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementNotLike(String value) {
            addCriterion("entry_agreement not like", value, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementIn(List<String> values) {
            addCriterion("entry_agreement in", values, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementNotIn(List<String> values) {
            addCriterion("entry_agreement not in", values, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementBetween(String value1, String value2) {
            addCriterion("entry_agreement between", value1, value2, "entryAgreement");
            return (Criteria) this;
        }

        public Criteria andEntryAgreementNotBetween(String value1, String value2) {
            addCriterion("entry_agreement not between", value1, value2, "entryAgreement");
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