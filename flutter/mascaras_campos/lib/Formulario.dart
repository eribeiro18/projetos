import 'package:brasil_fields/brasil_fields.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:validadores/validadores.dart';

class Formulario extends StatefulWidget {
  const Formulario({Key? key}) : super(key: key);

  @override
  State<Formulario> createState() => _FormularioState();
}

class _FormularioState extends State<Formulario> {

  final _formKey = GlobalKey<FormState>();
  String _mensagem = "Mensagem inicial.";
  TextEditingController _nomeController = TextEditingController();
  TextEditingController _idadeController = TextEditingController();
  TextEditingController _cpfController = TextEditingController();

  String? _nome;
  String? _idade;
  String? _cpf;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Formulário"),
      ),
      body: Container(
        padding: EdgeInsets.all(16),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              TextFormField(
                controller: _nomeController,
                onSaved: (vl){
                  _nome = vl;
                },
                decoration: InputDecoration(
                  hintText: "Digite seu nome"
                ),
                validator: (valor){
                  return Validador()
                      .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                      .minLength(5, msg: "Mínimo de 5 caracteres")
                      .maxLength(100, msg: "Maximo de 100 caracteres").validar(valor);
                },
              ),
              TextFormField(
                controller: _idadeController,
                keyboardType: TextInputType.number,
                onSaved: (vl){
                  _idade = vl;
                },
                decoration: InputDecoration(
                    hintText: "Digite sua idade!"
                ),
                validator: (valor){
                  if(valor!.isEmpty){
                    return "O campo é obrigatório!";
                  }
                  return null;
                },
              ),
              TextFormField(
                controller: _cpfController,
                keyboardType: TextInputType.number,
                onSaved: (vl){
                  _cpf = vl;
                },
                inputFormatters: [
                  FilteringTextInputFormatter.digitsOnly,
                  CpfInputFormatter()
                ],
                decoration: InputDecoration(
                    hintText: "Digite seu CPF!"
                ),
                validator: (valor){
                    return Validador()
                        .add(Validar.OBRIGATORIO, msg: "Campo obrigatório")
                        .add(Validar.CPF, msg: "CPF Inválido").validar(valor);
                },
              ),
              ElevatedButton(
                  onPressed: (){
                    if(_formKey.currentState!.validate()){
                      _formKey.currentState!.save();
                      setState(() {
                        /*String _nome = _nomeController.text;
                        String _idade = _idadeController.text;
                        String _cpf = _cpfController.text;*/
                        _mensagem = "Nome: ${_nome} idade: ${_idade} CPF: ${_cpf}";
                      });
                    }
                  },
                  child: Text("Salvar")),
              Text(
                _mensagem,
                style: TextStyle(
                  fontSize: 25
                ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
