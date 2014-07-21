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

/**
 * Criado por Giovanni Silva <giovanni@atende.info>
 * Date: 7/21/14.
 */
part of notification;
@Controller(
  selector: '[data-apps]',
  publishAs: 'ctrl'
)
class AppsCtl {
  Http _http;
  var apps = [];
  var app = {};
  bool selected = false;
  String keyConfirm = null;
  Notify _notify = vaderFactory(Notify);
  AppsCtl(this._http){
    loadApps();
  }

  loadApps(){
     _http.get("/rest/application").then((response){
       this.apps = response.data['data'];
     });
  }
  addApp(){
    if(!selected){
      if(keyConfirm != app["key"] || app["key"] == null){
        _notify.show("As senhas não conferem. É necessário ter uma key ao adicionar nova aplicação",
        status: Status.WARNING, delaySeconds: 15);
        return;
      }
      _http.post("/rest/application", app).then((_){
        apps.add(app);
        // Clear for new app
        app = {};
        keyConfirm = null;
        selected = false;
        dom.querySelectorAll("tr").classes.remove('current');
      });
    }else{ // Selecionado deve atualizar ao inves de criar
      if(keyConfirm != app["key"] && (app["key"] != null || app["key"] != "")){
        _notify.show("As senhas não conferem", status: Status.WARNING, delaySeconds: 15);
        return;
      }
      _http.put("/rest/application", app).then((_){app = {};keyConfirm = null;});
    }


  }
  select(dynamic app){
    selected = true;
    dom.querySelectorAll("tr").classes.remove('current');
    dom.querySelector("#app-${app['id']}").classes.add('current');
    this.app = app;
  }
  remover(){

    if(this.app.isEmpty){
      _notify.show("Selecione uma aplicação para deletar", status: Status.ERROR, delaySeconds: 0);
    }else{
      _http.delete("/rest/application", params: {'id': app['id']}).then((_){
        apps.remove(app);
      });
    }
  }
}