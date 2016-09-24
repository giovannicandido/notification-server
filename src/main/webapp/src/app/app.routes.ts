import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {GraphsComponent} from './graphs/graphs.component';
import {TestEmailComponent} from './test-email/test-email.component';
import {TokenComponent} from './token/token.component';

// Rotas
// config.map([
//       {route: ['', 'welcome'], name: 'welcome', moduleId: 'welcome', nav: true, title: 'Notification Server'},
//       {route: 'token', name: 'token', moduleId: 'token', nav: true, title: 'Tokens'},
//       {route: 'graph', name: 'graph', moduleId: 'graphs', nav: true, title: 'Graphs'},
//       {route: 'test-email', name: 'test-email', moduleId: 'test-email', nav: true, title: 'Test Send Email'}
//     ]);

const appRoutes: Routes = [
  { path: 'graph', component: GraphsComponent },
  { path: 'test-email', component: TestEmailComponent },
  { path: 'token', component: TokenComponent },
  { path: 'home', component: HomeComponent },
  { path: '', component: HomeComponent }
];

export const appRoutingProviders: any[] = [

];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
