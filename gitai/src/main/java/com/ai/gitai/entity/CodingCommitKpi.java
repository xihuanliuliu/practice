package com.ai.gitai.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.Serializable;
import java.util.Date;

public class CodingCommitKpi implements Serializable {
	@JsonSerialize(using= ToStringSerializer.class)
    private Long commitKpiId;

    private String commitId;

    private String authorName;

    private String authorEmail;

    private Long repositoryId;

    private String branchName;

    private String commitMessage;

    private Integer commitLines;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date commitTime;

    private Integer additions;

    private Integer deletions;

    private String updateMessage;
    
    private static final long serialVersionUID = 1L;
    
    public String getUpdateMessage() {
		return updateMessage;
	}

	public void setUpdateMessage(String updateMessage) {
		this.updateMessage = updateMessage;
	}

	public Long getCommitKpiId() {
        return commitKpiId;
    }

    public void setCommitKpiId(Long commitKpiId) {
        this.commitKpiId = commitKpiId;
    }

    public String getCommitId() {
        return commitId;
    }

    public void setCommitId(String commitId) {
        this.commitId = commitId == null ? null : commitId.trim();
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail == null ? null : authorEmail.trim();
    }

    public Long getRepositoryId() {
        return repositoryId;
    }

    public void setRepositoryId(Long repositoryId) {
        this.repositoryId = repositoryId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName == null ? null : branchName.trim();
    }

    public String getCommitMessage() {
        return commitMessage;
    }

    public void setCommitMessage(String commitMessage) {
        this.commitMessage = commitMessage == null ? null : commitMessage.trim();
    }

    public Integer getCommitLines() {
        return commitLines;
    }

    public void setCommitLines(Integer commitLines) {
        this.commitLines = commitLines;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public Integer getAdditions() {
        return additions;
    }

    public void setAdditions(Integer additions) {
        this.additions = additions;
    }

    public Integer getDeletions() {
        return deletions;
    }

    public void setDeletions(Integer deletions) {
        this.deletions = deletions;
    }
}