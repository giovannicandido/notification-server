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

package info.atende.nserver.dto;


import info.atende.nserver.model.Protocol;
import info.atende.nserver.validations.NotNullIfAnotherFieldHasValue;
import info.atende.webutil.jpa.ConfigDTO;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Classe para armazenamento de configuracoes de email
 * Criado por Giovanni Silva <giovanni@atende.info>
 *
 */
@NotNullIfAnotherFieldHasValue.List({
        @NotNullIfAnotherFieldHasValue(
                fieldName = "needAuthentication",
                fieldValue = "true",
                dependFieldName = "login"),
        @NotNullIfAnotherFieldHasValue(
                fieldName = "needAuthentication",
                fieldValue = "true",
                dependFieldName = "password")
})
public class EmailConfig implements ConfigDTO {
    public static final String CONFIG_NAME = "email_config";
    @NotBlank
    private String host;
    @NotNull
    private Protocol protocol;
    @NotNull
    private Integer port;
    private String login;
    private String password;
    @NotNull
    private Boolean needAuthentication;
    @NotNull
    private String sender;
    private boolean debug = false;

    public EmailConfig(String host, Protocol protocol, Integer port, String login, String password,
                       Boolean needAuthentication, String sender, boolean debug) {
        this.host = host;
        this.protocol = protocol;
        this.port = port;
        this.login = login;
        this.password = password;
        this.needAuthentication = needAuthentication;
        this.sender = sender;
        this.debug = debug;
    }

    public EmailConfig() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailConfig that = (EmailConfig) o;

        if (debug != that.debug) return false;
        if (host != null ? !host.equals(that.host) : that.host != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (needAuthentication != null ? !needAuthentication.equals(that.needAuthentication) : that.needAuthentication != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (port != null ? !port.equals(that.port) : that.port != null) return false;
        if (protocol != that.protocol) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = host != null ? host.hashCode() : 0;
        result = 31 * result + (protocol != null ? protocol.hashCode() : 0);
        result = 31 * result + (port != null ? port.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (needAuthentication != null ? needAuthentication.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (debug ? 1 : 0);
        return result;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getNeedAuthentication() {
        return needAuthentication;
    }

    public void setNeedAuthentication(Boolean needAuthentication) {
        this.needAuthentication = needAuthentication;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean getDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public String configName() {
        return CONFIG_NAME;
    }
}
