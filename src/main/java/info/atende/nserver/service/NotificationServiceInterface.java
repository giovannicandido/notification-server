package info.atende.nserver.service;

import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.exceptions.EmailNotSendedException;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public interface NotificationServiceInterface {
    /**
     * Send a email
     * @param email Email to send. Multiple emails separed by ;
     * @param subject Subject of the message
     * @param message Message to send
     * @param from From address, null if you want use the default
     * @param html If the message is Html content
     * @param token Token to validade the message
     * @param hsr HttpServelet Request
     * @return A 200 Ok response if the email is sended, 40x ou 50x if not
     * @throws EmailNotSendedException
     */
    public ResponseEntity<String> sendEmail(String email, String subject,
                                            String message,
                                            String from,
                                            boolean html,
                                            String token,
                                            HttpServletRequest hsr
    ) throws EmailNotSendedException;
    public RestResponse sendEmailTest(String email, HttpServletRequest hsr) throws EmailNotSendedException;
}
