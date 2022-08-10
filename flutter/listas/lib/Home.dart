import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  List _itens = [];

  void _carregaItens(){
    for(int i=0; i<=10; i++){
      Map<String, dynamic> item = Map();
      item["titulo"] = "Título ${i} Lorem ipsum dolor sit amet;";
      item["descricao"] = "Descriçãp ${i} Lorem ipsum dolor sit amet;";
      _itens.add(item);
    }
  }

  @override
  Widget build(BuildContext context) {

    _carregaItens();

    return Scaffold(
      appBar: AppBar(
        title: Text("Lista")
      ),
      body: Container(
        padding: EdgeInsets.all(20),
        child: ListView.builder(
            itemCount: _itens.length,
            itemBuilder: (context, indice){
              return ListTile(
                onTap: (){
                  showDialog(
                      context: context,
                      builder: (context){
                        return AlertDialog(
                          title: Text(_itens[indice]["titulo"]),
                          titlePadding: EdgeInsets.all(20),
                          titleTextStyle: TextStyle(
                            fontSize: 20,
                            color: Colors.purple
                          ),
                          content: Text(_itens[indice]["descricao"]),
                          contentPadding: EdgeInsets.all(20),
                          contentTextStyle: TextStyle(
                              fontSize: 20,
                              color: Colors.purple
                          ),
                          backgroundColor: Colors.white,
                          actions: [
                            TextButton(
                                onPressed: (){
                                  print("Selecionado sim");
                                  Navigator.pop(context);
                                },
                              child: Text("Sim"),
                            ),
                            FloatingActionButton(
                              onPressed: (){
                                print("Selecionado não");
                                Navigator.pop(context);
                              },
                              child: Text("Não"),
                            ),
                            ElevatedButton(
                              onPressed: (){
                                print("Selecionado talvez");
                                Navigator.pop(context);
                              },
                              child: Text("talvez"),
                            ),
                          ],
                        );
                      }
                  );
                },
/*                onLongPress: () {

                },*/
                title: Text(_itens[indice]["titulo"]),
                subtitle: Text(_itens[indice]["descricao"]),
              );
            }
        ),
      ),
    );
  }
}
