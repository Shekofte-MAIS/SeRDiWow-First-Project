package org.weaving.base;

import org.exceptions.AspectFileException;
import org.utils.XmlNodesLocatorUsingXPath;

import javax.xml.xpath.XPathExpressionException;
import java.util.List;

public abstract class AspectParser<T extends Advice> {
    protected XmlNodesLocatorUsingXPath xmlNodesLocatorUsingXPath;

    protected AspectParser(String aspectFilePath) throws Exception {
        xmlNodesLocatorUsingXPath = new XmlNodesLocatorUsingXPath(aspectFilePath);
    }

    public abstract List<T> getAdvices() throws XPathExpressionException, AspectFileException;
}
