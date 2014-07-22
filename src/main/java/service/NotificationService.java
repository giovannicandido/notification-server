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
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Servico principal
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Path("/notification")
public class NotificationService {
    public static final String INVALID_KEY_MESSAGE = "Invalid key or application";
    @EJB
    NotificationEJB notification;

    @Path("/email")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse sendEmail(@NotBlank @Email @FormParam("email") String email, @NotBlank @FormParam("subject") String subject,
                                  @NotBlank @FormParam("message") String message,
                                  @NotBlank @FormParam("application") String application, @NotBlank @FormParam("key") String key
                                  ){
        if(notification.validate(application, key)){
            String[] to = {email};
            boolean sended = notification.sendEmail(to, subject, message, MailMimeType.TXT);
            if(sended){
                return new RestResponse("Email enviado");
            }else{
                return new RestResponse("Falha ao enviar email", false);
            }

        }else {
            return new RestResponse(INVALID_KEY_MESSAGE, false);
        }
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse sendNotification(@FormParam("user") String user, @FormParam("subject") String subject,
                                         @FormParam("message") String message,
        @FormParam("application") String application, @FormParam("key") String key
    ){
        if(notification.validate(application, key)){
            return new RestResponse("Notificacao enviada");
        }else {
            return new RestResponse(INVALID_KEY_MESSAGE, false);
        }

    }

}
