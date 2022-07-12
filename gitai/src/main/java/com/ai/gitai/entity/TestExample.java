package com.ai.gitai.entity;

import java.util.ArrayList;
import java.util.List;

public class TestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestExample() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andBuNameIsNull() {
            addCriterion("bu_name is null");
            return (Criteria) this;
        }

        public Criteria andBuNameIsNotNull() {
            addCriterion("bu_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuNameEqualTo(String value) {
            addCriterion("bu_name =", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameNotEqualTo(String value) {
            addCriterion("bu_name <>", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameGreaterThan(String value) {
            addCriterion("bu_name >", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameGreaterThanOrEqualTo(String value) {
            addCriterion("bu_name >=", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameLessThan(String value) {
            addCriterion("bu_name <", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameLessThanOrEqualTo(String value) {
            addCriterion("bu_name <=", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameLike(String value) {
            addCriterion("bu_name like", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameNotLike(String value) {
            addCriterion("bu_name not like", value, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameIn(List<String> values) {
            addCriterion("bu_name in", values, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameNotIn(List<String> values) {
            addCriterion("bu_name not in", values, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameBetween(String value1, String value2) {
            addCriterion("bu_name between", value1, value2, "buName");
            return (Criteria) this;
        }

        public Criteria andBuNameNotBetween(String value1, String value2) {
            addCriterion("bu_name not between", value1, value2, "buName");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNull() {
            addCriterion("proj_name is null");
            return (Criteria) this;
        }

        public Criteria andProjNameIsNotNull() {
            addCriterion("proj_name is not null");
            return (Criteria) this;
        }

        public Criteria andProjNameEqualTo(String value) {
            addCriterion("proj_name =", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotEqualTo(String value) {
            addCriterion("proj_name <>", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThan(String value) {
            addCriterion("proj_name >", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameGreaterThanOrEqualTo(String value) {
            addCriterion("proj_name >=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThan(String value) {
            addCriterion("proj_name <", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLessThanOrEqualTo(String value) {
            addCriterion("proj_name <=", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameLike(String value) {
            addCriterion("proj_name like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotLike(String value) {
            addCriterion("proj_name not like", value, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameIn(List<String> values) {
            addCriterion("proj_name in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotIn(List<String> values) {
            addCriterion("proj_name not in", values, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameBetween(String value1, String value2) {
            addCriterion("proj_name between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjNameNotBetween(String value1, String value2) {
            addCriterion("proj_name not between", value1, value2, "projName");
            return (Criteria) this;
        }

        public Criteria andProjIdIsNull() {
            addCriterion("proj_id is null");
            return (Criteria) this;
        }

        public Criteria andProjIdIsNotNull() {
            addCriterion("proj_id is not null");
            return (Criteria) this;
        }

        public Criteria andProjIdEqualTo(Long value) {
            addCriterion("proj_id =", value, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdNotEqualTo(Long value) {
            addCriterion("proj_id <>", value, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdGreaterThan(Long value) {
            addCriterion("proj_id >", value, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdGreaterThanOrEqualTo(Long value) {
            addCriterion("proj_id >=", value, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdLessThan(Long value) {
            addCriterion("proj_id <", value, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdLessThanOrEqualTo(Long value) {
            addCriterion("proj_id <=", value, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdIn(List<Long> values) {
            addCriterion("proj_id in", values, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdNotIn(List<Long> values) {
            addCriterion("proj_id not in", values, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdBetween(Long value1, Long value2) {
            addCriterion("proj_id between", value1, value2, "projId");
            return (Criteria) this;
        }

        public Criteria andProjIdNotBetween(Long value1, Long value2) {
            addCriterion("proj_id not between", value1, value2, "projId");
            return (Criteria) this;
        }

        public Criteria andProjLevelIsNull() {
            addCriterion("proj_level is null");
            return (Criteria) this;
        }

        public Criteria andProjLevelIsNotNull() {
            addCriterion("proj_level is not null");
            return (Criteria) this;
        }

        public Criteria andProjLevelEqualTo(String value) {
            addCriterion("proj_level =", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelNotEqualTo(String value) {
            addCriterion("proj_level <>", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelGreaterThan(String value) {
            addCriterion("proj_level >", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelGreaterThanOrEqualTo(String value) {
            addCriterion("proj_level >=", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelLessThan(String value) {
            addCriterion("proj_level <", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelLessThanOrEqualTo(String value) {
            addCriterion("proj_level <=", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelLike(String value) {
            addCriterion("proj_level like", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelNotLike(String value) {
            addCriterion("proj_level not like", value, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelIn(List<String> values) {
            addCriterion("proj_level in", values, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelNotIn(List<String> values) {
            addCriterion("proj_level not in", values, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelBetween(String value1, String value2) {
            addCriterion("proj_level between", value1, value2, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjLevelNotBetween(String value1, String value2) {
            addCriterion("proj_level not between", value1, value2, "projLevel");
            return (Criteria) this;
        }

        public Criteria andProjTimeIsNull() {
            addCriterion("proj_time is null");
            return (Criteria) this;
        }

        public Criteria andProjTimeIsNotNull() {
            addCriterion("proj_time is not null");
            return (Criteria) this;
        }

        public Criteria andProjTimeEqualTo(String value) {
            addCriterion("proj_time =", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeNotEqualTo(String value) {
            addCriterion("proj_time <>", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeGreaterThan(String value) {
            addCriterion("proj_time >", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeGreaterThanOrEqualTo(String value) {
            addCriterion("proj_time >=", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeLessThan(String value) {
            addCriterion("proj_time <", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeLessThanOrEqualTo(String value) {
            addCriterion("proj_time <=", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeLike(String value) {
            addCriterion("proj_time like", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeNotLike(String value) {
            addCriterion("proj_time not like", value, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeIn(List<String> values) {
            addCriterion("proj_time in", values, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeNotIn(List<String> values) {
            addCriterion("proj_time not in", values, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeBetween(String value1, String value2) {
            addCriterion("proj_time between", value1, value2, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjTimeNotBetween(String value1, String value2) {
            addCriterion("proj_time not between", value1, value2, "projTime");
            return (Criteria) this;
        }

        public Criteria andProjStatusIsNull() {
            addCriterion("proj_status is null");
            return (Criteria) this;
        }

        public Criteria andProjStatusIsNotNull() {
            addCriterion("proj_status is not null");
            return (Criteria) this;
        }

        public Criteria andProjStatusEqualTo(String value) {
            addCriterion("proj_status =", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusNotEqualTo(String value) {
            addCriterion("proj_status <>", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusGreaterThan(String value) {
            addCriterion("proj_status >", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusGreaterThanOrEqualTo(String value) {
            addCriterion("proj_status >=", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusLessThan(String value) {
            addCriterion("proj_status <", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusLessThanOrEqualTo(String value) {
            addCriterion("proj_status <=", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusLike(String value) {
            addCriterion("proj_status like", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusNotLike(String value) {
            addCriterion("proj_status not like", value, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusIn(List<String> values) {
            addCriterion("proj_status in", values, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusNotIn(List<String> values) {
            addCriterion("proj_status not in", values, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusBetween(String value1, String value2) {
            addCriterion("proj_status between", value1, value2, "projStatus");
            return (Criteria) this;
        }

        public Criteria andProjStatusNotBetween(String value1, String value2) {
            addCriterion("proj_status not between", value1, value2, "projStatus");
            return (Criteria) this;
        }

        public Criteria andRepoNameIsNull() {
            addCriterion("repo_name is null");
            return (Criteria) this;
        }

        public Criteria andRepoNameIsNotNull() {
            addCriterion("repo_name is not null");
            return (Criteria) this;
        }

        public Criteria andRepoNameEqualTo(String value) {
            addCriterion("repo_name =", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameNotEqualTo(String value) {
            addCriterion("repo_name <>", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameGreaterThan(String value) {
            addCriterion("repo_name >", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameGreaterThanOrEqualTo(String value) {
            addCriterion("repo_name >=", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameLessThan(String value) {
            addCriterion("repo_name <", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameLessThanOrEqualTo(String value) {
            addCriterion("repo_name <=", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameLike(String value) {
            addCriterion("repo_name like", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameNotLike(String value) {
            addCriterion("repo_name not like", value, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameIn(List<String> values) {
            addCriterion("repo_name in", values, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameNotIn(List<String> values) {
            addCriterion("repo_name not in", values, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameBetween(String value1, String value2) {
            addCriterion("repo_name between", value1, value2, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoNameNotBetween(String value1, String value2) {
            addCriterion("repo_name not between", value1, value2, "repoName");
            return (Criteria) this;
        }

        public Criteria andRepoUrlIsNull() {
            addCriterion("repo_url is null");
            return (Criteria) this;
        }

        public Criteria andRepoUrlIsNotNull() {
            addCriterion("repo_url is not null");
            return (Criteria) this;
        }

        public Criteria andRepoUrlEqualTo(String value) {
            addCriterion("repo_url =", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlNotEqualTo(String value) {
            addCriterion("repo_url <>", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlGreaterThan(String value) {
            addCriterion("repo_url >", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlGreaterThanOrEqualTo(String value) {
            addCriterion("repo_url >=", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlLessThan(String value) {
            addCriterion("repo_url <", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlLessThanOrEqualTo(String value) {
            addCriterion("repo_url <=", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlLike(String value) {
            addCriterion("repo_url like", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlNotLike(String value) {
            addCriterion("repo_url not like", value, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlIn(List<String> values) {
            addCriterion("repo_url in", values, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlNotIn(List<String> values) {
            addCriterion("repo_url not in", values, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlBetween(String value1, String value2) {
            addCriterion("repo_url between", value1, value2, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoUrlNotBetween(String value1, String value2) {
            addCriterion("repo_url not between", value1, value2, "repoUrl");
            return (Criteria) this;
        }

        public Criteria andRepoTimeIsNull() {
            addCriterion("repo_time is null");
            return (Criteria) this;
        }

        public Criteria andRepoTimeIsNotNull() {
            addCriterion("repo_time is not null");
            return (Criteria) this;
        }

        public Criteria andRepoTimeEqualTo(String value) {
            addCriterion("repo_time =", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeNotEqualTo(String value) {
            addCriterion("repo_time <>", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeGreaterThan(String value) {
            addCriterion("repo_time >", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeGreaterThanOrEqualTo(String value) {
            addCriterion("repo_time >=", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeLessThan(String value) {
            addCriterion("repo_time <", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeLessThanOrEqualTo(String value) {
            addCriterion("repo_time <=", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeLike(String value) {
            addCriterion("repo_time like", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeNotLike(String value) {
            addCriterion("repo_time not like", value, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeIn(List<String> values) {
            addCriterion("repo_time in", values, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeNotIn(List<String> values) {
            addCriterion("repo_time not in", values, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeBetween(String value1, String value2) {
            addCriterion("repo_time between", value1, value2, "repoTime");
            return (Criteria) this;
        }

        public Criteria andRepoTimeNotBetween(String value1, String value2) {
            addCriterion("repo_time not between", value1, value2, "repoTime");
            return (Criteria) this;
        }

        public Criteria andActiveIsNull() {
            addCriterion("active is null");
            return (Criteria) this;
        }

        public Criteria andActiveIsNotNull() {
            addCriterion("active is not null");
            return (Criteria) this;
        }

        public Criteria andActiveEqualTo(String value) {
            addCriterion("active =", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotEqualTo(String value) {
            addCriterion("active <>", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThan(String value) {
            addCriterion("active >", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveGreaterThanOrEqualTo(String value) {
            addCriterion("active >=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThan(String value) {
            addCriterion("active <", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLessThanOrEqualTo(String value) {
            addCriterion("active <=", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveLike(String value) {
            addCriterion("active like", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotLike(String value) {
            addCriterion("active not like", value, "active");
            return (Criteria) this;
        }

        public Criteria andActiveIn(List<String> values) {
            addCriterion("active in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotIn(List<String> values) {
            addCriterion("active not in", values, "active");
            return (Criteria) this;
        }

        public Criteria andActiveBetween(String value1, String value2) {
            addCriterion("active between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andActiveNotBetween(String value1, String value2) {
            addCriterion("active not between", value1, value2, "active");
            return (Criteria) this;
        }

        public Criteria andTotalCommitIsNull() {
            addCriterion("total_commit is null");
            return (Criteria) this;
        }

        public Criteria andTotalCommitIsNotNull() {
            addCriterion("total_commit is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCommitEqualTo(Long value) {
            addCriterion("total_commit =", value, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitNotEqualTo(Long value) {
            addCriterion("total_commit <>", value, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitGreaterThan(Long value) {
            addCriterion("total_commit >", value, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitGreaterThanOrEqualTo(Long value) {
            addCriterion("total_commit >=", value, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitLessThan(Long value) {
            addCriterion("total_commit <", value, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitLessThanOrEqualTo(Long value) {
            addCriterion("total_commit <=", value, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitIn(List<Long> values) {
            addCriterion("total_commit in", values, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitNotIn(List<Long> values) {
            addCriterion("total_commit not in", values, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitBetween(Long value1, Long value2) {
            addCriterion("total_commit between", value1, value2, "totalCommit");
            return (Criteria) this;
        }

        public Criteria andTotalCommitNotBetween(Long value1, Long value2) {
            addCriterion("total_commit not between", value1, value2, "totalCommit");
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