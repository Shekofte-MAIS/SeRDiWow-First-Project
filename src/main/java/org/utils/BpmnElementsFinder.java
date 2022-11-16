package org.utils;

import org.apache.log4j.Logger;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.FlowNode;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

public class BpmnElementsFinder {
    private final Logger logger;
    private final BpmnModelInstance bpmnModelInstance;
    private final XmlNodesLocatorUsingXPath xmlNodesLocatorUsingXPath;

    public BpmnElementsFinder(BpmnModelInstance bpmnModelInstance, String bpmnFilePath) throws Exception {
        this.bpmnModelInstance = bpmnModelInstance;
        logger = Logger.getLogger(BpmnElementsFinder.class);
        xmlNodesLocatorUsingXPath = new XmlNodesLocatorUsingXPath(bpmnFilePath);
    }

    public List<FlowNode> getFlowNodes(String xPathExpression) throws XPathExpressionException {
        var flowNodesIds = xmlNodesLocatorUsingXPath.getNodesIds(xPathExpression);
        var flowNodes = new ArrayList<FlowNode>();
        for (String flowNodeId : flowNodesIds) {
            addFlowNodeToList(flowNodes, flowNodeId);
        }
        return flowNodes;
    }

    private void addFlowNodeToList(List<FlowNode> flowNodes, String flowNodeId) {
        try {
            FlowNode flowNode = bpmnModelInstance.getModelElementById(flowNodeId);
            flowNodes.add(flowNode);
        }
        catch (ClassCastException ex) {
            logger.warn("Id " + flowNodeId + " doesn't match any flow node");
        }
    }
}
