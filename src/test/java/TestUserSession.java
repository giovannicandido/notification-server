import org.junit.Assert;
import org.junit.Test;
import info.atende.nserver.ws.WebSocketService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import javax.websocket.*;
import java.io.IOException;
import java.net.URI;
import java.security.Principal;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This test websocket user session class
 * @author Giovanni Silva
 */
public class TestUserSession {
    @Test
    public void shouldEqualsToWebsocketSessionAndUserSession(){
        Session session = new MockSession();
        HttpSession httpSession = new MockHttpSession();
        WebSocketService.UserSession userSession = new WebSocketService.UserSession(session, httpSession);
        Assert.assertTrue(userSession.equals(session));
        Assert.assertTrue(userSession.equals(userSession));
        Assert.assertFalse(userSession.equals(httpSession));
    }
    @Test
    public void shouldHashCodeBeEqualSessionHashcode(){
        Session session = new MockSession();
        HttpSession httpSession = new MockHttpSession();
        WebSocketService.UserSession userSession = new WebSocketService.UserSession(session, httpSession);
        Assert.assertEquals(session.hashCode(), userSession.hashCode());
    }
    class MockSession implements Session {

        @Override
        public WebSocketContainer getContainer() {
            return null;
        }

        @Override
        public void addMessageHandler(MessageHandler handler) throws IllegalStateException {

        }

        @Override
        public Set<MessageHandler> getMessageHandlers() {
            return null;
        }

        @Override
        public void removeMessageHandler(MessageHandler handler) {

        }

        @Override
        public String getProtocolVersion() {
            return null;
        }

        @Override
        public String getNegotiatedSubprotocol() {
            return null;
        }

        @Override
        public List<Extension> getNegotiatedExtensions() {
            return null;
        }

        @Override
        public boolean isSecure() {
            return false;
        }

        @Override
        public boolean isOpen() {
            return false;
        }

        @Override
        public long getMaxIdleTimeout() {
            return 0;
        }

        @Override
        public void setMaxIdleTimeout(long milliseconds) {

        }

        @Override
        public void setMaxBinaryMessageBufferSize(int length) {

        }

        @Override
        public int getMaxBinaryMessageBufferSize() {
            return 0;
        }

        @Override
        public void setMaxTextMessageBufferSize(int length) {

        }

        @Override
        public int getMaxTextMessageBufferSize() {
            return 0;
        }

        @Override
        public RemoteEndpoint.Async getAsyncRemote() {
            return null;
        }

        @Override
        public RemoteEndpoint.Basic getBasicRemote() {
            return null;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public void close() throws IOException {

        }

        @Override
        public void close(CloseReason closeReason) throws IOException {

        }

        @Override
        public URI getRequestURI() {
            return null;
        }

        @Override
        public Map<String, List<String>> getRequestParameterMap() {
            return null;
        }

        @Override
        public String getQueryString() {
            return null;
        }

        @Override
        public Map<String, String> getPathParameters() {
            return null;
        }

        @Override
        public Map<String, Object> getUserProperties() {
            return null;
        }

        @Override
        public Principal getUserPrincipal() {
            return null;
        }

        @Override
        public Set<Session> getOpenSessions() {
            return null;
        }
    }
    class MockHttpSession implements HttpSession {

        @Override
        public long getCreationTime() {
            return 0;
        }

        @Override
        public String getId() {
            return null;
        }

        @Override
        public long getLastAccessedTime() {
            return 0;
        }

        @Override
        public ServletContext getServletContext() {
            return null;
        }

        @Override
        public void setMaxInactiveInterval(int interval) {

        }

        @Override
        public int getMaxInactiveInterval() {
            return 0;
        }

        @Override
        public HttpSessionContext getSessionContext() {
            return null;
        }

        @Override
        public Object getAttribute(String name) {
            return null;
        }

        @Override
        public Object getValue(String name) {
            return null;
        }

        @Override
        public Enumeration<String> getAttributeNames() {
            return null;
        }

        @Override
        public String[] getValueNames() {
            return new String[0];
        }

        @Override
        public void setAttribute(String name, Object value) {

        }

        @Override
        public void putValue(String name, Object value) {

        }

        @Override
        public void removeAttribute(String name) {

        }

        @Override
        public void removeValue(String name) {

        }

        @Override
        public void invalidate() {

        }

        @Override
        public boolean isNew() {
            return false;
        }
    }
}
