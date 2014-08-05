package service;

import javax.enterprise.util.AnnotationLiteral;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class RestRequestLiteral extends AnnotationLiteral<RestRequest> implements RestRequest {
    private static final long serialVersionUID = -6229036106752634991L;

    public static final RestRequest INSTANCE = new RestRequestLiteral();

    private RestRequestLiteral() {
    }
}
