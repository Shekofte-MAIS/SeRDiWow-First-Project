import org.exceptions.AspectFileException;
import org.junit.jupiter.api.Test;
import org.utils.AdviceType;
import org.weaving.javaClassServiceTask.JavaClassServiceTaskAdvicesParser;

import static org.junit.jupiter.api.Assertions.*;

public class JavaClassServiceTaskAdvicesParserTest {
    @Test
    public void validAdvices_parseAdvices_assert() throws Exception {
        var aspectFilePath = "src//test//data//aspects//javaClassServiceTask//aspect.xml";
        var javaClassServiceTaskAdvicesParser = new JavaClassServiceTaskAdvicesParser(aspectFilePath);
        var advices = javaClassServiceTaskAdvicesParser.getAdvices();

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
        var aspectFilePath = "src//test//data//aspects//javaClassServiceTask//aspectContainingAdviceWithInvalidAdviceType.xml";
        var javaClassServiceTaskAdvicesParser = new JavaClassServiceTaskAdvicesParser(aspectFilePath);
        assertThrows(AspectFileException.class, javaClassServiceTaskAdvicesParser::getAdvices);
    }
}