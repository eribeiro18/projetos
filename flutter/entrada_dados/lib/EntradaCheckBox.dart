import 'package:flutter/material.dart';

class EntradaCheckBox extends StatefulWidget {
  const EntradaCheckBox({Key? key}) : super(key: key);

  @override
  State<EntradaCheckBox> createState() => _EntradaCheckBoxState();
}

class _EntradaCheckBoxState extends State<EntradaCheckBox> {

  bool _estaSelecionado = false;
  bool _estaSelecionado2 = false;
  bool _estaSelecionado3 = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Entrada Check Box"),
      ),
      body: Container(
        child: Column(
          children: [
            //este é clicavel na linha toda ou seja se clicar no label
            // o checkBox e marcado tbm
            CheckboxListTile(
                title: Text("Comida Brasileira 2"),
                subtitle: Text("A melhor comida do mundo!"),
                activeColor: Colors.red,
                //selected: true,
                //secondary: Icon(Icons.add_box),
                value: _estaSelecionado2,
                onChanged: (bool? v){
                  setState((){
                    _estaSelecionado2 = v!;
                  });
                }
            ),
            CheckboxListTile(
                title: Text("Comida Mexicana"),
                subtitle: Text("A melhor comida do mundo!"),
                activeColor: Colors.red,
                //selected: true,
                //secondary: Icon(Icons.add_box),
                value: _estaSelecionado3,
                onChanged: (bool? v){
                  setState((){
                    _estaSelecionado3 = v!;
                  });
                }
            ),
            ElevatedButton(
                onPressed: (){
                  print(
                    "Commida Brasileira: " + _estaSelecionado2.toString() +
                    "Commida Mexicana: " + _estaSelecionado3.toString()
                  );
                },
                child: Text(
                    "Salvar",
                    style: TextStyle(
                      fontSize: 20
                    ),)
            ),
            Text("Comida Brasileira"),
            Checkbox(
                value: _estaSelecionado,
                onChanged: (bool? e){
                  setState((){
                    _estaSelecionado = e!;
                  });
                }
            )
          ],
        ),
      )
    );
  }
}
