import 'dart:async';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';
import 'package:intl/intl.dart';
import 'dart:io';

import 'package:uber/model/Destino.dart';
import 'package:uber/model/Marcador.dart';
import 'package:uber/model/Requisicao.dart';
import 'package:uber/model/Usuario.dart';
import 'package:uber/util/StatusRequisicao.dart';
import 'package:uber/util/UsuarioFirebase.dart';

class PainelPassageiro extends StatefulWidget {
  const PainelPassageiro({Key? key}) : super(key: key);

  @override
  State<PainelPassageiro> createState() => _PainelPassageiroState();
}

class _PainelPassageiroState extends State<PainelPassageiro> {

  FirebaseFirestore db = FirebaseFirestore.instance;
  TextEditingController _controlerDestino = TextEditingController();
  List<String> itensMenu = ["Configurações" , "Deslogar"];
  Completer<GoogleMapController> _controller = Completer();
  CameraPosition _positionCamera = CameraPosition(target: LatLng(-21.480115316678308, -47.36773450685508), zoom: 16);
  Set<Marker> _marcadores = {};
  bool _exibirCaixaEnderecoDestino = true;
  String _textoBotao = "Chamar Uber";
  Color _corBotao = Color(0xff1ebbd8);
  VoidCallback _funcaoBotao = () {};
  String? _idRequisicao;
  Position? _localPassageiro;
  dynamic _dadosRequisicao;
  StreamSubscription<DocumentSnapshot>? _streamSubscriptionRequisicoes;
  var formatacao = NumberFormat('#,##0.00', 'pt_BR');

  _deslogarUsuario() async{
    FirebaseAuth auth = FirebaseAuth.instance;
    await auth.signOut();
    Navigator.pushReplacementNamed(context, "/");
  }

