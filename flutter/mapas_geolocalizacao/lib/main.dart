import 'package:flutter/material.dart';
import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';
import 'dart:async';
import 'package:google_maps_flutter/google_maps_flutter.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Mapas e geolocalização',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const Home(title: 'Mapas e geolocalização'),
    );
  }
}

class Home extends StatefulWidget {
  const Home({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  Completer<GoogleMapController> _controller = Completer();
  CameraPosition _positionCamera = CameraPosition(target: LatLng(-23.563370, -46.652923), zoom: 16);
  Set<Marker>  _marcdadores = {};
  Set<Polygon> _poligons = {};
  Set<Polyline> _polylines = {};

  _onMapCreated(GoogleMapController googleMapController) {
    _controller.complete(googleMapController);
  }

  _movimentarCamera() async {
    GoogleMapController googleMapController = await _controller.future;
    googleMapController
        .animateCamera(CameraUpdate.newCameraPosition(
        _positionCamera
        /*CameraPosition(
            target: LatLng(-23.562436, -46.655005),
            zoom: 16,
            //tilt serve pra mudar o angulo do mapa
            tilt: 0,
            //bearing rotaciona a camera do mapa
            bearing: 0
        )*/
    ));
  }

  _carregarMarcadores(){
    Set<Polyline> listaPolylines = {};
    Polyline polyline1 = Polyline(
        polylineId: PolylineId("polyline"),
        color: Colors.red,
        width: 10,
        //formato da ponta do inicio do marcador
        startCap: Cap.roundCap,
        //formato da ponta do final do marcador
        endCap: Cap.roundCap,
        //formato da quebra do marcador
        jointType: JointType.round,
        points: [
          LatLng(-23.563645, -46.653642),
          LatLng(-23.566064, -46.650778),
          LatLng(-23.563232, -46.648020),
        ],
        //mesma regra do polygon
        consumeTapEvents: true,
        onTap: (){
          print("clicado na área");
        }
    );
    listaPolylines.add(polyline1);
    setState(() {
      _polylines = listaPolylines;
    });
  }

  _carregarMarcadores2(){
    Set<Polygon> listaPolygons = {};
    Polygon polygon1 = Polygon(
        polygonId: PolygonId("Polygon1"),
        fillColor: Colors.green,
        strokeColor: Colors.red,
        strokeWidth: 5,
        points: [
          LatLng(-23.561816, -46.652044),
          LatLng(-23.563625, -46.653642),
          LatLng(-23.564786, -46.652226),
          LatLng(-23.563085, -46.650531),
        ],
      //consumeTapEvents com valor falso não habilita o onTap
      consumeTapEvents: true,
      onTap: (){
          print("clicado na área");
      },
        //zIndex define a prioridade dos poligonos quanto maior o valor mais prioridade ele tem
      zIndex: 1
    );
    Polygon polygon2 = Polygon(
        polygonId: PolygonId("Polygon2"),
        fillColor: Colors.purple,
        strokeColor: Colors.orange,
        strokeWidth: 5,
        points: [
          LatLng(-23.561629, -46.653031),
          LatLng(-23.565189, -46.651872),
          LatLng(-23.562032, -46.650831)
        ],
        //consumeTapEvents com valor falso não habilita o onTap
        consumeTapEvents: true,
        onTap: (){
          print("clicado na área");
        },
        zIndex: 1
    );
    listaPolygons.add(polygon1);
    listaPolygons.add(polygon2);
    setState(() {
      _poligons = listaPolygons;
    });
  }

  _carregarMarcadores3(){
    Set<Marker> marcadoresLocal = {};
    Marker macadorShopping = Marker(
        markerId: MarkerId("marcador-shopping"),
      position: LatLng(-23.563370, -46.652923),
      infoWindow: InfoWindow(
        title: "Shopping Cidade São Paulo"
      ),
      icon: BitmapDescriptor.defaultMarkerWithHue(
        BitmapDescriptor.hueMagenta
      ),
      //muda a rotação do icone
      rotation: 0,
      onTap: (){
          print("Shopping clicado");
      }
    );
    Marker macadorCartorio = Marker(
        markerId: MarkerId("marcador-cartorio"),
        position: LatLng(-23.562868, -46.655874),
        infoWindow: InfoWindow(
            title: "12 Cartorio de notas"
        ),
      icon: BitmapDescriptor.defaultMarkerWithHue(
        BitmapDescriptor.hueBlue
      ),
      onTap: (){
          print("Cartorio clicado");
      }
    );
    marcadoresLocal.add(macadorShopping);
    marcadoresLocal.add(macadorCartorio);
    setState(() {
      _marcdadores = marcadoresLocal;
    });
  }

  //recebe os dados e plota no mapa
  _marcarLocalizacaoAtual(double latitude, double longitude){
    Set<Marker> marcadoresLocal = {};
    Marker macadorShopping = Marker(
        markerId: MarkerId("marcador-localização-atual"),
        position: LatLng(latitude, longitude),
        infoWindow: InfoWindow(
            title: "Você está aqui"
        ),
        icon: BitmapDescriptor.defaultMarkerWithHue(
            BitmapDescriptor.hueMagenta
        ),
        //muda a rotação do icone
        rotation: 0,
    );
    marcadoresLocal.add(macadorShopping);
    setState(() {
      _marcdadores = marcadoresLocal;
    });
  }

  _recuperarLocalizacaoAtual() async{
      _validatePermissoes();
      Position p = await Geolocator.getCurrentPosition(
        desiredAccuracy: LocationAccuracy.high
      );
      setState(() {
        _positionCamera = CameraPosition(
            target: LatLng(p.latitude, p.longitude),
            zoom: 17
        );
      });
      _movimentarCamera();
  }

  //metodo fica escutando a movimentação do usuario e atualizando no mapa
  _adicionarListenerLocalizacao() async {
    _validatePermissoes();
    var locationSettings =
    LocationSettings(
       accuracy: LocationAccuracy.high,
       distanceFilter: 10
    );
    Geolocator.getPositionStream( locationSettings: locationSettings).listen(
            (event) {
              print("localização atual: " + event.toString());
              //linha abaixo serve para plotar no mapa um marcador da localização atual
              //_marcarLocalizacaoAtual(event.latitude, event.longitude);
              setState(() {
                _positionCamera = CameraPosition(
                    target: LatLng(event.latitude, event.longitude),
                    zoom: 17
                );
                _movimentarCamera();
              });
            });
  }

  _validatePermissoes() async {
    bool serviceEnabled;
    LocationPermission permission;
    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Location services are disabled.');
    }
    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        return Future.error('Location permissions are denied');
      }
    }
    if (permission == LocationPermission.deniedForever) {
      return Future.error(
          'Location permissions are permanently denied, we cannot request permissions.');
    }
  }

  _recuperarLatitudeLongitudePorEndereco() async {
    _validatePermissoes();
    List<Location> listLocation = await locationFromAddress("Rua Roberto armbrust, 225");
    print("Total localização: " + listLocation.length.toString());
    if(listLocation != null && listLocation.length > 0){
      print("Primeira localização: " + listLocation[0].toString());
    }else{
      print("Nenhuma localização encontrada: " + listLocation[0].toString());
    }
  }

  _recuperarEnderecoPorLatitudeLongitude() async {
    List<Placemark> listaEnderecos = await placemarkFromCoordinates(-21.4773598, -47.368624999999994);
    print("total: " + listaEnderecos.length.toString() );
    if( listaEnderecos != null && listaEnderecos.length > 0 ){
      Placemark endereco = listaEnderecos[0];
      String resultado;
      resultado  = "\n administrativeArea " + endereco.administrativeArea! ;
      resultado += "\n subAdministrativeArea " + endereco.subAdministrativeArea! ;
      resultado += "\n locality " + endereco.locality! ;
      resultado += "\n subLocality " + endereco.subLocality! ;
      resultado += "\n thoroughfare " + endereco.thoroughfare! ;
      resultado += "\n subThoroughfare " + endereco.subThoroughfare! ;
      resultado += "\n postalCode " + endereco.postalCode! ;
      resultado += "\n country " + endereco.country! ;
      resultado += "\n isoCountryCode " + endereco.isoCountryCode! ;

      print("resultado localização: " + resultado );
    }
  }

  @override
  void initState() {
    super.initState();
    //plota no mapa marcadores
    //_carregarMarcadores();
    //plota no mapa marcador da localização atual
    //_recuperarLocalizacaoAtual();
    //escuta se usuario esta em movimento e marca no mapa em tempo real
    _adicionarListenerLocalizacao();
    //recupera informações com base no endereço ou latitude e logintude
    //_recuperarLatitudeLongitudePorEndereco();
    //_recuperarEnderecoPorLatitudeLongitude();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.startTop,
      floatingActionButton: FloatingActionButton(
        onPressed: _movimentarCamera,
        tooltip: 'Increment',
        child: Icon(Icons.done),
      ),
      body: Container(
        child: GoogleMap(
          mapType: MapType.normal,
          //-21.192865,-47.738965
          initialCameraPosition: _positionCamera,
          onMapCreated: _onMapCreated,
          myLocationEnabled: true,
          markers: _marcdadores,
          //polygons: _poligons,
          //polylines: _polylines,
        ),
      ),
    );
  }
}
