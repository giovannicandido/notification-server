part of notification;

@Controller(selector: '[graphics-view]', publishAs: 'ctrl')
class GraphicsCtrl {
  Http _http;
  var appData = [];
  GraphicsCtrl(this._http);
  var chartAplicacao = new HighChart()
    ..chart = (new Chart()
    ..borderColor = '#CCC'
    ..borderWidth = 1
    ..borderRadius = 8
    ..backgroundColor = 'rgba(0,0,0,0)')
    ..title = (new Title()
    ..text = 'Porcentagem de Notificações Enviadas por Aplicação')
    ..tooltip = (new Tooltip()
    ..pointFormat = '{series.name}: <b>{point.percentage:.1f}%</b>')
    ..plotOptions = (new PlotOptions()
    ..moreOptions = {
      'pie': {
          'allowPointSelect': true, 'cursor': 'pointer', 'dataLabels': {
              'enabled': true, 'format': '<b>{point.name}</b>: {point.percentage:.1f} %', 'style': {
                  'color': 'black'
              }
          }
      }
  });
  var chartTipo = new HighChart()
    ..chart = (new Chart()
    ..borderColor = '#CCC'
    ..borderWidth = 1
    ..borderRadius = 8
    ..backgroundColor = 'rgba(0,0,0,0)')
    ..title = (new Title()
    ..text = 'Porcentagem de Notificações por Tipo')
    ..tooltip = (new Tooltip()
    ..pointFormat = '{series.name}: <b>{point.percentage:.1f}%</b>')
    ..plotOptions = (new PlotOptions()
    ..moreOptions = {
      'pie': {
          'allowPointSelect': true, 'cursor': 'pointer', 'dataLabels': {
              'enabled': true, 'format': '<b>{point.name}</b>: {point.percentage:.1f} %', 'style': {
                  'color': 'black'
              }
          }
      }
  });

  load() {
    var seriesAplicacao = [(new Series ()
      ..type = 'pie'
      ..name = 'Aplicações'
      ..data = [(new Point()
      ..name = 'App 1'
      ..y = 24.9), (new Point()
      ..name = 'App 2'
      ..y = 8.9), (new Point()
      ..name = 'App 3'
      ..y = 59.2
      ..sliced = true
      ..moreOptions = {
        'selected': true
    }), (new Point ()
      ..name = 'App 4'
      ..y = 3.8), (new Point ()
      ..name = 'App 5'
      ..y = 1.8), (new Point ()
      ..name = 'App 6'
      ..y = 1.4),

    ])];
    var seriesTipo = [(new Series ()
      ..type = 'pie'
      ..name = 'Tipo de Envio'
      ..data = [(new Point()
      ..name = 'EMAIL'
      ..y = 40.0), (new Point()
      ..name = 'EMAIL TEST'
      ..y = 30.0), (new Point()
      ..name = 'WEBSOCKET'
      ..y = 30.0
      ..sliced = true
      ..moreOptions = {
        'selected': true
    })
    ])];

    chartTipo.series = seriesTipo;
    _loadInformacao();
  }
  _loadInformacao(){
     _http.get("/rest/graphic/apps").then((_){
        this.appData = _.data['data'];
        List<Point> points = appData.map((e){
          return new Point()
              ..name = e['value']
              .. y = e['count'];
        });
        print(points);
        var series = [new Series()
          ..type= 'pie'
          ..name = 'Aplicações'
          ..data = points

        ];
       chartAplicacao.series = series;
     });
  }
}