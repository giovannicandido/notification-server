package service;

import dto.RestResponse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * REST Service para graficos
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@Path("/graphic")
public class GraphicService {
    @PersistenceContext
    private EntityManager em;
    @Path("/apps")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse notificacoesPorAplicacao(){
        List graphicCounts = em.createNamedQuery("Statistic.byApplication").getResultList();
        return new RestResponse(graphicCounts);
    }
    @Path("/type")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse notificacoesPorTipo(){
      List list = em.createNamedQuery("Statistic.byType").getResultList();
      return new RestResponse(list);
    }
    @Path("/apptime")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse notificacoesPorAppTime(@QueryParam("id") String id){
        List list = em.createNamedQuery("Statistic.byApplicationTime")
                .setParameter("userId", id)
                .getResultList();
        return new RestResponse(list);
    }
    @Path("/userlist")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public RestResponse usersList(){
        List list = em.createNamedQuery("Statistic.userList")
                .getResultList();
        return new RestResponse(list);
    }

}
