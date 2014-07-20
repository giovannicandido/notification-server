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
import dto.EmailConfig;
import entity.Config;
import model.CrudEJB;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;


/**
 * Serviço rest para salvar configurações
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Path("/config")
public class ConfigService {
    @EJB
    private CrudEJB crud;

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse salvar(@Valid EmailConfig config){
        crud.save(config.convertToConfig());
        return new RestResponse("Salvo com sucesso");
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse getConfig(){
        Config config = crud.find(Config.class, EmailConfig.CONFIG_NAME);
        RestResponse resp = new RestResponse();
        if(config != null){
            ArrayList data = new ArrayList();
            data.add(new EmailConfig(config));
            resp.setData(data);
            resp.setSuccess(true);
        }else {
            resp.setSuccess(false);
            resp.setMessage("Configurações de email não criadas");
        }
        return resp;
    }
}
