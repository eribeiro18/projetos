import 'package:flutter/material.dart';

class EntradaSwitch extends StatefulWidget {
  const EntradaSwitch({Key? key}) : super(key: key);

  @override
  State<EntradaSwitch> createState() => _EntradaSwitchState();
}

class _EntradaSwitchState extends State<EntradaSwitch> {

  bool _escolhaUsuario = false;
  bool _escolhaUsuario2 = false;
  bool _escolhaUsuario3 = false;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Entrada Switch"),
        ),
        body: Container(
          child: Column(
            children: [
              //caso tente colocar Row ao envés de column(pai do children acima)
              //não dará certo pois o componente abaixo é um list
              //mesmas tags do checkbox pode ser usado aqui
              SwitchListTile(
                  activeColor: Colors.teal,
                  secondary: Icon(Icons.add_box),
                  title: Text("Receber notificação 1.0?"),
                  value: _escolhaUsuario2,
                  onChanged: (bool? escolha){
                    setState((){
                      _escolhaUsuario2 = escolha!;
                    });
                  }
              ),
              SwitchListTile(
                  title: Text("Receber notificação 2.0?"),
                  value: _escolhaUsuario3,
                  onChanged: (bool? escolha){
                    setState((){
                      _escolhaUsuario3 = escolha!;
                    });
                  }
              ),
              Switch(
                  value: _escolhaUsuario,
                  onChanged: (bool? escolha){
                    setState((){
                      _escolhaUsuario = escolha!;
                    });
                  }
              ),
              //1.0,2.0 e 3.0 somente label nada relacionado a versão
              Text("Receber notificação 3.0?"),
              ElevatedButton(
                  onPressed: (){
                    print("Resultado 3.0: " + _escolhaUsuario.toString());
                    print("Resultado 1.0: " + _escolhaUsuario2.toString());
                    print("Resultado 2.0: " + _escolhaUsuario3.toString());
                  },
                  child: Text(
                    "Salvar",
                    style: TextStyle(
                        fontSize: 20
                    ),
                  )
              ),
            ],
          ),
        )
    );
  }
}
