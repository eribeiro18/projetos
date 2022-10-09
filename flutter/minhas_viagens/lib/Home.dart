import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  List _litaViagens = [
    "Cristo Redentor",
    "Grande Muralha da China",
    "Taj Mahal",
    "Machu Pucchu",
    "Coliseu"
  ];

  _abriMapa(){

  }

  _excluirViagem(){

  }
  _adicionarLocal(){

  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Minhas Viagens")),
      floatingActionButton: FloatingActionButton(
          child: Icon(Icons.add),
          backgroundColor: Color(0xff0066cc),
          onPressed: (){
            _adicionarLocal();
          }
      ),
      body: Column(
        children: [
          Expanded(
              child: ListView.builder(
                  itemCount: _litaViagens.length,
                  itemBuilder: (context, idx){
                    String titulo = _litaViagens[idx];
                    return GestureDetector(
                      onTap: (){
                        _abriMapa();
                      },
                      child: Card(
                        child: ListTile(
                            title: Text(titulo),
                            trailing: Row(
                              mainAxisSize: MainAxisSize.min,
                              children: [
                                GestureDetector(
                                  onTap: (){
                                    _excluirViagem();
                                  },
                                  child: Padding(
                                      padding: EdgeInsets.all(8),
                                      child: Icon(
                                          Icons.remove_circle,
                                          color: Colors.red,
                                      ),
                                  ),
                                )
                              ],
                            ),
                          )
                        ),
                      );
                  }
              )
          )
        ],
      ),
    );
  }
}
