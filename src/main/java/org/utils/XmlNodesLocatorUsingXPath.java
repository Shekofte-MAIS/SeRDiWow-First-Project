package org.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XmlNodesLocatorUsingXPath {
    private final Document document;
    private final XPath xPath;

    public XmlNodesLocatorUsingXPath(String xmlFilePath) throws Exception {
        var documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true); //necessary when a namespace is used
        var documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(xmlFilePath);
        xPath = XPathFactory.newInstance().newXPath();
        xPath.setNamespaceContext(new NamespaceContext() {
            @Override
            public Iterator getPrefixes(String arg0) {
                return null;
            }

            @Override
            public String getPrefix(String arg0) {
                return null;
            }

            @Override
            public String getNamespaceURI(String arg0) {
                if(arg0.equals("bpmn"))
                    return "http://www.omg.org/spec/BPMN/20100524/MODEL";
                return null;
            }
        });
    }

    public NodeList getNodes(String xPathExpression) throws XPathExpressionException {
        return (NodeList) xPath.compile(xPathExpression).evaluate(document, XPathConstants.NODESET);
    }

    public List<String> getNodesIds(String xPathExpression) throws XPathExpressionException {
        xPathExpression = xPathExpression + "//@id";
        return getNodesValues(xPathExpression);
    }

    public String getNodeValue(Node rootNode, String xPathExpression) throws XPathExpressionException {
        String node = xPath.compile(xPathExpression).evaluate(rootNode);
        if (node == null) {
            throw new XPathExpressionException(ExceptionMessages.getNoNodeMatchesTheXPathExpressionExceptionMessage(xPathExpression));
        }
        return node;
    }

     private List<String> getNodesValues(String xPathExpression) throws XPathExpressionException {
        var nodeList = getNodes(xPathExpression);
        var nodesList = new ArrayList<String>();
        for (int nodeListIndex = 0; nodeListIndex < nodeList.getLength(); nodeListIndex++)
            nodesList.add(nodeList.item(nodeListIndex).getNodeValue());

        return nodesList;
    }
}
