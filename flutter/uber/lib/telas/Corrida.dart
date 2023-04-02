import 'dart:async';
import 'dart:io';

import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:intl/intl.dart';
import 'package:uber/model/Usuario.dart';
import 'package:uber/util/StatusRequisicao.dart';
import 'package:uber/util/UsuarioFirebase.dart';

class Corrida extends StatefulWidget {
  String? idReqisicao;
  Corrida({Key? key, required this.idReqisicao}) : super(key: key);

  @override
  State<Corrida> createState() => _CorridaState();
}

class _CorridaState extends State<Corrida> {

  FirebaseFirestore db = FirebaseFirestore.instance;
  Completer<GoogleMapController> _controller = Completer();
  Set<Marker> _marcadores = {};
  CameraPosition _positionCamera = CameraPosition(target: LatLng(-23.563370, -46.652923), zoom: 16);
  dynamic _dadosRequisicao;
  var formatacao = NumberFormat('#,##0.00', 'pt_BR');

  String _textoBotao = "Aceitar corrida";
  Color _corBotao = Color(0xff1ebbd8);
  VoidCallback _funcaoBotao = () {};
  String _mensagemStatus = "";
  String? _idRequisicao;
  Position? _localMotorista;
  String _statusRequisicao = StatusRequisicao.AGUARDANDO;

