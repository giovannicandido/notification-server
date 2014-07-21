library notification;
import "package:angular/angular.dart";
import 'package:angular/application_factory.dart';
import 'package:logging/logging.dart';
import 'package:vader/vader.dart';
import 'dart:html' as dom;
import 'dart:async';
import 'dart:convert';
import 'dart:math' as math;

part 'app/http_interceptors.dart';
part 'app/AppRouter.dart';

// Controllers
part 'app/controller/config/email.dart';
part 'app/controller/apps.dart';


main(){
  // ConfigService Logger
  Logger.root.level = Level.FINEST;
  Logger.root.onRecord.listen((LogRecord r) { print(r.message); });
  var logger = new Logger("app");

  // Start Application

  var module = new Module()
    ..type(RouteInitializerFn, implementedBy: AppRouter)
    ..bind(EmailCtl)
    ..bind(AppsCtl);
  Injector di = applicationFactory().addModule(module).run();

  // Setup HttpInterceptors

  di.get(HttpInterceptors).add(new NotificationHttpInterceptor(logger));

  logger.finest("Application Started");
}