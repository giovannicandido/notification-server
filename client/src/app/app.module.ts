import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import {HomeComponent} from './home/home.component'
import {NavBarComponent} from "./shared/navbar/navbar.component";
import {routing, appRoutingProviders} from './app.routes';
import { GraphsComponent } from './graphs/graphs.component';
import { TestEmailComponent } from './test-email/test-email.component';
import { TokenComponent } from './token/token.component';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HomeComponent,
    GraphsComponent,
    TestEmailComponent,
    TokenComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [
    appRoutingProviders
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
