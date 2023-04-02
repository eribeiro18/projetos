import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:olx/RouteGenerator.dart';
import 'package:olx/views/Anuncios.dart';
import 'package:olx/views/Login.dart';

final ThemeData theme = ThemeData();
void main() async{
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(MaterialApp(
    title: "OLX",
    home: Anuncios(),
    theme: theme.copyWith(
        colorScheme: theme.colorScheme.copyWith(
            primary: Color(0xff9c27b0)
        ),
        scaffoldBackgroundColor: Colors.white
    ),
    initialRoute: "/",
    onGenerateRoute: RouteGenerator.generateRoute,
    debugShowCheckedModeBanner: false,
  ));
}