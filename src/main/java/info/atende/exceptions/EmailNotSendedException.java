package info.atende.exceptions;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class EmailNotSendedException extends ApplicationLogicException {
    public EmailNotSendedException(String message) {
        super(message);
    }
}
