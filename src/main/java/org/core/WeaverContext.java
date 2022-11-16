package org.core;

import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.utils.AdviceType;
import org.utils.BpmnElementsFinder;
import org.utils.ConfigProperties;
import org.weaving.base.Advice;
import org.weaving.base.AspectWeaver;

import java.util.List;

public class WeaverContext {
    private final AspectWeaver aspectWeaver;

    public WeaverContext(AspectWeaver aspectWeaver) {
        this.aspectWeaver = aspectWeaver;
    }

    public void weave(BpmnModelInstance bpmnModelInstance) throws Exception {
        var advicesParser = aspectWeaver.getAdviceParser(ConfigProperties.aspectFilePath);
        List<Advice> advices = advicesParser.getAdvices();
        BpmnElementsFinder bpmnElementsFinder = new BpmnElementsFinder(bpmnModelInstance, ConfigProperties.bpmnFilePath);
        for (Advice advice : advices) {
            var monitoredElements = bpmnElementsFinder.getFlowNodes(advice.xPathExpression);
            for (org.camunda.bpm.model.bpmn.instance.FlowNode monitoredElement : monitoredElements) {
                if (advice.adviceType == AdviceType.After) {
                    aspectWeaver.weaveAfterAdvice(advice, monitoredElement);
                } else if (advice.adviceType == AdviceType.Before) {
                    aspectWeaver.weaveBeforeAdvice(advice, monitoredElement);
                }
            }
        }
    }
}
