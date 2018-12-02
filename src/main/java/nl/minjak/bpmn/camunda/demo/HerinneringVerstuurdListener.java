package nl.minjak.bpmn.camunda.demo;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class HerinneringVerstuurdListener implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		GarageOnderhoudsProces processData = (GarageOnderhoudsProces) execution.getVariable("processData"); // p.getValue();
		processData.setIndHerinneringVerstuurd(true);
		execution.setVariable("processData", processData);
	}
}
