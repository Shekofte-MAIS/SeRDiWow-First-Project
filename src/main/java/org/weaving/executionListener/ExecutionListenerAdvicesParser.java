package org.weaving.executionListener;

import org.exceptions.AspectFileException;
import org.w3c.dom.Node;
import org.weaving.base.AdvicesParser;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

public class ExecutionListenerAdvicesParser extends AdvicesParser<ExecutionListenerAdvice> {

    public ExecutionListenerAdvicesParser(String aspectFilePath) throws Exception {
        super(aspectFilePath);
    }

    @Override
    public List<ExecutionListenerAdvice> getAdvices() throws XPathExpressionException, AspectFileException {
        var advicesList = new ArrayList<ExecutionListenerAdvice>();
        var advicesNodes = xmlNodesLocatorUsingXPath.getNodes("//advice");
        for (int adviceIndex = 0; adviceIndex < advicesNodes.getLength(); adviceIndex++) {
            Node rootNode = advicesNodes.item(adviceIndex);
            var advice = getAdvice(rootNode);
            advicesList.add(advice);
        }
        return advicesList;
    }

    private ExecutionListenerAdvice getAdvice(Node rootNode) throws XPathExpressionException, AspectFileException {
        var executionListenerAdvice = new ExecutionListenerAdvice();
        var adviceType = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@type");
        var xPathExpression = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@xPathExpression");
        var javaClass = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./executionListener/@javaClass");
        executionListenerAdvice.setAdviceType(adviceType);
        executionListenerAdvice.xPathExpression = xPathExpression;
        executionListenerAdvice.javaClass = javaClass;
        return executionListenerAdvice;
    }
}
