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

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Provê transações CRUD para todos os serviços
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Stateless
public class CrudEJB {
    @PersistenceContext
    private EntityManager em;
    public void save(Object entity){
       em.merge(entity);
    }
    public <T> T find(Class<T> klass, Object id){
       return em.find(klass, id);
    }
    public void removeById(Object id, Class klass){
        Object entity  = em.find(klass, id);
        em.remove(entity);
    }
    public <T> Collection<T> findAll(Class<T> klass){
        return new ArrayList<>();
    }
}
