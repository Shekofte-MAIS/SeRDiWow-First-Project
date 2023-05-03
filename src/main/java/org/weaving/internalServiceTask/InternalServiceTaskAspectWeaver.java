package org.weaving.internalServiceTask;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.weaving.base.AspectWeaver;
import org.weaving.base.AspectParser;

public class InternalServiceTaskAspectWeaver extends AspectWeaver<InternalServiceTaskAdvice> {
    public InternalServiceTaskAspectWeaver(BpmnModelInstance bpmnModelInstance) {
        super(bpmnModelInstance);
    }

    @Override
    public AspectParser getAspectParser(String aspectFilePath) throws Exception {
        return new InternalServiceTaskAspectParser(aspectFilePath);
    }

    @Override
    public void weaveAfterAdvice(InternalServiceTaskAdvice advice, FlowNode monitoredElement) {
        SequenceFlow outgoingSequenceFlow = monitoredElement.getOutgoing().iterator().next(); //TODO: suppose multiple outgoings
        FlowNode oldTargetNode = outgoingSequenceFlow.getTarget();
        monitoredElement.getParentElement().removeChildElement(outgoingSequenceFlow);
        monitoredElement.builder()
                .serviceTask(advice.serviceTaskId).name(advice.serviceTaskName).camundaClass(advice.javaClass)
                .connectTo(oldTargetNode.getId());
        addEnforcerPart(advice.serviceTaskId);
    }

    @Override
    public void weaveBeforeAdvice(InternalServiceTaskAdvice advice, FlowNode monitoredElement) {
        SequenceFlow incomingSequenceFlow = monitoredElement.getIncoming().iterator().next(); //TODO: suppose multiple outgoings
        FlowNode oldSourceNode = incomingSequenceFlow.getSource();
        monitoredElement.getParentElement().removeChildElement(incomingSequenceFlow);
        oldSourceNode.builder()
                .serviceTask(advice.serviceTaskId).name(advice.serviceTaskName).camundaClass(advice.javaClass)
                .connectTo(monitoredElement.getId());
        addEnforcerPart(advice.serviceTaskId);
    }

    private void addEnforcerPart(String addedServiceTaskId) {
        ServiceTask addedServiceTask = bpmnModelInstance.getModelElementById(addedServiceTaskId);
        addedServiceTask.builder()
                .boundaryEvent(addedServiceTaskId + "-boundary-event").name("")
                .errorEventDefinition().errorMessageVariable("wrong-pin-code-error-message")
                .error("wrong-pin-code");
        BoundaryEvent boundaryEvent = bpmnModelInstance.getModelElementById(addedServiceTaskId + "-boundary-event");
        boundaryEvent.builder()
                .serviceTask().name("Email error message").camundaClass("com.example.workflow.ErrorMessageEmailSenderDelegate")
                .endEvent();
    }
}
