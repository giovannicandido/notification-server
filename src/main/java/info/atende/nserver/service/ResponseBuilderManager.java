package info.atende.nserver.service;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.ws.rs.core.Response;

/**
 * A request-scoped resource for customizing an REST error response from within an exception handler.
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@RequestScoped
public class ResponseBuilderManager {
    private Response.ResponseBuilder responseBuilder;

    private Response response;

    @Produces
    @RequestScoped
    public Response.ResponseBuilder getResponseBuilder() {
        return responseBuilder;
    }

    @Produces
    public Response buildResponse() {
        if (response == null) {
            // the builder is reset upon build()
            // therefore, we cache the response
            response = responseBuilder.build();
        }
        return response;
    }

    @PostConstruct
    public void initialize() {
        responseBuilder = Response.serverError();
    }
}
