part of notification;
@Controller(
  selector: '[config-email]',
  publishAs: 'ctrl'
)
class EmailCtl {
  Http _http;
  EmailCtl(this._http){
    _loadConfig();
  }
  var config = {};
  void salvar(){
    _http.post('/rest/config', config);
  }
  _loadConfig(){
    _http.get('/rest/config').then((HttpResponse resp){
      if(resp.data != null){
        config = resp.data['data'][0];
      }
    });
  }
}