package handles;

import info.atende.exceptions.EmailNotSendedException;
import org.apache.deltaspike.core.api.exception.control.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.Handles;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@ExceptionHandler
public class EmailNotSendedHandler {

    public void handleException(@Handles ExceptionEvent<EmailNotSendedException> evt, Response.ResponseBuilder builder){
        builder.status(500).entity(evt.getException().getMessage()).type(MediaType.TEXT_PLAIN);
        evt.handledAndContinue();
    }
}
