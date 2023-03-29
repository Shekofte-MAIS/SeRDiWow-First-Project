package org.weaving.executionListener;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.impl.instance.TaskImpl;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.weaving.base.AspectParser;
import org.weaving.base.AspectWeaver;

public class ExecutionListenerAspectWeaver extends AspectWeaver<ExecutionListenerAdvice> {
    public ExecutionListenerAspectWeaver(BpmnModelInstance bpmnModelInstance) {
        super(bpmnModelInstance);
    }

    @Override
    public AspectParser<ExecutionListenerAdvice> getAspectParser(String aspectFilePath) throws Exception {
        return new ExecutionListenerAspectParser(aspectFilePath);
    }

    @Override
    public void weaveAfterAdvice(ExecutionListenerAdvice advice, FlowNode monitoredElement) {
        weaveAdvice(advice, monitoredElement, "end");
    }

    @Override
    public void weaveBeforeAdvice(ExecutionListenerAdvice advice, FlowNode monitoredElement) {
        weaveAdvice(advice, monitoredElement, "start");
    }

    private void weaveAdvice(ExecutionListenerAdvice advice, FlowNode monitoredElement, String executionListenerEventName) {
        monitoredElement.builder().camundaExecutionListenerClass(executionListenerEventName, advice.javaClass);
        ((TaskImpl)monitoredElement).builder().boundaryEvent( monitoredElement.getId() + "-boundary-event").name("")
                .errorEventDefinition().errorMessageVariable("wrong-pin-code-error-message")
                .error("wrong-pin-code");
        BoundaryEvent boundaryEvent = bpmnModelInstance.getModelElementById(monitoredElement.getId() + "-boundary-event");
        boundaryEvent.builder()
                .serviceTask().name("Email error message").camundaClass("com.example.workflow.ErrorMessageEmailSenderDelegate")
                .endEvent();
    }
}
