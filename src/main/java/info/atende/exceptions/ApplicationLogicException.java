package info.atende.exceptions;

/**
 * This class is a mark for application exceptions. This execeptions are predicted and expected to happen
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public class ApplicationLogicException extends Exception {
    private static final long serialVersionUID = -81928391239100L;
    public ApplicationLogicException(String message){
        super(message);
    }
}
