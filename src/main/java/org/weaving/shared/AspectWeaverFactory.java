package org.weaving.shared;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.utils.HookType;
import org.weaving.base.AspectWeaver;
import org.weaving.executionListener.ExecutionListenerAspectWeaver;
import org.weaving.externalServiceTask.ExternalServiceTaskAspectWeaver;
import org.weaving.javaClassServiceTask.JavaClassServiceTaskAspectWeaver;

public class AspectWeaverFactory {
    private final BpmnModelInstance bpmnModelInstance;

    public AspectWeaverFactory(BpmnModelInstance bpmnModelInstance) {
        this.bpmnModelInstance = bpmnModelInstance;
    }

    public AspectWeaver generateWeaver(HookType hookType) {
        switch (hookType) {
            case JavaClassServiceTask: return new JavaClassServiceTaskAspectWeaver(bpmnModelInstance);
            case ExternalServiceTask: return new ExternalServiceTaskAspectWeaver(bpmnModelInstance);
            case ExecutionListener: return new ExecutionListenerAspectWeaver(bpmnModelInstance);
            default: return new JavaClassServiceTaskAspectWeaver(bpmnModelInstance);
        }
    }
}
