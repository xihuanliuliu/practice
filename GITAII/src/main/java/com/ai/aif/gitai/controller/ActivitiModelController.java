package com.ai.aif.gitai.controller;


import com.ai.aif.gitai.dao.entity.ActivitiModel;
import com.ai.aif.gitai.dao.entity.ActivitiRunVariableDto;
import com.ai.aif.gitai.service.ActivitiApi;
import com.ai.aif.gitai.utils.AIException;
import com.ai.aif.gitai.utils.ErrorCode;
import com.ai.aif.gitai.vo.ApproveRequest;
import com.ai.server.model.common.dto.ControllerResponse;
import com.ai.server.model.common.enums.ResposeCodeEnum;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * 工作流api
 */
@RestController
@RequestMapping(value = "/act")
@Slf4j
public class ActivitiModelController {

    @Resource
    private ActivitiApi activitiAPI;


    /**
     * 新建一个空模型
     */
    @PostMapping(value = "/modelCreate")
    public ControllerResponse newModel(@RequestBody ActivitiModel activitiModel) throws AIException {
        ControllerResponse<JSONObject> ajaxResponse = new ControllerResponse<>(ResposeCodeEnum.SUCCESS);
        String modelName = activitiModel.getName();
        String description = activitiModel.getDescription();
        try {
            activitiAPI.newModel(modelName,description);
        } catch (Exception e) {
            log.error("创建工作流模型失败",e);
            throw new AIException("创建工作流模型失败",e);
        }
        return ajaxResponse;
    }

    /**
     * 删除流程模板
     * @param modelId 模版id
     * @return
     */
    @DeleteMapping(value = "/deleteModel")
    public ControllerResponse deleteModelById(@RequestParam String modelId) throws AIException{
        ControllerResponse ajaxResponse = new ControllerResponse(ResposeCodeEnum.SUCCESS);
        if(StringUtils.isBlank(modelId)){
            ajaxResponse.setCode(ErrorCode.NULL_INPUT.getCode());
            ajaxResponse.setMsg("modelId不能为空");
            return ajaxResponse;
        }
        try {
            activitiAPI.deleteModelById(modelId);
        } catch (Exception e) {
            log.error("删除流程模板失败",e);
            throw new AIException("删除流程模板失败",e);
        }
        return ajaxResponse;
    }

    /**
     * 批量删除流程模板
     * @param modelIds 模版id数组
     * @return
     */
    @DeleteMapping(value = "/deleteModelBatch")
    public ControllerResponse deleteModelById(@RequestBody String[] modelIds) throws AIException{
        ControllerResponse ajaxResponse = new ControllerResponse(ResposeCodeEnum.SUCCESS);
        for (String modelId : modelIds) {
            try {
                activitiAPI.deleteModelById(modelId);
            } catch (Exception e) {
                log.error("删除流程模板{}失败", modelId, e);
                throw new AIException("删除流程模板失败",e);
            }
        }
        return ajaxResponse;
    }

    /**
     * 获取工作流列表
     * @param jsonObject
     * @return
     */
    @GetMapping(value = "/queryActivitiList")
    public ControllerResponse queryActivitiList(@RequestBody Map<String,String> jsonObject){
        ControllerResponse<PageInfo<Map<String, Object>>> ajaxResponse = new ControllerResponse<>(ResposeCodeEnum.SUCCESS);
        int pageNum = jsonObject.get("pageNum") == null ? 0 : Integer.parseInt(String.valueOf(jsonObject.get("pageNum")));
        int pageSize = jsonObject.get("pageSize") == null ? 0 : Integer.parseInt(String.valueOf(jsonObject.get("pageSize")));
        String serOrderId = jsonObject.get("srvApplicationId") == null ? "" : String.valueOf(jsonObject.get("srvApplicationId"));
        String serviceId = jsonObject.get("serviceId") == null ? "" : String.valueOf(jsonObject.get("serviceId"));

        Map<String,Object> map = new HashMap<>();
        map.put("srvApplicationId",serOrderId);
        map.put("serviceId",serviceId);

        PageInfo<Map<String, Object>> pageInfo = activitiAPI.queryActivitiList(map, pageNum, pageSize);
        ajaxResponse.setData(pageInfo);
        return ajaxResponse;
    }


