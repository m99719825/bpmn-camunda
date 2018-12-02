package nl.minjak.bpmn.camunda.demo;

import java.util.Random;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimulatieTask implements JavaDelegate {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Random rnd = new Random();

		logger.error("processID: {} executed : {}", execution.getProcessInstanceId(),
				execution.getCurrentActivityName().replaceAll("\n", " "));

		String duration = (String) execution.getVariable("duration");
		if (duration != null)
		{
			
			int sleepTime = new Double(rnd.nextGaussian()*10000).intValue() + (new Integer(duration).intValue() * 1000) ;
			Thread.sleep(sleepTime);
		} else {
			int sleepTime = new Double(rnd.nextGaussian()*10000).intValue() + (10000) ;
			Thread.sleep(sleepTime);
		}

		
	}

}
