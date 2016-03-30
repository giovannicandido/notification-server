import {HttpClient} from 'aurelia-http-client';
import {autoinject} from "aurelia-dependency-injection";
import 'bootstrap3-dialog';
import 'bootstrap3-dialog/dist/css/bootstrap-dialog.min.css!';
@autoinject
export class TestEmail {
  to = ""
  wait = false
  constructor(private http: HttpClient){

  }

  send(){
    this.waiting()
    this.http.post('api/notification/test-email',this.to).then(r => {
      BootstrapDialog.alert({
        title: 'Message',
        message: r.content.message
      })
      this.done()
    }).catch(r => {
      BootstrapDialog.alert({
        title: 'WARNING',
        message: r.content.message,
        type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
        closable: true, // <-- Default value is false
      });
      this.done()
    })
  }

  waiting(){
    this.wait = true
    $('button[type=submit]').prop('disabled', true);
  }
  done(){
    this.wait = false
    $('button[type=submit]').prop('disabled', false);
  }
}
