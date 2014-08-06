part of notification;

@Controller(selector: '[graphics-view]', publishAs: 'ctrl')
class GraphicsCtrl {
  Http _http;
  var appData = [];
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

  load() {
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
  }
}