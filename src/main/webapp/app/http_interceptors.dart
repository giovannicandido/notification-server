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

part of notification;
/**
 * Interceptor example, this just forward the logic for the VaderHttpInterceptor.
 * Since VaderHttpInterceptor do **NOT** depends on Angular
 */
class NotificationHttpInterceptor implements HttpInterceptor {
  VaderHttpInterceptors impl;
  NotificationHttpInterceptor(){
    impl = vaderFactory(VaderHttpInterceptors);
  }
  responseError(error) {
    impl.responseError(error, error.status);
    return new Future.error(error);
  }
  request(HttpResponseConfig config){
    impl.processRequest(config.data);
    return config;
  }
  requestError(request){
    return new Future.error(request);
  }
  response(HttpResponse httpResponse){
    impl.processRequest(httpResponse.data);
    return httpResponse;
  }
}