import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import {NavBarComponent} from "./shared/navbar/navbar.component";

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

// Rotas
// config.map([
//       {route: ['', 'welcome'], name: 'welcome', moduleId: 'welcome', nav: true, title: 'Notification Server'},
//       {route: 'token', name: 'token', moduleId: 'token', nav: true, title: 'Tokens'},
//       {route: 'graph', name: 'graph', moduleId: 'graphs', nav: true, title: 'Graphs'},
//       {route: 'test-email', name: 'test-email', moduleId: 'test-email', nav: true, title: 'Test Send Email'}
//     ]);
