import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("FloatingActionButton")),
      body: Text("Conteudo"),
      //componente abaixo serve pra mudas a localização do float..Button
      //quando centerDockerd ele ficará acoplado no bottomNavigation
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: FloatingActionButton(
        backgroundColor: Colors.purple,
        foregroundColor: Colors.white,
        //elevation sombreamento do botão
        elevation: 20,
        //botão menor quando true
        mini: false,
        child: Icon(Icons.add),
        onPressed: (){
          print("on pressed");
        },
      ),
      bottomNavigationBar: BottomAppBar(
        //faz com que o float..Button fique bem encaixado aqui no navigation
        shape: CircularNotchedRectangle(),
        child: Row(
          children: [
            IconButton(
                onPressed: (){

                },
                icon: Icon(Icons.add),
            )
          ],
        ),
      ),
    );
  }
}
