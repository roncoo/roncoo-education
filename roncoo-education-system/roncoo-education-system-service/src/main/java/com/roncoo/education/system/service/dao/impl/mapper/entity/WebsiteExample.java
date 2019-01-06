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

        public Criteria andIcpIsNull() {
            addCriterion("icp is null");
            return (Criteria) this;
        }

        public Criteria andIcpIsNotNull() {
            addCriterion("icp is not null");
            return (Criteria) this;
        }

        public Criteria andIcpEqualTo(String value) {
            addCriterion("icp =", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotEqualTo(String value) {
            addCriterion("icp <>", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpGreaterThan(String value) {
            addCriterion("icp >", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpGreaterThanOrEqualTo(String value) {
            addCriterion("icp >=", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpLessThan(String value) {
            addCriterion("icp <", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpLessThanOrEqualTo(String value) {
            addCriterion("icp <=", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpLike(String value) {
            addCriterion("icp like", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotLike(String value) {
            addCriterion("icp not like", value, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpIn(List<String> values) {
            addCriterion("icp in", values, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotIn(List<String> values) {
            addCriterion("icp not in", values, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpBetween(String value1, String value2) {
            addCriterion("icp between", value1, value2, "icp");
            return (Criteria) this;
        }

        public Criteria andIcpNotBetween(String value1, String value2) {
            addCriterion("icp not between", value1, value2, "icp");
            return (Criteria) this;
        }

        public Criteria andPrnIsNull() {
            addCriterion("prn is null");
            return (Criteria) this;
        }

        public Criteria andPrnIsNotNull() {
            addCriterion("prn is not null");
            return (Criteria) this;
        }

        public Criteria andPrnEqualTo(String value) {
            addCriterion("prn =", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnNotEqualTo(String value) {
            addCriterion("prn <>", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnGreaterThan(String value) {
            addCriterion("prn >", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnGreaterThanOrEqualTo(String value) {
            addCriterion("prn >=", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnLessThan(String value) {
            addCriterion("prn <", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnLessThanOrEqualTo(String value) {
            addCriterion("prn <=", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnLike(String value) {
            addCriterion("prn like", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnNotLike(String value) {
            addCriterion("prn not like", value, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnIn(List<String> values) {
            addCriterion("prn in", values, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnNotIn(List<String> values) {
            addCriterion("prn not in", values, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnBetween(String value1, String value2) {
            addCriterion("prn between", value1, value2, "prn");
            return (Criteria) this;
        }

        public Criteria andPrnNotBetween(String value1, String value2) {
            addCriterion("prn not between", value1, value2, "prn");
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

        public Criteria andWeixinXcxIsNull() {
            addCriterion("weixin_xcx is null");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxIsNotNull() {
            addCriterion("weixin_xcx is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxEqualTo(String value) {
            addCriterion("weixin_xcx =", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxNotEqualTo(String value) {
            addCriterion("weixin_xcx <>", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxGreaterThan(String value) {
            addCriterion("weixin_xcx >", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxGreaterThanOrEqualTo(String value) {
            addCriterion("weixin_xcx >=", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxLessThan(String value) {
            addCriterion("weixin_xcx <", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxLessThanOrEqualTo(String value) {
            addCriterion("weixin_xcx <=", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxLike(String value) {
            addCriterion("weixin_xcx like", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxNotLike(String value) {
            addCriterion("weixin_xcx not like", value, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxIn(List<String> values) {
            addCriterion("weixin_xcx in", values, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxNotIn(List<String> values) {
            addCriterion("weixin_xcx not in", values, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxBetween(String value1, String value2) {
            addCriterion("weixin_xcx between", value1, value2, "weixinXcx");
            return (Criteria) this;
        }

        public Criteria andWeixinXcxNotBetween(String value1, String value2) {
            addCriterion("weixin_xcx not between", value1, value2, "weixinXcx");
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

        public Criteria andUserAgreementIsNull() {
            addCriterion("user_agreement is null");
            return (Criteria) this;
        }

        public Criteria andUserAgreementIsNotNull() {
            addCriterion("user_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andUserAgreementEqualTo(String value) {
            addCriterion("user_agreement =", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotEqualTo(String value) {
            addCriterion("user_agreement <>", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementGreaterThan(String value) {
            addCriterion("user_agreement >", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("user_agreement >=", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementLessThan(String value) {
            addCriterion("user_agreement <", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementLessThanOrEqualTo(String value) {
            addCriterion("user_agreement <=", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementLike(String value) {
            addCriterion("user_agreement like", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotLike(String value) {
            addCriterion("user_agreement not like", value, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementIn(List<String> values) {
            addCriterion("user_agreement in", values, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotIn(List<String> values) {
            addCriterion("user_agreement not in", values, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementBetween(String value1, String value2) {
            addCriterion("user_agreement between", value1, value2, "userAgreement");
            return (Criteria) this;
        }

        public Criteria andUserAgreementNotBetween(String value1, String value2) {
            addCriterion("user_agreement not between", value1, value2, "userAgreement");
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