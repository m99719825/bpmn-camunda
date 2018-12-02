package nl.minjak.bpmn.camunda.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class GarageBPMNInitTask extends SimulatieTask implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		super.execute(execution);
		GarageOnderhoudsProces procesData = new GarageOnderhoudsProces();
		procesData.init();
		procesData.setDurationUntilAppointment("PT1S");
		procesData.setIndBetalingContant(false);
		procesData.setIndBetalingOntvangen(false);
		procesData.setIndHerinneringVerstuurd(false);
		execution.setVariable("processData", procesData);
	}

}
