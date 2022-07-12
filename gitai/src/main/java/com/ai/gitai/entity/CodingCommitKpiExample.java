package com.ai.gitai.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CodingCommitKpiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CodingCommitKpiExample() {
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

        public Criteria andCommitKpiIdIsNull() {
            addCriterion("commit_kpi_id is null");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdIsNotNull() {
            addCriterion("commit_kpi_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdEqualTo(Long value) {
            addCriterion("commit_kpi_id =", value, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdNotEqualTo(Long value) {
            addCriterion("commit_kpi_id <>", value, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdGreaterThan(Long value) {
            addCriterion("commit_kpi_id >", value, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdGreaterThanOrEqualTo(Long value) {
            addCriterion("commit_kpi_id >=", value, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdLessThan(Long value) {
            addCriterion("commit_kpi_id <", value, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdLessThanOrEqualTo(Long value) {
            addCriterion("commit_kpi_id <=", value, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdIn(List<Long> values) {
            addCriterion("commit_kpi_id in", values, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdNotIn(List<Long> values) {
            addCriterion("commit_kpi_id not in", values, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdBetween(Long value1, Long value2) {
            addCriterion("commit_kpi_id between", value1, value2, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitKpiIdNotBetween(Long value1, Long value2) {
            addCriterion("commit_kpi_id not between", value1, value2, "commitKpiId");
            return (Criteria) this;
        }

        public Criteria andCommitIdIsNull() {
            addCriterion("commit_id is null");
            return (Criteria) this;
        }

        public Criteria andCommitIdIsNotNull() {
            addCriterion("commit_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommitIdEqualTo(String value) {
            addCriterion("commit_id =", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdNotEqualTo(String value) {
            addCriterion("commit_id <>", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdGreaterThan(String value) {
            addCriterion("commit_id >", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdGreaterThanOrEqualTo(String value) {
            addCriterion("commit_id >=", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdLessThan(String value) {
            addCriterion("commit_id <", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdLessThanOrEqualTo(String value) {
            addCriterion("commit_id <=", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdLike(String value) {
            addCriterion("commit_id like", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdNotLike(String value) {
            addCriterion("commit_id not like", value, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdIn(List<String> values) {
            addCriterion("commit_id in", values, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdNotIn(List<String> values) {
            addCriterion("commit_id not in", values, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdBetween(String value1, String value2) {
            addCriterion("commit_id between", value1, value2, "commitId");
            return (Criteria) this;
        }

        public Criteria andCommitIdNotBetween(String value1, String value2) {
            addCriterion("commit_id not between", value1, value2, "commitId");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNull() {
            addCriterion("author_name is null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIsNotNull() {
            addCriterion("author_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorNameEqualTo(String value) {
            addCriterion("author_name =", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotEqualTo(String value) {
            addCriterion("author_name <>", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThan(String value) {
            addCriterion("author_name >", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameGreaterThanOrEqualTo(String value) {
            addCriterion("author_name >=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThan(String value) {
            addCriterion("author_name <", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLessThanOrEqualTo(String value) {
            addCriterion("author_name <=", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameLike(String value) {
            addCriterion("author_name like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotLike(String value) {
            addCriterion("author_name not like", value, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameIn(List<String> values) {
            addCriterion("author_name in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotIn(List<String> values) {
            addCriterion("author_name not in", values, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameBetween(String value1, String value2) {
            addCriterion("author_name between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorNameNotBetween(String value1, String value2) {
            addCriterion("author_name not between", value1, value2, "authorName");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailIsNull() {
            addCriterion("author_email is null");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailIsNotNull() {
            addCriterion("author_email is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailEqualTo(String value) {
            addCriterion("author_email =", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailNotEqualTo(String value) {
            addCriterion("author_email <>", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailGreaterThan(String value) {
            addCriterion("author_email >", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailGreaterThanOrEqualTo(String value) {
            addCriterion("author_email >=", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailLessThan(String value) {
            addCriterion("author_email <", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailLessThanOrEqualTo(String value) {
            addCriterion("author_email <=", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailLike(String value) {
            addCriterion("author_email like", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailNotLike(String value) {
            addCriterion("author_email not like", value, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailIn(List<String> values) {
            addCriterion("author_email in", values, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailNotIn(List<String> values) {
            addCriterion("author_email not in", values, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailBetween(String value1, String value2) {
            addCriterion("author_email between", value1, value2, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andAuthorEmailNotBetween(String value1, String value2) {
            addCriterion("author_email not between", value1, value2, "authorEmail");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdIsNull() {
            addCriterion("repository_id is null");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdIsNotNull() {
            addCriterion("repository_id is not null");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdEqualTo(Long value) {
            addCriterion("repository_id =", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdNotEqualTo(Long value) {
            addCriterion("repository_id <>", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdGreaterThan(Long value) {
            addCriterion("repository_id >", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("repository_id >=", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdLessThan(Long value) {
            addCriterion("repository_id <", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdLessThanOrEqualTo(Long value) {
            addCriterion("repository_id <=", value, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdIn(List<Long> values) {
            addCriterion("repository_id in", values, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdNotIn(List<Long> values) {
            addCriterion("repository_id not in", values, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdBetween(Long value1, Long value2) {
            addCriterion("repository_id between", value1, value2, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andRepositoryIdNotBetween(Long value1, Long value2) {
            addCriterion("repository_id not between", value1, value2, "repositoryId");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNull() {
            addCriterion("branch_name is null");
            return (Criteria) this;
        }

        public Criteria andBranchNameIsNotNull() {
            addCriterion("branch_name is not null");
            return (Criteria) this;
        }

        public Criteria andBranchNameEqualTo(String value) {
            addCriterion("branch_name =", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotEqualTo(String value) {
            addCriterion("branch_name <>", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThan(String value) {
            addCriterion("branch_name >", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameGreaterThanOrEqualTo(String value) {
            addCriterion("branch_name >=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThan(String value) {
            addCriterion("branch_name <", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLessThanOrEqualTo(String value) {
            addCriterion("branch_name <=", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameLike(String value) {
            addCriterion("branch_name like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotLike(String value) {
            addCriterion("branch_name not like", value, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameIn(List<String> values) {
            addCriterion("branch_name in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotIn(List<String> values) {
            addCriterion("branch_name not in", values, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameBetween(String value1, String value2) {
            addCriterion("branch_name between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andBranchNameNotBetween(String value1, String value2) {
            addCriterion("branch_name not between", value1, value2, "branchName");
            return (Criteria) this;
        }

        public Criteria andCommitMessageIsNull() {
            addCriterion("commit_message is null");
            return (Criteria) this;
        }

        public Criteria andCommitMessageIsNotNull() {
            addCriterion("commit_message is not null");
            return (Criteria) this;
        }

        public Criteria andCommitMessageEqualTo(String value) {
            addCriterion("commit_message =", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageNotEqualTo(String value) {
            addCriterion("commit_message <>", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageGreaterThan(String value) {
            addCriterion("commit_message >", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageGreaterThanOrEqualTo(String value) {
            addCriterion("commit_message >=", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageLessThan(String value) {
            addCriterion("commit_message <", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageLessThanOrEqualTo(String value) {
            addCriterion("commit_message <=", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageLike(String value) {
            addCriterion("commit_message like", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageNotLike(String value) {
            addCriterion("commit_message not like", value, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageIn(List<String> values) {
            addCriterion("commit_message in", values, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageNotIn(List<String> values) {
            addCriterion("commit_message not in", values, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageBetween(String value1, String value2) {
            addCriterion("commit_message between", value1, value2, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitMessageNotBetween(String value1, String value2) {
            addCriterion("commit_message not between", value1, value2, "commitMessage");
            return (Criteria) this;
        }

        public Criteria andCommitLinesIsNull() {
            addCriterion("commit_lines is null");
            return (Criteria) this;
        }

        public Criteria andCommitLinesIsNotNull() {
            addCriterion("commit_lines is not null");
            return (Criteria) this;
        }

        public Criteria andCommitLinesEqualTo(Integer value) {
            addCriterion("commit_lines =", value, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesNotEqualTo(Integer value) {
            addCriterion("commit_lines <>", value, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesGreaterThan(Integer value) {
            addCriterion("commit_lines >", value, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesGreaterThanOrEqualTo(Integer value) {
            addCriterion("commit_lines >=", value, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesLessThan(Integer value) {
            addCriterion("commit_lines <", value, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesLessThanOrEqualTo(Integer value) {
            addCriterion("commit_lines <=", value, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesIn(List<Integer> values) {
            addCriterion("commit_lines in", values, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesNotIn(List<Integer> values) {
            addCriterion("commit_lines not in", values, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesBetween(Integer value1, Integer value2) {
            addCriterion("commit_lines between", value1, value2, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitLinesNotBetween(Integer value1, Integer value2) {
            addCriterion("commit_lines not between", value1, value2, "commitLines");
            return (Criteria) this;
        }

        public Criteria andCommitTimeIsNull() {
            addCriterion("commit_time is null");
            return (Criteria) this;
        }

        public Criteria andCommitTimeIsNotNull() {
            addCriterion("commit_time is not null");
            return (Criteria) this;
        }

        public Criteria andCommitTimeEqualTo(Date value) {
            addCriterion("commit_time =", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeNotEqualTo(Date value) {
            addCriterion("commit_time <>", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeGreaterThan(Date value) {
            addCriterion("commit_time >", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("commit_time >=", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeLessThan(Date value) {
            addCriterion("commit_time <", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeLessThanOrEqualTo(Date value) {
            addCriterion("commit_time <=", value, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeIn(List<Date> values) {
            addCriterion("commit_time in", values, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeNotIn(List<Date> values) {
            addCriterion("commit_time not in", values, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeBetween(Date value1, Date value2) {
            addCriterion("commit_time between", value1, value2, "commitTime");
            return (Criteria) this;
        }

        public Criteria andCommitTimeNotBetween(Date value1, Date value2) {
            addCriterion("commit_time not between", value1, value2, "commitTime");
            return (Criteria) this;
        }

        public Criteria andAdditionsIsNull() {
            addCriterion("additions is null");
            return (Criteria) this;
        }

        public Criteria andAdditionsIsNotNull() {
            addCriterion("additions is not null");
            return (Criteria) this;
        }

        public Criteria andAdditionsEqualTo(Integer value) {
            addCriterion("additions =", value, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsNotEqualTo(Integer value) {
            addCriterion("additions <>", value, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsGreaterThan(Integer value) {
            addCriterion("additions >", value, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsGreaterThanOrEqualTo(Integer value) {
            addCriterion("additions >=", value, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsLessThan(Integer value) {
            addCriterion("additions <", value, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsLessThanOrEqualTo(Integer value) {
            addCriterion("additions <=", value, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsIn(List<Integer> values) {
            addCriterion("additions in", values, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsNotIn(List<Integer> values) {
            addCriterion("additions not in", values, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsBetween(Integer value1, Integer value2) {
            addCriterion("additions between", value1, value2, "additions");
            return (Criteria) this;
        }

        public Criteria andAdditionsNotBetween(Integer value1, Integer value2) {
            addCriterion("additions not between", value1, value2, "additions");
            return (Criteria) this;
        }

        public Criteria andDeletionsIsNull() {
            addCriterion("deletions is null");
            return (Criteria) this;
        }

        public Criteria andDeletionsIsNotNull() {
            addCriterion("deletions is not null");
            return (Criteria) this;
        }

        public Criteria andDeletionsEqualTo(Integer value) {
            addCriterion("deletions =", value, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsNotEqualTo(Integer value) {
            addCriterion("deletions <>", value, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsGreaterThan(Integer value) {
            addCriterion("deletions >", value, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsGreaterThanOrEqualTo(Integer value) {
            addCriterion("deletions >=", value, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsLessThan(Integer value) {
            addCriterion("deletions <", value, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsLessThanOrEqualTo(Integer value) {
            addCriterion("deletions <=", value, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsIn(List<Integer> values) {
            addCriterion("deletions in", values, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsNotIn(List<Integer> values) {
            addCriterion("deletions not in", values, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsBetween(Integer value1, Integer value2) {
            addCriterion("deletions between", value1, value2, "deletions");
            return (Criteria) this;
        }

        public Criteria andDeletionsNotBetween(Integer value1, Integer value2) {
            addCriterion("deletions not between", value1, value2, "deletions");
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