import org.junit.jupiter.api.Test;
import org.utils.AdviceType;
import org.weaving.executionListener.ExecutionListenerAspectParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExecutionListenerAspectParserTest {
    @Test
    public void validAdvices_parseAdvices_assert() throws Exception {
        var aspectFilePath = "src//test//data//aspects//executionListener//aspect.xml";
        var executionListenerAdvicesParser = new ExecutionListenerAspectParser(aspectFilePath);
        var advices = executionListenerAdvicesParser.getAdvices();

        assertEquals(advices.size(), 2);
        var secondAdvice = advices.get(1);
        assertEquals(secondAdvice.adviceType, AdviceType.after);
        assertEquals(secondAdvice.xPathExpression, "//bpmn:process//bpmn:serviceTask[@id='enter-card-number']");
        assertEquals(secondAdvice.javaClass, "com.example.workflow.EndEnterCardNumberTaskListener");
    }
}
