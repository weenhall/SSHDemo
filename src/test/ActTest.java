import org.activiti.engine.*;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2017/12/26.
 */
public class ActTest {

    @Test
    public void test() {
        //部署流程
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RepositoryService repositoryService = processEngine.getRepositoryService();
        repositoryService.createDeployment().addClasspathResource("act/test2.bpmn20.xml").deploy();
        //System.out.println(repositoryService.createProcessDefinitionQuery().count());

        //启动流程实例
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("employeeName", "Kermit");
        map.put("numberOfDays", new Integer(4));
        map.put("vacationMotivation", "I'm really tired!");

        RuntimeService runtimeService = processEngine.getRuntimeService();
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", map);
        //System.out.println(runtimeService.createProcessInstanceQuery().count());

        //查询任务
        TaskService taskService = processEngine.getTaskService();
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println(task.getName());
        }
        //完成任务
        Task task = tasks.get(0);
        Map<String,Object> taskVariables=new HashMap<String, Object>();
        taskVariables.put("vacationApproved","false");
        taskVariables.put("managerMotivation","We have a tight deadline");
        taskService.complete(task.getId(),taskVariables);


        //挂起流程
        repositoryService.suspendProcessDefinitionByKey("vacationRequest");
        try {
            runtimeService.startProcessInstanceByKey("vacationRequest");
        }catch (ActivitiException e){
            //激活流程
            repositoryService.activateProcessDefinitionByKey("vacationRequest");
            e.printStackTrace();
        }

    }

    @Test
    public void activitiSpringIntegration(){
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext(new String[]{"spring/spring-context.xml", "spring/spring-activiti-context.xml"});
        RepositoryService repositoryService= (RepositoryService) applicationContext.getBean("repositoryService");
        String deploymentId=repositoryService.createDeployment().addClasspathResource("act/test2.bpmn20.xml").deploy().getId();
    }

    public static void main(String[] args) {
        //创建流程引擎
        ProcessEngine processEngine=ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration().buildProcessEngine();
        //获取services
        RepositoryService repositoryService=processEngine.getRepositoryService();
        RuntimeService runtimeService=processEngine.getRuntimeService();
        //部署流程
        repositoryService.createDeployment().addClasspathResource("act/FinancialReportProcess.bpmn20.xml").deploy();
        //启动一个流程
        String processId=runtimeService.startProcessInstanceByKey("financialReport").getId();
        //获取任务
        TaskService taskService=processEngine.getTaskService();
        List<Task> tasks=taskService.createTaskQuery().taskCandidateGroup("accountancy").list();
        for(Task  task:tasks){
            System.out.println(task.getName());
            taskService.claim(task.getId(),"fozzie");
        }
        //查询fozzie的任务
        tasks=taskService.createTaskQuery().taskAssignee("fozzie").list();
        for(Task task:tasks){
            System.out.println("Task for fozzie"+task.getName());
        }
        System.out.println("fozzie的任务条数"+taskService.createTaskQuery().taskAssignee("fozzie").count());

        tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
        for (Task task : tasks) {
            System.out.println("Following task is available for accountancy group: " + task.getName());
            taskService.claim(task.getId(), "kermit");
        }

        // Completing the second task ends the process
        for (Task task : tasks) {
            taskService.complete(task.getId());
        }

        // verify that the process is actually finished
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstance historicProcessInstance =
                historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();
        System.out.println("Process instance end time: " + historicProcessInstance.getEndTime());
    }
}
