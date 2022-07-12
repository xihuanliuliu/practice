package com.jd.edi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class Test implements Serializable {
    private String code;

    private String buName;

    private String projName;

    private Long projId;

    private String projLevel;

    private String projTime;

    private String projStatus;

    private String repoName;

    private String repoUrl;

    private String repoTime;

    private String active;

    private Long totalCommit;

    private String statisticMonth ;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS",timezone = "GMT+8")
    private Date createTime;
    
    private Integer branchSize;
    
    private static final long serialVersionUID = 1L;
    
    public Integer getBranchSize() {
		return branchSize;
	}

	public void setBranchSize(Integer branchSize) {
		this.branchSize = branchSize;
	}

	public String getStatisticMonth() {
		return statisticMonth;
	}

	public void setStatisticMonth(String statisticMonth) {
		this.statisticMonth = statisticMonth;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName == null ? null : buName.trim();
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName == null ? null : projName.trim();
    }

    public Long getProjId() {
        return projId;
    }

    public void setProjId(Long projId) {
        this.projId = projId;
    }

    public String getProjLevel() {
        return projLevel;
    }

    public void setProjLevel(String projLevel) {
        this.projLevel = projLevel == null ? null : projLevel.trim();
    }

    public String getProjTime() {
        return projTime;
    }

    public void setProjTime(String projTime) {
        this.projTime = projTime == null ? null : projTime.trim();
    }

    public String getProjStatus() {
        return projStatus;
    }

    public void setProjStatus(String projStatus) {
        this.projStatus = projStatus == null ? null : projStatus.trim();
    }

    public String getRepoName() {
        return repoName;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName == null ? null : repoName.trim();
    }

    public String getRepoUrl() {
        return repoUrl;
    }

    public void setRepoUrl(String repoUrl) {
        this.repoUrl = repoUrl == null ? null : repoUrl.trim();
    }

    public String getRepoTime() {
        return repoTime;
    }

    public void setRepoTime(String repoTime) {
        this.repoTime = repoTime == null ? null : repoTime.trim();
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active == null ? null : active.trim();
    }

    public Long getTotalCommit() {
        return totalCommit;
    }

    public void setTotalCommit(Long totalCommit) {
        this.totalCommit = totalCommit;
    }

    @Override
    public String toString() {
        return "Test{" +
                "code='" + code + '\'' +
                ", buName='" + buName + '\'' +
                ", projName='" + projName + '\'' +
                ", projId=" + projId +
                ", projLevel='" + projLevel + '\'' +
                ", projTime='" + projTime + '\'' +
                ", projStatus='" + projStatus + '\'' +
                ", repoName='" + repoName + '\'' +
                ", repoUrl='" + repoUrl + '\'' +
                ", repoTime='" + repoTime + '\'' +
                ", active='" + active + '\'' +
                ", totalCommit=" + totalCommit +
                ", statisticMonth='" + statisticMonth + '\'' +
                ", createTime=" + createTime +
                ", branchSize=" + branchSize +
                '}';
    }
}