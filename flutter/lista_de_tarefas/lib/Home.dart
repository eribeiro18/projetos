import 'dart:io';
import 'dart:async';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:path_provider/path_provider.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  List _listaTarefas = [];
  Map<String, dynamic> _ultimaTarefaRemovida = Map();
  TextEditingController _controllerTarefa = TextEditingController();

  Future<File> _getFile() async{
    final diretorio = await getApplicationDocumentsDirectory();
    return File("${diretorio.path}/dados.json");
  }

  _salvarTarefa(){
    String textoDigitado = _controllerTarefa.text;
    Map<String, dynamic> tarefa = Map();
    tarefa["titulo"] = textoDigitado;
    tarefa["realizada"] = false;
    setState(() {
      _listaTarefas.add(tarefa);
    });
    _salvarArquivo();
    _controllerTarefa.text = "";
  }

  _salvarArquivo() async{
    var arquivo = await _getFile();
    String dados = json.encode(_listaTarefas);
    arquivo.writeAsString(dados);
  }

  _lerArquivo() async {
    try{
      final arquivo = await _getFile();
      return arquivo.readAsString();
    }catch(e){
      return null;
    }
  }

  @override
  void initState() {
    super.initState();
    _lerArquivo().then( (dados){
      setState(() {
        _listaTarefas = json.decode(dados);
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    //_salvarArquivo();
    print("itens: " + _listaTarefas.toString());
    return Scaffold(
      appBar: AppBar(
        title: Text("Lista de tarefas"),
        backgroundColor: Colors.purple,
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.endFloat,
      floatingActionButton: FloatingActionButton(
        child: Icon(Icons.add),
        backgroundColor: Colors.purple,
        onPressed: (){
          showDialog(
              context: context,
              builder: (context){
                return AlertDialog(
                  title: Text("Adicionar Tarefa"),
                  content: TextField(
                    controller: _controllerTarefa,
                    decoration: InputDecoration(
                        labelText: "Digite sua tarefa"
                    ),
                    onChanged: (text){

                    },
                  ),
                  actions: [
                    TextButton(
                        onPressed: () => Navigator.pop(context),
                        child: Text("Cancelar")
                    ),
                    TextButton(
                        onPressed: (){
                          _salvarTarefa();
                          Navigator.pop(context);
                        },
                        child: Text("Salvar")
                    ),
                  ],
                );
              }
          );
        },
      ),
      body: Column(
        children: [
          Expanded(
              child: ListView.builder(
                  itemCount: _listaTarefas.length,
                  itemBuilder: criarItemLista
              )
          )
        ],
      ),
    );
  }

  Widget criarItemLista(context, index){
    //final item = _listaTarefas[index]["titulo"];

    return Dismissible(
        // ideal que a tag abaixo contenha uma chave sem repetição na lista exemplo id
        key: Key(DateTime.now().microsecondsSinceEpoch.toString()),
        direction: DismissDirection.endToStart,
        onDismissed: (direction){
          _ultimaTarefaRemovida = _listaTarefas[index];
          _listaTarefas.removeAt(index);
          _salvarArquivo();
          final snackbar = SnackBar(
              backgroundColor: Colors.deepPurple,
              duration: Duration(seconds: 5),
              content: Text("Tarefa Removida!!!"),
              action: SnackBarAction(
                label: "Defazer",
                onPressed: (){
                  setState(() {
                    //insere o item novamente na mesma posição de antes
                    _listaTarefas.insert(index, _ultimaTarefaRemovida);
                  });
                  _salvarArquivo();
                },
              ),
          );
          ScaffoldMessenger.of(context).showSnackBar(snackbar);
        },
        background: Container(
          color: Colors.red,
          padding: EdgeInsets.all(16),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.end,
            children: [
              Icon(Icons.delete, color: Colors.white)
            ],
          ),
        ),
        child: CheckboxListTile(
          title: Text(_listaTarefas[index]["titulo"]),
          value: _listaTarefas[index]["realizada"],
          onChanged: (value){
            setState(() {
              _listaTarefas[index]["realizada"] = value;
            });
            _salvarArquivo();
          },
        )
    );
  }
}
