import 'package:flutter/material.dart';

void main(){
  runApp(MaterialApp(
    // abaixo remove o debug
    debugShowCheckedModeBanner: false,
    //deste jeito não funciona como esperado
    /*title: "Frases do dia",*/
    home: Scaffold(
      appBar: AppBar(
        title: Text("Instagram"),
        backgroundColor: Colors.green,
      ),
      body: Padding(
          padding: EdgeInsets.all(16),
          child: Text("Conteúdo principal..."),
      ),
      bottomNavigationBar: BottomAppBar(
        color: Colors.lightGreen,
        child: Padding(
          padding: EdgeInsets.all(16),
          child: Row(
            children: [
              Text("Conteúdo principal 1"),
              Text("Conteúdo principal 2")
            ],
          ),
        ),
      ),
    )
  ));
}