import 'package:flutter/material.dart';

class Home2 extends StatefulWidget {
  const Home2({Key? key}) : super(key: key);

  @override
  State<Home2> createState() => _Home2State();
}

class _Home2State extends State<Home2> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("FloatingActionButton")),
      body: Text("Conteudo"),
      //componente abaixo serve pra mudas a localização do float..Button
      //quando centerDockerd ele ficará acoplado no bottomNavigation
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: FloatingActionButton.extended(
        backgroundColor: Colors.purple,
        foregroundColor: Colors.white,
        //elevation sombreamento do botão
        elevation: 20,
        //botão menor quando true
        icon: Icon(Icons.add_shopping_cart),
/*        shape: BeveledRectangleBorder(
          borderRadius: BorderRadius.circular(8)
        ),*/
        label: Text("Adicionar"),
        onPressed: (){
          print("on pressed");
        },
      ),
      bottomNavigationBar: BottomAppBar(
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
