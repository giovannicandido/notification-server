import {Component, OnInit} from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";

@Component({
  selector: 'app-token',
  templateUrl: './token.component.html',
  styleUrls: ['./token.component.css']
})
export class TokenComponent implements OnInit {
  token = {}
  tokens = []

  constructor(private http: Http) {

  }

  save() {
    this.http.post('api/config/token', this.token)
      .map(_ => _.json())
      .catch(this.handleError)
      .subscribe(r => {
        this.tokens.push(r);
        this.token = {};
      })
  }

  handleError(error, caught) {
    BootstrapDialog.alert({
      title: 'WARNING',
      message: error.toString(),
      type: BootstrapDialog.TYPE_DANGER, // <-- Default value is BootstrapDialog.TYPE_PRIMARY
      closable: true, // <-- Default value is false
    });
    return Observable.throw(error);
  }

  delete(token) {
    BootstrapDialog.confirm(`Deletar token ${token} ?`, result => {
      if (result) {
        this.http.delete(`api/config/token/${token}`).toPromise().then(r => {
          this.reloadTokens();
        })
      }
    });
  }

  reloadTokens() {
    this.http.get('api/config/token')
      .map(_ => _.json())
      .catch(this.handleError)
      .subscribe(r => {
        this.tokens = r
      })
  }

  ngOnInit() {
    this.reloadTokens();
  }

}
