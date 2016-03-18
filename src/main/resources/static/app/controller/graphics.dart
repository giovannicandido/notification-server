part of notification;

@Controller(selector: '[graphics-view]', publishAs: 'ctrl')
class GraphicsCtrl {
  Http _http;
  var appData = [];
  var appList = [];
  String app;
  GraphicsCtrl(this._http){
    load();
  }
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

  var chartAppTime = new HighChart()
    ..title = (new Title()..text = 'Número de envios por Aplicação vs Tempo')
    ..chart = (new Chart()
      ..type = 'line'
      ..borderColor = '#CCC')
    ..yAxis = (new YAxis()..title = (new AxisTitle()..text = 'Número de envios')
  );

  load() {
    // Load Application Combobox
    _http.get('/rest/graphic/userlist').then((_){
      this.appList = _.data['data'];
    });
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
        var series = [new Series()
          ..type= 'pie'
          ..name = 'Aplicação'
          ..data = points

        ];
       chartAplicacao.series = series;
     });

    _http.get("/rest/graphic/type").then((_){
      var data = _.data['data'];
      List<Point> points = data.map((e){
        return new Point()
          ..name = e['value']
          ..y = e['count'];
      });
      var series = [new Series()
        ..type='pie'
          ..name='Tipo'
          ..data = points
      ];
      chartTipo.series = series;
    });

    _http.get('/rest/graphic/apptime', params: {'id': app}).then((_){
      var data = _.data['data'];
      print(data);
      List<num> numData = data.map((e){
        return e['count'];
      });
      print(numData);
      List<String> values = data.map((e){
        return e['value'];
      });
      print(values);
      var series = [new Series()
        ..type='line'
        ..name='App'
        ..numData = numData
      ];
      var xAxis = new XAxis()..categories = values;
      chartAppTime.xAxis = xAxis;
      chartAppTime.series = series;
    });
  }
}