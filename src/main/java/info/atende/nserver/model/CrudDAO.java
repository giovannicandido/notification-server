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

package info.atende.nserver.model;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;

/**
 * Provê transações CRUD para todos os serviços
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Component
@Transactional
@SuppressWarnings("unchecked")
public class CrudDAO {
    @PersistenceContext
    private EntityManager em;

    /**
     * Salvar entidade na base de dados
     * @param entity
     */
    public Object save(Object entity){
       return em.merge(entity);
    }

    /**
     * Procurar uma classe na base de dados pelo ID
     * @param klass Classe da Entidade
     * @param id ID da entidade
     * @param <T> Entidade a ser retornada
     * @return Entidade a ser retornada ou null caso não exista
     */
    public <T> T find(Class<T> klass, Object id){
       return em.find(klass, id);
    }

    /**
     * Remover uma entidade da base de dados pelo ID
     * @param id ID da entidade
     * @param klass Classe da entidade
     */
    public void removeById(Object id, Class klass){
        Object entity  = em.find(klass, id);
        em.remove(entity);
    }

    /**
     * Procurar todos os registros de uma entidade
     * @param klass Entidade a ser procurada
     * @param <T>
     * @return Collection de entidades
     */
    public <T> Collection<T> findAll(Class<T> klass){
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(klass);
        Root<T> rootEntry = cq.from(klass);
        CriteriaQuery<T> all = cq.select(rootEntry);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }
}
