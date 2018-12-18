package com.roncoo.education.course.service.dao.impl.mapper.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public OrderInfoExample() {
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

        public Criteria andUserNoIsNull() {
            addCriterion("user_no is null");
            return (Criteria) this;
        }

        public Criteria andUserNoIsNotNull() {
            addCriterion("user_no is not null");
            return (Criteria) this;
        }

        public Criteria andUserNoEqualTo(Long value) {
            addCriterion("user_no =", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotEqualTo(Long value) {
            addCriterion("user_no <>", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThan(Long value) {
            addCriterion("user_no >", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoGreaterThanOrEqualTo(Long value) {
            addCriterion("user_no >=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThan(Long value) {
            addCriterion("user_no <", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoLessThanOrEqualTo(Long value) {
            addCriterion("user_no <=", value, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoIn(List<Long> values) {
            addCriterion("user_no in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotIn(List<Long> values) {
            addCriterion("user_no not in", values, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoBetween(Long value1, Long value2) {
            addCriterion("user_no between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andUserNoNotBetween(Long value1, Long value2) {
            addCriterion("user_no not between", value1, value2, "userNo");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNull() {
            addCriterion("register_time is null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIsNotNull() {
            addCriterion("register_time is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeEqualTo(Date value) {
            addCriterion("register_time =", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotEqualTo(Date value) {
            addCriterion("register_time <>", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThan(Date value) {
            addCriterion("register_time >", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("register_time >=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThan(Date value) {
            addCriterion("register_time <", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeLessThanOrEqualTo(Date value) {
            addCriterion("register_time <=", value, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeIn(List<Date> values) {
            addCriterion("register_time in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotIn(List<Date> values) {
            addCriterion("register_time not in", values, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeBetween(Date value1, Date value2) {
            addCriterion("register_time between", value1, value2, "registerTime");
            return (Criteria) this;
        }

        public Criteria andRegisterTimeNotBetween(Date value1, Date value2) {
            addCriterion("register_time not between", value1, value2, "registerTime");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(Long value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(Long value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(Long value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(Long value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(Long value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(Long value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<Long> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<Long> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(Long value1, Long value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(Long value1, Long value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNull() {
            addCriterion("course_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseIdIsNotNull() {
            addCriterion("course_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseIdEqualTo(Long value) {
            addCriterion("course_id =", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotEqualTo(Long value) {
            addCriterion("course_id <>", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThan(Long value) {
            addCriterion("course_id >", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdGreaterThanOrEqualTo(Long value) {
            addCriterion("course_id >=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThan(Long value) {
            addCriterion("course_id <", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdLessThanOrEqualTo(Long value) {
            addCriterion("course_id <=", value, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdIn(List<Long> values) {
            addCriterion("course_id in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotIn(List<Long> values) {
            addCriterion("course_id not in", values, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdBetween(Long value1, Long value2) {
            addCriterion("course_id between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseIdNotBetween(Long value1, Long value2) {
            addCriterion("course_id not between", value1, value2, "courseId");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNull() {
            addCriterion("course_name is null");
            return (Criteria) this;
        }

        public Criteria andCourseNameIsNotNull() {
            addCriterion("course_name is not null");
            return (Criteria) this;
        }

        public Criteria andCourseNameEqualTo(String value) {
            addCriterion("course_name =", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotEqualTo(String value) {
            addCriterion("course_name <>", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThan(String value) {
            addCriterion("course_name >", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameGreaterThanOrEqualTo(String value) {
            addCriterion("course_name >=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThan(String value) {
            addCriterion("course_name <", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLessThanOrEqualTo(String value) {
            addCriterion("course_name <=", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameLike(String value) {
            addCriterion("course_name like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotLike(String value) {
            addCriterion("course_name not like", value, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameIn(List<String> values) {
            addCriterion("course_name in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotIn(List<String> values) {
            addCriterion("course_name not in", values, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameBetween(String value1, String value2) {
            addCriterion("course_name between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andCourseNameNotBetween(String value1, String value2) {
            addCriterion("course_name not between", value1, value2, "courseName");
            return (Criteria) this;
        }

        public Criteria andPricePayableIsNull() {
            addCriterion("price_payable is null");
            return (Criteria) this;
        }

        public Criteria andPricePayableIsNotNull() {
            addCriterion("price_payable is not null");
            return (Criteria) this;
        }

        public Criteria andPricePayableEqualTo(BigDecimal value) {
            addCriterion("price_payable =", value, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableNotEqualTo(BigDecimal value) {
            addCriterion("price_payable <>", value, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableGreaterThan(BigDecimal value) {
            addCriterion("price_payable >", value, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price_payable >=", value, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableLessThan(BigDecimal value) {
            addCriterion("price_payable <", value, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price_payable <=", value, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableIn(List<BigDecimal> values) {
            addCriterion("price_payable in", values, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableNotIn(List<BigDecimal> values) {
            addCriterion("price_payable not in", values, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_payable between", value1, value2, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPricePayableNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_payable not between", value1, value2, "pricePayable");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountIsNull() {
            addCriterion("price_discount is null");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountIsNotNull() {
            addCriterion("price_discount is not null");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountEqualTo(BigDecimal value) {
            addCriterion("price_discount =", value, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountNotEqualTo(BigDecimal value) {
            addCriterion("price_discount <>", value, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountGreaterThan(BigDecimal value) {
            addCriterion("price_discount >", value, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price_discount >=", value, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountLessThan(BigDecimal value) {
            addCriterion("price_discount <", value, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price_discount <=", value, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountIn(List<BigDecimal> values) {
            addCriterion("price_discount in", values, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountNotIn(List<BigDecimal> values) {
            addCriterion("price_discount not in", values, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_discount between", value1, value2, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPriceDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_discount not between", value1, value2, "priceDiscount");
            return (Criteria) this;
        }

        public Criteria andPricePaidIsNull() {
            addCriterion("price_paid is null");
            return (Criteria) this;
        }

        public Criteria andPricePaidIsNotNull() {
            addCriterion("price_paid is not null");
            return (Criteria) this;
        }

        public Criteria andPricePaidEqualTo(BigDecimal value) {
            addCriterion("price_paid =", value, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidNotEqualTo(BigDecimal value) {
            addCriterion("price_paid <>", value, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidGreaterThan(BigDecimal value) {
            addCriterion("price_paid >", value, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price_paid >=", value, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidLessThan(BigDecimal value) {
            addCriterion("price_paid <", value, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price_paid <=", value, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidIn(List<BigDecimal> values) {
            addCriterion("price_paid in", values, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidNotIn(List<BigDecimal> values) {
            addCriterion("price_paid not in", values, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_paid between", value1, value2, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPricePaidNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price_paid not between", value1, value2, "pricePaid");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitIsNull() {
            addCriterion("platform_profit is null");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitIsNotNull() {
            addCriterion("platform_profit is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitEqualTo(BigDecimal value) {
            addCriterion("platform_profit =", value, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitNotEqualTo(BigDecimal value) {
            addCriterion("platform_profit <>", value, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitGreaterThan(BigDecimal value) {
            addCriterion("platform_profit >", value, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_profit >=", value, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitLessThan(BigDecimal value) {
            addCriterion("platform_profit <", value, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_profit <=", value, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitIn(List<BigDecimal> values) {
            addCriterion("platform_profit in", values, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitNotIn(List<BigDecimal> values) {
            addCriterion("platform_profit not in", values, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_profit between", value1, value2, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andPlatformProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_profit not between", value1, value2, "platformProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitIsNull() {
            addCriterion("lecturer_profit is null");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitIsNotNull() {
            addCriterion("lecturer_profit is not null");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitEqualTo(BigDecimal value) {
            addCriterion("lecturer_profit =", value, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitNotEqualTo(BigDecimal value) {
            addCriterion("lecturer_profit <>", value, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitGreaterThan(BigDecimal value) {
            addCriterion("lecturer_profit >", value, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("lecturer_profit >=", value, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitLessThan(BigDecimal value) {
            addCriterion("lecturer_profit <", value, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("lecturer_profit <=", value, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitIn(List<BigDecimal> values) {
            addCriterion("lecturer_profit in", values, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitNotIn(List<BigDecimal> values) {
            addCriterion("lecturer_profit not in", values, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lecturer_profit between", value1, value2, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andLecturerProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("lecturer_profit not between", value1, value2, "lecturerProfit");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTradeTypeEqualTo(Integer value) {
            addCriterion("trade_type =", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotEqualTo(Integer value) {
            addCriterion("trade_type <>", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThan(Integer value) {
            addCriterion("trade_type >", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_type >=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThan(Integer value) {
            addCriterion("trade_type <", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeLessThanOrEqualTo(Integer value) {
            addCriterion("trade_type <=", value, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeIn(List<Integer> values) {
            addCriterion("trade_type in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotIn(List<Integer> values) {
            addCriterion("trade_type not in", values, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeBetween(Integer value1, Integer value2) {
            addCriterion("trade_type between", value1, value2, "tradeType");
            return (Criteria) this;
        }

        public Criteria andTradeTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_type not between", value1, value2, "tradeType");
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

        public Criteria andChannelTypeIsNull() {
            addCriterion("channel_type is null");
            return (Criteria) this;
        }

        public Criteria andChannelTypeIsNotNull() {
            addCriterion("channel_type is not null");
            return (Criteria) this;
        }

        public Criteria andChannelTypeEqualTo(Integer value) {
            addCriterion("channel_type =", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotEqualTo(Integer value) {
            addCriterion("channel_type <>", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeGreaterThan(Integer value) {
            addCriterion("channel_type >", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("channel_type >=", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeLessThan(Integer value) {
            addCriterion("channel_type <", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeLessThanOrEqualTo(Integer value) {
            addCriterion("channel_type <=", value, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeIn(List<Integer> values) {
            addCriterion("channel_type in", values, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotIn(List<Integer> values) {
            addCriterion("channel_type not in", values, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeBetween(Integer value1, Integer value2) {
            addCriterion("channel_type between", value1, value2, "channelType");
            return (Criteria) this;
        }

        public Criteria andChannelTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("channel_type not between", value1, value2, "channelType");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerIsNull() {
            addCriterion("is_show_lecturer is null");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerIsNotNull() {
            addCriterion("is_show_lecturer is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerEqualTo(Integer value) {
            addCriterion("is_show_lecturer =", value, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerNotEqualTo(Integer value) {
            addCriterion("is_show_lecturer <>", value, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerGreaterThan(Integer value) {
            addCriterion("is_show_lecturer >", value, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_show_lecturer >=", value, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerLessThan(Integer value) {
            addCriterion("is_show_lecturer <", value, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerLessThanOrEqualTo(Integer value) {
            addCriterion("is_show_lecturer <=", value, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerIn(List<Integer> values) {
            addCriterion("is_show_lecturer in", values, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerNotIn(List<Integer> values) {
            addCriterion("is_show_lecturer not in", values, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerBetween(Integer value1, Integer value2) {
            addCriterion("is_show_lecturer between", value1, value2, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowLecturerNotBetween(Integer value1, Integer value2) {
            addCriterion("is_show_lecturer not between", value1, value2, "isShowLecturer");
            return (Criteria) this;
        }

        public Criteria andIsShowUserIsNull() {
            addCriterion("is_show_user is null");
            return (Criteria) this;
        }

        public Criteria andIsShowUserIsNotNull() {
            addCriterion("is_show_user is not null");
            return (Criteria) this;
        }

        public Criteria andIsShowUserEqualTo(Integer value) {
            addCriterion("is_show_user =", value, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserNotEqualTo(Integer value) {
            addCriterion("is_show_user <>", value, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserGreaterThan(Integer value) {
            addCriterion("is_show_user >", value, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_show_user >=", value, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserLessThan(Integer value) {
            addCriterion("is_show_user <", value, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserLessThanOrEqualTo(Integer value) {
            addCriterion("is_show_user <=", value, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserIn(List<Integer> values) {
            addCriterion("is_show_user in", values, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserNotIn(List<Integer> values) {
            addCriterion("is_show_user not in", values, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserBetween(Integer value1, Integer value2) {
            addCriterion("is_show_user between", value1, value2, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andIsShowUserNotBetween(Integer value1, Integer value2) {
            addCriterion("is_show_user not between", value1, value2, "isShowUser");
            return (Criteria) this;
        }

        public Criteria andRemarkCusIsNull() {
            addCriterion("remark_cus is null");
            return (Criteria) this;
        }

        public Criteria andRemarkCusIsNotNull() {
            addCriterion("remark_cus is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkCusEqualTo(String value) {
            addCriterion("remark_cus =", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusNotEqualTo(String value) {
            addCriterion("remark_cus <>", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusGreaterThan(String value) {
            addCriterion("remark_cus >", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusGreaterThanOrEqualTo(String value) {
            addCriterion("remark_cus >=", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusLessThan(String value) {
            addCriterion("remark_cus <", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusLessThanOrEqualTo(String value) {
            addCriterion("remark_cus <=", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusLike(String value) {
            addCriterion("remark_cus like", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusNotLike(String value) {
            addCriterion("remark_cus not like", value, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusIn(List<String> values) {
            addCriterion("remark_cus in", values, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusNotIn(List<String> values) {
            addCriterion("remark_cus not in", values, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusBetween(String value1, String value2) {
            addCriterion("remark_cus between", value1, value2, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkCusNotBetween(String value1, String value2) {
            addCriterion("remark_cus not between", value1, value2, "remarkCus");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
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