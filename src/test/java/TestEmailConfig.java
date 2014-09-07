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

import dto.EmailConfig;
import info.atende.webutil.jpa.Config;
import info.atende.webutil.jpa.ConfigUtils;
import model.Protocol;
import org.junit.Assert;
import org.junit.Test;

/**
 * Para testar EmailConfig
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/22/14.
 */
public class TestEmailConfig {
    @Test
    public void convertToConfig(){
        EmailConfig emailConfig = new EmailConfig("google.com", Protocol.TLS, 465, "login", "password", true,
                "no-reply@pucminas.br", true);

        Config convert = ConfigUtils.convertToConfig(emailConfig).get();
        Assert.assertEquals(EmailConfig.CONFIG_NAME, convert.getConfig());
        Assert.assertEquals("google.com", convert.getValues().get("host"));
        Assert.assertEquals("TLS", convert.getValues().get("protocol"));
        Assert.assertEquals("465", convert.getValues().get("port"));
        Assert.assertEquals("login", convert.getValues().get("login"));
        Assert.assertEquals("password", convert.getValues().get("password"));
        Assert.assertEquals("true", convert.getValues().get("needAuthentication"));
        Assert.assertEquals("no-reply@pucminas.br", convert.getValues().get("sender"));
        Assert.assertEquals("true", convert.getValues().get("debug"));

    }
    @Test
    public void parseConfig(){
        EmailConfig emailConfig = new EmailConfig("google.com", Protocol.SMTP, 465, "login", "password", true,
                "no-reply@pucminas.br", true);

        Config config = new Config();
        config.setConfig(EmailConfig.CONFIG_NAME);
        config.getValues().put("host", "google.com");
        config.getValues().put("protocol","SMTP");
        config.getValues().put("login","login");
        config.getValues().put("password","password");
        config.getValues().put("needAuthentication","true");
        config.getValues().put("sender","no-reply@pucminas.br");
        config.getValues().put("debug","true");
        config.getValues().put("port","465");

        EmailConfig converted = ConfigUtils.parseConfig(config, EmailConfig.class).get();

        Assert.assertEquals(emailConfig.getHost(), converted.getHost());
        Assert.assertEquals(emailConfig.getProtocol(), converted.getProtocol());
        Assert.assertEquals(emailConfig.getLogin(), converted.getLogin());
        Assert.assertEquals(emailConfig.getPassword(), converted.getPassword());
        Assert.assertEquals(emailConfig.getSender(), converted.getSender());
        Assert.assertEquals(emailConfig.getDebug(), converted.getDebug());
        Assert.assertEquals(emailConfig.getNeedAuthentication(), converted.getNeedAuthentication());
        Assert.assertEquals(emailConfig.getPort(), converted.getPort());
        Assert.assertEquals(emailConfig, converted);
    }
    @Test
    public void convertBetweenEmailConfigAndConfig(){
        EmailConfig emailConfig = new EmailConfig("google.com", Protocol.TLS, 465, "login", "password", true,
                "no-reply@pucminas.br", true);
        Config config = ConfigUtils.convertToConfig(emailConfig).get();
        EmailConfig converted = ConfigUtils.parseConfig(config, EmailConfig.class).get();
        Assert.assertEquals(converted, emailConfig);

    }
}
