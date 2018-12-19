package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WebsiteExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public WebsiteExample() {
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

        public Criteria andWebsiteTitleIsNull() {
            addCriterion("website_title is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleIsNotNull() {
            addCriterion("website_title is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleEqualTo(String value) {
            addCriterion("website_title =", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotEqualTo(String value) {
            addCriterion("website_title <>", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleGreaterThan(String value) {
            addCriterion("website_title >", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleGreaterThanOrEqualTo(String value) {
            addCriterion("website_title >=", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleLessThan(String value) {
            addCriterion("website_title <", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleLessThanOrEqualTo(String value) {
            addCriterion("website_title <=", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleLike(String value) {
            addCriterion("website_title like", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotLike(String value) {
            addCriterion("website_title not like", value, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleIn(List<String> values) {
            addCriterion("website_title in", values, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotIn(List<String> values) {
            addCriterion("website_title not in", values, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleBetween(String value1, String value2) {
            addCriterion("website_title between", value1, value2, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteTitleNotBetween(String value1, String value2) {
            addCriterion("website_title not between", value1, value2, "websiteTitle");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordIsNull() {
            addCriterion("website_keyword is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordIsNotNull() {
            addCriterion("website_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordEqualTo(String value) {
            addCriterion("website_keyword =", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotEqualTo(String value) {
            addCriterion("website_keyword <>", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordGreaterThan(String value) {
            addCriterion("website_keyword >", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("website_keyword >=", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordLessThan(String value) {
            addCriterion("website_keyword <", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordLessThanOrEqualTo(String value) {
            addCriterion("website_keyword <=", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordLike(String value) {
            addCriterion("website_keyword like", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotLike(String value) {
            addCriterion("website_keyword not like", value, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordIn(List<String> values) {
            addCriterion("website_keyword in", values, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotIn(List<String> values) {
            addCriterion("website_keyword not in", values, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordBetween(String value1, String value2) {
            addCriterion("website_keyword between", value1, value2, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteKeywordNotBetween(String value1, String value2) {
            addCriterion("website_keyword not between", value1, value2, "websiteKeyword");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescIsNull() {
            addCriterion("website_desc is null");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescIsNotNull() {
            addCriterion("website_desc is not null");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescEqualTo(String value) {
            addCriterion("website_desc =", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotEqualTo(String value) {
            addCriterion("website_desc <>", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescGreaterThan(String value) {
            addCriterion("website_desc >", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescGreaterThanOrEqualTo(String value) {
            addCriterion("website_desc >=", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescLessThan(String value) {
            addCriterion("website_desc <", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescLessThanOrEqualTo(String value) {
            addCriterion("website_desc <=", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescLike(String value) {
            addCriterion("website_desc like", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotLike(String value) {
            addCriterion("website_desc not like", value, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescIn(List<String> values) {
            addCriterion("website_desc in", values, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotIn(List<String> values) {
            addCriterion("website_desc not in", values, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescBetween(String value1, String value2) {
            addCriterion("website_desc between", value1, value2, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andWebsiteDescNotBetween(String value1, String value2) {
            addCriterion("website_desc not between", value1, value2, "websiteDesc");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNull() {
            addCriterion("copyright is null");
            return (Criteria) this;
        }

        public Criteria andCopyrightIsNotNull() {
            addCriterion("copyright is not null");
            return (Criteria) this;
        }

        public Criteria andCopyrightEqualTo(String value) {
            addCriterion("copyright =", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotEqualTo(String value) {
            addCriterion("copyright <>", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThan(String value) {
            addCriterion("copyright >", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightGreaterThanOrEqualTo(String value) {
            addCriterion("copyright >=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThan(String value) {
            addCriterion("copyright <", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLessThanOrEqualTo(String value) {
            addCriterion("copyright <=", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightLike(String value) {
            addCriterion("copyright like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotLike(String value) {
            addCriterion("copyright not like", value, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightIn(List<String> values) {
            addCriterion("copyright in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotIn(List<String> values) {
            addCriterion("copyright not in", values, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightBetween(String value1, String value2) {
            addCriterion("copyright between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andCopyrightNotBetween(String value1, String value2) {
            addCriterion("copyright not between", value1, value2, "copyright");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNull() {
            addCriterion("weixin is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNotNull() {
            addCriterion("weixin is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinEqualTo(String value) {
            addCriterion("weixin =", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotEqualTo(String value) {
            addCriterion("weixin <>", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThan(String value) {
            addCriterion("weixin >", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThanOrEqualTo(String value) {
            addCriterion("weixin >=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThan(String value) {
            addCriterion("weixin <", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThanOrEqualTo(String value) {
            addCriterion("weixin <=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLike(String value) {
            addCriterion("weixin like", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotLike(String value) {
            addCriterion("weixin not like", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinIn(List<String> values) {
            addCriterion("weixin in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotIn(List<String> values) {
            addCriterion("weixin not in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinBetween(String value1, String value2) {
            addCriterion("weixin between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotBetween(String value1, String value2) {
            addCriterion("weixin not between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeiboIsNull() {
            addCriterion("weibo is null");
            return (Criteria) this;
        }

        public Criteria andWeiboIsNotNull() {
            addCriterion("weibo is not null");
            return (Criteria) this;
        }

        public Criteria andWeiboEqualTo(String value) {
            addCriterion("weibo =", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotEqualTo(String value) {
            addCriterion("weibo <>", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboGreaterThan(String value) {
            addCriterion("weibo >", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboGreaterThanOrEqualTo(String value) {
            addCriterion("weibo >=", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboLessThan(String value) {
            addCriterion("weibo <", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboLessThanOrEqualTo(String value) {
            addCriterion("weibo <=", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboLike(String value) {
            addCriterion("weibo like", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotLike(String value) {
            addCriterion("weibo not like", value, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboIn(List<String> values) {
            addCriterion("weibo in", values, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotIn(List<String> values) {
            addCriterion("weibo not in", values, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboBetween(String value1, String value2) {
            addCriterion("weibo between", value1, value2, "weibo");
            return (Criteria) this;
        }

        public Criteria andWeiboNotBetween(String value1, String value2) {
            addCriterion("weibo not between", value1, value2, "weibo");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsIsNull() {
            addCriterion("is_enable_statistics is null");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsIsNotNull() {
            addCriterion("is_enable_statistics is not null");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsEqualTo(Integer value) {
            addCriterion("is_enable_statistics =", value, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsNotEqualTo(Integer value) {
            addCriterion("is_enable_statistics <>", value, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsGreaterThan(Integer value) {
            addCriterion("is_enable_statistics >", value, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_enable_statistics >=", value, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsLessThan(Integer value) {
            addCriterion("is_enable_statistics <", value, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsLessThanOrEqualTo(Integer value) {
            addCriterion("is_enable_statistics <=", value, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsIn(List<Integer> values) {
            addCriterion("is_enable_statistics in", values, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsNotIn(List<Integer> values) {
            addCriterion("is_enable_statistics not in", values, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsBetween(Integer value1, Integer value2) {
            addCriterion("is_enable_statistics between", value1, value2, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andIsEnableStatisticsNotBetween(Integer value1, Integer value2) {
            addCriterion("is_enable_statistics not between", value1, value2, "isEnableStatistics");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeIsNull() {
            addCriterion("statistics_code is null");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeIsNotNull() {
            addCriterion("statistics_code is not null");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeEqualTo(String value) {
            addCriterion("statistics_code =", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotEqualTo(String value) {
            addCriterion("statistics_code <>", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeGreaterThan(String value) {
            addCriterion("statistics_code >", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("statistics_code >=", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLessThan(String value) {
            addCriterion("statistics_code <", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLessThanOrEqualTo(String value) {
            addCriterion("statistics_code <=", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeLike(String value) {
            addCriterion("statistics_code like", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotLike(String value) {
            addCriterion("statistics_code not like", value, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeIn(List<String> values) {
            addCriterion("statistics_code in", values, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotIn(List<String> values) {
            addCriterion("statistics_code not in", values, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeBetween(String value1, String value2) {
            addCriterion("statistics_code between", value1, value2, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andStatisticsCodeNotBetween(String value1, String value2) {
            addCriterion("statistics_code not between", value1, value2, "statisticsCode");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceIsNull() {
            addCriterion("is_show_service is null");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceIsNotNull() {
            addCriterion("is_show_service is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceEqualTo(Integer value) {
            addCriterion("is_show_service =", value, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceNotEqualTo(Integer value) {
            addCriterion("is_show_service <>", value, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceGreaterThan(Integer value) {
            addCriterion("is_show_service >", value, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_show_service >=", value, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceLessThan(Integer value) {
            addCriterion("is_show_service <", value, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceLessThanOrEqualTo(Integer value) {
            addCriterion("is_show_service <=", value, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceIn(List<Integer> values) {
            addCriterion("is_show_service in", values, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceNotIn(List<Integer> values) {
            addCriterion("is_show_service not in", values, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceBetween(Integer value1, Integer value2) {
            addCriterion("is_show_service between", value1, value2, "isShowService");
            return (Criteria) this;
        }

        public Criteria andIsShowServiceNotBetween(Integer value1, Integer value2) {
            addCriterion("is_show_service not between", value1, value2, "isShowService");
            return (Criteria) this;
        }

        public Criteria andService1IsNull() {
            addCriterion("service1 is null");
            return (Criteria) this;
        }

        public Criteria andService1IsNotNull() {
            addCriterion("service1 is not null");
            return (Criteria) this;
        }

        public Criteria andService1EqualTo(String value) {
            addCriterion("service1 =", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1NotEqualTo(String value) {
            addCriterion("service1 <>", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1GreaterThan(String value) {
            addCriterion("service1 >", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1GreaterThanOrEqualTo(String value) {
            addCriterion("service1 >=", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1LessThan(String value) {
            addCriterion("service1 <", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1LessThanOrEqualTo(String value) {
            addCriterion("service1 <=", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1Like(String value) {
            addCriterion("service1 like", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1NotLike(String value) {
            addCriterion("service1 not like", value, "service1");
            return (Criteria) this;
        }

        public Criteria andService1In(List<String> values) {
            addCriterion("service1 in", values, "service1");
            return (Criteria) this;
        }

        public Criteria andService1NotIn(List<String> values) {
            addCriterion("service1 not in", values, "service1");
            return (Criteria) this;
        }

        public Criteria andService1Between(String value1, String value2) {
            addCriterion("service1 between", value1, value2, "service1");
            return (Criteria) this;
        }

        public Criteria andService1NotBetween(String value1, String value2) {
            addCriterion("service1 not between", value1, value2, "service1");
            return (Criteria) this;
        }

        public Criteria andService2IsNull() {
            addCriterion("service2 is null");
            return (Criteria) this;
        }

        public Criteria andService2IsNotNull() {
            addCriterion("service2 is not null");
            return (Criteria) this;
        }

        public Criteria andService2EqualTo(String value) {
            addCriterion("service2 =", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2NotEqualTo(String value) {
            addCriterion("service2 <>", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2GreaterThan(String value) {
            addCriterion("service2 >", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2GreaterThanOrEqualTo(String value) {
            addCriterion("service2 >=", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2LessThan(String value) {
            addCriterion("service2 <", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2LessThanOrEqualTo(String value) {
            addCriterion("service2 <=", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2Like(String value) {
            addCriterion("service2 like", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2NotLike(String value) {
            addCriterion("service2 not like", value, "service2");
            return (Criteria) this;
        }

        public Criteria andService2In(List<String> values) {
            addCriterion("service2 in", values, "service2");
            return (Criteria) this;
        }

        public Criteria andService2NotIn(List<String> values) {
            addCriterion("service2 not in", values, "service2");
            return (Criteria) this;
        }

        public Criteria andService2Between(String value1, String value2) {
            addCriterion("service2 between", value1, value2, "service2");
            return (Criteria) this;
        }

        public Criteria andService2NotBetween(String value1, String value2) {
            addCriterion("service2 not between", value1, value2, "service2");
            return (Criteria) this;
        }

        public Criteria andService3IsNull() {
            addCriterion("service3 is null");
            return (Criteria) this;
        }

        public Criteria andService3IsNotNull() {
            addCriterion("service3 is not null");
            return (Criteria) this;
        }

        public Criteria andService3EqualTo(String value) {
            addCriterion("service3 =", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3NotEqualTo(String value) {
            addCriterion("service3 <>", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3GreaterThan(String value) {
            addCriterion("service3 >", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3GreaterThanOrEqualTo(String value) {
            addCriterion("service3 >=", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3LessThan(String value) {
            addCriterion("service3 <", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3LessThanOrEqualTo(String value) {
            addCriterion("service3 <=", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3Like(String value) {
            addCriterion("service3 like", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3NotLike(String value) {
            addCriterion("service3 not like", value, "service3");
            return (Criteria) this;
        }

        public Criteria andService3In(List<String> values) {
            addCriterion("service3 in", values, "service3");
            return (Criteria) this;
        }

        public Criteria andService3NotIn(List<String> values) {
            addCriterion("service3 not in", values, "service3");
            return (Criteria) this;
        }

        public Criteria andService3Between(String value1, String value2) {
            addCriterion("service3 between", value1, value2, "service3");
            return (Criteria) this;
        }

        public Criteria andService3NotBetween(String value1, String value2) {
            addCriterion("service3 not between", value1, value2, "service3");
            return (Criteria) this;
        }

        public Criteria andService4IsNull() {
            addCriterion("service4 is null");
            return (Criteria) this;
        }

        public Criteria andService4IsNotNull() {
            addCriterion("service4 is not null");
            return (Criteria) this;
        }

        public Criteria andService4EqualTo(String value) {
            addCriterion("service4 =", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4NotEqualTo(String value) {
            addCriterion("service4 <>", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4GreaterThan(String value) {
            addCriterion("service4 >", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4GreaterThanOrEqualTo(String value) {
            addCriterion("service4 >=", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4LessThan(String value) {
            addCriterion("service4 <", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4LessThanOrEqualTo(String value) {
            addCriterion("service4 <=", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4Like(String value) {
            addCriterion("service4 like", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4NotLike(String value) {
            addCriterion("service4 not like", value, "service4");
            return (Criteria) this;
        }

        public Criteria andService4In(List<String> values) {
            addCriterion("service4 in", values, "service4");
            return (Criteria) this;
        }

        public Criteria andService4NotIn(List<String> values) {
            addCriterion("service4 not in", values, "service4");
            return (Criteria) this;
        }

        public Criteria andService4Between(String value1, String value2) {
            addCriterion("service4 between", value1, value2, "service4");
            return (Criteria) this;
        }

        public Criteria andService4NotBetween(String value1, String value2) {
            addCriterion("service4 not between", value1, value2, "service4");
            return (Criteria) this;
        }

        public Criteria andService5IsNull() {
            addCriterion("service5 is null");
            return (Criteria) this;
        }

        public Criteria andService5IsNotNull() {
            addCriterion("service5 is not null");
            return (Criteria) this;
        }

        public Criteria andService5EqualTo(String value) {
            addCriterion("service5 =", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5NotEqualTo(String value) {
            addCriterion("service5 <>", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5GreaterThan(String value) {
            addCriterion("service5 >", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5GreaterThanOrEqualTo(String value) {
            addCriterion("service5 >=", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5LessThan(String value) {
            addCriterion("service5 <", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5LessThanOrEqualTo(String value) {
            addCriterion("service5 <=", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5Like(String value) {
            addCriterion("service5 like", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5NotLike(String value) {
            addCriterion("service5 not like", value, "service5");
            return (Criteria) this;
        }

        public Criteria andService5In(List<String> values) {
            addCriterion("service5 in", values, "service5");
            return (Criteria) this;
        }

        public Criteria andService5NotIn(List<String> values) {
            addCriterion("service5 not in", values, "service5");
            return (Criteria) this;
        }

        public Criteria andService5Between(String value1, String value2) {
            addCriterion("service5 between", value1, value2, "service5");
            return (Criteria) this;
        }

        public Criteria andService5NotBetween(String value1, String value2) {
            addCriterion("service5 not between", value1, value2, "service5");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusIsNull() {
            addCriterion("polyv_status is null");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusIsNotNull() {
            addCriterion("polyv_status is not null");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusEqualTo(Integer value) {
            addCriterion("polyv_status =", value, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusNotEqualTo(Integer value) {
            addCriterion("polyv_status <>", value, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusGreaterThan(Integer value) {
            addCriterion("polyv_status >", value, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("polyv_status >=", value, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusLessThan(Integer value) {
            addCriterion("polyv_status <", value, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusLessThanOrEqualTo(Integer value) {
            addCriterion("polyv_status <=", value, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusIn(List<Integer> values) {
            addCriterion("polyv_status in", values, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusNotIn(List<Integer> values) {
            addCriterion("polyv_status not in", values, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusBetween(Integer value1, Integer value2) {
            addCriterion("polyv_status between", value1, value2, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("polyv_status not between", value1, value2, "polyvStatus");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoIsNull() {
            addCriterion("polyv_logo is null");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoIsNotNull() {
            addCriterion("polyv_logo is not null");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoEqualTo(String value) {
            addCriterion("polyv_logo =", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoNotEqualTo(String value) {
            addCriterion("polyv_logo <>", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoGreaterThan(String value) {
            addCriterion("polyv_logo >", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoGreaterThanOrEqualTo(String value) {
            addCriterion("polyv_logo >=", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoLessThan(String value) {
            addCriterion("polyv_logo <", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoLessThanOrEqualTo(String value) {
            addCriterion("polyv_logo <=", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoLike(String value) {
            addCriterion("polyv_logo like", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoNotLike(String value) {
            addCriterion("polyv_logo not like", value, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoIn(List<String> values) {
            addCriterion("polyv_logo in", values, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoNotIn(List<String> values) {
            addCriterion("polyv_logo not in", values, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoBetween(String value1, String value2) {
            addCriterion("polyv_logo between", value1, value2, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andPolyvLogoNotBetween(String value1, String value2) {
            addCriterion("polyv_logo not between", value1, value2, "polyvLogo");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIsNull() {
            addCriterion("is_watermark is null");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIsNotNull() {
            addCriterion("is_watermark is not null");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkEqualTo(Integer value) {
            addCriterion("is_watermark =", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotEqualTo(Integer value) {
            addCriterion("is_watermark <>", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkGreaterThan(Integer value) {
            addCriterion("is_watermark >", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_watermark >=", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLessThan(Integer value) {
            addCriterion("is_watermark <", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkLessThanOrEqualTo(Integer value) {
            addCriterion("is_watermark <=", value, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkIn(List<Integer> values) {
            addCriterion("is_watermark in", values, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotIn(List<Integer> values) {
            addCriterion("is_watermark not in", values, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkBetween(Integer value1, Integer value2) {
            addCriterion("is_watermark between", value1, value2, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andIsWatermarkNotBetween(Integer value1, Integer value2) {
            addCriterion("is_watermark not between", value1, value2, "isWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkIsNull() {
            addCriterion("pic_watermark is null");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkIsNotNull() {
            addCriterion("pic_watermark is not null");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkEqualTo(String value) {
            addCriterion("pic_watermark =", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkNotEqualTo(String value) {
            addCriterion("pic_watermark <>", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkGreaterThan(String value) {
            addCriterion("pic_watermark >", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkGreaterThanOrEqualTo(String value) {
            addCriterion("pic_watermark >=", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkLessThan(String value) {
            addCriterion("pic_watermark <", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkLessThanOrEqualTo(String value) {
            addCriterion("pic_watermark <=", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkLike(String value) {
            addCriterion("pic_watermark like", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkNotLike(String value) {
            addCriterion("pic_watermark not like", value, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkIn(List<String> values) {
            addCriterion("pic_watermark in", values, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkNotIn(List<String> values) {
            addCriterion("pic_watermark not in", values, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkBetween(String value1, String value2) {
            addCriterion("pic_watermark between", value1, value2, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicWatermarkNotBetween(String value1, String value2) {
            addCriterion("pic_watermark not between", value1, value2, "picWatermark");
            return (Criteria) this;
        }

        public Criteria andPicxIsNull() {
            addCriterion("picx is null");
            return (Criteria) this;
        }

        public Criteria andPicxIsNotNull() {
            addCriterion("picx is not null");
            return (Criteria) this;
        }

        public Criteria andPicxEqualTo(Integer value) {
            addCriterion("picx =", value, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxNotEqualTo(Integer value) {
            addCriterion("picx <>", value, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxGreaterThan(Integer value) {
            addCriterion("picx >", value, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxGreaterThanOrEqualTo(Integer value) {
            addCriterion("picx >=", value, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxLessThan(Integer value) {
            addCriterion("picx <", value, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxLessThanOrEqualTo(Integer value) {
            addCriterion("picx <=", value, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxIn(List<Integer> values) {
            addCriterion("picx in", values, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxNotIn(List<Integer> values) {
            addCriterion("picx not in", values, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxBetween(Integer value1, Integer value2) {
            addCriterion("picx between", value1, value2, "picx");
            return (Criteria) this;
        }

        public Criteria andPicxNotBetween(Integer value1, Integer value2) {
            addCriterion("picx not between", value1, value2, "picx");
            return (Criteria) this;
        }

        public Criteria andPicyIsNull() {
            addCriterion("picy is null");
            return (Criteria) this;
        }

        public Criteria andPicyIsNotNull() {
            addCriterion("picy is not null");
            return (Criteria) this;
        }

        public Criteria andPicyEqualTo(Integer value) {
            addCriterion("picy =", value, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyNotEqualTo(Integer value) {
            addCriterion("picy <>", value, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyGreaterThan(Integer value) {
            addCriterion("picy >", value, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyGreaterThanOrEqualTo(Integer value) {
            addCriterion("picy >=", value, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyLessThan(Integer value) {
            addCriterion("picy <", value, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyLessThanOrEqualTo(Integer value) {
            addCriterion("picy <=", value, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyIn(List<Integer> values) {
            addCriterion("picy in", values, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyNotIn(List<Integer> values) {
            addCriterion("picy not in", values, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyBetween(Integer value1, Integer value2) {
            addCriterion("picy between", value1, value2, "picy");
            return (Criteria) this;
        }

        public Criteria andPicyNotBetween(Integer value1, Integer value2) {
            addCriterion("picy not between", value1, value2, "picy");
            return (Criteria) this;
        }

        public Criteria andLogoImgIsNull() {
            addCriterion("logo_img is null");
            return (Criteria) this;
        }

        public Criteria andLogoImgIsNotNull() {
            addCriterion("logo_img is not null");
            return (Criteria) this;
        }

        public Criteria andLogoImgEqualTo(String value) {
            addCriterion("logo_img =", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotEqualTo(String value) {
            addCriterion("logo_img <>", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgGreaterThan(String value) {
            addCriterion("logo_img >", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgGreaterThanOrEqualTo(String value) {
            addCriterion("logo_img >=", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgLessThan(String value) {
            addCriterion("logo_img <", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgLessThanOrEqualTo(String value) {
            addCriterion("logo_img <=", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgLike(String value) {
            addCriterion("logo_img like", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotLike(String value) {
            addCriterion("logo_img not like", value, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgIn(List<String> values) {
            addCriterion("logo_img in", values, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotIn(List<String> values) {
            addCriterion("logo_img not in", values, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgBetween(String value1, String value2) {
            addCriterion("logo_img between", value1, value2, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoImgNotBetween(String value1, String value2) {
            addCriterion("logo_img not between", value1, value2, "logoImg");
            return (Criteria) this;
        }

        public Criteria andLogoIcoIsNull() {
            addCriterion("logo_ico is null");
            return (Criteria) this;
        }

        public Criteria andLogoIcoIsNotNull() {
            addCriterion("logo_ico is not null");
            return (Criteria) this;
        }

        public Criteria andLogoIcoEqualTo(String value) {
            addCriterion("logo_ico =", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoNotEqualTo(String value) {
            addCriterion("logo_ico <>", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoGreaterThan(String value) {
            addCriterion("logo_ico >", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoGreaterThanOrEqualTo(String value) {
            addCriterion("logo_ico >=", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoLessThan(String value) {
            addCriterion("logo_ico <", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoLessThanOrEqualTo(String value) {
            addCriterion("logo_ico <=", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoLike(String value) {
            addCriterion("logo_ico like", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoNotLike(String value) {
            addCriterion("logo_ico not like", value, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoIn(List<String> values) {
            addCriterion("logo_ico in", values, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoNotIn(List<String> values) {
            addCriterion("logo_ico not in", values, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoBetween(String value1, String value2) {
            addCriterion("logo_ico between", value1, value2, "logoIco");
            return (Criteria) this;
        }

        public Criteria andLogoIcoNotBetween(String value1, String value2) {
            addCriterion("logo_ico not between", value1, value2, "logoIco");
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