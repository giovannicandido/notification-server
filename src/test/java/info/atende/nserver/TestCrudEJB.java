package info.atende.nserver;/*
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

import info.atende.nserver.model.CrudDAO;
import info.atende.nserver.test.annotations.SpringIntegrationTest;
import info.atende.webutil.jpa.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

/**
 * Teste para CrudEJB
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@SuppressWarnings("unchecked")
@SpringIntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
public class TestCrudEJB {
    @PersistenceContext
    EntityManager em;

    @Autowired
    CrudDAO crudDAO;

    /**
     * Testa se o metodo salvar salva na base de dados
     */
    @Test
    public void salvar(){
        Config conf = new Config();
        conf.setConfig("new");
        crudDAO.save(conf);
        Config looked = em.find(Config.class, "new");
        Assert.assertNotNull(looked);
    }

    /**
     * Testa remover
     */
    @Test
    public void remover(){
        Config conf = new Config();
        conf.setConfig("newc");
        crudDAO.save(conf);
        Config looked = em.find(Config.class, "newc");
        Assert.assertNotNull(looked);
        crudDAO.removeById("newc", Config.class);
        looked = em.find(Config.class, "newc");
        Assert.assertNull(looked);
    }

    /**
     * Testa procurar
     */
    @Test
    public void find(){
        Config conf = new Config();
        conf.setConfig("newd");
        crudDAO.save(conf);
        Config looked = crudDAO.find(Config.class, "newd");
        Assert.assertNotNull(looked);
    }

    /**
     * Testa salvar todos
     */
    @Test
    public void saveAll(){
        Collection c = crudDAO.findAll(Config.class);
        Assert.assertTrue(c.size() > 0);
    }
}
