import {Component, OnInit, OnDestroy} from '@angular/core';
import {Http} from "@angular/http";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {

  total = {};

  private repeat;

  constructor(private http: Http) { }

  ngOnInit() {
    this.repeat = setInterval(()=>this.loadCurrentSending(), 1000);
  }

  ngOnDestroy(): void {
    clearInterval(this.repeat)
  }

  loadCurrentSending(){
    this.http.get("api/notification/currentSending").subscribe(r => {
      this.total = r.json();
    })
  }

}
