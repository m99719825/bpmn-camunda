package nl.minjak.bpmn.camunda.demo;

import static org.camunda.bpm.engine.authorization.Groups.CAMUNDA_ADMIN;
import static org.slf4j.LoggerFactory.getLogger;

import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.authorization.Groups;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.User;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Showcase {

	private final Logger logger = getLogger(this.getClass());

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	private String processInstanceId;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private ProcessEngine processEngine;

	private User createUser(String id, String firstName, String lastName, String email) {
		User user = identityService.newUser(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		user.setPassword("demo");
		identityService.saveUser(user);

		if (identityService.createGroupQuery().groupId(CAMUNDA_ADMIN).count() == 0) {
			Group camundaAdminGroup = identityService.newGroup(CAMUNDA_ADMIN);
			camundaAdminGroup.setName("camunda BPM Administrators");
			camundaAdminGroup.setType(Groups.GROUP_TYPE_SYSTEM);
			identityService.saveGroup(camundaAdminGroup);
		}

		return user;
	}

	@EventListener
	public void notify(final PostDeployEvent unused) {
		createUser("demo", "demo", "demo", "demo@garage.nl");
		createUser("dirk", "Dirk", "Baas", "dirk@garage.nl");
		createUser("bas", "Bas", "Bookie", "bas@garage.nl");
		createUser("martijn", "Martijn", "Mechanic", "martijn@garage.nl");
		createUser("mark", "Mark", "Mechanicien", "mark@garage.nl");

		identityService.saveGroup(identityService.newGroup("directeur"));
		identityService.saveGroup(identityService.newGroup("boekhouder"));
		identityService.saveGroup(identityService.newGroup("intaker"));
		identityService.saveGroup(identityService.newGroup("monteur"));

		identityService.saveTenant(identityService.newTenant("Garagebedrijf Hoeksema"));

		identityService.createMembership("dirk", "directeur");
		identityService.createMembership("dirk", "intaker");
		identityService.createMembership("bas", "intaker");
		identityService.createMembership("bas", "boekhouder");
		identityService.createMembership("mark", "monteur");
		identityService.createMembership("martijn", "monteur");
		identityService.createMembership("demo", "camunda-admin");

		identityService.createTenantUserMembership("Garagebedrijf Hoeksema", "bas");

		for (int i = 0; i < 2; i++) {
			processInstanceId = runtimeService.startProcessInstanceByKey("GarageProcess").getProcessInstanceId();
			logger.info("started instance: {}", processInstanceId);
		}

//    Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
//    taskService.complete(task.getId());
//    logger.info("completed task: {}", task);

		// now jobExecutor should execute the async job

	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}
}