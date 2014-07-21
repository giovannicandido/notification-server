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

import entity.Application;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Stateless
public class NotificationEJB {
    @PersistenceContext
    EntityManager em;
    /**
     * Valida se a chave é válida para aplicação
     * @param application
     * @param key
     * @return
     */
    public boolean validate(String application, String key){
        if(application == null || key == null){
            return false;
        }
        Query q = em.createNamedQuery("Application.findByName").setParameter("name", application);
        Application app = (Application) q.getSingleResult();
        if(app != null){
            return app.isKeyValid(key);
        }else {
            return false;
        }
    }
}
