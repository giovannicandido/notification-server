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

package model;

import dto.EmailConfig;
import info.atende.exceptions.EmailNotSendedException;
import info.atende.webutil.jpa.Config;
import info.atende.webutil.jpa.ConfigUtils;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Date;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Criado por Giovanni Silva <giovanni@atende.info>
 */
@Stateless
public class NotificationEJB {
    @Inject
    Logger logger;
    @PersistenceContext
    EntityManager em;
    private EmailConfig emailConfig = null;

    @PostConstruct
    public void init() {
        emailConfig = loadConfig();
    }


    private EmailConfig loadConfig() {
        Config conf = em.find(Config.class, EmailConfig.CONFIG_NAME);
        EmailConfig emailConfig = null;
        if (conf != null) {
            emailConfig = ConfigUtils.parseConfig(conf, EmailConfig.class).get();
        }
        return emailConfig;
    }

    /**
     * Envia um email para os destinatarios
     * @param to
     * @param subject
     * @param body
     * @param mailMimeType
     * @return
     */
    public void sendEmail(String[] to, String subject, String body, MailMimeType mailMimeType) throws EmailNotSendedException {
        if(emailConfig ==  null){
            throw new EmailNotSendedException("Servidor de email não está configurado");
        }
        // Validar configuracoes de email
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<EmailConfig>> violations = validator.validate(emailConfig);
        if(violations.size() > 0){
            throw new EmailNotSendedException("Configurações de Email Incorretas ao tentar enviar");
        }
        Properties props = new Properties();
        props.put("mail.smtp.host", emailConfig.getHost());
        props.put("mail.smtp.port", emailConfig.getPort());
        if (emailConfig.getProtocol() == Protocol.TLS) {
            props.put("mail.smtp.starttls.enable", "true");
        }else if(emailConfig.getProtocol() == Protocol.SMTPS){
            props.put("mail.smtp.ssl.enable", "true");
        }
        final String username = emailConfig.getLogin();
        final String password = emailConfig.getPassword();
        final String from = emailConfig.getSender();
        Authenticator authenticator = null;
        if(emailConfig.getNeedAuthentication()){

            props.put("mail.smtp.auth", "true");
            authenticator = new Authenticator() {
                private PasswordAuthentication pa = new PasswordAuthentication(username, password);

                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    return pa;
                }
            };
        }

        Session session = Session.getInstance(props, authenticator);
        session.setDebug(emailConfig.getDebug());
        MimeMessage message = new MimeMessage(session);
        try {
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
            message.setSubject(subject);
            message.setSentDate(new Date());

            Multipart multipart = new MimeMultipart("alternative");

            MimeBodyPart bodyPart = new MimeBodyPart();

            switch (mailMimeType){
                case TXT:
                    bodyPart.setText(body);
                    break;
                case HTML:
                    bodyPart.setContent(body, "text/html");
                    break;
                default:
                    bodyPart.setText(body);

            }

            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);
            Transport.send(message);

        } catch (MessagingException ex) {
            throw new EmailNotSendedException("Não foi possível enviar email: " + ex.getMessage());

        }

    }

    public EmailConfig getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(EmailConfig emailConfig) {
        this.emailConfig = emailConfig;
    }
}
