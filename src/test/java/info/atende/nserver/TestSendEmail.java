package info.atende.nserver;/*
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

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import info.atende.nserver.exceptions.EmailNotSendedException;
import info.atende.nserver.model.MailMimeType;
import info.atende.nserver.model.Notification;
import info.atende.nserver.test.annotations.SpringIntegrationTest;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/22/14.
 */
@SpringIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestSendEmail {
    @Autowired
    private Notification notification;
    private static GreenMail greenMail;

    @BeforeClass
    public static void init(){
        greenMail = new GreenMail();
        greenMail.start();
    }
    @AfterClass
    public static void shutdown(){
        greenMail.stop();
    }

    @Test
    public void sendEmail() throws MessagingException, EmailNotSendedException {

        String to[] = {"giovanni@atende.info","alberto@testdomain.com.br"};
        notification.sendEmail(to, "test", "body", MailMimeType.TXT);
        MimeMessage mimeMessage = greenMail.getReceivedMessages()[0];
        Assert.assertTrue(GreenMailUtil.getBody(mimeMessage).contains("body"));
        Assert.assertEquals("test", mimeMessage.getSubject());
        Assert.assertEquals("no-reply@test.com", mimeMessage.getFrom()[0].toString());
        Assert.assertEquals("giovanni@atende.info", mimeMessage.getRecipients(Message.RecipientType.TO)[0].toString());
        Assert.assertEquals("alberto@testdomain.com.br", mimeMessage.getRecipients(Message.RecipientType.TO)[1].toString());


    }
}
