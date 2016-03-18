package info.atende.nserver.service;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;

import javax.enterprise.event.Event;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@Provider
public class DeltaSpikeExceptionMapper implements ExceptionMapper<Exception> {
    // Inject CDI Event
    @Inject
    private Event<ExceptionToCatchEvent> catchEvent;

    @Inject
    private Instance<Response> response;

    /*
     * (non-Javadoc)
     *
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     */
    @Override
    public Response toResponse(Exception exception) {
        catchEvent.fire(new ExceptionToCatchEvent(exception, RestRequestLiteral.INSTANCE));
        return response.get();
    }
}
