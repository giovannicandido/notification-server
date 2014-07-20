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

package entity;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa uma configuracao do sistema
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
@Entity
public class Config {
    @Id
    private String config;
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name="value")
    @CollectionTable(name="config_values")

    private Map<String,String> values = new HashMap<>();

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public Map<String, String> getValues() {
        return values;
    }

    public void setValues(Map<String, String> values) {
        this.values = values;
    }
}
