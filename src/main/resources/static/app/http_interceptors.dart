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
  Logger log;
  NotificationHttpInterceptor(this.log){
    impl = vaderFactory(VaderHttpInterceptors);
  }
  responseError(error) {
    dom.querySelector("#loading").querySelector("img").classes.add('ng-hide');
    // verifica se o erro é bad reques (400)
    // Tenta extrair as informações da requisição
    var request = error.data;
    List parameterViolations = [];
    extractMap(Map map){
      parameterViolations= map["parameterViolations"];

    }

    if(error.status == 400){
      if(request is Map){
        extractMap(request);
      }
      if(request is String) {
        // Tray Json Parse
        try {
          var mapRequest = JSON.decode(request);
          extractMap(mapRequest);
        }catch(e){
          log.log(Level.WARNING, "The interceptor could not parse the request, "
          + "its not a json string: Previous exception $e");
        }

      }
      // Houve mensagem de violacoes
      if(parameterViolations != null && !parameterViolations.isEmpty){
         String message = "";
         parameterViolations.forEach((e)=> message += "<b>${e['path'].split(".").last}</b> : ${e["message"]} </br>");
         Notify notify = vaderFactory(Notify);
         notify.show("<h1>Verifique os erros a seguir</h1> <br /> $message", title: "Erro ao processar requisição", status: Status.ERROR, delaySeconds: 0);
      }
      return new Future.error(error);
    }
    impl.responseError(error, error.status);
    return new Future.error(error);
  }
  request(HttpResponseConfig config){
    dom.querySelector("#loading").querySelector("img").classes.remove('ng-hide');
    impl.processRequest(config.data);
    return config;
  }
  requestError(request){
    dom.querySelector("#loading").querySelector("img").classes.add('ng-hide');
    return new Future.error(request);
  }
  response(HttpResponse httpResponse){
    dom.querySelector("#loading").querySelector("img").classes.add('ng-hide');
    impl.processRequest(httpResponse.data);
    return httpResponse;
  }
}