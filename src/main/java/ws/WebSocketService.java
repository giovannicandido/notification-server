package ws;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Websocket server for users
 * @author Giovanni Silva
 */
@ServerEndpoint(value = "/notification", configurator = HttpSessionConfigurator.class)
public class WebSocketService {
    static Set<UserSession> peers = Collections.synchronizedSet(new HashSet<UserSession>());

    @OnOpen
    public void open(Session session, EndpointConfig config){
        HttpSession http = (HttpSession) config.getUserProperties()
                .get(HttpSession.class.getName());
        peers.add(new UserSession(session, http));
    }

    @OnClose
    public void close(Session session){
        // remove from quee
        peers.remove(session);
    }
    public static class UserSession {
        public Session wsSession;
        public HttpSession httpSession;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || (o instanceof UserSession && getClass() != o.getClass())) return false;
            if(o instanceof UserSession){
                UserSession that = (UserSession) o;

                if (!wsSession.equals(that.wsSession)) return false;
            }else if(o instanceof Session){
                return wsSession.equals(o);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return wsSession.hashCode();
        }

        public UserSession(Session wsSession, HttpSession httpSession) {
            this.wsSession = wsSession;
            this.httpSession = httpSession;
        }
    }
}
