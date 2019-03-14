package com.roncoo.education.system.service.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SysExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public SysExample() {
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

        public Criteria andVideoTypeIsNull() {
            addCriterion("video_type is null");
            return (Criteria) this;
        }

        public Criteria andVideoTypeIsNotNull() {
            addCriterion("video_type is not null");
            return (Criteria) this;
        }

        public Criteria andVideoTypeEqualTo(Integer value) {
            addCriterion("video_type =", value, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeNotEqualTo(Integer value) {
            addCriterion("video_type <>", value, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeGreaterThan(Integer value) {
            addCriterion("video_type >", value, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_type >=", value, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeLessThan(Integer value) {
            addCriterion("video_type <", value, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeLessThanOrEqualTo(Integer value) {
            addCriterion("video_type <=", value, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeIn(List<Integer> values) {
            addCriterion("video_type in", values, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeNotIn(List<Integer> values) {
            addCriterion("video_type not in", values, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeBetween(Integer value1, Integer value2) {
            addCriterion("video_type between", value1, value2, "videoType");
            return (Criteria) this;
        }

        public Criteria andVideoTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("video_type not between", value1, value2, "videoType");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidIsNull() {
            addCriterion("polyv_useid is null");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidIsNotNull() {
            addCriterion("polyv_useid is not null");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidEqualTo(String value) {
            addCriterion("polyv_useid =", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidNotEqualTo(String value) {
            addCriterion("polyv_useid <>", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidGreaterThan(String value) {
            addCriterion("polyv_useid >", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidGreaterThanOrEqualTo(String value) {
            addCriterion("polyv_useid >=", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidLessThan(String value) {
            addCriterion("polyv_useid <", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidLessThanOrEqualTo(String value) {
            addCriterion("polyv_useid <=", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidLike(String value) {
            addCriterion("polyv_useid like", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidNotLike(String value) {
            addCriterion("polyv_useid not like", value, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidIn(List<String> values) {
            addCriterion("polyv_useid in", values, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidNotIn(List<String> values) {
            addCriterion("polyv_useid not in", values, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidBetween(String value1, String value2) {
            addCriterion("polyv_useid between", value1, value2, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvUseidNotBetween(String value1, String value2) {
            addCriterion("polyv_useid not between", value1, value2, "polyvUseid");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenIsNull() {
            addCriterion("polyv_writetoken is null");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenIsNotNull() {
            addCriterion("polyv_writetoken is not null");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenEqualTo(String value) {
            addCriterion("polyv_writetoken =", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenNotEqualTo(String value) {
            addCriterion("polyv_writetoken <>", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenGreaterThan(String value) {
            addCriterion("polyv_writetoken >", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenGreaterThanOrEqualTo(String value) {
            addCriterion("polyv_writetoken >=", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenLessThan(String value) {
            addCriterion("polyv_writetoken <", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenLessThanOrEqualTo(String value) {
            addCriterion("polyv_writetoken <=", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenLike(String value) {
            addCriterion("polyv_writetoken like", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenNotLike(String value) {
            addCriterion("polyv_writetoken not like", value, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenIn(List<String> values) {
            addCriterion("polyv_writetoken in", values, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenNotIn(List<String> values) {
            addCriterion("polyv_writetoken not in", values, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenBetween(String value1, String value2) {
            addCriterion("polyv_writetoken between", value1, value2, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvWritetokenNotBetween(String value1, String value2) {
            addCriterion("polyv_writetoken not between", value1, value2, "polyvWritetoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenIsNull() {
            addCriterion("polyv_readtoken is null");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenIsNotNull() {
            addCriterion("polyv_readtoken is not null");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenEqualTo(String value) {
            addCriterion("polyv_readtoken =", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenNotEqualTo(String value) {
            addCriterion("polyv_readtoken <>", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenGreaterThan(String value) {
            addCriterion("polyv_readtoken >", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenGreaterThanOrEqualTo(String value) {
            addCriterion("polyv_readtoken >=", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenLessThan(String value) {
            addCriterion("polyv_readtoken <", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenLessThanOrEqualTo(String value) {
            addCriterion("polyv_readtoken <=", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenLike(String value) {
            addCriterion("polyv_readtoken like", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenNotLike(String value) {
            addCriterion("polyv_readtoken not like", value, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenIn(List<String> values) {
            addCriterion("polyv_readtoken in", values, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenNotIn(List<String> values) {
            addCriterion("polyv_readtoken not in", values, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenBetween(String value1, String value2) {
            addCriterion("polyv_readtoken between", value1, value2, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvReadtokenNotBetween(String value1, String value2) {
            addCriterion("polyv_readtoken not between", value1, value2, "polyvReadtoken");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyIsNull() {
            addCriterion("polyv_secretkey is null");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyIsNotNull() {
            addCriterion("polyv_secretkey is not null");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyEqualTo(String value) {
            addCriterion("polyv_secretkey =", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyNotEqualTo(String value) {
            addCriterion("polyv_secretkey <>", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyGreaterThan(String value) {
            addCriterion("polyv_secretkey >", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyGreaterThanOrEqualTo(String value) {
            addCriterion("polyv_secretkey >=", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyLessThan(String value) {
            addCriterion("polyv_secretkey <", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyLessThanOrEqualTo(String value) {
            addCriterion("polyv_secretkey <=", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyLike(String value) {
            addCriterion("polyv_secretkey like", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyNotLike(String value) {
            addCriterion("polyv_secretkey not like", value, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyIn(List<String> values) {
            addCriterion("polyv_secretkey in", values, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyNotIn(List<String> values) {
            addCriterion("polyv_secretkey not in", values, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyBetween(String value1, String value2) {
            addCriterion("polyv_secretkey between", value1, value2, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andPolyvSecretkeyNotBetween(String value1, String value2) {
            addCriterion("polyv_secretkey not between", value1, value2, "polyvSecretkey");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNull() {
            addCriterion("file_type is null");
            return (Criteria) this;
        }

        public Criteria andFileTypeIsNotNull() {
            addCriterion("file_type is not null");
            return (Criteria) this;
        }

        public Criteria andFileTypeEqualTo(Integer value) {
            addCriterion("file_type =", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotEqualTo(Integer value) {
            addCriterion("file_type <>", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThan(Integer value) {
            addCriterion("file_type >", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("file_type >=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThan(Integer value) {
            addCriterion("file_type <", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeLessThanOrEqualTo(Integer value) {
            addCriterion("file_type <=", value, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeIn(List<Integer> values) {
            addCriterion("file_type in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotIn(List<Integer> values) {
            addCriterion("file_type not in", values, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeBetween(Integer value1, Integer value2) {
            addCriterion("file_type between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andFileTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("file_type not between", value1, value2, "fileType");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdIsNull() {
            addCriterion("aliyun_access_key_id is null");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdIsNotNull() {
            addCriterion("aliyun_access_key_id is not null");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdEqualTo(String value) {
            addCriterion("aliyun_access_key_id =", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdNotEqualTo(String value) {
            addCriterion("aliyun_access_key_id <>", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdGreaterThan(String value) {
            addCriterion("aliyun_access_key_id >", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdGreaterThanOrEqualTo(String value) {
            addCriterion("aliyun_access_key_id >=", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdLessThan(String value) {
            addCriterion("aliyun_access_key_id <", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdLessThanOrEqualTo(String value) {
            addCriterion("aliyun_access_key_id <=", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdLike(String value) {
            addCriterion("aliyun_access_key_id like", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdNotLike(String value) {
            addCriterion("aliyun_access_key_id not like", value, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdIn(List<String> values) {
            addCriterion("aliyun_access_key_id in", values, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdNotIn(List<String> values) {
            addCriterion("aliyun_access_key_id not in", values, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdBetween(String value1, String value2) {
            addCriterion("aliyun_access_key_id between", value1, value2, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeyIdNotBetween(String value1, String value2) {
            addCriterion("aliyun_access_key_id not between", value1, value2, "aliyunAccessKeyId");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretIsNull() {
            addCriterion("aliyun_access_key_secret is null");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretIsNotNull() {
            addCriterion("aliyun_access_key_secret is not null");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretEqualTo(String value) {
            addCriterion("aliyun_access_key_secret =", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretNotEqualTo(String value) {
            addCriterion("aliyun_access_key_secret <>", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretGreaterThan(String value) {
            addCriterion("aliyun_access_key_secret >", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretGreaterThanOrEqualTo(String value) {
            addCriterion("aliyun_access_key_secret >=", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretLessThan(String value) {
            addCriterion("aliyun_access_key_secret <", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretLessThanOrEqualTo(String value) {
            addCriterion("aliyun_access_key_secret <=", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretLike(String value) {
            addCriterion("aliyun_access_key_secret like", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretNotLike(String value) {
            addCriterion("aliyun_access_key_secret not like", value, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretIn(List<String> values) {
            addCriterion("aliyun_access_key_secret in", values, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretNotIn(List<String> values) {
            addCriterion("aliyun_access_key_secret not in", values, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretBetween(String value1, String value2) {
            addCriterion("aliyun_access_key_secret between", value1, value2, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunAccessKeySecretNotBetween(String value1, String value2) {
            addCriterion("aliyun_access_key_secret not between", value1, value2, "aliyunAccessKeySecret");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlIsNull() {
            addCriterion("aliyun_oss_url is null");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlIsNotNull() {
            addCriterion("aliyun_oss_url is not null");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlEqualTo(String value) {
            addCriterion("aliyun_oss_url =", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlNotEqualTo(String value) {
            addCriterion("aliyun_oss_url <>", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlGreaterThan(String value) {
            addCriterion("aliyun_oss_url >", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlGreaterThanOrEqualTo(String value) {
            addCriterion("aliyun_oss_url >=", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlLessThan(String value) {
            addCriterion("aliyun_oss_url <", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlLessThanOrEqualTo(String value) {
            addCriterion("aliyun_oss_url <=", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlLike(String value) {
            addCriterion("aliyun_oss_url like", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlNotLike(String value) {
            addCriterion("aliyun_oss_url not like", value, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlIn(List<String> values) {
            addCriterion("aliyun_oss_url in", values, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlNotIn(List<String> values) {
            addCriterion("aliyun_oss_url not in", values, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlBetween(String value1, String value2) {
            addCriterion("aliyun_oss_url between", value1, value2, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssUrlNotBetween(String value1, String value2) {
            addCriterion("aliyun_oss_url not between", value1, value2, "aliyunOssUrl");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketIsNull() {
            addCriterion("aliyun_oss_bucket is null");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketIsNotNull() {
            addCriterion("aliyun_oss_bucket is not null");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketEqualTo(String value) {
            addCriterion("aliyun_oss_bucket =", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketNotEqualTo(String value) {
            addCriterion("aliyun_oss_bucket <>", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketGreaterThan(String value) {
            addCriterion("aliyun_oss_bucket >", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketGreaterThanOrEqualTo(String value) {
            addCriterion("aliyun_oss_bucket >=", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketLessThan(String value) {
            addCriterion("aliyun_oss_bucket <", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketLessThanOrEqualTo(String value) {
            addCriterion("aliyun_oss_bucket <=", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketLike(String value) {
            addCriterion("aliyun_oss_bucket like", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketNotLike(String value) {
            addCriterion("aliyun_oss_bucket not like", value, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketIn(List<String> values) {
            addCriterion("aliyun_oss_bucket in", values, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketNotIn(List<String> values) {
            addCriterion("aliyun_oss_bucket not in", values, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketBetween(String value1, String value2) {
            addCriterion("aliyun_oss_bucket between", value1, value2, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andAliyunOssBucketNotBetween(String value1, String value2) {
            addCriterion("aliyun_oss_bucket not between", value1, value2, "aliyunOssBucket");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Integer value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Integer value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Integer value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Integer value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Integer value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Integer> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Integer> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Integer value1, Integer value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayUrlIsNull() {
            addCriterion("pay_url is null");
            return (Criteria) this;
        }

        public Criteria andPayUrlIsNotNull() {
            addCriterion("pay_url is not null");
            return (Criteria) this;
        }

        public Criteria andPayUrlEqualTo(String value) {
            addCriterion("pay_url =", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotEqualTo(String value) {
            addCriterion("pay_url <>", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlGreaterThan(String value) {
            addCriterion("pay_url >", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlGreaterThanOrEqualTo(String value) {
            addCriterion("pay_url >=", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLessThan(String value) {
            addCriterion("pay_url <", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLessThanOrEqualTo(String value) {
            addCriterion("pay_url <=", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlLike(String value) {
            addCriterion("pay_url like", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotLike(String value) {
            addCriterion("pay_url not like", value, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlIn(List<String> values) {
            addCriterion("pay_url in", values, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotIn(List<String> values) {
            addCriterion("pay_url not in", values, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlBetween(String value1, String value2) {
            addCriterion("pay_url between", value1, value2, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayUrlNotBetween(String value1, String value2) {
            addCriterion("pay_url not between", value1, value2, "payUrl");
            return (Criteria) this;
        }

        public Criteria andPayKeyIsNull() {
            addCriterion("pay_key is null");
            return (Criteria) this;
        }

        public Criteria andPayKeyIsNotNull() {
            addCriterion("pay_key is not null");
            return (Criteria) this;
        }

        public Criteria andPayKeyEqualTo(String value) {
            addCriterion("pay_key =", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotEqualTo(String value) {
            addCriterion("pay_key <>", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyGreaterThan(String value) {
            addCriterion("pay_key >", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyGreaterThanOrEqualTo(String value) {
            addCriterion("pay_key >=", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyLessThan(String value) {
            addCriterion("pay_key <", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyLessThanOrEqualTo(String value) {
            addCriterion("pay_key <=", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyLike(String value) {
            addCriterion("pay_key like", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotLike(String value) {
            addCriterion("pay_key not like", value, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyIn(List<String> values) {
            addCriterion("pay_key in", values, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotIn(List<String> values) {
            addCriterion("pay_key not in", values, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyBetween(String value1, String value2) {
            addCriterion("pay_key between", value1, value2, "payKey");
            return (Criteria) this;
        }

        public Criteria andPayKeyNotBetween(String value1, String value2) {
            addCriterion("pay_key not between", value1, value2, "payKey");
            return (Criteria) this;
        }

        public Criteria andPaySecretIsNull() {
            addCriterion("pay_secret is null");
            return (Criteria) this;
        }

        public Criteria andPaySecretIsNotNull() {
            addCriterion("pay_secret is not null");
            return (Criteria) this;
        }

        public Criteria andPaySecretEqualTo(String value) {
            addCriterion("pay_secret =", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretNotEqualTo(String value) {
            addCriterion("pay_secret <>", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretGreaterThan(String value) {
            addCriterion("pay_secret >", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretGreaterThanOrEqualTo(String value) {
            addCriterion("pay_secret >=", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretLessThan(String value) {
            addCriterion("pay_secret <", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretLessThanOrEqualTo(String value) {
            addCriterion("pay_secret <=", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretLike(String value) {
            addCriterion("pay_secret like", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretNotLike(String value) {
            addCriterion("pay_secret not like", value, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretIn(List<String> values) {
            addCriterion("pay_secret in", values, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretNotIn(List<String> values) {
            addCriterion("pay_secret not in", values, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretBetween(String value1, String value2) {
            addCriterion("pay_secret between", value1, value2, "paySecret");
            return (Criteria) this;
        }

        public Criteria andPaySecretNotBetween(String value1, String value2) {
            addCriterion("pay_secret not between", value1, value2, "paySecret");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNull() {
            addCriterion("notify_url is null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIsNotNull() {
            addCriterion("notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlEqualTo(String value) {
            addCriterion("notify_url =", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotEqualTo(String value) {
            addCriterion("notify_url <>", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThan(String value) {
            addCriterion("notify_url >", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlGreaterThanOrEqualTo(String value) {
            addCriterion("notify_url >=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThan(String value) {
            addCriterion("notify_url <", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLessThanOrEqualTo(String value) {
            addCriterion("notify_url <=", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlLike(String value) {
            addCriterion("notify_url like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotLike(String value) {
            addCriterion("notify_url not like", value, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlIn(List<String> values) {
            addCriterion("notify_url in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotIn(List<String> values) {
            addCriterion("notify_url not in", values, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlBetween(String value1, String value2) {
            addCriterion("notify_url between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andNotifyUrlNotBetween(String value1, String value2) {
            addCriterion("notify_url not between", value1, value2, "notifyUrl");
            return (Criteria) this;
        }

        public Criteria andSmsCodeIsNull() {
            addCriterion("sms_code is null");
            return (Criteria) this;
        }

        public Criteria andSmsCodeIsNotNull() {
            addCriterion("sms_code is not null");
            return (Criteria) this;
        }

        public Criteria andSmsCodeEqualTo(String value) {
            addCriterion("sms_code =", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotEqualTo(String value) {
            addCriterion("sms_code <>", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeGreaterThan(String value) {
            addCriterion("sms_code >", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sms_code >=", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeLessThan(String value) {
            addCriterion("sms_code <", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeLessThanOrEqualTo(String value) {
            addCriterion("sms_code <=", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeLike(String value) {
            addCriterion("sms_code like", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotLike(String value) {
            addCriterion("sms_code not like", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeIn(List<String> values) {
            addCriterion("sms_code in", values, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotIn(List<String> values) {
            addCriterion("sms_code not in", values, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeBetween(String value1, String value2) {
            addCriterion("sms_code between", value1, value2, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotBetween(String value1, String value2) {
            addCriterion("sms_code not between", value1, value2, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSignNameIsNull() {
            addCriterion("sign_name is null");
            return (Criteria) this;
        }

        public Criteria andSignNameIsNotNull() {
            addCriterion("sign_name is not null");
            return (Criteria) this;
        }

        public Criteria andSignNameEqualTo(String value) {
            addCriterion("sign_name =", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotEqualTo(String value) {
            addCriterion("sign_name <>", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameGreaterThan(String value) {
            addCriterion("sign_name >", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameGreaterThanOrEqualTo(String value) {
            addCriterion("sign_name >=", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLessThan(String value) {
            addCriterion("sign_name <", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLessThanOrEqualTo(String value) {
            addCriterion("sign_name <=", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameLike(String value) {
            addCriterion("sign_name like", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotLike(String value) {
            addCriterion("sign_name not like", value, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameIn(List<String> values) {
            addCriterion("sign_name in", values, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotIn(List<String> values) {
            addCriterion("sign_name not in", values, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameBetween(String value1, String value2) {
            addCriterion("sign_name between", value1, value2, "signName");
            return (Criteria) this;
        }

        public Criteria andSignNameNotBetween(String value1, String value2) {
            addCriterion("sign_name not between", value1, value2, "signName");
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