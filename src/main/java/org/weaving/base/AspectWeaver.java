package org.weaving.base;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

public abstract class AspectWeaver<T extends Advice> {
    protected BpmnModelInstance bpmnModelInstance;

    public AspectWeaver(BpmnModelInstance bpmnModelInstance) {
        this.bpmnModelInstance = bpmnModelInstance;
    }

    public abstract AspectParser<T> getAspectParser(String aspectFilePath) throws Exception;
    public abstract void weaveAfterAdvice(T advice, FlowNode monitoredElement);
    public abstract  void weaveBeforeAdvice(T advice, FlowNode monitoredElement);
}