  _escolhaMenuItem(String escolha){
      switch(escolha){
        case "Deslogar":
          _deslogarUsuario();
          break;
        case "Configuraçoes":
          break;
      }
  }

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
    Geolocator.getPositionStream( locationSettings: locationSettings).listen((position) {
     if(_idRequisicao != null && _idRequisicao!.isNotEmpty){
      UsuarioFirebase.atualizarDadosLocalizacao(
          _idRequisicao!,
          position.latitude,
          position.longitude,
          "passageiro");
     }else {
        setState(() {
          _localPassageiro = position;
        });
        _statusUberNaoChamado();
     }
    });
  }

  _recuperarUltimaLocalizacaoValida() async {
    Position? position = await Geolocator.getLastKnownPosition(forceAndroidLocationManager: true);
      setState(() {
        if(position != null){
          _exibirMarcadorPassageiro(position);
          _positionCamera = CameraPosition(
              target: LatLng(position.latitude, position.longitude),
              zoom: 19
          );
          _localPassageiro = position;
          _movimentarCamera(_positionCamera);
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

  _exibirMarcadorPassageiro(Position local) async{
    double pixelRatio = MediaQuery.of(context).devicePixelRatio;

    BitmapDescriptor.fromAssetImage(
        ImageConfiguration(devicePixelRatio: pixelRatio),
        "imagens/passageiro.png"
    ).then((BitmapDescriptor icone) {
      Marker marcadorPassageiro = Marker(
          markerId: MarkerId("marcador-passageiro"),
          position: LatLng(local.latitude, local.longitude),
          infoWindow: InfoWindow(
              title: "Meu Local"
          ),
          icon: icone
      );
      setState(() {
        _marcadores.add(marcadorPassageiro);
      });
    });
  }

  _chamarUber() async{
    String enderecoDestion = _controlerDestino.text;
    if(enderecoDestion.isNotEmpty){
      List<Location> listLocation = await locationFromAddress(enderecoDestion);
      if(listLocation != null && listLocation.length > 0){
        Location location = listLocation[0];
        List<Placemark> listaEnderecos = await placemarkFromCoordinates(location.latitude, location.longitude);
        if(listaEnderecos != null && listaEnderecos.length > 0){
          Placemark endereco = listaEnderecos[0];
          Destino destino = Destino();
          destino.cidade = endereco.administrativeArea;
          destino.cep = endereco.postalCode;
          destino.bairro = endereco.subLocality;
          destino.rua = endereco.thoroughfare;
          destino.numero = endereco.subThoroughfare;
          destino.latitude = location.latitude;
          destino.longitude = location.longitude;

          String enderecoConfirmacao;
          enderecoConfirmacao = "\n Cidade: " + destino.cidade!;
          enderecoConfirmacao += "\n Rua: " + destino.rua! + ", " + destino.numero!;
          enderecoConfirmacao += "\n Bairro: " + destino.bairro!;
          enderecoConfirmacao += "\n Cep: " + destino.cep!;

          showDialog(
              context: context,
              builder: (contex) {
                return AlertDialog(
                  title: Text("Confirmação do endereço"),
                  content: Text(enderecoConfirmacao),
                  contentPadding: EdgeInsets.all(16),
                  actions: [
                    TextButton(
                        onPressed: () => Navigator.pop(contex),
                        child: Text("Cancelar", style: TextStyle(color: Colors.red))
                    ),
                    TextButton(
                        onPressed: (){
                          _salvarRequisicao(destino);
                          Navigator.pop(contex);
                        },
                        child: Text("Confirmar", style: TextStyle(color: Colors.green))
                    ),
                  ],
                );
              }
          );
        }
      }else{
        print("Nenhuma localização encontrada: " + listLocation[0].toString());
      }
    }
  }

  _cancelarUber() async{
      User? user = await UsuarioFirebase.getUsuarioAtual();
      FirebaseFirestore db = FirebaseFirestore.instance;
      db.collection("requisicoes")
        .doc( _idRequisicao)
        .update({"status" : StatusRequisicao.CANCELADA
      }).then((_) {
        db.collection("requisicao_ativa")
            .doc(user!.uid).delete();

        _statusUberNaoChamado();
        if(_streamSubscriptionRequisicoes != null) {
          _streamSubscriptionRequisicoes!.cancel();
          _streamSubscriptionRequisicoes = null;
        }
      });
  }

  _salvarRequisicao(Destino destino) async{
      Usuario? passageiro = await UsuarioFirebase.getDadosUsuarioLogado();
      passageiro?.latitude = _localPassageiro?.latitude;
      passageiro?.longitude = _localPassageiro?.longitude;
      Requisicao requisicao = Requisicao();
      requisicao.destino = destino;
      requisicao.passageiro = passageiro;
      requisicao.status = StatusRequisicao.AGUARDANDO;

      FirebaseFirestore db = FirebaseFirestore.instance;
      db.collection("requisicoes")
      .doc(requisicao.id)
      .set(requisicao.toMap());

      Map<String, dynamic> dadosRequisicaoAtiva = {};
      dadosRequisicaoAtiva["id_requisicao"] = requisicao.id;
      dadosRequisicaoAtiva["id_usuario"] = passageiro!.idUsuario;
      dadosRequisicaoAtiva["status"] = StatusRequisicao.AGUARDANDO;

      db.collection("requisicao_ativa")
      .doc(passageiro.idUsuario)
      .set(dadosRequisicaoAtiva);

      if(_streamSubscriptionRequisicoes == null){
        _adicionarListenerRequisicao(requisicao.id!);
      }
  }

  _alterarBotaoPrincipal(String texto, Color cor, VoidCallback funcao){
    setState(() {
      _textoBotao = texto;
      _corBotao = cor;
      _funcaoBotao = funcao;
    });
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

  _statusUberNaoChamado(){
    _exibirCaixaEnderecoDestino = true;
    _alterarBotaoPrincipal("Chamar Uber", Color(0xff1ebbd8), (){
          _chamarUber();
    });
    if(_localPassageiro != null){
      Position position = _criaPosition(_localPassageiro!.latitude, _localPassageiro!.longitude);
      _exibirMarcadorPassageiro(position);
      _positionCamera = CameraPosition(
          target: LatLng(position.latitude, position.longitude),
          zoom: 17
      );
      _movimentarCamera(_positionCamera);
    }
  }

  _statusAguardando(){
    _exibirCaixaEnderecoDestino = false;
    _alterarBotaoPrincipal("Cancelar", Colors.red, (){
          _cancelarUber();
    });
    double passageiroLat = _dadosRequisicao["passageiro"]["latitude"];
    double passageiroLon = _dadosRequisicao["passageiro"]["longitude"];
    Position position = _criaPosition(passageiroLat, passageiroLon);
    _exibirMarcadorPassageiro(position);
    _positionCamera = CameraPosition(
        target: LatLng(position.latitude, position.longitude),
        zoom: 17
    );
    _movimentarCamera(_positionCamera);
  }

  _statusACaminho(){
    _exibirCaixaEnderecoDestino = false;
    _alterarBotaoPrincipal("Motorista a caminho", Colors.grey, (){});

    double latitudePassageiro = _dadosRequisicao["passageiro"]["latitude"];
    double longitudePassageiro = _dadosRequisicao["passageiro"]["longitude"];
    double latitudeMotorista = _dadosRequisicao["motorista"]["latitude"];
    double longitudeMotorista = _dadosRequisicao["motorista"]["longitude"];


    Marcador marcadorOrigem = Marcador(
        LatLng(latitudeMotorista, longitudeMotorista),
        "imagens/motorista.png",
        "Local motorista");
    Marcador marcadorDestino = Marcador(
        LatLng(latitudePassageiro, longitudePassageiro),
        "imagens/passageiro.png",
        "Local destino");

    _exibirCentralizarDoisMarcadores(marcadorOrigem, marcadorDestino);
  }

  _statusEmViagem(){
    _exibirCaixaEnderecoDestino = false;
    _alterarBotaoPrincipal(
        "Em viagem",
        Colors.grey, (){}
    );
    double latitudeDestino = _dadosRequisicao["destino"]["latitude"];
    double longitudeDestino = _dadosRequisicao["destino"]["longitude"];
    double latitudeOrigem = _dadosRequisicao["motorista"]["latitude"];
    double longitudeOrigem = _dadosRequisicao["motorista"]["longitude"];

    Marcador marcadorOrigem = Marcador(
        LatLng(latitudeOrigem, longitudeOrigem),
        "imagens/motorista.png",
        "Local motorista");
    Marcador marcadorDestino = Marcador(
        LatLng(latitudeDestino, longitudeDestino),
        "imagens/destino.png",
        "Local destino");

    _exibirCentralizarDoisMarcadores(marcadorOrigem, marcadorDestino);
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
    _alterarBotaoPrincipal(
        "Total - R\$ ${valorViagemFormatado}",
        Colors.green,
            (){}
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
      if(_streamSubscriptionRequisicoes != null){
        _streamSubscriptionRequisicoes!.cancel();
        _streamSubscriptionRequisicoes = null;
      }
      _exibirCaixaEnderecoDestino = true;
      _alterarBotaoPrincipal("Chamar Uber", Color(0xff1ebbd8), (){
        _chamarUber();
      });
      double passageiroLat = _dadosRequisicao["passageiro"]["latitude"];
      double passageiroLon = _dadosRequisicao["passageiro"]["longitude"];
      Position position = _criaPosition(passageiroLat, passageiroLon);
      _exibirMarcadorPassageiro(position);
      _positionCamera = CameraPosition(
          target: LatLng(position.latitude, position.longitude),
          zoom: 17
      );
      _movimentarCamera(_positionCamera);
      _dadosRequisicao = {};
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

  _exibirCentralizarDoisMarcadores(Marcador marcadorOrigem, Marcador marcadorDestino){

    double latitudeOrigem = marcadorOrigem.local.latitude;
    double longitudeOrigem = marcadorOrigem.local.longitude;
    double latitudeDestino = marcadorDestino.local.latitude;
    double longitudeDestino = marcadorDestino.local.longitude;

    _exibirDoisMarcadores(marcadorOrigem, marcadorDestino);

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

  _exibirDoisMarcadores(Marcador marcadorOrigem, Marcador marcadorDestino){
    double pixelRatio = MediaQuery.of(context).devicePixelRatio;
    Set<Marker> _listaMarcadores = {};
    BitmapDescriptor.fromAssetImage(
        ImageConfiguration(devicePixelRatio: pixelRatio),
        marcadorOrigem.caminhoImagem
    ).then((BitmapDescriptor icone) {
      Marker maOrigem = Marker(
          markerId: MarkerId(marcadorOrigem.caminhoImagem),
          position: marcadorOrigem.local,
          infoWindow: InfoWindow(
              title: marcadorOrigem.titulo
          ),
          icon: icone);
      _listaMarcadores.add(maOrigem);
    });
    BitmapDescriptor.fromAssetImage(
        ImageConfiguration(devicePixelRatio: pixelRatio),
        marcadorDestino.caminhoImagem
    ).then((BitmapDescriptor icone) {
      Marker maDestino = Marker(
          markerId: MarkerId(marcadorDestino.caminhoImagem),
          position: marcadorDestino.local,
          infoWindow: InfoWindow(
              title: marcadorDestino.titulo
          ),
          icon: icone);
      _listaMarcadores.add(maDestino);
    });

    setState(() {
      _marcadores = _listaMarcadores;
    });
  }

  _recuperarRequisicaoAtiva() async {
    User? user = await UsuarioFirebase.getUsuarioAtual();
    FirebaseFirestore db = FirebaseFirestore.instance;
    DocumentSnapshot documentSnapshot = await db
            .collection("requisicao_ativa")
            .doc(user!.uid)
            .get();
    if(documentSnapshot.data() != null){
      dynamic dados = documentSnapshot.data();
      _idRequisicao = dados["id_requisicao"];
      _adicionarListenerRequisicao(_idRequisicao!);
    }else{
      _statusUberNaoChamado();
    }
  }

  _adicionarListenerRequisicao(String idRequisicao) async{
    _streamSubscriptionRequisicoes = await db.collection("requisicoes")
        .doc(idRequisicao)
        .snapshots()
        .listen((snapshot) {
      if(snapshot.data() != null){
        dynamic dados = snapshot.data();
        _dadosRequisicao = dados;
        String status = dados!["status"];
        _idRequisicao = dados!["id"];
        switch (status){
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
    _recuperarRequisicaoAtiva();
    //_recuperarUltimaLocalizacaoValida();
    _adicionarListenerLocalizacao();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Painel passageiro"),
        actions: [
          PopupMenuButton<String> (
            onSelected: _escolhaMenuItem,
            itemBuilder: (context){
              return itensMenu.map((e){
                return PopupMenuItem<String>(
                  value: e,
                  child: Text(e),
                );
              }).toList();
            },
          )
        ],
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
            Visibility(
                visible: _exibirCaixaEnderecoDestino,
                child: Stack(
                  children: [
                    Positioned(
                        top: 0,
                        left: 0,
                        right: 0,
                        child: Padding(
                          padding: EdgeInsets.all(10),
                          child: Container(
                            height: 50,
                            width: double.infinity,
                            decoration: BoxDecoration(
                                border: Border.all(color: Colors.grey),
                                borderRadius: BorderRadius.circular(3),
                                color: Colors.white
                            ),
                            child: TextField(
                              readOnly: true,
                              decoration: InputDecoration(
                                  icon: Container(
                                    margin: EdgeInsets.only(left: 20),
                                    width: 20,
                                    height: 20,
                                    child: Icon(Icons.location_on, color: Colors.grey),
                                  ),
                                  hintText: "Meu Local",
                                  border: InputBorder.none,
                                  contentPadding: EdgeInsets.only(left: 15)
                              ),
                            ),
                          ),
                        )
                    ),
                    Positioned(
                        top: 55,
                        left: 0,
                        right: 0,
                        child: Padding(
                          padding: EdgeInsets.all(10),
                          child: Container(
                            height: 50,
                            width: double.infinity,
                            decoration: BoxDecoration(
                                border: Border.all(color: Colors.grey),
                                borderRadius: BorderRadius.circular(3),
                                color: Colors.white
                            ),
                            child: TextField(
                              controller: _controlerDestino,
                              decoration: InputDecoration(
                                  icon: Container(
                                    margin: EdgeInsets.only(left: 20),
                                    width: 20,
                                    height: 20,
                                    child: Icon(Icons.local_taxi, color: Colors.black),
                                  ),
                                  hintText: "Digite o destino",
                                  border: InputBorder.none,
                                  contentPadding: EdgeInsets.only(left: 15, )
                              ),
                            ),
                          ),
                        )
                    )
                  ],
                )
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

  @override
  void dispose() {
    super.dispose();
    _streamSubscriptionRequisicoes!.cancel();
    _streamSubscriptionRequisicoes = null;
  }

}
