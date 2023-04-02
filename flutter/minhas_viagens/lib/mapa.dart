import 'dart:async';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:geocoding/geocoding.dart';
import 'package:geolocator/geolocator.dart';
import 'package:flutter/material.dart';
import 'package:google_maps_flutter/google_maps_flutter.dart';

class Mapa extends StatefulWidget {
  String idViagem;

  Mapa({Key? key, required this.idViagem}) : super(key: key);

  @override
  State<Mapa> createState() => _MapaState();
}

class _MapaState extends State<Mapa> {

  Completer<GoogleMapController> _controller = Completer();
  Set<Marker> _marcadores = {};
  CameraPosition _posicaoCamera = CameraPosition(
      target: LatLng(-23.562436, -46.65505),
      zoom: 18
  );
  FirebaseFirestore _db = FirebaseFirestore.instance;

  _onMapCreated (GoogleMapController controller) async{
    _controller.complete(controller);
  }

  _adicionarMarcador(LatLng latLng) async{
    List<Placemark> listaEnderecos = await placemarkFromCoordinates(latLng.latitude, latLng.longitude);

    if( listaEnderecos != null && listaEnderecos.length > 0){
      Placemark endereco = listaEnderecos[0];
      String rua = endereco.thoroughfare!;
      Marker marcador = Marker(
          markerId: MarkerId("marcador-${latLng.latitude}-${latLng.longitude}"),
          position: latLng,
          infoWindow: InfoWindow(
              title: rua
          )
      );
      setState(() {
        _marcadores.add(marcador);
        Map<String, dynamic> viagem = Map();
        viagem["titulo"] = rua;
        viagem["latitude"] = latLng.latitude;
        viagem["longitude"] =latLng.longitude;
        _db.collection("viagens")
        .add(viagem);
      });
    }
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

  _movimentarCamera() async{

    GoogleMapController googleMapController = await _controller.future;
    googleMapController
    .animateCamera(
        CameraUpdate.newCameraPosition(_posicaoCamera)
    );
  }

  _adicionarListenerLocalizacao() async {
    _validatePermissoes();
    var locationSettings = LocationSettings(accuracy: LocationAccuracy.high);
    Geolocator.getPositionStream(locationSettings: locationSettings).listen((event) {
      setState(() {
        _posicaoCamera = CameraPosition(
            target: LatLng(event.latitude, event.longitude),
            zoom: 18
        );
        _movimentarCamera();
      });
    });
  }

  _recuperarViagemParaID(String idViagem) async{
    if(idViagem != null && idViagem != ""){
      DocumentSnapshot documentSnapshot = await _db
      .collection("viagens").doc(idViagem)
      .get();
      dynamic dados = documentSnapshot.data();
      String titulo = dados["titulo"];
      LatLng latLng = LatLng(dados["latitude"], dados["longitude"]);
      setState(() {
        Marker marcador = Marker(
            markerId: MarkerId("marcador-${latLng.latitude}-${latLng.longitude}"),
            position: latLng,
            infoWindow: InfoWindow(
                title: titulo
            )
        );
        _marcadores.add(marcador);
        _posicaoCamera = CameraPosition(
            target: latLng,
          zoom: 18
        );
        _movimentarCamera();
      });
    }else{
      _adicionarListenerLocalizacao();
    }
  }

  @override
  void initState() {
    super.initState();
    _recuperarViagemParaID(widget.idViagem);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Mapa"),),
      body: Container(
        child: GoogleMap(
          markers: _marcadores,
          mapType: MapType.normal,
          initialCameraPosition: _posicaoCamera,
          onMapCreated: _onMapCreated,
          onLongPress: _adicionarMarcador,
        ),
      ),
    );
  }
}
