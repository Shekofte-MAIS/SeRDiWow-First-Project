import org.exceptions.AspectFileException;
import org.junit.jupiter.api.Test;
import org.utils.AdviceType;
import org.weaving.internalServiceTask.InternalServiceTaskAspectParser;

import static org.junit.jupiter.api.Assertions.*;

public class InternalServiceTaskAspectParserTest {
    @Test
    public void validAdvices_parseAdvices_assert() throws Exception {
        var aspectFilePath = "src//test//data//aspects//internalServiceTask//aspect.xml";
        var internalServiceTaskAdvicesParser = new InternalServiceTaskAspectParser(aspectFilePath);
        var advices = internalServiceTaskAdvicesParser.getAdvices();

        assertEquals(advices.size(), 2);
        var secondAdvice = advices.get(1);
        assertEquals(secondAdvice.adviceType, AdviceType.after);
        assertEquals(secondAdvice.xPathExpression, "//bpmn:process//bpmn:serviceTask[@id='enter-card-number']");
        assertEquals(secondAdvice.serviceTaskId, "check-card-number");
        assertEquals(secondAdvice.serviceTaskName, "Check card number");
        assertEquals(secondAdvice.javaClass, "com.example.workflow.CardNumberCheckerDelegate");
    }

    @Test
    public void invalidAdvices_parseAdvices_throwsAdvicesFileException() throws Exception {
        var aspectFilePath = "src//test//data//aspects//internalServiceTask//aspectContainingAdviceWithInvalidAdviceType.xml";
        var internalServiceTaskAdvicesParser = new InternalServiceTaskAspectParser(aspectFilePath);
        assertThrows(AspectFileException.class, internalServiceTaskAdvicesParser::getAdvices);
    }
}