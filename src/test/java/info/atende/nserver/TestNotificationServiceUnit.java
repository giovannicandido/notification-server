package info.atende.nserver;

import info.atende.nserver.exceptions.EmailNotSendedException;
import info.atende.nserver.model.Notification;
import info.atende.nserver.service.NotificationService;
import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.*;
/**
 * @author Giovanni Silva.
 */
public class TestNotificationServiceUnit {

    @Test
    public void testSendEmailWithInvalidToken() throws EmailNotSendedException {
        Notification notification = mock(Notification.class);
        when(notification.validateToken(anyObject())).thenReturn(false);
        NotificationService notificationService = new NotificationService(notification);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        ResponseEntity<String> stringResponseEntity = notificationService.sendEmail("email@email.com", "subject", "message", null, true, "invalid",
                httpServletRequest);
        assertEquals("Response should be Bad Request", HttpStatus.BAD_REQUEST, stringResponseEntity.getStatusCode());
        assertEquals("Response Text should be invalid token", "invalid token", stringResponseEntity.getBody());
    }

    @Test
    public void testWithValidToken() throws EmailNotSendedException {
        Notification notification = mock(Notification.class);
        when(notification.validateToken(anyObject())).thenReturn(true);
        NotificationService notificationService = new NotificationService(notification);
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        String[] to = {"email@email.com"};
        notificationService.sendEmail(to[0], "subject","message", "no-reply@test.com", true, "invalid",
                httpServletRequest);
        verify(notification).sendEmail(eq(to), eq("no-reply@test.com"), eq("subject"),eq("message"), anyObject());
    }
}
