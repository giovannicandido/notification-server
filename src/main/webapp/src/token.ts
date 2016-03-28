import {HttpClient} from 'aurelia-http-client';
import {autoinject} from "aurelia-dependency-injection";
import 'bootstrap3-dialog';
import 'bootstrap3-dialog/dist/css/bootstrap-dialog.min.css!';
@autoinject
export class Token {
  token = {}
  tokens = []

  constructor(private http: HttpClient){

  }

  save(){
    this.http.post('api/config/token',this.token).then(r => {
      this.tokens.push(r.content);
      this.token = {};
    }).catch(r => {
      BootstrapDialog.alert({
        title: 'WARNING',
        message: r.content,
        type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is false
      });
    })
  }
  delete(token){
    BootstrapDialog.confirm(`Deletar token ${token} ?`, result => {
      if(result){
        this.http.delete(`api/config/token/${token}`).then(r =>{
          this.reloadTokens();
        })
      }
    });
  }
  reloadTokens(){
    this.http.get('api/config/token').then(r => {
      this.tokens = r.content
    })
  }
  activate(){
    this.reloadTokens();
  }
}
