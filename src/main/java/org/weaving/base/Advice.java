package org.weaving.base;

import org.exceptions.AspectFileException;
import org.utils.AdviceType;
import org.utils.ExceptionMessages;

public abstract class Advice {
    public AdviceType adviceType;
    public String xPathExpression;

    public void setAdviceType(String adviceType) throws AspectFileException {
        try {
            this.adviceType = AdviceType.valueOf(adviceType);
        }
        catch (IllegalArgumentException ex) {
            throw new AspectFileException(ExceptionMessages.getAspectFileGeneralExceptionMessage("advice type"));
        }
    }
}
