package com.ai.aif.gitai.service.impl;


import com.ai.aif.gitai.dao.entity.ActivitiModel;
import com.ai.aif.gitai.dao.entity.ActivitiRunVariableDto;
import com.ai.aif.gitai.dao.entity.ExceptionJobEntity;
import com.ai.aif.gitai.dao.mapper.ActivitiMapper;
import com.ai.aif.gitai.service.ActivitiApi;
import com.ai.aif.gitai.utils.AIException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.constants.ModelDataJsonConstants;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.impl.pvm.process.ProcessDefinitionImpl;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.Job;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ActivitiImpl implements ActivitiApi {

    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private ManagementService managementService;
    @Autowired
    private ActivitiMapper activitiMapper;

    /**
     * 新建一个空模型
     */
    @Override
    public String newModel(String processName,String description) throws IOException {
        //初始化一个空模型
        Model model = repositoryService.newModel();
        //设置一些默认信息
        int revision = 1;
        ObjectNode modelNode = objectMapper.createObjectNode();
        modelNode.put(ModelDataJsonConstants.MODEL_NAME, processName);
        modelNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION, description);
        modelNode.put(ModelDataJsonConstants.MODEL_REVISION, revision);
        model.setName(processName);
        model.setKey(processName);
        model.setMetaInfo(modelNode.toString());
        repositoryService.saveModel(model);
        String id = model.getId();
        //完善ModelEditorSource
        ObjectNode editorNode = objectMapper.createObjectNode();
        editorNode.put("id", "canvas");
        editorNode.put("resourceId", "canvas");
        ObjectNode stencilSetNode = objectMapper.createObjectNode();
        stencilSetNode.put("namespace",                "http://b3mn.org/stencilset/bpmn2.0#");
        editorNode.put("stencilset", stencilSetNode);
        repositoryService.addModelEditorSource(id,editorNode.toString().getBytes("utf-8"));
        return model.getId();
    }

    /**
     * 删除流程模板
     * @param modelId
     * @return
     */
    @Override
    public void deleteModelById(String modelId){
        try {
            repositoryService.deleteModel(modelId);
        }catch (Exception e){
            log.error("删除流程模板【deleteModelById】失败：modelId={}", modelId, e);
            throw e;
        }
    }

    /**
     * 查询工作流列表
     */
    @Override
    public PageInfo<Map<String, Object>> queryActivitiList(Map<String,Object> map, int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<Map<String,Object>> activitiList = activitiMapper.queryActivitiList(map);
        PageInfo<Map<String,Object>> pageInfo = new PageInfo<>(activitiList);
        return pageInfo;
    }

    /**
     * 删除工作流
     * @param deploymentId
     * @return
     */
    @Override
    public void deleteActivitiById(String deploymentId){
        try {
            repositoryService.deleteDeployment(deploymentId, true);
        }catch (Exception e){
            log.error("删除工作流【deleteActivitiById】失败,deploymentId【{}】",deploymentId, e);
            throw e;
        }
    }

    /**
     *  * 获取所有模型
     *
     *  */
    @Override
    public List<ActivitiModel> modelList(String modelName){

        return activitiMapper.queryModelList(modelName);
        //return repositoryService.createModelQuery().list();
    }

    /**
     *  * 发布模型为流程定义
     *
     *  */
    @Override
    public void deploy(String modelId) throws Exception {
        //获取模型
        Model modelData = repositoryService.getModel(modelId);
        if(modelData ==null){
            throw new Exception("模型不存在");
        }
        byte[] bytes = repositoryService.getModelEditorSource(modelData.getId());
        if (bytes == null) {
            throw new Exception("模型数据为空，请先设计流程并成功保存，再进行发布。");
        }
        JsonNode modelNode = new ObjectMapper().readTree(bytes);
        BpmnModel model = new BpmnJsonConverter().convertToBpmnModel(modelNode);
        if(model.getProcesses().size()==0){
            throw new Exception("数据模型不符要求，请至少设计一条主线流程。");
        }
        byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(model);
        //发布流程
        String processName = modelData.getName() + ".bpmn20.xml";
        Deployment deployment = repositoryService.createDeployment()
                .name(modelData.getName())
                .addString(processName, new String(bpmnBytes, StandardCharsets.UTF_8))
                .deploy();
        modelData.setDeploymentId(deployment.getId());
        repositoryService.saveModel(modelData);
    }

    /**
     *  *  启动流程
     * @return 流程实例id
     *  */
    @Override
    public String startProcess(String keyName,Map<String,Object> variables) throws Exception{
        ProcessInstance process = runtimeService.startProcessInstanceByKey(keyName,variables);
        return process.getId();
    }

    @Override
    public String startProcessByModelKey(String keyName,Map<String,Object> variables) throws Exception{
        String processId="";
        List<Model> modelList = repositoryService.createModelQuery().modelKey(keyName).list();
        if(modelList.size() > 0){
            Model model = repositoryService.createModelQuery().modelKey(keyName).list().get(0);
            List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                    .processDefinitionKey(model.getKey()).list();
            if(processDefinitionList.size() > 0){
                ProcessDefinition processDefinition = processDefinitionList.get(0);
                processId = startProcess(processDefinition.getKey(), variables);
            }else {
                deploy(model.getId());
                startProcess(model.getKey(), variables);
            }
        }else {
            log.error("can not find model by keyName {}",keyName);
            throw new AIException("未找到模型"+keyName);
        }
        return processId;
    }

    @Override
    public String startProcessByModelId(String modelId,Map<String,Object> variables)throws Exception{

        //启动
        String processId="";
        Model model = repositoryService.getModel(modelId);
        List<ProcessDefinition> processDefinitionList = repositoryService.createProcessDefinitionQuery()
                                                                         .processDefinitionKey(model.getKey()).list();
        if(processDefinitionList.size() > 0){
            ProcessDefinition processDefinition = processDefinitionList.get(0);
            processId = startProcess(processDefinition.getKey(), variables);
        }else{
            //部署
            deploy(modelId);
            startProcess(model.getKey(),variables);
        }
        return  processId;
    }

    /**
     *
     * *  提交任务，审批通过
     * @param taskId 节点id
     * @param userName 签收人
     * */
    @Override
    public void claim(String taskId,String userName,String desc){
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        task.setDescription(desc);
        //验证信息
        List<IdentityLink> linksForTask = taskService.getIdentityLinksForTask(taskId);
        List<String> candidateOrAssignedUsers = new ArrayList<>();
        linksForTask.forEach(identityLink->{
            if(StringUtils.equals(identityLink.getUserId(),userName)){
                candidateOrAssignedUsers.add(identityLink.getUserId());
            }
        });

        if(candidateOrAssignedUsers.isEmpty()){
            //throw new Exception("审批人信息有误");
            log.error("审批人信息有误");
        }

        //签收任务
        taskService.claim(taskId,userName);
        //审批任务
        taskService.complete(taskId);
    }

    /**
     * 驳回
     */
    @Override
    public void turnTransition(String taskId, String activityId, Map<String, Object> variables) throws Exception {

        // 当前节点
        ActivityImpl currActivity = findActivitiImpl(taskId, null);
        // 清空当前流向
        List<PvmTransition> oriPvmTransitionList = clearTransition(currActivity);

        // 创建新流向
        TransitionImpl newTransition = currActivity.createOutgoingTransition();
        // 目标节点
        ActivityImpl pointActivity = findActivitiImpl(taskId, activityId);
        // 设置新流向的目标节点
        newTransition.setDestination(pointActivity);

        // 执行转向任务
        taskService.complete(taskId, variables);
        // 删除目标节点新流入
        pointActivity.getIncomingTransitions().remove(newTransition);
        // 还原以前流向
        restoreTransition(currActivity, oriPvmTransitionList);
    }

    @Override
    public List<Task> findAllUserTask() {
        List<Task> taskList;
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskList = taskQuery.orderByTaskCreateTime()
                .desc()
                .list();
        return taskList;
    }

    /**
     * 查询任务
     */
    @Override
    public List<Task> findUserTask(String assignee,String paramName,String paramValue){
        List<Task> taskList;
        log.info("当前查询签收人|审批人{}",assignee);
        TaskQuery taskQuery = taskService.createTaskQuery();
        if(StringUtils.isNotBlank(assignee)){
            taskQuery = taskQuery.taskCandidateOrAssigned(assignee);
        }
        if(StringUtils.isNotBlank(paramValue)){
            taskQuery = taskQuery.processVariableValueEquals(paramName,paramValue);
        }
        taskList = taskQuery.orderByTaskCreateTime()
                            .desc()
                            .list();
        return taskList;
    }

    @Override
    public Object getExcutionVariable(String taskId,String variableName) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        return runtimeService.getVariable(task.getExecutionId(), variableName);
    }

    @Override
    public List<HistoricActivityInstance> findActivitiHis(String processInstanceId) {
        List<HistoricActivityInstance> historicActivityInstanceList = historyService
                .createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId)
                .orderByHistoricActivityInstanceId()
                .asc()
                .list();
        return historicActivityInstanceList;
    }

    @Override
    public HistoricProcessInstance findProcessHis(String processInstanceId) {
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();
        return historicProcessInstance;
    }

    @Override
    public ActivityImpl getCurrentExecution(String executionId) {
        ExecutionEntity execution = (ExecutionEntity)runtimeService.createExecutionQuery().executionId(executionId).singleResult();

        List<ActivityImpl> activities = null;
        if(execution != null){
            ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                    .getDeployedProcessDefinition(execution.getProcessDefinitionId());
            activities = processDefinition.getActivities();
            activities = activities.stream().filter(activity -> execution.getActivityId().equals(activity.getId())).collect(Collectors.toList());
        }
        if(activities !=null && !activities.isEmpty()){
            return activities.get(0);
        }
        return null;
    }

    @Override
    public List<ExceptionJobEntity> findExceptionJobList(String processId) {
//        List<Job> jobList = managementService.createJobQuery().processInstanceId(processId).orderByJobId().asc().list();
        List<ExceptionJobEntity> exceptionJob = activitiMapper.getExceptionJob(processId);
        for (ExceptionJobEntity exceptionJobEntity : exceptionJob) {
            String exceptionMsg = exceptionJobEntity.getExceptionMsg();
            if(!exceptionMsg.isEmpty()){
                ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(exceptionJobEntity.getExecutionId()).singleResult();

                HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                        .processInstanceId(processId).singleResult();
                ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                        .getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());
                List<ActivityImpl> activities = processDefinition.getActivities();
                for (ActivityImpl activity : activities) {
                   if(execution.getActivityId().equals(activity.getId())){
                       exceptionJobEntity.setExceptionLink(activity.getProperty("name").toString());
                   }
                }

            }
            exceptionJobEntity.setExceptionMsg(exceptionMsg.substring(exceptionMsg.indexOf(":")+1));

        }
        return exceptionJob;
    }

    /**
     * 获取流程图
     * @param processInstanceId
     * @return
     * @throws IOException 
     */
    @Override
    public String getProcessPic(String processInstanceId) throws IOException{

        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId(processInstanceId).singleResult();

        // 获取流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(historicProcessInstance.getProcessDefinitionId());

        //按已执行顺序将历史流程节点排序
        List<HistoricActivityInstance> historicActivityInstanceList = historyService.createHistoricActivityInstanceQuery().processInstanceId(processInstanceId).orderByHistoricActivityInstanceId().asc().list();
        List<Job> jobList = managementService.createJobQuery().processInstanceId(processInstanceId).orderByJobId().asc().list();
        // 已执行的节点ID集合
        List<String> executedActivityIdList = new ArrayList<>();
        int index = 1;
        log.info("获取已经执行的节点ID");
        for (HistoricActivityInstance activityInstance : historicActivityInstanceList) {
            executedActivityIdList.add(activityInstance.getActivityId());
            log.info("第[" + index + "]个已执行节点=" + activityInstance.getActivityId() + " : " +activityInstance.getActivityName());
            index++;
        }

        return "imageStr";
    }

    @Override
    public String getProcessPicByVar(String variable) throws Exception {
        List<HistoricVariableInstance> variableInstances = historyService.createHistoricVariableInstanceQuery().variableValueEquals("applicationId", Long.valueOf(variable)).list();
        String processInstanceId;
        if(! variableInstances.isEmpty()){
            processInstanceId = variableInstances.get(variableInstances.size()-1).getProcessInstanceId();
            return getProcessPic(processInstanceId);
        }
        return null;
    }

    @Override
    public  String previewModel(String deploymentId) throws Exception{
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();

        //如果模型还未部署，提示要先部署发布
        if(null == processDefinition){
            throw new Exception("模型还未定义");
        }

        return "imageStr";
    }

    @Override
    public String previewModelByKey(String modelKey) throws Exception {
        Model model = repositoryService.createModelQuery().modelKey(modelKey).singleResult();
        return previewModel(model.getDeploymentId());
    }

    @Override
    public void addRuntimeVariable(ActivitiRunVariableDto runVariableDto) throws Exception {
        String taskId = runVariableDto.getTaskId();
        Map<String, Object> variables = runVariableDto.getVariables();
        for (String key:variables.keySet()){
            taskService.setVariable(taskId,key,variables.get(key));
        }
    }

    @Override
    public void finishTask(String procInsId) {
        Task task = taskService.createTaskQuery().processInstanceId(procInsId).singleResult();
        if(task !=null){
            taskService.complete(task.getId());
        }

    }


    /**
     * 还原指定活动节点流向
     *
     * @param activityImpl
     *            活动节点
     * @param oriPvmTransitionList
     *            原有节点流向集合
     */
    private void restoreTransition(ActivityImpl activityImpl, List<PvmTransition> oriPvmTransitionList) {
        // 清空现有流向
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        pvmTransitionList.clear();
        // 还原以前流向
        for (PvmTransition pvmTransition : oriPvmTransitionList) {
            pvmTransitionList.add(pvmTransition);
        }
    }

    /**
     * 清空指定活动节点流向
     *
     * @param activityImpl
     *            活动节点
     * @return 节点流向集合
     */
    private List<PvmTransition> clearTransition(ActivityImpl activityImpl) {
        // 存储当前节点所有流向临时变量
        List<PvmTransition> oriPvmTransitionList = new ArrayList<>();
        // 获取当前节点所有流向，存储到临时变量，然后清空
        List<PvmTransition> pvmTransitionList = activityImpl
                .getOutgoingTransitions();
        for (PvmTransition pvmTransition : pvmTransitionList) {
            oriPvmTransitionList.add(pvmTransition);
        }
        pvmTransitionList.clear();

        return oriPvmTransitionList;
    }

    /**
     * 根据任务ID获取流程定义
     *
     * @param taskId
     *            任务ID
     * @return
     * @throws Exception
     */
    private ProcessDefinitionEntity findProcessDefinitionEntityByTaskId(
            String taskId) throws Exception {
        TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = (ProcessDefinitionEntity) ((RepositoryServiceImpl) repositoryService)
                .getDeployedProcessDefinition(taskEntity.getProcessDefinitionId());

        if (processDefinition == null) {
            throw new Exception("流程定义未找到!");
        }

        return processDefinition;
    }

    /**
     * 根据任务ID和节点ID获取活动节点
     *
     * @param taskId
     *   任务ID
     * @param activityId
     *   活动节点ID
     *   如果为null或""，则默认查询当前活动节点
     *   如果为"end"，则查询结束节点
     *
     * @return
     * @throws Exception
     */
    private ActivityImpl findActivitiImpl(String taskId, String activityId)
            throws Exception {
        // 取得流程定义
        ProcessDefinitionEntity processDefinition = findProcessDefinitionEntityByTaskId(taskId);

        // 获取当前活动节点ID
        if (StringUtils.isBlank(activityId)) {
            TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(taskId).singleResult();
            activityId = taskEntity.getTaskDefinitionKey();
        }

        // 根据流程定义，获取该流程实例的结束节点
        if (activityId.toUpperCase().equals("END")) {
            for (ActivityImpl activityImpl : processDefinition.getActivities()) {
                List<PvmTransition> pvmTransitionList = activityImpl
                        .getOutgoingTransitions();
                if (pvmTransitionList.isEmpty()) {
                    return activityImpl;
                }
            }
        }

        // 根据节点ID，获取对应的活动节点
        ActivityImpl activityImpl = ((ProcessDefinitionImpl) processDefinition)
                .findActivity(activityId);

        return activityImpl;
    }

	@Override
	public Task findTask(String taskId) {
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}

    @Override
    public List<Task> findTaskRefDeploymentId(String deploymentId) {
        List<Task> needDelTask = new ArrayList<>();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deploymentId).singleResult();
        if(processDefinition !=null){
            String processDefinitionId = processDefinition.getId();
            List<Task> taskActive = taskService.createTaskQuery().active().list();
            needDelTask = taskActive.stream().filter(task -> task.getProcessDefinitionId().equals(processDefinitionId)).collect(Collectors.toList());
        }
        log.info("find taskList need to delete :{}",needDelTask);
        return needDelTask;
    }

    @Override
    public List<Object> getVariablesRefTask(String varName,Task ...taskList) {
        List<Object> varList = new ArrayList<>();
        for (Task task : taskList) {
            Object variable = runtimeService.getVariable(task.getExecutionId(), varName);
            varList.add(variable);
        }
        return varList;
    }

    @Override
    public List<HistoricTaskInstance> queryTaskHistory(String processInstanceId) {
        List<HistoricTaskInstance> historicTaskInstanceList = historyService.createHistoricTaskInstanceQuery()
                .processInstanceId(processInstanceId)
                //.finished()
                .orderByHistoricTaskInstanceEndTime()
                .asc()
                .list();
        return historicTaskInstanceList;
    }

    @Override
    public Model queryModelDefinition(String processDefinitionId) {
        Model model = null;
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();
        if(processDefinition !=null){
            String definitionId = processDefinition.getId();
            String modelKey = definitionId.split(":")[0];
            model = repositoryService.createModelQuery().modelKey(modelKey).singleResult();
        }
        return model;
    }

}
