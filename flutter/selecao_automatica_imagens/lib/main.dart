import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: Home(),
    debugShowCheckedModeBanner: false,
  ));
}

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  @override
  Widget build(BuildContext context) {
    
    double pixeRatio = MediaQuery.of(context).devicePixelRatio;
    print("pixel ratio: " + pixeRatio.toString());

    return Scaffold(
      appBar: AppBar(
        title: Text("Imagens"),
      ),
      body: Container(
        child: Container(
          child: Image.asset("assets/icone.png"),
        ),
      ),
    );
  }
}
