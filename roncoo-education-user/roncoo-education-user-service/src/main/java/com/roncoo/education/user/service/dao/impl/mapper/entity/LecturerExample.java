package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

        public Criteria andLecturerUserNoIsNull() {
            addCriterion("lecturer_user_no is null");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoIsNotNull() {
            addCriterion("lecturer_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoEqualTo(Long value) {
            addCriterion("lecturer_user_no =", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoNotEqualTo(Long value) {
            addCriterion("lecturer_user_no <>", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoGreaterThan(Long value) {
            addCriterion("lecturer_user_no >", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoGreaterThanOrEqualTo(Long value) {
            addCriterion("lecturer_user_no >=", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoLessThan(Long value) {
            addCriterion("lecturer_user_no <", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoLessThanOrEqualTo(Long value) {
            addCriterion("lecturer_user_no <=", value, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoIn(List<Long> values) {
            addCriterion("lecturer_user_no in", values, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoNotIn(List<Long> values) {
            addCriterion("lecturer_user_no not in", values, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoBetween(Long value1, Long value2) {
            addCriterion("lecturer_user_no between", value1, value2, "lecturerUserNo");
            return (Criteria) this;
        }

        public Criteria andLecturerUserNoNotBetween(Long value1, Long value2) {
            addCriterion("lecturer_user_no not between", value1, value2, "lecturerUserNo");
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

        public Criteria andLecturerEmailIsNull() {
            addCriterion("lecturer_email is null");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailIsNotNull() {
            addCriterion("lecturer_email is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailEqualTo(String value) {
            addCriterion("lecturer_email =", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailNotEqualTo(String value) {
            addCriterion("lecturer_email <>", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailGreaterThan(String value) {
            addCriterion("lecturer_email >", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailGreaterThanOrEqualTo(String value) {
            addCriterion("lecturer_email >=", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailLessThan(String value) {
            addCriterion("lecturer_email <", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailLessThanOrEqualTo(String value) {
            addCriterion("lecturer_email <=", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailLike(String value) {
            addCriterion("lecturer_email like", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailNotLike(String value) {
            addCriterion("lecturer_email not like", value, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailIn(List<String> values) {
            addCriterion("lecturer_email in", values, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailNotIn(List<String> values) {
            addCriterion("lecturer_email not in", values, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailBetween(String value1, String value2) {
            addCriterion("lecturer_email between", value1, value2, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andLecturerEmailNotBetween(String value1, String value2) {
            addCriterion("lecturer_email not between", value1, value2, "lecturerEmail");
            return (Criteria) this;
        }

        public Criteria andPositionIsNull() {
            addCriterion("position is null");
            return (Criteria) this;
        }

        public Criteria andPositionIsNotNull() {
            addCriterion("position is not null");
            return (Criteria) this;
        }

        public Criteria andPositionEqualTo(String value) {
            addCriterion("position =", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotEqualTo(String value) {
            addCriterion("position <>", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThan(String value) {
            addCriterion("position >", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionGreaterThanOrEqualTo(String value) {
            addCriterion("position >=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThan(String value) {
            addCriterion("position <", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLessThanOrEqualTo(String value) {
            addCriterion("position <=", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionLike(String value) {
            addCriterion("position like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotLike(String value) {
            addCriterion("position not like", value, "position");
            return (Criteria) this;
        }

        public Criteria andPositionIn(List<String> values) {
            addCriterion("position in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotIn(List<String> values) {
            addCriterion("position not in", values, "position");
            return (Criteria) this;
        }

        public Criteria andPositionBetween(String value1, String value2) {
            addCriterion("position between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andPositionNotBetween(String value1, String value2) {
            addCriterion("position not between", value1, value2, "position");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlIsNull() {
            addCriterion("head_img_url is null");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlIsNotNull() {
            addCriterion("head_img_url is not null");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlEqualTo(String value) {
            addCriterion("head_img_url =", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotEqualTo(String value) {
            addCriterion("head_img_url <>", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlGreaterThan(String value) {
            addCriterion("head_img_url >", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("head_img_url >=", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlLessThan(String value) {
            addCriterion("head_img_url <", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlLessThanOrEqualTo(String value) {
            addCriterion("head_img_url <=", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlLike(String value) {
            addCriterion("head_img_url like", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotLike(String value) {
            addCriterion("head_img_url not like", value, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlIn(List<String> values) {
            addCriterion("head_img_url in", values, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotIn(List<String> values) {
            addCriterion("head_img_url not in", values, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlBetween(String value1, String value2) {
            addCriterion("head_img_url between", value1, value2, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andHeadImgUrlNotBetween(String value1, String value2) {
            addCriterion("head_img_url not between", value1, value2, "headImgUrl");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNull() {
            addCriterion("introduce is null");
            return (Criteria) this;
        }

        public Criteria andIntroduceIsNotNull() {
            addCriterion("introduce is not null");
            return (Criteria) this;
        }

        public Criteria andIntroduceEqualTo(String value) {
            addCriterion("introduce =", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotEqualTo(String value) {
            addCriterion("introduce <>", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThan(String value) {
            addCriterion("introduce >", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceGreaterThanOrEqualTo(String value) {
            addCriterion("introduce >=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThan(String value) {
            addCriterion("introduce <", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLessThanOrEqualTo(String value) {
            addCriterion("introduce <=", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceLike(String value) {
            addCriterion("introduce like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotLike(String value) {
            addCriterion("introduce not like", value, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceIn(List<String> values) {
            addCriterion("introduce in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotIn(List<String> values) {
            addCriterion("introduce not in", values, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceBetween(String value1, String value2) {
            addCriterion("introduce between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andIntroduceNotBetween(String value1, String value2) {
            addCriterion("introduce not between", value1, value2, "introduce");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionIsNull() {
            addCriterion("lecturer_proportion is null");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionIsNotNull() {
            addCriterion("lecturer_proportion is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionEqualTo(BigDecimal value) {
            addCriterion("lecturer_proportion =", value, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionNotEqualTo(BigDecimal value) {
            addCriterion("lecturer_proportion <>", value, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionGreaterThan(BigDecimal value) {
            addCriterion("lecturer_proportion >", value, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lecturer_proportion >=", value, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionLessThan(BigDecimal value) {
            addCriterion("lecturer_proportion <", value, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lecturer_proportion <=", value, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionIn(List<BigDecimal> values) {
            addCriterion("lecturer_proportion in", values, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionNotIn(List<BigDecimal> values) {
            addCriterion("lecturer_proportion not in", values, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lecturer_proportion between", value1, value2, "lecturerProportion");
            return (Criteria) this;
        }

        public Criteria andLecturerProportionNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lecturer_proportion not between", value1, value2, "lecturerProportion");
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