  _onMapCreated(GoogleMapController googleMapController) {
    _controller.complete(googleMapController);
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

  _adicionarListenerLocalizacao() async {
    _validatePermissoes();
    var locationSettings =
    LocationSettings(
        accuracy: LocationAccuracy.high,
        distanceFilter: 10
    );
    Geolocator.getPositionStream( locationSettings: locationSettings).listen(
            (position) {
        if(position != null){
          if(_idRequisicao != null && _idRequisicao!.isNotEmpty){
            if(_statusRequisicao != StatusRequisicao.AGUARDANDO){
              UsuarioFirebase.atualizarDadosLocalizacao(
                  _idRequisicao!,
                  position.latitude,
                  position.longitude,
                  "motorista");
            }else{
              setState(() {
                _localMotorista = position;
              });
              _statusAguardando();
            }
          }
        }
    });
  }

  _movimentarCamera(CameraPosition cameraPosition) async {
    GoogleMapController googleMapController = await _controller.future;
    googleMapController
        .animateCamera(CameraUpdate.newCameraPosition(
        cameraPosition
    ));
  }

  _recuperarUltimaLocalizacaoValida() async {
    Position? position = await Geolocator.getLastKnownPosition(forceAndroidLocationManager: true);
    if(position != null){
        //Atualizar a localização em tempo real do motorista
    }
  }

  _exibirMarcador(Position local, String icone, String infoWindow) async{
    double pixelRatio = MediaQuery.of(context).devicePixelRatio;

    BitmapDescriptor.fromAssetImage(
        ImageConfiguration(devicePixelRatio: pixelRatio),
        icone
    ).then((BitmapDescriptor bitmapDescriptor) {
      Marker marcador = Marker(
          markerId: MarkerId(icone),
          position: LatLng(local.latitude, local.longitude),
          infoWindow: InfoWindow(
              title: infoWindow
          ),
          icon: bitmapDescriptor
      );
      setState(() {
        _marcadores.add(marcador);
      });
    });
  }

  _alterarBotaoPrincipal(String texto, Color cor, VoidCallback funcao){
    setState(() {
      _textoBotao = texto;
      _corBotao = cor;
      _funcaoBotao = funcao;
    });
  }

  _recuperarRequisicao() async{
      String idRequisicao = widget.idReqisicao!;
      DocumentSnapshot documentSnapshot = await
          db.collection("requisicoes")
            .doc(idRequisicao).get();
  }

  _aceitarCorrida() async {
    Usuario? usuarioMotorista   = await UsuarioFirebase.getDadosUsuarioLogado();
    usuarioMotorista?.latitude  = _localMotorista?.latitude;
    usuarioMotorista?.longitude = _localMotorista?.longitude;

    String idRequisicao = _dadosRequisicao["id"];
    db.collection("requisicoes").doc(idRequisicao).update({
      "motorista": usuarioMotorista!.toMap(),
      "status": StatusRequisicao.A_CAMINHO
    }).then((_) {
      String idPassageiro = _dadosRequisicao["passageiro"]["idUsuario"];
      db.collection("requisicao_ativa").doc(idPassageiro).update({
        "status": StatusRequisicao.A_CAMINHO,
      });
    });
    String? idMotorista = usuarioMotorista.idUsuario;
    db.collection("requisicao_ativa_motorista").doc(idMotorista).set({
      "id_requisicao": idRequisicao,
      "id_usuario": idMotorista,
      "status": StatusRequisicao.A_CAMINHO,
    });
  }

  _iniciarCorrida(){
    db.collection("requisicos")
        .doc(_idRequisicao)
        .update({
          "origem":{
              "latitude": _dadosRequisicao["motorista"]["latitude"],
              "longitude": _dadosRequisicao["motorista"]["longitude"]
            },
          "status": StatusRequisicao.VIAGEM
        });
    String idPassageiro = _dadosRequisicao["passageiro"]["idUsuario"];
    db.collection("requisicao_ativa")
    .doc(idPassageiro)
    .update({"status": StatusRequisicao.VIAGEM});

    String idMotorista = _dadosRequisicao["motorista"]["idUsuario"];
    db.collection("requisicao_ativa_motorista")
        .doc(idMotorista)
        .update({"status": StatusRequisicao.VIAGEM});
  }

  _statusAguardando(){
    _alterarBotaoPrincipal(
        "Aceitar corrida",
        Color(0xff1ebbd8),
            (){
          _aceitarCorrida();
        });
    if( _localMotorista != null){
      double motoristaLat = _localMotorista!.latitude;
      double motoristaLon = _localMotorista!.longitude;
      Position position = Position(
          longitude: motoristaLon,
          latitude: motoristaLat,
          timestamp: DateTime.now(),
          accuracy: 0.0,
          altitude: 0.0,
          heading: 0.0,
          speed: 0.0,
          speedAccuracy: 0.0);
      _exibirMarcador(position, "imagens/motorista.png", "Motorista");
      CameraPosition cameraPosition = CameraPosition(
          target: LatLng(position.latitude, position.longitude),
          zoom: 17
      );
      _movimentarCamera(cameraPosition);
    }
  }

  _statusACaminho(){
    _mensagemStatus = "A caminho do passageiro";
    _alterarBotaoPrincipal(
        "Iniciar corrida",
        Colors.grey,
        (){
          _iniciarCorrida();
        }
    );
    double latitudePassageiro = _dadosRequisicao["passageiro"]["latitude"];
    double longitudePassageiro = _dadosRequisicao["passageiro"]["longitude"];
    double latitudeMotorista = _dadosRequisicao["motorista"]["latitude"];
    double longitudeMotorista = _dadosRequisicao["motorista"]["longitude"];
    _exibirDoisMarcadores(
        LatLng(latitudeMotorista, longitudeMotorista),
        LatLng(latitudePassageiro, longitudePassageiro));

    var nLat, nLon, sLat, sLon;

    if(latitudeMotorista <= latitudePassageiro){
      sLat = latitudeMotorista;
      nLat = latitudePassageiro;
    }else{
      sLat = latitudePassageiro;
      nLat = latitudeMotorista;
    }
    if(longitudeMotorista <= longitudePassageiro){
      sLon = longitudeMotorista;
      nLon = longitudePassageiro;
    }else{
      sLon = longitudePassageiro;
      nLon = longitudeMotorista;
    }

    _movimentarCameraBounds(
      LatLngBounds(
          northeast: LatLng(nLat, nLon),
          southwest: LatLng(sLat, sLon)
      )
    );
  }

  _finalizarCorrida(){
    db.collection("requisicoes")
        .doc(_idRequisicao)
        .update({
          "status": StatusRequisicao.FINALIZADA
        });
    String idPassageiro = _dadosRequisicao["passageiro"]["idUsuario"];
    db.collection("requisicao_ativa")
        .doc(idPassageiro)
        .update({"status": StatusRequisicao.FINALIZADA});

    String idMotorista = _dadosRequisicao["motorista"]["idUsuario"];
    db.collection("requisicao_ativa_motorista")
        .doc(idMotorista)
        .update({"status": StatusRequisicao.FINALIZADA});
  }

  _statusFinalizada() async {

    double latitudeDestino = _dadosRequisicao["destino"]["latitude"];
    double longitudeDestino = _dadosRequisicao["destino"]["longitude"];
    double latitudeOrigem = _dadosRequisicao["origem"]["latitude"];
    double longitudeOrigem = _dadosRequisicao["origem"]["longitude"];

    double distanciaEmMetros = await Geolocator.distanceBetween(
        latitudeOrigem, longitudeOrigem, latitudeDestino, longitudeDestino);

    double distanciaKm = distanciaEmMetros / 1000;

    //8 reias KM
    double valorViagem = distanciaKm * 8;
    var valorViagemFormatado = formatacao.format(valorViagem);
    _mensagemStatus = "Viagem finalizada";
    _alterarBotaoPrincipal(
        "Confirmar - R\$ ${valorViagemFormatado}",
        Colors.grey,
            (){
          _confirmarCorrida();
        }
    );
    _marcadores = {};
    Position position = _criaPosition(latitudeDestino, longitudeDestino);
    _exibirMarcador(position, "imagens/destino.png", "Destino");
    CameraPosition cameraPosition = CameraPosition(
        target: LatLng(position.latitude, position.longitude),
        zoom: 17
    );
    _movimentarCamera(cameraPosition);
  }

  _statusConfirmada(){
    Navigator.pushReplacementNamed(context, "/painel-motorista");
  }

  _confirmarCorrida(){
    db.collection("requisicos")
        .doc(_idRequisicao)
        .update({
      "status": StatusRequisicao.CONFIRMADA
    });
    String idPassageiro = _dadosRequisicao["passageiro"]["idUsuario"];
    db.collection("requisicao_ativa")
        .doc(idPassageiro).delete();

    String idMotorista = _dadosRequisicao["motorista"]["idUsuario"];
    db.collection("requisicao_ativa_motorista")
        .doc(idMotorista).delete();
  }

  Position _criaPosition(double latitude, double longitude){
    return Position(
        longitude: longitude,
        latitude: latitude,
        timestamp: DateTime.now(),
        accuracy: 0.0,
        altitude: 0.0,
        heading: 0.0,
        speed: 0.0,
        speedAccuracy: 0.0);
  }

  _statusEmViagem(){
    _mensagemStatus = "Em viagem";
    _alterarBotaoPrincipal(
        "Finalizar corrida",
        Colors.grey,
            (){
          _finalizarCorrida();
        }
    );
    double latitudeDestino = _dadosRequisicao["destino"]["latitude"];
    double longitudeDestino = _dadosRequisicao["destino"]["longitude"];
    double latitudeOrigem = _dadosRequisicao["motorista"]["latitude"];
    double longitudeOrigem = _dadosRequisicao["motorista"]["longitude"];
    _exibirDoisMarcadores(
        LatLng(latitudeOrigem, longitudeOrigem),
        LatLng(latitudeDestino, longitudeDestino));

    var nLat, nLon, sLat, sLon;

    if(latitudeOrigem <= latitudeDestino){
      sLat = latitudeOrigem;
      nLat = latitudeDestino;
    }else{
      sLat = latitudeDestino;
      nLat = latitudeOrigem;
    }
    if(longitudeOrigem <= longitudeDestino){
      sLon = longitudeOrigem;
      nLon = longitudeDestino;
    }else{
      sLon = longitudeDestino;
      nLon = longitudeOrigem;
    }

    _movimentarCameraBounds(
        LatLngBounds(
            northeast: LatLng(nLat, nLon),
            southwest: LatLng(sLat, sLon)
        )
    );
  }

  _movimentarCameraBounds(LatLngBounds latLngBounds) async {
    GoogleMapController googleMapController = await _controller.future;
    googleMapController
        .animateCamera(
      CameraUpdate.newLatLngBounds(
          latLngBounds,
          100
      )
    );
  }

  _exibirDoisMarcadores(LatLng latLng1, LatLng latLng2){
    double pixelRatio = MediaQuery.of(context).devicePixelRatio;
    Set<Marker> _listaMarcadores = {};
    BitmapDescriptor.fromAssetImage(
        ImageConfiguration(devicePixelRatio: pixelRatio),
        "imagens/motorista.png"
    ).then((BitmapDescriptor icone) {
      Marker marcadorMotorista = Marker(
          markerId: MarkerId("marcador-motorista"),
          position: LatLng(latLng1.latitude, latLng1.longitude),
          infoWindow: InfoWindow(
              title: "Local motorista"
          ),
          icon: icone);
      _listaMarcadores.add(marcadorMotorista);
    });
    BitmapDescriptor.fromAssetImage(
        ImageConfiguration(devicePixelRatio: pixelRatio),
        "imagens/passageiro.png"
    ).then((BitmapDescriptor icone) {
      Marker marcadorPassageiro = Marker(
          markerId: MarkerId("marcador-passageiro"),
          position: LatLng(latLng2.latitude, latLng2.longitude),
          infoWindow: InfoWindow(
              title: "Local passageiro"
          ),
          icon: icone);
      _listaMarcadores.add(marcadorPassageiro);
    });

    setState(() {
      _marcadores = _listaMarcadores;
    });
  }

  _adicionarListenerRequisicao() async {
      await db.collection("requisicoes").doc(_idRequisicao).snapshots().listen((snapshot) {
        if(snapshot.data() != null){
          _dadosRequisicao = snapshot.data();
          dynamic dados = snapshot.data();
          _statusRequisicao = dados["status"];
          switch (_statusRequisicao){
            case StatusRequisicao.AGUARDANDO:
              _statusAguardando();
              break;
            case StatusRequisicao.A_CAMINHO:
              _statusACaminho();
              break;
            case StatusRequisicao.VIAGEM:
              _statusEmViagem();
              break;
            case StatusRequisicao.FINALIZADA:
              _statusFinalizada();
              break;
            case StatusRequisicao.CONFIRMADA:
              _statusConfirmada();
              break;
          }
        }
      });
  }

  @override
  void initState() {
    super.initState();
    setState(() {
      _idRequisicao = widget.idReqisicao;
    });
    _adicionarListenerRequisicao();
    //_recuperarUltimaLocalizacaoValida();
    _adicionarListenerLocalizacao();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Painel corrida - " + _mensagemStatus),
      ),
      body: Container(
          child: Stack(
            children: [
              GoogleMap(
                mapType: MapType.normal,
                //-21.192865,-47.738965
                initialCameraPosition: _positionCamera,
                onMapCreated: _onMapCreated,
                //myLocationEnabled: true,
                myLocationButtonEnabled: false,
                markers: _marcadores,
              ),
              Positioned(
                  right: 0,
                  left: 0,
                  bottom: 0,
                  child: Padding(
                    padding: Platform.isIOS
                        ? EdgeInsets.fromLTRB(20, 10, 20, 25)
                        : EdgeInsets.all(10),
                    child: ElevatedButton(
                      onPressed: _funcaoBotao,
                      child: Text(
                        _textoBotao,
                        style: TextStyle(color: Colors.white, fontSize: 20),
                      ),
                      style: ElevatedButton.styleFrom(
                        primary: _corBotao,
                        padding: EdgeInsets.fromLTRB(30, 16, 32, 16),
                      ),
                    ),
                  )
              )
            ],
          )
      ),
    );
  }
}
