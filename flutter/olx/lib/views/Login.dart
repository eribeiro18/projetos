import 'package:flutter/material.dart';
import 'package:olx/models/Usuario.dart';
import 'package:olx/views/widgets/BotaoCustomizado.dart';
import 'package:olx/views/widgets/InputCustomizado.dart';
import 'package:firebase_auth/firebase_auth.dart';

class Login extends StatefulWidget {
  const Login({Key? key}) : super(key: key);

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> {

  TextEditingController _controllerEmail = TextEditingController();
  TextEditingController _controllerSenha = TextEditingController();
  bool _cadastrar = false;
  String _mensagemErro = "";
  String _textoBotao = "Entrar";

  _validarCampos(){
    String msg = _validar();
    if(msg.isEmpty){
      Usuario usu = Usuario();
      usu.email = _controllerEmail.text;
      usu.senha = _controllerSenha.text;
      if(_cadastrar){
        _salvar(usu);
      }else{
        _logar(usu);
      }
    }else{
      setState(() {
        _mensagemErro = msg;
      });
    }
  }

  String _validar(){
    String msg = "";
    String email = _controllerEmail.text;
    String senha = _controllerSenha.text;
    if(email.isEmpty || !email.contains("@")){
      msg = "Informe um e-mail valido";
    }
    if(senha.isEmpty || senha.length < 6){
      msg = "Informe uma senha valida";
    }
    return msg;
  }

  _salvar(Usuario usuario) async{
    FirebaseAuth auth = FirebaseAuth.instance;
    auth.createUserWithEmailAndPassword(
        email: usuario.email!,
        password: usuario.senha!
    ).then((value){
      Navigator.pushReplacementNamed(context, "/");
    });
  }

  _logar(Usuario usuario) async{
    FirebaseAuth auth = FirebaseAuth.instance;
    auth.signInWithEmailAndPassword(
        email: usuario.email!,
        password: usuario.senha!
    ).then((value){
      Navigator.pushReplacementNamed(context, "/");
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(""),
      ),
      body: Container(
        padding: EdgeInsets.all(16),
        child: Center(
          child: SingleChildScrollView(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children: [
                Padding(
                    padding: EdgeInsets.only(bottom: 32),
                    child: Image.asset(
                        "imagens/logo.png",
                      width: 200,
                      height: 150,
                    ),
                ),
                InputCustomizado(
                    controller: _controllerEmail,
                    hint: "Email",
                    autofocus: true,
                    type: TextInputType.emailAddress,
                ),
                InputCustomizado(
                    controller: _controllerSenha,
                    hint: "Senha",
                    obscure: true
                ),
                Row(
                  mainAxisAlignment: MainAxisAlignment.center,
                  children: [
                    Text("Logar"),
                    Switch(
                        value: _cadastrar,
                        onChanged: (bool valor){
                          setState(() {
                            _cadastrar = valor;
                            _textoBotao = "Entrar";
                            if(_cadastrar){
                              _textoBotao = "Cadastrar";
                            }
                          });
                        }
                    ),
                    Text("Cadastrar"),
                  ],
                ),
                BotaoCustomizado(
                    texto: _textoBotao,
                    corTexto: Colors.white,
                    onPressed: (){
                      _validarCampos();
                    }
                ),
                BotaoCustomizado(
                    texto: "Ir Para anúncios",
                    corTexto: Colors.black,
                    onPressed: (){
                      Navigator.pushReplacementNamed(context, "/");
                    }
                ),
                Padding(
                    padding: EdgeInsets.only(top: 20),
                    child: Text(
                        _mensagemErro,
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold,
                          color: Colors.red
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
