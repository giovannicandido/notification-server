package service;

import dto.RestResponse;
import info.atende.exceptions.EmailNotSendedException;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;

/**
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
public interface NotificationServiceInterface {
    public RestResponse sendEmail(@NotBlank @FormParam("email") String email, @NotBlank @FormParam("subject") String subject,
                                  @NotBlank @FormParam("message") String message, @FormParam("html") boolean html,
                                  @Context HttpServletRequest hsr
    ) throws EmailNotSendedException;
    public RestResponse sendEmailTest(@Email @NotBlank @QueryParam("email") String email, @Context HttpServletRequest hsr) throws EmailNotSendedException;
}
