import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:uber/model/Usuario.dart';

class Cadastro extends StatefulWidget {
  @override
  _CadastroState createState() => _CadastroState();
}

class _CadastroState extends State<Cadastro> {

  TextEditingController _controllerNome = TextEditingController();
  TextEditingController _controllerEmail = TextEditingController();
  TextEditingController _controllerSenha = TextEditingController();
  bool _tipoUsuario = false;
  String _mensagemErro = "";
  bool _carregando = false;

  _validarCampos(){
    String nome = _controllerNome.text;
    String email = _controllerEmail.text;
    String senha = _controllerSenha.text;
    String mensagem = "";
    if(nome.isEmpty){
      mensagem = "Nome deve ser infomrado!";
    }else if(email.isEmpty || !email.contains("@")){
      mensagem = "E-mail deve ser informado! E deve ser um e-mail valido!";
    }else if(senha.isEmpty || senha.length < 6){
      mensagem = "Senha deve ser informado! E deve possuir 6 ou mais caracteres!";
    }else{
      mensagem = "";
    }
    if(mensagem.isEmpty){
      Usuario usuario = Usuario();
      usuario.nome = nome;
      usuario.email = email;
      usuario.senha = senha;
      usuario.tipoUsuario = usuario.verificaTipoUsuario(_tipoUsuario);
      _cadastrarUsuario(usuario);
    }
    setState(() {
      _mensagemErro = mensagem;
    });
  }

  _cadastrarUsuario(Usuario usuario){
      setState(() {
        _carregando = true;
      });
      FirebaseAuth auth = FirebaseAuth.instance;
      FirebaseFirestore db = FirebaseFirestore.instance;

      auth.createUserWithEmailAndPassword(
          email: usuario.email!,
          password: usuario.senha!
      ).then((value) {
        db.collection("usuarios")
            .doc(value.user!.uid)
            .set(usuario.toMap());
        setState(() {
          _carregando = false;
        });
        switch(usuario.tipoUsuario){
          case "motorista":
            Navigator.pushNamedAndRemoveUntil(
                context,
                "/painel-motorista",
                (route) => false
            );
            break;
          case "passageiro":
            Navigator.pushNamedAndRemoveUntil(
                context,
                "/painel-passagerio",
                    (route) => false
            );
            break;
        }
      }).catchError((error){
        _mensagemErro = "Erro ao cadastrar usuario, verifique os campos e tente novamente!";
      });;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Cadastro"),
      ),
      body: Container(
        padding: EdgeInsets.all(16),
        child: Center(
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: <Widget>[
                TextField(
                  controller: _controllerNome,
                  autofocus: true,
                  keyboardType: TextInputType.text,
                  style: TextStyle(fontSize: 20),
                  decoration: InputDecoration(
                      contentPadding: EdgeInsets.fromLTRB(32, 16, 32, 16),
                      hintText: "Nome completo",
                      filled: true,
                      fillColor: Colors.white,
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(6)
                      )
                  ),
                ),
                TextField(
                  controller: _controllerEmail,
                  keyboardType: TextInputType.emailAddress,
                  style: TextStyle(fontSize: 20),
                  decoration: InputDecoration(
                      contentPadding: EdgeInsets.fromLTRB(32, 16, 32, 16),
                      hintText: "e-mail",
                      filled: true,
                      fillColor: Colors.white,
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(6)
                      )
                  ),
                ),
                TextField(
                  controller: _controllerSenha,
                  obscureText: true,
                  keyboardType: TextInputType.emailAddress,
                  style: TextStyle(fontSize: 20),
                  decoration: InputDecoration(
                      contentPadding: EdgeInsets.fromLTRB(32, 16, 32, 16),
                      hintText: "senha",
                      filled: true,
                      fillColor: Colors.white,
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(6)
                      )
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(bottom: 10),
                  child: Row(
                    children: <Widget>[
                      Text("Passageiro"),
                      Switch(
                          value: _tipoUsuario,
                          onChanged: (bool valor){
                            setState(() {
                              _tipoUsuario = valor;
                            });
                          }
                      ),
                      Text("Motorista"),
                    ],
                  ),
                ),
                Padding(
                  padding: EdgeInsets.only(top: 16, bottom: 10),
                  child: ElevatedButton(
                    onPressed: (){
                      _validarCampos();
                    },
                    child: Text(
                      "Cadastrar",
                      style: TextStyle(color: Colors.white, fontSize: 20),
                    ),
                    style: ElevatedButton.styleFrom(
                      primary: Color(0xff1ebbd8),
                      padding: EdgeInsets.fromLTRB(30, 16, 32, 16),
                    ),
                  ),
                ),
                _carregando
                    ? Center(child: CircularProgressIndicator(backgroundColor: Colors.white,),)
                    : Container(),
                Padding(
                  padding: EdgeInsets.only(top: 16),
                  child: Center(
                    child: Text(
                      _mensagemErro,
                      style: TextStyle(color: Colors.red, fontSize: 20),
                    ),
                  ),
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
