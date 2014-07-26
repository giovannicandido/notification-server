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
import model.MailMimeType;
import model.NotificationEJB;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Servico principal
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Path("/notification")
public class NotificationService {
    @EJB
    NotificationEJB notification;

    @Path("/email")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse sendEmail(@NotBlank @Email @FormParam("email") String email, @NotBlank @FormParam("subject") String subject,
                                  @NotBlank @FormParam("message") String message
    ) {
        String[] to = {email};
        boolean sended = notification.sendEmail(to, subject, message, MailMimeType.TXT);
        if (sended) {
            return new RestResponse("Email enviado");
        } else {
            return new RestResponse("Falha ao enviar email", false);
        }

    }
    @Path("/test-email")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse sendEmailTest(@Email @NotBlank @QueryParam("email") String email){
        String[] to = {email};
        String message = "Notification Server Email Test";
        boolean sended = notification.sendEmail(to, "Email server test", message, MailMimeType.TXT);
        if(sended){
            return new RestResponse("Enviado com sucesso");
        }else{
            return new RestResponse("Não foi possível enviar o email, um erro ocorreu. " +
                    "Cheque os logs, se tiver habilitado o debug", false);
        }


    }

}
