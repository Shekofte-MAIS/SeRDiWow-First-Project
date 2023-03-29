import org.utils.XmlNodesLocatorUsingXPath;
import org.junit.jupiter.api.Test;

import javax.xml.xpath.XPathExpressionException;

import static org.junit.jupiter.api.Assertions.*;
class XmlNodesLocatorUsingXPathTest {
    @Test
    void validXPath_getNodesIds_assert() throws Exception {
        var xmlNodesLocatorUsingXPath = new XmlNodesLocatorUsingXPath("src//test//data//bpmn-sample.bpmn");
        var xPathExpression =  "//bpmn:process//bpmn:userTask[@name='Enter pin code']";
        var nodesIds = xmlNodesLocatorUsingXPath.getNodesIds(xPathExpression);
        assertEquals(nodesIds.size(), 1);
        assertEquals("enter-pin-code", nodesIds.get(0));
    }

    @Test
    void xPathExpressionWithNoMatchingNode_getNodeValue_throwXPathException() throws Exception {
        var xmlNodesLocatorUsingXPath = new XmlNodesLocatorUsingXPath("src//test//data//bpmn-sample.bpmn");

        var nodeList = xmlNodesLocatorUsingXPath.getNodes("//advice");
        var firstNode = nodeList.item(0);
        assertThrows(XPathExpressionException.class, () -> xmlNodesLocatorUsingXPath.getNodeValue(firstNode, "./@something"));
    }

    @Test
    void validXPath_getNodeValue_assert() throws Exception {
        var xmlNodesLocatorUsingXPath = new XmlNodesLocatorUsingXPath("src//test//data//aspects//javaClassServiceTask//aspect.xml");

        var nodeList = xmlNodesLocatorUsingXPath.getNodes("//advice");
        var firstNode = nodeList.item(0);
        assertEquals("before", xmlNodesLocatorUsingXPath.getNodeValue(firstNode, "./@type"));
        assertEquals("check-pin-code", xmlNodesLocatorUsingXPath.getNodeValue(firstNode, ".//@id"));
    }
}