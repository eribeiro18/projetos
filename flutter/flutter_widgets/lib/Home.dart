import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {
  List _lista = ["Evandro", "Mayra", "Marina", "Izabela"];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Widgets"),
      ),
      body: Column(
        children: [
          Expanded(
              child:
              ListView.builder(
                itemCount: _lista.length,
                  itemBuilder: (context, index){
                    final item = _lista[index];
                    return Dismissible(
                        //o backgroud é para dar uma cor de efeito
                        //ao arrastar a linha
                        background: Container(
                          color: Colors.green,
                          padding: EdgeInsets.all(16),
                          child: Row(
                            mainAxisAlignment: MainAxisAlignment.start,
                            children: [
                              Icon(
                                  Icons.edit,
                                  color: Colors.white,
                              ),
                            ],
                          )
                        ),
                       //o secondaryBackgroundé pra dar uma
                       //segunda opção de cor ao arrastar
                       secondaryBackground: Container(
                           color: Colors.red,
                           padding: EdgeInsets.all(16),
                           child: Row(
                             mainAxisAlignment: MainAxisAlignment.end,
                             children: [
                               Icon(
                                 Icons.delete,
                                 color: Colors.white,
                               ),
                             ],
                           )
                       ),
                       //tag abaixo é pra onde o item será arrastado
                       //direction: DismissDirection.horizontal,
                        onDismissed: (direction){
                          //com os ifs pode dar uma ação diferente para cada lado que arrastar o registro da linha
                          if(direction == DismissDirection.endToStart){

                          }else if(direction == DismissDirection.startToEnd){

                          }
                          setState(() {
                            _lista.removeAt(index);
                          });
                        },
                        key: Key(item),
                        child: ListTile(
                            title: Text(item),
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
