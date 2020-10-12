import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @author:ºÚ¾ø
 * @date:2017/12/18 19:28
 */
public class MainTest {

    public static void main(String[] args) {
        StandaloneProcessEngineConfiguration processEngineConfiguration = new StandaloneProcessEngineConfiguration();
        processEngineConfiguration.setJdbcUrl("jdbc:h2:mem:activiti;DB_CLOSE_DELAY=1000");
        processEngineConfiguration.setJdbcDriver("org.h2.Driver");
        processEngineConfiguration.setJdbcUsername("sa");
        processEngineConfiguration.setJdbcPassword("sa");
        processEngineConfiguration.setDatabaseSchemaUpdate("true");

        ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();

        repositoryService.createDeployment()
            .addClasspathResource("hello.bpmn20.xml")
            .deploy();

        RuntimeService runtimeService = processEngine.getRuntimeService();

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("helloPorcess");

        System.out.println(processInstance.getId());
    }
}
