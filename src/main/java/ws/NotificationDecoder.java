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

package ws;

import dto.NotificationDTO;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.StringReader;

/**
 * @author Giovanni Silva
 *         Date: 9/21/14.
 */
public class NotificationDecoder implements Decoder.Text<NotificationDTO> {
    @Override
    public NotificationDTO decode(String s) throws DecodeException {
        JsonReader parser = Json.createReader(new StringReader(s));
        JsonObject jsonObject = parser.readObject();
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setApplicationImageURL(jsonObject.getString("applicationImageURL"));
        notificationDTO.setMessageImageURL(jsonObject.getString("messageImageURL"));
        notificationDTO.setMessage(jsonObject.getString("message"));
        notificationDTO.setId(Long.valueOf(jsonObject.getInt("id")));
        return notificationDTO;

    }

    @Override
    public boolean willDecode(String s) {
        try{
            Json.createReader(new StringReader(s)).readObject();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
