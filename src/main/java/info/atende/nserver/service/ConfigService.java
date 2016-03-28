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
import info.atende.nserver.dto.EmailConfig;
import info.atende.nserver.dto.GeralConfig;
import info.atende.nserver.dto.RestResponse;
import info.atende.nserver.entity.Token;
import info.atende.nserver.model.CrudDAO;
import info.atende.webutil.jpa.Config;
import info.atende.webutil.jpa.ConfigUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;


/**
 * Serviço rest para salvar configurações
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@RestController
@RequestMapping("/api/config")
@SuppressWarnings("unchecked")
public class ConfigService {
    @Logging
    Logger logger;
    @Autowired
    private CrudDAO crud;

    @RequestMapping(value = "/email", method = RequestMethod.POST)
    public RestResponse salvar(@Valid EmailConfig config){
        crud.save(ConfigUtils.convertToConfig(config).get());
        return new RestResponse("Salvo com sucesso");
    }
    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public RestResponse getConfig(){
        Config config = crud.find(Config.class, EmailConfig.CONFIG_NAME);
        RestResponse resp = new RestResponse();
        if(config != null){
            ArrayList data = new ArrayList();
            Optional<EmailConfig> optional = ConfigUtils.parseConfig(config, EmailConfig.class);
            if(optional.isPresent()){
                data.add(optional.get());
            }
            resp.setData(data);
            resp.setSuccess(true);
        }else {
            resp.setSuccess(false);
            resp.setMessage("Configurações de email não criadas");
        }
        return resp;
    }
    @RequestMapping(value = "/geral", method = RequestMethod.POST)
    public RestResponse salvarGeral(@Valid GeralConfig config){
        crud.save(ConfigUtils.convertToConfig(config));
        return new RestResponse("Salvo com sucesso");
    }
    @RequestMapping(value = "/geral", method = RequestMethod.GET)
    public RestResponse getGeral(){
        Config config = crud.find(Config.class, GeralConfig.CONFIG_NAME);
        Optional<GeralConfig> geralConfig = ConfigUtils.parseConfig(config, GeralConfig.class);
        ArrayList data = new ArrayList();
        RestResponse restResponse = new RestResponse();
        if(geralConfig.isPresent()){

            data.add(geralConfig.get());
        }
        if(config != null){
           restResponse.setData(data);
           restResponse.setSuccess(true);
        }else{
            restResponse.setSuccess(false);
        }
        return restResponse;
    }

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public ResponseEntity<Object> generateToken(@RequestBody Token token){
        String id = UUID.randomUUID().toString();
        token.setToken(id);
        try {
            Token save = (Token) crud.save(token);
            return ResponseEntity.ok(save);
        }catch (Exception ex){
            Throwable rootCause = ExceptionUtils.getRootCause(ex);
            String message;
            if(rootCause != null){
                message = rootCause.getMessage();
            }else{
                message = ex.getMessage();
            }
            logger.error(message);
            return ResponseEntity.badRequest().body(message);
        }
    }
    @RequestMapping(value = "/token", method = RequestMethod.GET)
    public Collection<Token> getAllTokens(){
        return crud.findAll(Token.class);
    }
    @RequestMapping(value = "/token/{id}", method = RequestMethod.DELETE)
    public void deleteToken(@PathVariable String id){
        crud.removeById(id, Token.class);
    }

}
