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

import info.atende.nserver.config.logging.Logging;
import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.exceptions.EmailNotSendedException;
import info.atende.nserver.model.Counter;
import info.atende.nserver.model.MailMimeType;
import info.atende.nserver.model.Notification;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    @Autowired
    private Counter counter;

    public NotificationService(Notification notification) {
        this.notification = notification;
    }

    public NotificationService() {
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public ResponseEntity<String> sendEmail(@RequestParam(required = true) @NotBlank  String to,
                                            @RequestParam(required = true) @NotBlank  String subject,
                                            @RequestParam(required = true) @NotBlank  String message,
                                            String from,
                                            boolean html,
                                            @RequestParam(required = true) @NotBlank String token,
                                            HttpServletRequest hsr
    ) throws EmailNotSendedException {
        if(!notification.validateToken(token)){
            return ResponseEntity.badRequest().body("invalid token");
        }
        String[] toArray = to.split(";");
        MailMimeType mailMimeType = html ? MailMimeType.HTML : MailMimeType.TXT;
        notification.sendEmail(toArray, from, subject, message, mailMimeType);
        return ResponseEntity.ok("Sended Email");

    }

    @RequestMapping(value = "/test-email", method = RequestMethod.POST)
    public RestResponse sendEmailTest(@Email @NotBlank @RequestBody String to, HttpServletRequest hsr) throws EmailNotSendedException, ExecutionException, InterruptedException {
        String[] toArray = {to};
        String message = "Notification Server Email Test";
        Future<Boolean> booleanFuture = notification.sendEmail(toArray, null, "Email server test", message, MailMimeType.TXT);
        try {
            if(booleanFuture.get()){
                return new RestResponse("Email sended", true);
            }else{
                return new RestResponse("Can't send email", false);
            }
        }catch(Exception ex){
            return new RestResponse(ex.getMessage(), false);
        }

    }

    @RequestMapping(value = "/currentSending", method = RequestMethod.GET)
    public Counter.Total getCurrentSendedValue(){
        return counter.getTotal();
    }

}
