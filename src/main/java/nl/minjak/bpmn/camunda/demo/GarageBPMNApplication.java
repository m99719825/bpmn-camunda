package nl.minjak.bpmn.camunda.demo;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.impl.jobexecutor.JobExecutor;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.camunda.bpm.spring.boot.starter.event.PostDeployEvent;
import org.camunda.bpm.spring.boot.starter.event.PreUndeployEvent;
import org.camunda.bpm.spring.boot.starter.property.CamundaBpmProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableProcessApplication("GarageBPMNApplication")
public class GarageBPMNApplication implements CommandLineRunner {

	boolean processApplicationStopped;

	public static void main(final String... args) throws Exception {
		SpringApplication.run(GarageBPMNApplication.class, args);
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JobExecutor jobExecutor;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private ConfigurableApplicationContext context;

	@Autowired
	private CamundaBpmProperties camundaBpmProperties;

	@Autowired
	private Showcase showcase;

	@Autowired
	private ProcessEngine processEngine;

	@EventListener
	public void onPostDeploy(PostDeployEvent event) {
		logger.info("postDeploy: {}", event);
	}

	@EventListener
	public void onPreUndeploy(PreUndeployEvent event) {
		logger.info("preUndeploy: {}", event);
		processApplicationStopped = true;
	}

	@Override
	public void run(String... strings) throws Exception {
		logger.info("CommandLineRunner#run() - {}", ToStringBuilder
				.reflectionToString(camundaBpmProperties.getApplication(), ToStringStyle.SHORT_PREFIX_STYLE));
	}
}