    /**
     *  * 获取所有模型
     *
     *  */
    @GetMapping(value = "/modelList")
    public ControllerResponse modelList(@RequestParam String pageNum,@RequestParam String pageSize,@RequestParam String modelName) throws AIException{
        int pageNo = StringUtils.isNotBlank(pageNum) ? Integer.parseInt(pageNum) :1;
        int offset = StringUtils.isNotBlank(pageSize) ? Integer.parseInt(pageSize) :10;
        ControllerResponse<PageInfo<ActivitiModel>> ajaxResponse = new ControllerResponse<>(ResposeCodeEnum.SUCCESS);
        try {
            PageHelper.startPage(pageNo,offset);
            List<ActivitiModel> modelList = activitiAPI.modelList(modelName);
            PageInfo<ActivitiModel> pageInfo = new PageInfo<>(modelList);
            ajaxResponse.setData(pageInfo);
        } catch (Exception e) {
            log.error("查询工作流模版列表失败",e);
            throw new AIException("查询工作流模版列表失败",e);
        }
        return ajaxResponse;
    }

    /**
     *  * 发布模型为流程定义
     *
     *  */
    @PostMapping(value = "/deploy",produces="application/json;charset=utf-8")
    public ControllerResponse deploy(@RequestBody Map<String,String> param) throws AIException{
        ControllerResponse ajaxResponse = new ControllerResponse(ResposeCodeEnum.SUCCESS);
        String modelId = param.get("modelId");
        if(StringUtils.isBlank(modelId)){
            ajaxResponse.setCode(ErrorCode.NULL_INPUT.getCode());
            ajaxResponse.setMsg("modelId不能为空");
            return ajaxResponse;
        }
        try {
            activitiAPI.deploy(modelId);
        } catch (Exception e) {
            log.error("部署流程失败",e);
            throw new AIException("部署流程失败",e);
        }
        return ajaxResponse;
    }

    /**
     * 启动流程
     * @param keyName 流程名称
     * @return
     */
    @GetMapping(value = "/start")
    public ControllerResponse startProcess(String keyName,@RequestBody Map<String,Object> variables) throws AIException{
        ControllerResponse<JSONObject> ajaxResponse = new ControllerResponse<>(ResposeCodeEnum.SUCCESS);
        if(StringUtils.isBlank(keyName)){
            ajaxResponse.setCode(ErrorCode.NULL_INPUT.getCode());
            ajaxResponse.setMsg("启动流程缺少参数keyName");
            return ajaxResponse;
        }
        String procId;
        try {
            procId = activitiAPI.startProcess(keyName, variables);
            JSONObject result = new JSONObject();
            result.put("procId",procId);
            ajaxResponse.setData(result);
        } catch (Exception e) {
            ajaxResponse.setMsg("启动流程失败");
            log.error("启动流程失败",e);
            throw new AIException("启动流程失败",e);
        }
        return ajaxResponse;
    }

    /**
     * 启动流程
     * @param modelId 模版id
     *  */
    @PostMapping(value = "/startByModel")
    public ControllerResponse startProcessByModel(String modelId) throws AIException{
        ControllerResponse<JSONObject> ajaxResponse = new ControllerResponse<>(ResposeCodeEnum.SUCCESS);
        if(StringUtils.isBlank(modelId)){
            ajaxResponse.setCode(ErrorCode.NULL_INPUT.getCode());
            ajaxResponse.setMsg("启动流程缺少参数modelId");
            return ajaxResponse;
        }
        Map<String,Object> variables = new HashMap<>();
        String procId;
        try {
            procId = activitiAPI.startProcessByModelId(modelId, variables);
            JSONObject result = new JSONObject();
            result.put("procId",procId);
            ajaxResponse.setData(result);
            if(StringUtils.isNoneBlank(procId)){
                ajaxResponse.setMsg("启动流程成功");
            }else{
                ajaxResponse.setMsg("启动流程失败");
            }
        } catch (Exception e) {
            ajaxResponse.setMsg("启动流程失败");
            log.error("启动流程失败",e);
            throw new AIException("启动流程失败",e);
        }
        return ajaxResponse;
    }

