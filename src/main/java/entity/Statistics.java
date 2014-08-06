package entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Estatisticas de envio de notificacoes
 * Criado por Giovanni Silva <giovanni@pucminas.br>
 */
@Entity
@NamedQueries(
{
  @NamedQuery(name = "Statistic.byType",
          query = "SELECT new dto.GraphicCount(c.type, count(c)) FROM Statistics c group by c.type"),
  @NamedQuery(name = "Statistic.byApplication", query = "SELECT NEW dto.GraphicCount(s.userId, count(s)) FROM Statistics s group by s.userId")
}
)
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String userId;
    @Enumerated(EnumType.STRING)
    private TipoNotificacao type;
    private Date data;

    public Statistics() {
    }

    public Statistics(String userId, TipoNotificacao type) {
        this.type = type;
        this.userId = userId;
        data = new Date();
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public TipoNotificacao getType() {
        return type;
    }

    public void setType(TipoNotificacao type) {
        this.type = type;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
