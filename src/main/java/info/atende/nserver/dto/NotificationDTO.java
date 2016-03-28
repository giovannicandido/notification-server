package info.atende.nserver.dto;

/**
 * Represents a notification to be sended
 * @author Giovanni Silva
 */
public class NotificationDTO {

    private Long id;

    private String message;

    private String messageImageURL;
    private String applicationImageURL;

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

    public String getApplicationImageURL() {
        return applicationImageURL;
    }

    public void setApplicationImageURL(String applicationImageURL) {
        this.applicationImageURL = applicationImageURL;
    }
}
