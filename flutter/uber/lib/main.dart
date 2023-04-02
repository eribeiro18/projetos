import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:uber/Rotas.dart';
import 'package:uber/telas/Home.dart';

final ThemeData theme = ThemeData();
void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MaterialApp(
    title: "Uber",
    home: Home(),
    theme: theme.copyWith(
        colorScheme: theme.colorScheme.copyWith(
            primary: Color(0xff37474f)
        ),
        scaffoldBackgroundColor: Colors.white
    ),
    initialRoute: "/",
    onGenerateRoute: Rotas.gerarRotas,
    debugShowCheckedModeBanner: false,
  ));
}
