package com.ai.gitai.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MergeRequestExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MergeRequestExample() {
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

    @Override
    public String toString() {
        return "MergeRequestExample{" +
                "orderByClause='" + orderByClause + '\'' +
                ", distinct=" + distinct +
                ", oredCriteria=" + oredCriteria +
                '}';
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

        @Override
        public String toString() {
            return "GeneratedCriteria{" +
                    "criteria=" + criteria +
                    '}';
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

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andMergeRequestIidIsNull() {
            addCriterion("merge_request_iid is null");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidIsNotNull() {
            addCriterion("merge_request_iid is not null");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidEqualTo(Integer value) {
            addCriterion("merge_request_iid =", value, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidNotEqualTo(Integer value) {
            addCriterion("merge_request_iid <>", value, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidGreaterThan(Integer value) {
            addCriterion("merge_request_iid >", value, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidGreaterThanOrEqualTo(Integer value) {
            addCriterion("merge_request_iid >=", value, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidLessThan(Integer value) {
            addCriterion("merge_request_iid <", value, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidLessThanOrEqualTo(Integer value) {
            addCriterion("merge_request_iid <=", value, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidIn(List<Integer> values) {
            addCriterion("merge_request_iid in", values, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidNotIn(List<Integer> values) {
            addCriterion("merge_request_iid not in", values, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidBetween(Integer value1, Integer value2) {
            addCriterion("merge_request_iid between", value1, value2, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andMergeRequestIidNotBetween(Integer value1, Integer value2) {
            addCriterion("merge_request_iid not between", value1, value2, "mergeRequestIid");
            return (Criteria) this;
        }

        public Criteria andSourceBranchIsNull() {
            addCriterion("source_branch is null");
            return (Criteria) this;
        }

        public Criteria andSourceBranchIsNotNull() {
            addCriterion("source_branch is not null");
            return (Criteria) this;
        }

        public Criteria andSourceBranchEqualTo(String value) {
            addCriterion("source_branch =", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchNotEqualTo(String value) {
            addCriterion("source_branch <>", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchGreaterThan(String value) {
            addCriterion("source_branch >", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchGreaterThanOrEqualTo(String value) {
            addCriterion("source_branch >=", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchLessThan(String value) {
            addCriterion("source_branch <", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchLessThanOrEqualTo(String value) {
            addCriterion("source_branch <=", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchLike(String value) {
            addCriterion("source_branch like", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchNotLike(String value) {
            addCriterion("source_branch not like", value, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchIn(List<String> values) {
            addCriterion("source_branch in", values, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchNotIn(List<String> values) {
            addCriterion("source_branch not in", values, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchBetween(String value1, String value2) {
            addCriterion("source_branch between", value1, value2, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andSourceBranchNotBetween(String value1, String value2) {
            addCriterion("source_branch not between", value1, value2, "sourceBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchIsNull() {
            addCriterion("target_branch is null");
            return (Criteria) this;
        }

        public Criteria andTargetBranchIsNotNull() {
            addCriterion("target_branch is not null");
            return (Criteria) this;
        }

        public Criteria andTargetBranchEqualTo(String value) {
            addCriterion("target_branch =", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchNotEqualTo(String value) {
            addCriterion("target_branch <>", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchGreaterThan(String value) {
            addCriterion("target_branch >", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchGreaterThanOrEqualTo(String value) {
            addCriterion("target_branch >=", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchLessThan(String value) {
            addCriterion("target_branch <", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchLessThanOrEqualTo(String value) {
            addCriterion("target_branch <=", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchLike(String value) {
            addCriterion("target_branch like", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchNotLike(String value) {
            addCriterion("target_branch not like", value, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchIn(List<String> values) {
            addCriterion("target_branch in", values, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchNotIn(List<String> values) {
            addCriterion("target_branch not in", values, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchBetween(String value1, String value2) {
            addCriterion("target_branch between", value1, value2, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andTargetBranchNotBetween(String value1, String value2) {
            addCriterion("target_branch not between", value1, value2, "targetBranch");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserIsNull() {
            addCriterion("merge_user is null");
            return (Criteria) this;
        }

        public Criteria andMergeUserIsNotNull() {
            addCriterion("merge_user is not null");
            return (Criteria) this;
        }

        public Criteria andMergeUserEqualTo(String value) {
            addCriterion("merge_user =", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserNotEqualTo(String value) {
            addCriterion("merge_user <>", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserGreaterThan(String value) {
            addCriterion("merge_user >", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserGreaterThanOrEqualTo(String value) {
            addCriterion("merge_user >=", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserLessThan(String value) {
            addCriterion("merge_user <", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserLessThanOrEqualTo(String value) {
            addCriterion("merge_user <=", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserLike(String value) {
            addCriterion("merge_user like", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserNotLike(String value) {
            addCriterion("merge_user not like", value, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserIn(List<String> values) {
            addCriterion("merge_user in", values, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserNotIn(List<String> values) {
            addCriterion("merge_user not in", values, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserBetween(String value1, String value2) {
            addCriterion("merge_user between", value1, value2, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andMergeUserNotBetween(String value1, String value2) {
            addCriterion("merge_user not between", value1, value2, "mergeUser");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
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

        @Override
        public String toString() {
            return "Criterion{" +
                    "condition='" + condition + '\'' +
                    ", value=" + value +
                    ", secondValue=" + secondValue +
                    ", noValue=" + noValue +
                    ", singleValue=" + singleValue +
                    ", betweenValue=" + betweenValue +
                    ", listValue=" + listValue +
                    ", typeHandler='" + typeHandler + '\'' +
                    '}';
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