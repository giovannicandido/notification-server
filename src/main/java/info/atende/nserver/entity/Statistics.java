package info.atende.nserver.entity;

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
          query = "SELECT new info.atende.nserver.dto.GraphicCount(c.type, count(c)) FROM Statistics c group by c.type"),
  @NamedQuery(name = "Statistic.byApplication",
          query = "SELECT NEW info.atende.nserver.dto.GraphicCount(s.userId, count(s)) FROM Statistics s group by s.userId"),
  @NamedQuery(name ="Statistic.byApplicationTime",
          query = "SELECT NEW info.atende.nserver.dto.GraphicCount(to_char(s.date, 'dd-mm-yyyy'), count(s)) FROM Statistics s WHERE s.userId = :userId group by to_char(s.date, 'dd-mm-yyyy') order by to_char(s.date, 'dd-mm-yyyy')  "),
  @NamedQuery(name = "Statistic.userList",
    query="SELECT s.userId FROM Statistics s GROUP BY s.userId"
  )
}
)
@SequenceGenerator(name = "statistic_seq", sequenceName = "statistic_seq", initialValue = 1, allocationSize = 1)
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "statistic_seq")
    private Long Id;
    @Column(length = 40)
    private String userId;
    @Enumerated(EnumType.STRING)
    private TipoNotificacao type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    public Statistics() {
    }

    public Statistics(String userId, TipoNotificacao type) {
        this.type = type;
        this.userId = userId;
        date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
