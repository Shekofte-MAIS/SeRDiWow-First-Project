package org.weaving.javaClassServiceTask;

import org.exceptions.AspectFileException;
import org.w3c.dom.Node;
import org.weaving.base.AdvicesParser;

import javax.xml.xpath.XPathExpressionException;
import java.util.ArrayList;
import java.util.List;

public class JavaClassServiceTaskAdvicesParser extends AdvicesParser<JavaClassServiceTaskAdvice> {
    public JavaClassServiceTaskAdvicesParser(String aspectFilePath) throws Exception {
        super(aspectFilePath);
    }

    public List<JavaClassServiceTaskAdvice> getAdvices() throws XPathExpressionException, AspectFileException {
        var advicesList = new ArrayList<JavaClassServiceTaskAdvice>();
        var advicesNodes = xmlNodesLocatorUsingXPath.getNodes("//advice");
        for (int adviceIndex = 0; adviceIndex < advicesNodes.getLength(); adviceIndex++) {
            Node rootNode = advicesNodes.item(adviceIndex);
            var advice = getAdvice(rootNode);
            advicesList.add(advice);
        }
        return advicesList;
    }

    private JavaClassServiceTaskAdvice getAdvice(Node rootNode) throws XPathExpressionException, AspectFileException {
        var javaClassServiceTaskAdvice = new JavaClassServiceTaskAdvice();
        var adviceType = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@type");
        var xPathExpression = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./@xPathExpression");
        var serviceTaskId = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@id");
        var serviceTaskName = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@name");
        var javaClass = xmlNodesLocatorUsingXPath.getNodeValue(rootNode, "./serviceTask/@javaClass");
        javaClassServiceTaskAdvice.setAdviceType(adviceType);
        javaClassServiceTaskAdvice.xPathExpression = xPathExpression;
        javaClassServiceTaskAdvice.serviceTaskId = serviceTaskId;
        javaClassServiceTaskAdvice.serviceTaskName = serviceTaskName;
        javaClassServiceTaskAdvice.javaClass = javaClass;
        return javaClassServiceTaskAdvice;
    }
}
