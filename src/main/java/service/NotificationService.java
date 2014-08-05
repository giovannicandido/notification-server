/*
 * Copyright (c) 2014. Atende Tecnologia da Informação e Prestação de Serviços.
 *
 * Aviso. Este software está protegido por leis de direitos autorais e tratados internacionais.
 * A reprodução ou distribuição deste programa, ou qualquer parte dele, pode resultar em severas
 * penalidade civis e criminais e serão processadas sob a medida máxima prossível sob a lei.
 *
 * Warning: This computer program is protected by copyright law and international treaties. Unauthorized
 * reproduction or distribution of this program, or any portion of it, may result in severe civil and
 * criminal penalties, and will be prosecuted under the maximum extent possible under law.
 */

package service;

import dto.RestResponse;
import info.atende.exceptions.EmailNotSendedException;
import model.MailMimeType;
import model.NotificationEJB;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servico principal
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Path("/notification")
public class NotificationService implements NotificationServiceInterface {
    @EJB
    NotificationEJB notification;

    @Path("/email")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse sendEmail(@NotBlank @FormParam("email") String email, @NotBlank @FormParam("subject") String subject,
                                  @NotBlank @FormParam("message") String message, @FormParam("html") boolean html,
                                  @Context HttpServletRequest hsr
    ) throws EmailNotSendedException {
        String[] to = email.split(";");
        MailMimeType mailMimeType = html ? MailMimeType.HTML : MailMimeType.TXT;
        notification.sendEmail(to, subject, message, mailMimeType);
        return new RestResponse("Email enviado");

    }
    @Path("/test-email")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse sendEmailTest(@Email @NotBlank @QueryParam("email") String email, @Context HttpServletRequest hsr) throws EmailNotSendedException {
        String[] to = {email};
        String message = "Notification Server Email Test";
        notification.sendEmail(to, "Email server test", message, MailMimeType.TXT);
        return new RestResponse("Enviado com sucesso");



    }

}
