
import 'package:flutter/material.dart';
import 'package:minhas_viagens_new/SplashScreen.dart';

void main() async {

  WidgetsFlutterBinding.ensureInitialized();
  //await Firebase.initializeApp();

  runApp(MaterialApp(
    title: "Minhas viagens",
    home: SplashScreen(),
    debugShowCheckedModeBanner: false,
  ));
}