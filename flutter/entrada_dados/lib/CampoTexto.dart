import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class CampoTexto extends StatefulWidget {
  const CampoTexto({Key? key}) : super(key: key);

  @override
  State<CampoTexto> createState() => _CampoTextoState();
}

class _CampoTextoState extends State<CampoTexto> {

  TextEditingController _textEditingController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Entrada de daods"),
      ),
      body: Column(
        children: [
          Padding(
              padding: EdgeInsets.all(32),
              child: TextField(
                //abaixo opções text, number, emailAddress, datetime
                keyboardType: TextInputType.text,
                decoration: InputDecoration(
                  labelText: "Digite um valor",
                ),
                //ativa true desativa false
                enabled: true,
                //maximo de caracteres
                maxLength: 5,
                //tag abaixo permiti digitar acima do length acima só que o campo fica em vermelho
                maxLengthEnforcement: (MaxLengthEnforcement.none),
                //estilo para a o texto digitado no campo
                /*style: TextStyle(
                  fontSize: 25,
                  color: Colors.green,
                ),*/
                //campos de senha usar tag abaixo
                /*obscureText: true,*/
                // onChanged captura sempre que sofre mudança/alterado e passa para uma função
                /*onChanged: (String e){
                  print("valor digitado: " + e);
                },*/
                //quando o usuario digita e conclui apertando o Submitted variavel e será o valor passado
                /*onSubmitted: (String e){
                  print("valor digitado: " + e);
                },*/
                controller: _textEditingController,
              )),
          RaisedButton(
            onPressed: (){
              print("valor digitado: " + _textEditingController.text);
            },
            child: Text("Salvar"),
            color: Colors.lightGreen,
          )
        ],
      ),
    );
  }
}
