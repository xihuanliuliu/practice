package com.ai.aif.gitai.vo;

import lombok.Data;

@Data
public class ApproveRequest {

    /**
     * 任务id
     */
    String taskId;

    /**
     * 任务处理人
     */
    String dealUser;

    /**
     * 审批结果 0：同意；1：拒绝
     */
    String approvalResult;

    /**
     * 审批意见
     */
    String approvalDesc;

    /**
     * 待办id
     */
    String remainsId;

    /**
     * OA  targetTaskId
     */
    String targetTaskId;
}
