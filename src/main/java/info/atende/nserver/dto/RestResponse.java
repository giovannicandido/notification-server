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

package info.atende.nserver.dto;

import java.util.Collection;

/**
 * Encapsula respostas rest padronizadas
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/20/14.
 */
public class RestResponse {
    private String message;
    private Collection data;
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection getData() {
        return data;
    }

    public void setData(Collection data) {
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * RestResponse with true success by default
     */
    public RestResponse() {
        this.success = true;
    }

    public RestResponse(String message, Collection data, Boolean success) {
        this.message = message;
        this.data = data;
        this.success = success;
    }

    public RestResponse(String message) {
        this.message = message;
        this.success = true;
    }

    public RestResponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }

    public RestResponse(String message, Collection data) {
        this.message = message;
        this.data = data;
        this.success = true;
    }

    public RestResponse(Collection data) {
        this.data = data;
        this.success = true;
    }
}
