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

package info.atende.nserver.service;

import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.exceptions.EmailNotSendedException;
import info.atende.nserver.model.MailMimeType;
import info.atende.nserver.model.Notification;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Servico principal
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@RestController
@RequestMapping("/api/notification")
public class NotificationService implements NotificationServiceInterface {


    @Autowired
    private Notification notification;

    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public NotificationService() {
    }

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public RestResponse sendEmail(@NotBlank  String email, @NotBlank  String subject,
                                  @NotBlank  String message,  boolean html,
                                  @NotBlank String token,
                                  HttpServletRequest hsr
    ) throws EmailNotSendedException {
        if(!notification.validateToken(token)){
            throw new EmailNotSendedException("token inválido");
        }
        String[] to = email.split(";");
        MailMimeType mailMimeType = html ? MailMimeType.HTML : MailMimeType.TXT;
        notification.sendEmail(to, subject, message, mailMimeType);
        return new RestResponse("Email enviado");

    }
    @RequestMapping(value = "/test-email", method = RequestMethod.POST)
    public RestResponse sendEmailTest(@Email @NotBlank String email, HttpServletRequest hsr) throws EmailNotSendedException {
        String[] to = {email};
        String message = "Notification Server Email Test";
        notification.sendEmail(to, "Email server test", message, MailMimeType.TXT);
        return new RestResponse("Enviado com sucesso");

    }

}
