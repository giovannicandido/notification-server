library notification;
import "package:angular/angular.dart";
import 'package:angular/application_factory.dart';
import 'package:logging/logging.dart';
import 'package:vader/vader.dart';
import 'dart:html';

part 'app/AppRouter.dart';
part 'app/controller/config/email.dart';
part 'app/http_interceptors.dart';

main(){
  // ConfigService Logger
  Logger.root.level = Level.FINEST;
  Logger.root.onRecord.listen((LogRecord r) { print(r.message); });

  // Start Application

  var module = new Module()
    ..type(RouteInitializerFn, implementedBy: AppRouter)
    ..bind(EmailCtl);
  Injector di = applicationFactory().addModule(module).run();

  // Setup HttpInterceptors

  di.get(HttpInterceptors).add(new NotificationHttpInterceptor());

  var logger = new Logger("app");
  logger.finest("Application Started");
}