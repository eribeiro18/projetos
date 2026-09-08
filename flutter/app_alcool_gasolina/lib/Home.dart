import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  @override
  _HomeState createState() => _HomeState();
}

class _HomeState extends State<Home> {

  TextEditingController _controllerAlcool = TextEditingController();
  TextEditingController _controllerGasolina = TextEditingController();
  String _textoResultado = "";

  void _calcular(){
    double? precoAlcool = double.tryParse(_controllerAlcool.text);
    double? precoGasolina = double.tryParse(_controllerGasolina.text);
    String value = "";
    if(precoAlcool == null || precoGasolina == null){
        value = "Número invalido, digite números maiores que 0 e utilizando (.)";
    }else{
      if((precoAlcool / precoGasolina) >= 0.7){
        value = "Melhor abastecer com gasolina!";
      }else{
        value = "Melhor abastecer com alcool!";
      }
    }
    setState((){
      _textoResultado = value;
    });
    //caso queira limpar o campo após o clique do botão
    _limparCampos();
  }

  void _limparCampos(){
    _controllerGasolina.text = "";
    _controllerAlcool.text = "";
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Álcool ou Gasolina"),
        backgroundColor: Colors.blue,
      ),
      body: Container(
        child: SingleChildScrollView(
          padding: EdgeInsets.all(32),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Padding(
                padding: EdgeInsets.only(bottom: 32),
                child: Image.asset("images/logo.png"),
              ),
              Padding(
                padding: EdgeInsets.only(bottom: 10),
                child: Text(
                  "Saiba qual a melhor opção para abastecimento do seu carro",
                  style: TextStyle(
                      fontSize: 25,
                      fontWeight: FontWeight.bold
                  ),
                ),
              ),
              TextField(
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                    labelText: "Preço Alcool, ex: 1.59"
                ),
                style: TextStyle(
                    fontSize: 22
                ),
                controller: _controllerAlcool,
              ),
              TextField(
                keyboardType: TextInputType.number,
                decoration: InputDecoration(
                    labelText: "Preço Gasolina, ex: 3.59"
                ),
                style: TextStyle(
                    fontSize: 22
                ),
                controller: _controllerGasolina,
              ),
              Padding(
                padding: EdgeInsets.only(top: 10),
                child: ElevatedButton(
                  // Estilo personalizado para cores, padding e fonte
                  style: ElevatedButton.styleFrom(
                    // Cor de fundo do botão (substitui 'color')
                    backgroundColor: Colors.blue,

                    // Estilo do texto (substitui 'textColor')
                    foregroundColor: Colors.white,

                    // Padding interno (substitui 'padding')
                    padding: const EdgeInsets.all(15),

                    // Estilo do texto (para o 'Text' widget dentro do botão)
                    textStyle: const TextStyle(
                        fontSize: 20
                    ),
                  ),
                  // A ação a ser executada ao pressionar
                  onPressed: _calcular,

                  // O conteúdo do botão
                  child: const Text("Calcular"),
                )
              ),
              Padding(
                padding: EdgeInsets.only(top: 20),
                child: Text(
                  _textoResultado,
                  style: TextStyle(
                      fontSize: 22,
                      fontWeight: FontWeight.bold
                  ),
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
