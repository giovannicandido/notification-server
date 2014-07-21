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
import entity.Application;
import model.CrudEJB;

import javax.ejb.EJB;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Map;
import java.util.Optional;

/**
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/21/14.
 */
@Path("/application")
public class ApplicationService {
    @EJB
    private CrudEJB crudEJB;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public RestResponse createApplication(@Valid Application application){
        crudEJB.save(application);
        return new RestResponse("Salvo com sucesso");
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse listApplication(){
        RestResponse resp = new RestResponse();
        resp.setData(crudEJB.findAll(Application.class));
        return resp;
    }
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse deleteApplication(@NotNull @QueryParam("id") Integer id){
        crudEJB.removeById(id, Application.class);
        return new RestResponse("Aplicação removida com sucesso");
    }
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse atualizarApplication(Map<String,String> app){
        Integer id;
        try {
            id = Integer.parseInt(app.get("id"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return new RestResponse("Id passado não é um numero", false);
        }
        Application appFinded = crudEJB.find(Application.class, id);
        if(appFinded == null){
            return new RestResponse("Applicação não pode ser encontrada", false);
        }
        appFinded.setDescription(app.get("description"));
        appFinded.setName(app.get("name"));
        Optional<String> key = Optional.ofNullable(app.get("key"));

        // Se não houver key ou esta for vazia não atualiza a key
        if(!key.orElse("").trim().equals("")){
            appFinded.setKey(app.get("key"));
        }
        crudEJB.save(appFinded);
        return new RestResponse("Applicação atualizada com sucesso");
    }
}
