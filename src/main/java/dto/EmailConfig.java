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

package dto;


import entity.Config;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Classe para armazenamento de configuracoes de email
 * Criado por Giovanni Silva <giovanni@atende.info>
 *
 */
public class EmailConfig {
    public static final String CONFIG_NAME = "email_config";
    @NotBlank
    private String url;
    private Boolean tls;
    @NotBlank
    private Integer porta;
    @NotBlank
    private String login;
    @NotBlank
    private String password;

    public EmailConfig(String url, Boolean tls, Integer porta, String login, String password) {
        this.url = url;
        this.tls = tls;
        this.porta = porta;
        this.login = login;
        this.password = password;
    }

    public EmailConfig() {
    }
    public Config convertToConfig(){
       Config conf = new Config();
       conf.setConfig(EmailConfig.CONFIG_NAME);
       conf.getValues().put("url", url);
        if(porta != null){
            conf.getValues().put("porta", porta.toString());
        }
        if(tls != null){
            conf.getValues().put("tls", tls.toString());
        }

       conf.getValues().put("login", login);
       conf.getValues().put("password", password);
       return conf;
    }
    public EmailConfig(Config conf){
        parseConfig(conf);
    }

    /**
     * Acessa todos os valores de Config e convert para os respectivos valores de EmailConfig
     * Ao chamar esse método os valores de EmailConfig passarão a conter os valores de Config
     * @param conf
     */
    public void parseConfig(Config conf){
        if(!conf.getConfig().equalsIgnoreCase(EmailConfig.CONFIG_NAME)){
            throw new RuntimeException("Config is not an EmailConfig");
        }
        this.url = conf.getValues().get("url");
        Integer porta = null;
        Boolean tls = null;
        try {
            porta = Integer.parseInt(conf.getValues().get("porta"));
        }catch(Exception e){

        }
        try{
            tls = Boolean.parseBoolean(conf.getValues().get("tls"));
        }catch(Exception e){

        }


        this.porta = porta;
        this.tls = tls;
        this.login = conf.getValues().get("login");
        this.password = conf.getValues().get("url");
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getTls() {
        return tls;
    }

    public void setTls(Boolean tls) {
        this.tls = tls;
    }

    public Integer getPorta() {
        return porta;
    }

    public void setPorta(Integer porta) {
        this.porta = porta;
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
}
