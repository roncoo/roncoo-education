package com.roncoo.education.course.dao.impl.mapper.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

        public Criteria andResourceNameIsNull() {
            addCriterion("resource_name is null");
            return (Criteria) this;
        }

        public Criteria andResourceNameIsNotNull() {
            addCriterion("resource_name is not null");
            return (Criteria) this;
        }

        public Criteria andResourceNameEqualTo(String value) {
            addCriterion("resource_name =", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotEqualTo(String value) {
            addCriterion("resource_name <>", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameGreaterThan(String value) {
            addCriterion("resource_name >", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("resource_name >=", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameLessThan(String value) {
            addCriterion("resource_name <", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameLessThanOrEqualTo(String value) {
            addCriterion("resource_name <=", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameLike(String value) {
            addCriterion("resource_name like", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotLike(String value) {
            addCriterion("resource_name not like", value, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameIn(List<String> values) {
            addCriterion("resource_name in", values, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotIn(List<String> values) {
            addCriterion("resource_name not in", values, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameBetween(String value1, String value2) {
            addCriterion("resource_name between", value1, value2, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceNameNotBetween(String value1, String value2) {
            addCriterion("resource_name not between", value1, value2, "resourceName");
            return (Criteria) this;
        }

        public Criteria andResourceTypeIsNull() {
            addCriterion("resource_type is null");
            return (Criteria) this;
        }

        public Criteria andResourceTypeIsNotNull() {
            addCriterion("resource_type is not null");
            return (Criteria) this;
        }

        public Criteria andResourceTypeEqualTo(Integer value) {
            addCriterion("resource_type =", value, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeNotEqualTo(Integer value) {
            addCriterion("resource_type <>", value, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeGreaterThan(Integer value) {
            addCriterion("resource_type >", value, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("resource_type >=", value, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeLessThan(Integer value) {
            addCriterion("resource_type <", value, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeLessThanOrEqualTo(Integer value) {
            addCriterion("resource_type <=", value, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeIn(List<Integer> values) {
            addCriterion("resource_type in", values, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeNotIn(List<Integer> values) {
            addCriterion("resource_type not in", values, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeBetween(Integer value1, Integer value2) {
            addCriterion("resource_type between", value1, value2, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("resource_type not between", value1, value2, "resourceType");
            return (Criteria) this;
        }

        public Criteria andResourceSizeIsNull() {
            addCriterion("resource_size is null");
            return (Criteria) this;
        }

        public Criteria andResourceSizeIsNotNull() {
            addCriterion("resource_size is not null");
            return (Criteria) this;
        }

        public Criteria andResourceSizeEqualTo(Long value) {
            addCriterion("resource_size =", value, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeNotEqualTo(Long value) {
            addCriterion("resource_size <>", value, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeGreaterThan(Long value) {
            addCriterion("resource_size >", value, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeGreaterThanOrEqualTo(Long value) {
            addCriterion("resource_size >=", value, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeLessThan(Long value) {
            addCriterion("resource_size <", value, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeLessThanOrEqualTo(Long value) {
            addCriterion("resource_size <=", value, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeIn(List<Long> values) {
            addCriterion("resource_size in", values, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeNotIn(List<Long> values) {
            addCriterion("resource_size not in", values, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeBetween(Long value1, Long value2) {
            addCriterion("resource_size between", value1, value2, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceSizeNotBetween(Long value1, Long value2) {
            addCriterion("resource_size not between", value1, value2, "resourceSize");
            return (Criteria) this;
        }

        public Criteria andResourceUrlIsNull() {
            addCriterion("resource_url is null");
            return (Criteria) this;
        }

        public Criteria andResourceUrlIsNotNull() {
            addCriterion("resource_url is not null");
            return (Criteria) this;
        }

        public Criteria andResourceUrlEqualTo(String value) {
            addCriterion("resource_url =", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotEqualTo(String value) {
            addCriterion("resource_url <>", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlGreaterThan(String value) {
            addCriterion("resource_url >", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlGreaterThanOrEqualTo(String value) {
            addCriterion("resource_url >=", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlLessThan(String value) {
            addCriterion("resource_url <", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlLessThanOrEqualTo(String value) {
            addCriterion("resource_url <=", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlLike(String value) {
            addCriterion("resource_url like", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotLike(String value) {
            addCriterion("resource_url not like", value, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlIn(List<String> values) {
            addCriterion("resource_url in", values, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotIn(List<String> values) {
            addCriterion("resource_url not in", values, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlBetween(String value1, String value2) {
            addCriterion("resource_url between", value1, value2, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andResourceUrlNotBetween(String value1, String value2) {
            addCriterion("resource_url not between", value1, value2, "resourceUrl");
            return (Criteria) this;
        }

        public Criteria andVodPlatformIsNull() {
            addCriterion("vod_platform is null");
            return (Criteria) this;
        }

        public Criteria andVodPlatformIsNotNull() {
            addCriterion("vod_platform is not null");
            return (Criteria) this;
        }

        public Criteria andVodPlatformEqualTo(Integer value) {
            addCriterion("vod_platform =", value, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformNotEqualTo(Integer value) {
            addCriterion("vod_platform <>", value, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformGreaterThan(Integer value) {
            addCriterion("vod_platform >", value, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("vod_platform >=", value, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformLessThan(Integer value) {
            addCriterion("vod_platform <", value, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformLessThanOrEqualTo(Integer value) {
            addCriterion("vod_platform <=", value, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformIn(List<Integer> values) {
            addCriterion("vod_platform in", values, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformNotIn(List<Integer> values) {
            addCriterion("vod_platform not in", values, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformBetween(Integer value1, Integer value2) {
            addCriterion("vod_platform between", value1, value2, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andVodPlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("vod_platform not between", value1, value2, "vodPlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformIsNull() {
            addCriterion("storage_platform is null");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformIsNotNull() {
            addCriterion("storage_platform is not null");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformEqualTo(Integer value) {
            addCriterion("storage_platform =", value, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformNotEqualTo(Integer value) {
            addCriterion("storage_platform <>", value, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformGreaterThan(Integer value) {
            addCriterion("storage_platform >", value, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("storage_platform >=", value, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformLessThan(Integer value) {
            addCriterion("storage_platform <", value, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformLessThanOrEqualTo(Integer value) {
            addCriterion("storage_platform <=", value, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformIn(List<Integer> values) {
            addCriterion("storage_platform in", values, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformNotIn(List<Integer> values) {
            addCriterion("storage_platform not in", values, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformBetween(Integer value1, Integer value2) {
            addCriterion("storage_platform between", value1, value2, "storagePlatform");
            return (Criteria) this;
        }

        public Criteria andStoragePlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("storage_platform not between", value1, value2, "storagePlatform");
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

        public Criteria andVideoLengthEqualTo(Integer value) {
            addCriterion("video_length =", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotEqualTo(Integer value) {
            addCriterion("video_length <>", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthGreaterThan(Integer value) {
            addCriterion("video_length >", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthGreaterThanOrEqualTo(Integer value) {
            addCriterion("video_length >=", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLessThan(Integer value) {
            addCriterion("video_length <", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthLessThanOrEqualTo(Integer value) {
            addCriterion("video_length <=", value, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthIn(List<Integer> values) {
            addCriterion("video_length in", values, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotIn(List<Integer> values) {
            addCriterion("video_length not in", values, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthBetween(Integer value1, Integer value2) {
            addCriterion("video_length between", value1, value2, "videoLength");
            return (Criteria) this;
        }

        public Criteria andVideoLengthNotBetween(Integer value1, Integer value2) {
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

        public Criteria andDocPageIsNull() {
            addCriterion("doc_page is null");
            return (Criteria) this;
        }

        public Criteria andDocPageIsNotNull() {
            addCriterion("doc_page is not null");
            return (Criteria) this;
        }

        public Criteria andDocPageEqualTo(Integer value) {
            addCriterion("doc_page =", value, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageNotEqualTo(Integer value) {
            addCriterion("doc_page <>", value, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageGreaterThan(Integer value) {
            addCriterion("doc_page >", value, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageGreaterThanOrEqualTo(Integer value) {
            addCriterion("doc_page >=", value, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageLessThan(Integer value) {
            addCriterion("doc_page <", value, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageLessThanOrEqualTo(Integer value) {
            addCriterion("doc_page <=", value, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageIn(List<Integer> values) {
            addCriterion("doc_page in", values, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageNotIn(List<Integer> values) {
            addCriterion("doc_page not in", values, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageBetween(Integer value1, Integer value2) {
            addCriterion("doc_page between", value1, value2, "docPage");
            return (Criteria) this;
        }

        public Criteria andDocPageNotBetween(Integer value1, Integer value2) {
            addCriterion("doc_page not between", value1, value2, "docPage");
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
