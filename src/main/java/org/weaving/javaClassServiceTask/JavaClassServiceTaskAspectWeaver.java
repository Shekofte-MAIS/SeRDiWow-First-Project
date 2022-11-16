package org.weaving.javaClassServiceTask;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.BoundaryEvent;
import org.camunda.bpm.model.bpmn.instance.FlowNode;
import org.camunda.bpm.model.bpmn.instance.SequenceFlow;
import org.camunda.bpm.model.bpmn.instance.ServiceTask;
import org.weaving.base.AspectWeaver;
import org.weaving.base.AdvicesParser;

public class JavaClassServiceTaskAspectWeaver extends AspectWeaver<JavaClassServiceTaskAdvice> {
    public JavaClassServiceTaskAspectWeaver(BpmnModelInstance bpmnModelInstance) {
        super(bpmnModelInstance);
    }

    @Override
    public AdvicesParser getAdviceParser(String aspectFilePath) throws Exception {
        return new JavaClassServiceTaskAdvicesParser(aspectFilePath);
    }

    @Override
    public void weaveAfterAdvice(JavaClassServiceTaskAdvice advice, FlowNode monitoredElement) {
        SequenceFlow outgoingSequenceFlow = monitoredElement.getOutgoing().iterator().next(); //TODO: suppose multiple outgoings
        FlowNode oldTargetNode = outgoingSequenceFlow.getTarget();
        monitoredElement.getParentElement().removeChildElement(outgoingSequenceFlow);
        monitoredElement.builder()
                .serviceTask(advice.serviceTaskId).name(advice.serviceTaskName).camundaClass(advice.javaClass)
                .connectTo(oldTargetNode.getId());
        ServiceTask addedServiceTask = bpmnModelInstance.getModelElementById(advice.serviceTaskId);
        addedServiceTask.builder()
                .boundaryEvent(advice.serviceTaskId + "-boundary-event").name("")
                .errorEventDefinition().errorMessageVariable("wrong-pin-code-error-message")
                .error("wrong-pin-code");
        BoundaryEvent boundaryEvent = bpmnModelInstance.getModelElementById(advice.serviceTaskId + "-boundary-event");
        boundaryEvent.builder()
                .serviceTask().name("Email error message").camundaClass("com.example.workflow.ErrorMessageEmailSenderDelegate")
                .endEvent();
    }

    @Override
    public void weaveBeforeAdvice(JavaClassServiceTaskAdvice advice, FlowNode monitoredElement) {
        SequenceFlow incomingSequenceFlow = monitoredElement.getIncoming().iterator().next(); //TODO: suppose multiple outgoings
        FlowNode oldSourceNode = incomingSequenceFlow.getSource();
        monitoredElement.getParentElement().removeChildElement(incomingSequenceFlow);
        oldSourceNode.builder()
                .serviceTask(advice.serviceTaskId).name(advice.serviceTaskName).camundaClass(advice.javaClass)
                .connectTo(monitoredElement.getId());
        ServiceTask addedServiceTask = bpmnModelInstance.getModelElementById(advice.serviceTaskId);
        addedServiceTask.builder()
                .boundaryEvent(advice.serviceTaskId + "-boundary-event").name("")
                .errorEventDefinition().errorMessageVariable("wrong-pin-code-error-message")
                .error("wrong-pin-code");
        BoundaryEvent boundaryEvent = bpmnModelInstance.getModelElementById(advice.serviceTaskId + "-boundary-event");
        boundaryEvent.builder()
                .serviceTask().name("Email error message").camundaClass("com.example.workflow.ErrorMessageEmailSenderDelegate")
                .endEvent();
    }
}
