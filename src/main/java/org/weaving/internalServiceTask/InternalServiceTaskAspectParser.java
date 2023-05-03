package org.weaving.internalServiceTask;

import org.exceptions.AspectFileException;
import org.w3c.dom.Node;
import org.weaving.base.AspectParser;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

public class InternalServiceTaskAspectParser extends AspectParser<InternalServiceTaskAdvice> {
    public InternalServiceTaskAspectParser(String aspectFilePath) throws Exception {
        super(aspectFilePath);
    }

    public List<InternalServiceTaskAdvice> getAdvices() throws XPathExpressionException, AspectFileException {
        var advicesList = new ArrayList<InternalServiceTaskAdvice>();
        var advicesNodes = xmlNodesLocatorUsingXPath.getNodes("//advice");
        for (int adviceIndex = 0; adviceIndex < advicesNodes.getLength(); adviceIndex++) {
            Node rootNode = advicesNodes.item(adviceIndex);
            var advice = getAdvice(rootNode);
            advicesList.add(advice);
        }
        return advicesList;
    }

    private InternalServiceTaskAdvice getAdvice(Node rootNode) throws XPathExpressionException, AspectFileException {
        var internalServiceTaskAdvice = new InternalServiceTaskAdvice();
        var adviceType = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@type");
        var xPathExpression = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@xPathExpression");
        var serviceTaskId = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@id");
        var serviceTaskName = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@name");
        var javaClass = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@javaClass");
        internalServiceTaskAdvice.setAdviceType(adviceType);
        internalServiceTaskAdvice.xPathExpression = xPathExpression;
        internalServiceTaskAdvice.serviceTaskId = serviceTaskId;
        internalServiceTaskAdvice.serviceTaskName = serviceTaskName;
        internalServiceTaskAdvice.javaClass = javaClass;
        return internalServiceTaskAdvice;
    }
}
