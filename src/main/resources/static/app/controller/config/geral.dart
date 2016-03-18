part of notification;
@Controller(
  selector: '[config-geral]',
  publishAs: 'ctrl'
)
class GeralCtl {
  Http _http;
  GeralCtl(this._http){
    _loadConfig();
  }
  var config = {};
  void salvar(){
    _http.post('/rest/config/geral', config);
  }

  _loadConfig(){
    _http.get('/rest/config/geral').then((HttpResponse resp){
      if(resp.data != null){
        config = resp.data['data'][0];
      }
    });
  }
}