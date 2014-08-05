package handles;

import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import java.util.logging.Logger;

import org.apache.deltaspike.core.api.exception.control.BeforeHandles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;

/**
 * This class log all exceptions
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@ExceptionHandler
public class LogExceptionHandler {
    void logExceptions(@BeforeHandles ExceptionEvent<Throwable> evt, Logger log) {
        log.warning("Something bad happened: " + evt.getException().getMessage());
        evt.handledAndContinue();
    }
}
