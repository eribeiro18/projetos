import 'package:flutter/material.dart';

class EntradaRaionButton extends StatefulWidget {
  const EntradaRaionButton({Key? key}) : super(key: key);

  @override
  State<EntradaRaionButton> createState() => _EntradaRaionButtonState();
}

class _EntradaRaionButtonState extends State<EntradaRaionButton> {

  //como o value do Radio é string a variavel abaixo deve ser String
  String _escolhaUsuario = "";
  int _escolhaUsuario2 = 3;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Entrada Radio Button"),
        ),
        body: Container(
          child: Column(
            children: [
              //caso tente colocar Row ao envés de column(pai do children acima)
              //não dará certo pois o componente abaixo é um list
              RadioListTile(
                title: Text("Masculino"),
                  value: 1,
                  groupValue: _escolhaUsuario2,
                  onChanged: (int? escolha){
                    setState((){
                      _escolhaUsuario2 = escolha!;
                    });
                  }
              ),
              RadioListTile(
                  title: Text("Feminino"),
                  value: 2,
                  groupValue: _escolhaUsuario2,
                  onChanged: (int? escolha){
                    setState((){
                      _escolhaUsuario2 = escolha!;
                    });
                  }
              ),
              ElevatedButton(
                  onPressed: (){
                      print("Resultado: " + _escolhaUsuario2.toString());
                  },
                  child: Text(
                      "Salvar",
                    style: TextStyle(
                        fontSize: 20
                    ),
                  )
              ),
              Text("Masculino"),
              Radio(
                  value: "m",
                  groupValue: _escolhaUsuario,
                  onChanged: (String? escolha){
                    setState((){
                      _escolhaUsuario = escolha!;
                    });
                  }
              ),
              Text("Feminio"),
              Radio(
                  value: "f",
                  groupValue: _escolhaUsuario,
                  onChanged: (String? escolha){
                    setState((){
                      _escolhaUsuario = escolha!;
                    });
                  }
              ),
            ],
          ),
        )
    );
  }
}
