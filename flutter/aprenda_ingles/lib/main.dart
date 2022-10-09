import 'package:aprenda_ingles/telas/Home.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    final ThemeData theme = ThemeData();
    return MaterialApp(
      theme: theme.copyWith(
          colorScheme: theme.colorScheme.copyWith(
              primary: Color(0xff795548)
          ),
        scaffoldBackgroundColor: Color(0xfff5e9b9)
      ),
      home: Home(),
      debugShowCheckedModeBanner: false,
    );
  }
}


