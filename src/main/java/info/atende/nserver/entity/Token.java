package info.atende.nserver.entity;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Token to promote a minimun of security and not let the service open
 * @author Giovanni Silva.
 */
@Entity
@Immutable
@Cacheable
public class Token {
    @Id
    @Column(length = 36, columnDefinition = "char(36)")
    private String token;
    @Column(length = 50)
    private String application;
    private LocalDateTime dateCreated;


    public Token(String token, String name) {
        this.token = token;
    }

    public Token() {
    }
    @PrePersist
    private void setCreatedDate(){
        this.dateCreated = LocalDateTime.now();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }
}
