package org.weaving.externalServiceTask;

import org.exceptions.AspectFileException;
import org.w3c.dom.Node;
import org.weaving.base.AdvicesParser;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

public class ExternalServiceTaskAdvicesParser extends AdvicesParser<ExternalServiceTaskAdvice> {
    public ExternalServiceTaskAdvicesParser(String aspectFilePath) throws Exception {
        super(aspectFilePath);
    }

    @Override
    public List<ExternalServiceTaskAdvice> getAdvices() throws XPathExpressionException, AspectFileException {
        var advicesList = new ArrayList<ExternalServiceTaskAdvice>();
        var advicesNodes = xmlNodesLocatorUsingXPath.getNodes("//advice");
        for (int adviceIndex = 0; adviceIndex < advicesNodes.getLength(); adviceIndex++) {
            Node rootNode = advicesNodes.item(adviceIndex);
            var advice = getAdvice(rootNode);
            advicesList.add(advice);
        }
        return advicesList;
    }

    private ExternalServiceTaskAdvice getAdvice(Node rootNode) throws XPathExpressionException, AspectFileException {
        var externalServiceTaskAdvice = new ExternalServiceTaskAdvice();
        var adviceType = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@type");
        var xPathExpression = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@xPathExpression");
        var serviceTaskId = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@id");
        var serviceTaskName = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@name");
        var topic = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@topic");
        externalServiceTaskAdvice.setAdviceType(adviceType);
        externalServiceTaskAdvice.xPathExpression = xPathExpression;
        externalServiceTaskAdvice.serviceTaskId = serviceTaskId;
        externalServiceTaskAdvice.serviceTaskName = serviceTaskName;
        externalServiceTaskAdvice.topic = topic;
        return externalServiceTaskAdvice;
    }
}
