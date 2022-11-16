package org.core;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.utils.ConfigProperties;
import org.weaving.shared.AspectWeaverFactory;

import java.io.File;

public class Encapsulator {
    private final BpmnModelInstance bpmnModelInstance;

    public Encapsulator() {
        bpmnModelInstance = Bpmn.readModelFromFile(new File(ConfigProperties.bpmnFilePath));
    }

    public void encapsulate() throws Exception {
        var aspectWeaver = new AspectWeaverFactory(bpmnModelInstance).generateWeaver(ConfigProperties.hookType);
        var weaverContext = new WeaverContext(aspectWeaver);
        weaverContext.weave(bpmnModelInstance);
        writeWeaveResultInNewBpmnFile();
    }

    protected void writeWeaveResultInNewBpmnFile() {
        Bpmn.validateModel(bpmnModelInstance);
        var newFile = new File(ConfigProperties.wovenBpmnFilePath);
        Bpmn.writeModelToFile(newFile, bpmnModelInstance);
    }
}
