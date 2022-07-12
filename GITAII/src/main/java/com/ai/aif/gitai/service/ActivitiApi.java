package com.ai.aif.gitai.service;


import com.ai.aif.gitai.dao.entity.ActivitiModel;
import com.ai.aif.gitai.dao.entity.ActivitiRunVariableDto;
import com.ai.aif.gitai.dao.entity.ExceptionJobEntity;

import com.github.pagehelper.PageInfo;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Model;
import org.activiti.engine.task.Task;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ActivitiApi {

    /**
     * 新建空白模型
     * @param processName
     * @param description
     * @return
     * @throws IOException
     */
     String newModel(String processName,String description) throws IOException;

    /**
     * 删除流程模板
     * @param modelId
     * @return
     */
     void deleteModelById(String modelId);

     /**
      *  获取工作流列表
      * @param map
      * @return
      */
     PageInfo<Map<String, Object>> queryActivitiList(Map<String, Object> map, int pageNum, int pageSize);

    /**
     * 删除工作流
     * @param deploymentId
     * @return
     */
     void deleteActivitiById(String deploymentId);

    /**
     * 获取所有模型
     * @param modelName
     * @return
     */
     List<ActivitiModel> modelList(String modelName);

    /**
     * 发布模型为流程定义
     * @param modelId
     * @throws Exception
     */
    void deploy(String modelId) throws Exception;

    /**
     * 启动流程
     * @param keyName
     * @param param
     * @return
     * @throws Exception
     */
    String startProcess(String keyName, Map<String, Object> param)throws Exception;

    /**
     * 根据模型id启动流程
     * @param modelId
     * @param variables
     * @return
     * @throws Exception
     */
    String startProcessByModelId(String modelId, Map<String, Object> variables) throws Exception;

     /**
      * 根据模型名称启动流程
      * @param keyName
      * @param variables
      * @return
      * @throws Exception
      */
    String startProcessByModelKey(String keyName, Map<String, Object> variables) throws Exception;

    /**
     *
     * *  提交任务，审批通过
     * @param taskId 节点id
     * @param userName 签收人
     * @param desc 审批意见
     * */
    void claim(String taskId, String userName,String desc);

    /**
     * 驳回task
     * @param taskId 人工节点id
     * @param activityId 节点定义唯一标识
     * @param variables
     * @throws Exception
     */
    void turnTransition(String taskId, String activityId, Map<String, Object> variables) throws Exception;


    /**
     * 查询所有任务
     * @return
     */
    List<Task> findAllUserTask();

    /**
     * 查询任务
     * @param assignee 签收人
     * @param paramName 流程变量名
     * @param paramValue 流程变量值
     */
    List<Task> findUserTask(String assignee, String paramName, String paramValue);


    /**
     * 查询流程变量
     * @param taskId
     * @param variableName 流程变量名
     */
    Object getExcutionVariable(String taskId, String variableName);

     /**
      * 查询工作流历史执行节点
      * @param processInstanceIds
      */
     List<HistoricActivityInstance> findActivitiHis(String processInstanceIds);

    /**
     * 查询工作流历史执行节点
     * @param processInstanceId
     */
    HistoricProcessInstance findProcessHis(String processInstanceId);

    /**
     * 获取流程当前执行节点
     * @param executionId
     * @return
     */
    ActivityImpl getCurrentExecution(String executionId);

    /**
     * 查找异常节点
     * @param processId
     * @return
     */
    List<ExceptionJobEntity> findExceptionJobList(String processId);

    /**
     * 获取流程图
     * @param processInstanceId
     * @return
     */
     String getProcessPic(String processInstanceId) throws Exception;


    /**
     * 获取流程图
     * @param variable
     * @return
     */
    String getProcessPicByVar(String variable) throws Exception;

     /**
      * 获取模型预览
      * @param deploymentId
      * @return
      * @throws Exception
      */
     String previewModel(String deploymentId) throws Exception;

    /**
     * 获取模型预览
     * @param modelKey
     * @return
     * @throws Exception
     */
    String previewModelByKey(String modelKey) throws Exception;

    /**
     * 加入流程运行变量
     * @param runVariableDto 参数组装
     */
    void addRuntimeVariable(ActivitiRunVariableDto runVariableDto) throws Exception;

    /**
     * 结束任务
     * @param procInsId
     */
    void finishTask(String procInsId);

    /**
     * 获取task
     * @param taskId
     * @return
     */
    Task findTask(String taskId);

    /**
     * 根据部署id查询已创建task任务
     * @param deploymentId
     * @return
     */
    List<Task> findTaskRefDeploymentId(String deploymentId);

    /**
     * 查询task关联的流程变量
     * @param varName
     * @param taskList
     * @return
     */
    List<Object> getVariablesRefTask(String varName,Task ...taskList);

    /**
     * 查找历史task
     * @param processInstanceId
     * @return
     */
    List<HistoricTaskInstance> queryTaskHistory(String processInstanceId);

    /**
     * 根据流程定义id获取模型
     * @param processDefinitionId
     * @return
     */
    Model queryModelDefinition(String processDefinitionId);
}
