import {Router, RouterConfiguration} from 'aurelia-router'

export class App {
  router:Router;

  configureRouter(config:RouterConfiguration, router:Router) {
    config.title = 'NF Server';
    config.map([
      {route: ['', 'welcome'], name: 'welcome', moduleId: 'welcome', nav: true, title: 'Notification Server'},
      {route: 'token', name: 'token', moduleId: 'token', nav: true, title: 'Tokens'},
      {route: 'graph', name: 'graph', moduleId: 'graphs', nav: true, title: 'Graphs'},
      {route: 'test-email', name: 'test-email', moduleId: 'test-email', nav: true, title: 'Test Send Email'}
    ]);

    this.router = router;
  }
}
