package info.atende.nserver;

import info.atende.nserver.exceptions.EmailNotSendedException;
import info.atende.nserver.model.Notification;
import info.atende.nserver.service.NotificationService;
import org.junit.Test;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
/**
 * @author Giovanni Silva.
 */
public class TestNotificationServiceUnit {

    @Test(expected = EmailNotSendedException.class)
    public void testSendEmailWithInvalidToken() throws EmailNotSendedException {
        Notification notification = mock(Notification.class);
        when(notification.validateToken(anyObject())).thenReturn(false);
        NotificationService notificationService = new NotificationService(notification);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        notificationService.sendEmail("email@email.com", "subject","message", true, "invalid",
                httpServletRequest);

    }

    @Test
    public void testWithValidToken() throws EmailNotSendedException {
        Notification notification = mock(Notification.class);
        when(notification.validateToken(anyObject())).thenReturn(true);
        NotificationService notificationService = new NotificationService(notification);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String[] to = {"email@email.com"};
        notificationService.sendEmail(to[0], "subject","message", true, "invalid",
                httpServletRequest);
        verify(notification).sendEmail(eq(to), eq("subject"),eq("message"), anyObject());
    }
}
