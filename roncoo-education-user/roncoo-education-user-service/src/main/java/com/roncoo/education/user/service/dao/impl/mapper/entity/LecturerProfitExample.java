package com.roncoo.education.user.service.dao.impl.mapper.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LecturerProfitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected int limitStart = -1;

    protected int pageSize = -1;

    public LecturerProfitExample() {
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

        public Criteria andBankCardNoIsNull() {
            addCriterion("bank_card_no is null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIsNotNull() {
            addCriterion("bank_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankCardNoEqualTo(String value) {
            addCriterion("bank_card_no =", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotEqualTo(String value) {
            addCriterion("bank_card_no <>", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThan(String value) {
            addCriterion("bank_card_no >", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_card_no >=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThan(String value) {
            addCriterion("bank_card_no <", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLessThanOrEqualTo(String value) {
            addCriterion("bank_card_no <=", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoLike(String value) {
            addCriterion("bank_card_no like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotLike(String value) {
            addCriterion("bank_card_no not like", value, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoIn(List<String> values) {
            addCriterion("bank_card_no in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotIn(List<String> values) {
            addCriterion("bank_card_no not in", values, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoBetween(String value1, String value2) {
            addCriterion("bank_card_no between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankCardNoNotBetween(String value1, String value2) {
            addCriterion("bank_card_no not between", value1, value2, "bankCardNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameIsNull() {
            addCriterion("bank_branch_name is null");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameIsNotNull() {
            addCriterion("bank_branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameEqualTo(String value) {
            addCriterion("bank_branch_name =", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameNotEqualTo(String value) {
            addCriterion("bank_branch_name <>", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameGreaterThan(String value) {
            addCriterion("bank_branch_name >", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_branch_name >=", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameLessThan(String value) {
            addCriterion("bank_branch_name <", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameLessThanOrEqualTo(String value) {
            addCriterion("bank_branch_name <=", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameLike(String value) {
            addCriterion("bank_branch_name like", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameNotLike(String value) {
            addCriterion("bank_branch_name not like", value, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameIn(List<String> values) {
            addCriterion("bank_branch_name in", values, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameNotIn(List<String> values) {
            addCriterion("bank_branch_name not in", values, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameBetween(String value1, String value2) {
            addCriterion("bank_branch_name between", value1, value2, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankBranchNameNotBetween(String value1, String value2) {
            addCriterion("bank_branch_name not between", value1, value2, "bankBranchName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameIsNull() {
            addCriterion("bank_user_name is null");
            return (Criteria) this;
        }

        public Criteria andBankUserNameIsNotNull() {
            addCriterion("bank_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankUserNameEqualTo(String value) {
            addCriterion("bank_user_name =", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotEqualTo(String value) {
            addCriterion("bank_user_name <>", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameGreaterThan(String value) {
            addCriterion("bank_user_name >", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_user_name >=", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameLessThan(String value) {
            addCriterion("bank_user_name <", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameLessThanOrEqualTo(String value) {
            addCriterion("bank_user_name <=", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameLike(String value) {
            addCriterion("bank_user_name like", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotLike(String value) {
            addCriterion("bank_user_name not like", value, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameIn(List<String> values) {
            addCriterion("bank_user_name in", values, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotIn(List<String> values) {
            addCriterion("bank_user_name not in", values, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameBetween(String value1, String value2) {
            addCriterion("bank_user_name between", value1, value2, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankUserNameNotBetween(String value1, String value2) {
            addCriterion("bank_user_name not between", value1, value2, "bankUserName");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoIsNull() {
            addCriterion("bank_id_card_no is null");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoIsNotNull() {
            addCriterion("bank_id_card_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoEqualTo(String value) {
            addCriterion("bank_id_card_no =", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoNotEqualTo(String value) {
            addCriterion("bank_id_card_no <>", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoGreaterThan(String value) {
            addCriterion("bank_id_card_no >", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_id_card_no >=", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoLessThan(String value) {
            addCriterion("bank_id_card_no <", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoLessThanOrEqualTo(String value) {
            addCriterion("bank_id_card_no <=", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoLike(String value) {
            addCriterion("bank_id_card_no like", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoNotLike(String value) {
            addCriterion("bank_id_card_no not like", value, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoIn(List<String> values) {
            addCriterion("bank_id_card_no in", values, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoNotIn(List<String> values) {
            addCriterion("bank_id_card_no not in", values, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoBetween(String value1, String value2) {
            addCriterion("bank_id_card_no between", value1, value2, "bankIdCardNo");
            return (Criteria) this;
        }

        public Criteria andBankIdCardNoNotBetween(String value1, String value2) {
            addCriterion("bank_id_card_no not between", value1, value2, "bankIdCardNo");
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

        public Criteria andProfitStatusIsNull() {
            addCriterion("profit_status is null");
            return (Criteria) this;
        }

        public Criteria andProfitStatusIsNotNull() {
            addCriterion("profit_status is not null");
            return (Criteria) this;
        }

        public Criteria andProfitStatusEqualTo(Integer value) {
            addCriterion("profit_status =", value, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusNotEqualTo(Integer value) {
            addCriterion("profit_status <>", value, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusGreaterThan(Integer value) {
            addCriterion("profit_status >", value, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("profit_status >=", value, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusLessThan(Integer value) {
            addCriterion("profit_status <", value, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusLessThanOrEqualTo(Integer value) {
            addCriterion("profit_status <=", value, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusIn(List<Integer> values) {
            addCriterion("profit_status in", values, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusNotIn(List<Integer> values) {
            addCriterion("profit_status not in", values, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusBetween(Integer value1, Integer value2) {
            addCriterion("profit_status between", value1, value2, "profitStatus");
            return (Criteria) this;
        }

        public Criteria andProfitStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("profit_status not between", value1, value2, "profitStatus");
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