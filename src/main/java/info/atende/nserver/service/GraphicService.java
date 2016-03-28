package info.atende.nserver.service;

import info.atende.nserver.dto.RestResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * REST Service para graficos
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@RestController
@RequestMapping("/api/graphic")
public class GraphicService {
    @PersistenceContext
    private EntityManager em;

    @RequestMapping(value="/apps", method = RequestMethod.GET)
    public RestResponse notificacoesPorAplicacao(){
        List graphicCounts = em.createNamedQuery("Statistic.byApplication").getResultList();
        return new RestResponse(graphicCounts);
    }
    @RequestMapping(value="/type", method = RequestMethod.GET)
    public RestResponse notificacoesPorTipo(){
      List list = em.createNamedQuery("Statistic.byType").getResultList();
      return new RestResponse(list);
    }
    @RequestMapping(value="/apptime", method = RequestMethod.GET)
    public RestResponse notificacoesPorAppTime(String id){
        List list = em.createNamedQuery("Statistic.byApplicationTime")
                .setParameter("userId", id)
                .getResultList();
        return new RestResponse(list);
    }
    @RequestMapping(value="/userlist", method = RequestMethod.GET)
    public RestResponse usersList(){
        List list = em.createNamedQuery("Statistic.userList")
                .getResultList();
        return new RestResponse(list);
    }

}
