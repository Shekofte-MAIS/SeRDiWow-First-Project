package org.weaving.shared;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.utils.HookType;
import org.weaving.base.AspectWeaver;
import org.weaving.executionListener.ExecutionListenerAspectWeaver;
import org.weaving.externalServiceTask.ExternalServiceTaskAspectWeaver;
import org.weaving.internalServiceTask.InternalServiceTaskAspectWeaver;

public class AspectWeaverFactory {
    private final BpmnModelInstance bpmnModelInstance;

    public AspectWeaverFactory(BpmnModelInstance bpmnModelInstance) {
        this.bpmnModelInstance = bpmnModelInstance;
    }

    public AspectWeaver generateWeaver(HookType hookType) {
        switch (hookType) {
            case InternalServiceTask: return new InternalServiceTaskAspectWeaver(bpmnModelInstance);
            case ExternalServiceTask: return new ExternalServiceTaskAspectWeaver(bpmnModelInstance);
            case ExecutionListener: return new ExecutionListenerAspectWeaver(bpmnModelInstance);
            default: return new InternalServiceTaskAspectWeaver(bpmnModelInstance);
        }
    }
}
