package com.roncoo.education.course.dao.impl.mapper.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public ResourceExample() {
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

        public Criteria andRecourceNameIsNull() {
            addCriterion("recource_name is null");
            return (Criteria) this;
        }

        public Criteria andRecourceNameIsNotNull() {
            addCriterion("recource_name is not null");
            return (Criteria) this;
        }

        public Criteria andRecourceNameEqualTo(String value) {
            addCriterion("recource_name =", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameNotEqualTo(String value) {
            addCriterion("recource_name <>", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameGreaterThan(String value) {
            addCriterion("recource_name >", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("recource_name >=", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameLessThan(String value) {
            addCriterion("recource_name <", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameLessThanOrEqualTo(String value) {
            addCriterion("recource_name <=", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameLike(String value) {
            addCriterion("recource_name like", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameNotLike(String value) {
            addCriterion("recource_name not like", value, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameIn(List<String> values) {
            addCriterion("recource_name in", values, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameNotIn(List<String> values) {
            addCriterion("recource_name not in", values, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameBetween(String value1, String value2) {
            addCriterion("recource_name between", value1, value2, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceNameNotBetween(String value1, String value2) {
            addCriterion("recource_name not between", value1, value2, "recourceName");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeIsNull() {
            addCriterion("recource_type is null");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeIsNotNull() {
            addCriterion("recource_type is not null");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeEqualTo(Integer value) {
            addCriterion("recource_type =", value, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeNotEqualTo(Integer value) {
            addCriterion("recource_type <>", value, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeGreaterThan(Integer value) {
            addCriterion("recource_type >", value, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("recource_type >=", value, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeLessThan(Integer value) {
            addCriterion("recource_type <", value, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("recource_type <=", value, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeIn(List<Integer> values) {
            addCriterion("recource_type in", values, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeNotIn(List<Integer> values) {
            addCriterion("recource_type not in", values, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeBetween(Integer value1, Integer value2) {
            addCriterion("recource_type between", value1, value2, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("recource_type not between", value1, value2, "recourceType");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeIsNull() {
            addCriterion("recource_size is null");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeIsNotNull() {
            addCriterion("recource_size is not null");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeEqualTo(Long value) {
            addCriterion("recource_size =", value, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeNotEqualTo(Long value) {
            addCriterion("recource_size <>", value, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeGreaterThan(Long value) {
            addCriterion("recource_size >", value, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("recource_size >=", value, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeLessThan(Long value) {
            addCriterion("recource_size <", value, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeLessThanOrEqualTo(Long value) {
            addCriterion("recource_size <=", value, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeIn(List<Long> values) {
            addCriterion("recource_size in", values, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeNotIn(List<Long> values) {
            addCriterion("recource_size not in", values, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeBetween(Long value1, Long value2) {
            addCriterion("recource_size between", value1, value2, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceSizeNotBetween(Long value1, Long value2) {
            addCriterion("recource_size not between", value1, value2, "recourceSize");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlIsNull() {
            addCriterion("recource_url is null");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlIsNotNull() {
            addCriterion("recource_url is not null");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlEqualTo(String value) {
            addCriterion("recource_url =", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlNotEqualTo(String value) {
            addCriterion("recource_url <>", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlGreaterThan(String value) {
            addCriterion("recource_url >", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("recource_url >=", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlLessThan(String value) {
            addCriterion("recource_url <", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlLessThanOrEqualTo(String value) {
            addCriterion("recource_url <=", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlLike(String value) {
            addCriterion("recource_url like", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlNotLike(String value) {
            addCriterion("recource_url not like", value, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlIn(List<String> values) {
            addCriterion("recource_url in", values, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlNotIn(List<String> values) {
            addCriterion("recource_url not in", values, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlBetween(String value1, String value2) {
            addCriterion("recource_url between", value1, value2, "recourceUrl");
            return (Criteria) this;
        }

        public Criteria andRecourceUrlNotBetween(String value1, String value2) {
            addCriterion("recource_url not between", value1, value2, "recourceUrl");
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