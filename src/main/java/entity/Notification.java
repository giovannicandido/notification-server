package entity;

import javax.persistence.*;

/**
 * This class represents a notification for the user connected in websocket
 * @author Giovanni Silva
 */
@Entity
@SequenceGenerator(name = "notification_seq", sequenceName = "notification_seq")
public class Notification {
    @Id
    @GeneratedValue(generator = "notification_seq")
    private Long id;
    @Column(length = 140, nullable = false)
    private String message;
    @Column(length = 255)
    private String messageImageURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageImageURL() {
        return messageImageURL;
    }

    public void setMessageImageURL(String messageImageURL) {
        this.messageImageURL = messageImageURL;
    }
}