    /**
     * 提交任务
     * @param approveRequest
     * @return
     */
    @PostMapping(value = "/approve")
    public void claim(@RequestBody ApproveRequest approveRequest) throws AIException{
        String taskId = approveRequest.getTaskId();
        String dealUser = approveRequest.getDealUser();
        String approvalResult = approveRequest.getApprovalResult();
        String remainsId = approveRequest.getRemainsId();
        String approvalDesc = approveRequest.getApprovalDesc();
        if(StringUtils.isAnyBlank(taskId,dealUser,approvalResult)){
        	log.error("参数为空");
        	return;
        }
        Map<String,Object> variableMap = new HashMap<>(2);
        variableMap.put("result",approvalResult);
        variableMap.put("remainsId", remainsId);
        variableMap.put("approvalDesc", approvalDesc);
        ActivitiRunVariableDto runVariableDto = new ActivitiRunVariableDto();
        runVariableDto.setTaskId(taskId);
        runVariableDto.setVariables(variableMap);

        try {
             //设置流程变量
             activitiAPI.addRuntimeVariable(runVariableDto);
             //签收任务
             activitiAPI.claim(taskId,dealUser,approvalDesc);
             //gitai内部通知下转已办
            //OaPageController.sendTaskAgreeStatus(approveRequest);
        } catch (Exception e) {
            log.error("审批工作流任务异常",e);
            throw new AIException("审批工作流任务异常",e);
        }
    }

    /**
     * 驳回
     */
    @PostMapping(value = "/reject")
    public ControllerResponse turnTransition(String taskId, String activityId, Map<String, Object> variables){
        ControllerResponse ajaxResponse = new ControllerResponse(ResposeCodeEnum.SUCCESS);
        try {
            activitiAPI.turnTransition(taskId,activityId,variables);
        } catch (Exception e) {
            log.error("驳回失败",e);
            ajaxResponse.setCode(ErrorCode.DEF_ERROR.getCode());
            ajaxResponse.setMsg("驳回失败");
        }
        return ajaxResponse;
    }


    /**
     * 获取流程图
     * @param processInstanceId 流程实例id
     * @return
     */
    @GetMapping(value = "/flowPic")
    public void getProcessPic(String processInstanceId, HttpServletResponse response) throws AIException{
        if(StringUtils.isBlank(processInstanceId)){
            return;
        }

        //设置页面不缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);


        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String imageStr = activitiAPI.getProcessPic(processInstanceId);
            writeImageToPage(response, decoder, imageStr);
        } catch (Exception e) {
            log.error("获取流程图失败",e);
            throw new AIException("获取流程图失败",e);
        }
    }

    /**
     * 预览模板图
     * @param deploymentId 流程部署id
     *  */
    @GetMapping(value = "/previewModel")
    public void previewModel(@RequestParam String deploymentId, HttpServletResponse response) throws AIException{
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            if(StringUtils.isBlank(deploymentId)){
                throw new AIException(ErrorCode.LACK_INPUT);
            }
            String imageStr = activitiAPI.previewModel(deploymentId);
            writeImageToPage(response,decoder,imageStr);
        } catch (Exception e) {
            log.error("获取模型预览失败",e);
            throw new AIException("获取模型数据失败",e);
        }
    }

    /**
     * 预览模板图
     * @param processModelName 模版名称
     *  */
    @GetMapping(value = "/preModelByKey")
    public void preModelByKey(String processModelName, HttpServletResponse response) throws AIException{
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            String imageStr = activitiAPI.previewModelByKey(processModelName);
            writeImageToPage(response,decoder,imageStr);
        } catch (Exception e) {
            log.error("获取模型数据失败",e);
            throw new AIException("获取模型数据失败",e);
        }
    }

    /**
     * 查询已部署流程相关任务
     * @param deploymentId 模型部署id
     * @return
     */
    @GetMapping(value = "/queryTaskModelInActive")
    public ControllerResponse queryTaskModelInActive(@RequestParam String deploymentId){
        ControllerResponse<Integer> taskControllerResponse = new ControllerResponse<>(ResposeCodeEnum.SUCCESS);
        List<Task> taskList = activitiAPI.findTaskRefDeploymentId(deploymentId);
        taskControllerResponse.setData(taskList.size());
        return taskControllerResponse;
    }

    private void writeImageToPage(HttpServletResponse response, BASE64Decoder decoder, String imageStr) throws IOException {
        byte[] b = decoder.decodeBuffer(imageStr);
        // 处理数据
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        response.setContentType("image/png");
        OutputStream os = response.getOutputStream();
        os.write(b);
        os.close();
    }
}
