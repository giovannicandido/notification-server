import { Component, OnInit } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-test-email',
  templateUrl: './test-email.component.html',
  styleUrls: ['./test-email.component.css']
})
export class TestEmailComponent implements OnInit {

  constructor(private http: Http) { }

  ngOnInit() {
  }

  to = ""
  wait = false

  send(){
    this.waiting()
    this.http.post('api/notification/test-email',this.to)
      .map(_ => _.json())
      .catch(this.handleError)
      .subscribe(r => {
      BootstrapDialog.alert({
        title: 'Message',
        message: r.json().message
      })
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

  handleError(error, caught) {
    $('button[type=submit]').prop('disabled', false);
    BootstrapDialog.alert({

      title: 'WARNING',
      message: error.toString(),
      type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
      closable: true, // <-- Default value is false
    });
    return Observable.throw(error);
  }

}
