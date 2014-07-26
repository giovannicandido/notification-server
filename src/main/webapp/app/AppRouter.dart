part of notification;
class AppRouter {
 void call(Router router, ViewFactory views){
   views.configure({
     'default': ngRoute(
        defaultRoute: true,
        view: 'app/views/default.html'
     ),
     'graphics': ngRoute(
        path: '/graphics',
        view: 'app/views/graphics.html'
     ),
     'status': ngRoute(
        path: '/status',
        view: 'app/views/status.html'
     ),
     'config': ngRoute(
       path: '/config',
       mount: {
         'email': ngRoute(
             path: '/email',
             view: '/app/views/config/email.html'
         )
       })

   });
 }
}
