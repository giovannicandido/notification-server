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

package config.ws;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * WebSocket EndPoint to delivery messages
 * @author Giovanni Silva
 * Date: 9/12/14.
 */
@ServerEndpoint("/messages")
public class MessageEndPoint {
    static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    @OnOpen
    public void open(Session session, EndpointConfig config){
        peers.add(session);
        try {
            session.getBasicRemote().sendText("Olá");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @OnClose
    public void close(Session session, CloseReason reason){
        peers.remove(session);
    }
}
