package info.atende.nserver.service;

import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.exceptions.EmailNotSendedException;

import javax.servlet.http.HttpServletRequest;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public interface NotificationServiceInterface {
    public RestResponse sendEmail(String email,String subject,
                                  String message,  boolean html,
                                  String token,
                                  HttpServletRequest hsr
    ) throws EmailNotSendedException;
    public RestResponse sendEmailTest(String email, HttpServletRequest hsr) throws EmailNotSendedException;
}
