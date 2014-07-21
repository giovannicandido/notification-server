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

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

/**
 * Model an application that communicate with the system.
 * The key of application changes in periods of time, if a application request a key and this key need change,
 * the system could initiate a changeKey process. The application and the notification server need to agree in a new key.
 * This is current not implemented
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/21/14.
 */
@Entity
@SequenceGenerator(name = "application_seq", sequenceName = "application_seq")
@NamedQueries({
    @NamedQuery(name="Application.findByName", query = "SELECT a FROM Application a where a.name = :name")
})
public class Application {
    public static final Long DEFAULT_KEY_LIFETIME = 120L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_seq")
    private Integer Id;
    @Column(nullable = false, unique = true, length = 12)
    @NotBlank
    private String name;
    @Column(length = 140)
    private String description;
    @NotBlank
    @Column(length = 45, nullable = false)

    private String key;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastKeyChange;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setKey(String key) {
        this.key = key;
        this.lastKeyChange = new Date();

    }
    @Transient
    public boolean isKeyValid(String key){
        if(this.key == null || key == null){
            return false;
        }
        if(key.equals(this.key)){
            return true;
        }
        return false;
    }

    public Date getLastKeyChange() {
        return lastKeyChange;
    }

    /**
     * Verify if key need to change
     * @return
     */
    public boolean keyNeedChange(){
        if(lastKeyChange == null){
            return true;
        }
        Instant lastKeyChange = Instant.ofEpochMilli(this.lastKeyChange.getTime());
        Duration duration = Duration.between(lastKeyChange, Instant.now());
        if(duration.compareTo(getTimeToChange()) > 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Return the lifetime of a key
     * @return
     */
    @Transient
    @XmlTransient
    public Duration getTimeToChange(){
        return Duration.ofSeconds(DEFAULT_KEY_LIFETIME);
    }

}
