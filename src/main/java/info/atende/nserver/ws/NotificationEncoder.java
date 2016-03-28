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

package info.atende.nserver.ws;

import info.atende.nserver.dto.NotificationDTO;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import java.io.StringWriter;

/**
 * @author Giovanni Silva
 *         Date: 9/21/14.
 */
public class NotificationEncoder implements Encoder.Text<NotificationDTO> {
    @Override
    public String encode(NotificationDTO object) throws EncodeException {
        StringWriter swriter = new StringWriter();
        try(JsonWriter jsonWriter = Json.createWriter(swriter)){
            JsonObjectBuilder builder = Json.createObjectBuilder();
            builder.add("message", object.getMessage())
                    .add("applicationImageURL", object.getApplicationImageURL())
                    .add("messageImageURL", object.getMessageImageURL())
                    .add("id", object.getId());
            jsonWriter.writeObject(builder.build());
        }
        return swriter.toString();

    }

    @Override
    public void init(EndpointConfig config) {

    }

    @Override
    public void destroy() {

    }
}
