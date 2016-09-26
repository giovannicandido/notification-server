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

package info.atende.nserver.model;

import info.atende.nserver.config.logging.Logging;
import info.atende.nserver.entity.Token;
import info.atende.nserver.exceptions.EmailNotSendedException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.concurrent.Future;

/**
 * Criado por Giovanni Silva <giovanni@atende.info>
 */
@Component
public class Notification {
    @Logging
    private Logger logger;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Counter counter;

    @Value("${mail.from}")
    private String from;

    /**
     * Envia um email para os destinatarios
     * @param to
     * @param subject
     * @param body
     * @param mailMimeType
     * @return
     */
    @Async
    public Future<Boolean> sendEmail(String[] to, String from, String subject, String body, MailMimeType mailMimeType) throws EmailNotSendedException {
        counter.incrementCurrentValue();
        try {
            MimeMessage message = mailSender.createMimeMessage();
            from = getFrom(from);
            message.setFrom(new InternetAddress(from));
            InternetAddress[] address = new InternetAddress[to.length];

            for (int i = 0; i < to.length; i++) {
                try {
                    address[i] = new InternetAddress(to[i]);
                } catch (Exception e) {
                   throw new EmailNotSendedException("Endereço de email inválido");
                }
            }
            message.setRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject, "UTF-8");
            message.setSentDate(new Date());

            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart bodyPart = new MimeBodyPart();
            switch (mailMimeType){
                case TXT:
                    bodyPart.setText(body, "UTF-8");
                    break;
                case HTML:
                    bodyPart.setContent(body, "text/html; charset=UTF-8");
                    break;
                default:
                    bodyPart.setText(body, "UTF-8");

            }

            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            mailSender.send(message);
            logger.info("Email enviado: " + "from " + from + " to " + getToAddressString(to) + " subject: " + subject);
            counter.incrementTotalSended();
            return new AsyncResult<>(true);

        } catch (Exception ex) {
            counter.incrementFailed();
            String messageEx = "Não foi possível enviar email: " + ex.getMessage();
            logger.error(messageEx);
            throw new EmailNotSendedException(messageEx);

        } finally {
            counter.decrementCurrentValue();
        }

    }


    private String getFrom(String from){
        if(from == null || from.trim().equals("")) {
            return this.from;
        } else {
            return from;
        }
    }
    
    private String getToAddressString(String[] to) {
        StringBuilder format = new StringBuilder();
        for(String s : to){
            format.append(s + ";");
        }
        return format.toString();
    }

    /**
     * Valida se o token existe
     * @param token Token para ser validado
     * @return Verdadeiro se token válido
     */
    public boolean validateToken(String token){
        Token tokenEntity = em.find(Token.class, token);
        return tokenEntity != null;
    }
}
