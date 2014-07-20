library notification;
import "package:angular/angular.dart";
import 'package:angular/application_factory.dart';
import 'package:logging/logging.dart';

part 'app/AppRouter.dart';

main(){
  // Config Logger
  Logger.root.level = Level.FINEST;
  Logger.root.onRecord.listen((LogRecord r) { print(r.message); });

  // Start Application

  var module = new Module()
    ..type(RouteInitializerFn, implementedBy: AppRouter);
  applicationFactory().addModule(module).run();

  var logger = new Logger("app");
  logger.finest("Application Started");
}