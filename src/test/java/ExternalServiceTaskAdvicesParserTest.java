import org.junit.jupiter.api.Test;
import org.utils.AdviceType;
import org.weaving.externalServiceTask.ExternalServiceTaskAdvicesParser;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExternalServiceTaskAdvicesParserTest {
    @Test
    public void validAdvices_parseAdvices_assert() throws Exception {
        var aspectFilePath = "src//test//data//aspects//externalServiceTask//aspect.xml";
        var externalServiceTaskAdvicesParser = new ExternalServiceTaskAdvicesParser(aspectFilePath);
        var advices = externalServiceTaskAdvicesParser.getAdvices();

        assertEquals(advices.size(), 2);
        var secondAdvice = advices.get(1);
        assertEquals(secondAdvice.adviceType, AdviceType.after);
        assertEquals(secondAdvice.xPathExpression, "//bpmn:process//bpmn:serviceTask[@id='enter-card-number']");
        assertEquals(secondAdvice.serviceTaskId, "check-card-number");
        assertEquals(secondAdvice.serviceTaskName, "Check card number");
        assertEquals(secondAdvice.topic, "check-card-number-topic");
    }
}
