package info.atende.nserver.service;

import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.exceptions.EmailNotSendedException;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.servlet.http.HttpServletRequest;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public interface NotificationServiceInterface {
    public RestResponse sendEmail(@NotBlank String email, @NotBlank  String subject,
                                  @NotBlank  String message,  boolean html, HttpServletRequest hsr
    ) throws EmailNotSendedException;
    public RestResponse sendEmailTest(@Email @NotBlank String email, HttpServletRequest hsr) throws EmailNotSendedException;
}
