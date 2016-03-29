import {HttpClient} from 'aurelia-http-client';
import {autoinject} from "aurelia-dependency-injection";
import 'bootstrap3-dialog';
import 'bootstrap3-dialog/dist/css/bootstrap-dialog.min.css!';
@autoinject
export class TestEmail {
 to = ""

  constructor(private http: HttpClient){

  }

  save(){
    this.http.post('api/notification/test-email',this.to).then(r => {
      BootstrapDialog.alert({
        title: 'Message',
        message: r.content.message
      })
    }).catch(r => {
      BootstrapDialog.alert({
        title: 'WARNING',
        message: r.content.message,
        type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is false
      });
    })
  }
}
