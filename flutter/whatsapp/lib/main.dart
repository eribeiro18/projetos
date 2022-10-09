import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:whatsapp/RouteGenerator.dart';
import 'package:whatsapp/telas/Login.dart';
import 'dart:io';

final ThemeData theme = ThemeData();

final ThemeData themePadrao = ThemeData()
    .copyWith(
  colorScheme: theme.colorScheme.copyWith(
      primary: Color(0xff075E54)
  ),
);

final ThemeData themeIOS = ThemeData()
    .copyWith(
  colorScheme: theme.colorScheme.copyWith(
      primary: Colors.grey[200]
  ),
);

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();

  runApp(MaterialApp(
    home: Login(),
    theme: Platform.isIOS ? themeIOS : themePadrao,
    initialRoute: "/",
    onGenerateRoute: RouteGenerator.generateRoute,
    //PODERIA SER FEITO ASSIM, FAREMOS DO JEITO ACIMA
    /*routes: {
      "/login" : (context) => Login(),
      "/home" : (context) => Home()
    },*/
    debugShowCheckedModeBanner: false,
  ));
}