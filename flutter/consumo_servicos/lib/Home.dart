import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
//com ass nomeia o import
import 'package:http/http.dart' as http;
import 'dart:convert';

 class Home extends StatefulWidget {
   const Home({Key? key}) : super(key: key);

   @override
   State<Home> createState() => _HomeState();
 }

 class _HomeState extends State<Home> {

   String _resultado = "Resultado";
   TextEditingController _textEditingController = TextEditingController();

   //metodo assincrono
   void _recupearaCep() async {
     String cep = _textEditingController.text;
     String url = "https://viacep.com.br/ws/${cep}/json/";
     http.Response response;

     response = await http.get(Uri.parse(url));
     Map<String, dynamic> retorno = json.decode(response.body);

     String logradouro = retorno["logradouro"];
     String complemento = retorno["complemento"];
     String bairro = retorno["bairro"];
     String localidade = retorno["localidade"];

     setState((){
       _resultado = "Resposta -\nlogradouro: ${logradouro}\n\ncomplemento: ${complemento}\n\nbairro: ${bairro}\n\nlocalidade: ${localidade}";
     });

     //print("Resposta status: " + response.statusCode.toString());
     //print("Resposta body: " + response.body);
     
     print(
         "Resposta -\n logradouro: ${logradouro}\n\n complemento: ${complemento}\n\n bairro: ${bairro}\n\n localidade: ${localidade}"
     );
   }

   @override
   Widget build(BuildContext context) {
     return Scaffold(
       appBar: AppBar(
         title: Text("Consumo de serviços web"),
       ),
       body: Container(
         padding: EdgeInsets.all(40),
         child: Column(
           children: [
             Padding(
                 padding: EdgeInsets.all(4),
                 child: TextField(
                   keyboardType: TextInputType.number,
                   decoration: InputDecoration(
                     labelText: "Digite o cep: ex: 05428200",
                   ),
                   maxLength: 8,
                   maxLengthEnforcement: (MaxLengthEnforcement.none),
                   controller: _textEditingController,
                 )
             ),
             ElevatedButton(
                 onPressed: _recupearaCep,
                 child: Text("Clique aqui")
             ),
             Text(_resultado),
           ],
         ),
       ),
     );
   }
 }
