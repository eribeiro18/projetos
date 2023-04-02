import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';
import 'package:geolocator/geolocator.dart';
import '../model/Usuario.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  TextEditingController _controllerEmail = TextEditingController();
  TextEditingController _controllerSenha = TextEditingController();
  String _mensagemErro = "";
  bool _carregando = false;

  _validarCampos(){
    String email = _controllerEmail.text;
    String senha = _controllerSenha.text;
    String mensagem = "";

    if(email.isEmpty || !email.contains("@")){
      mensagem = "E-mail deve ser informado!";
    }else if(senha.isEmpty || senha.length < 6){
      mensagem = "Senha deve ser informado!";
    }else{
      mensagem = "";
    }
    if(mensagem.isEmpty){
      Usuario usuario = Usuario();
      usuario.email = email;
      usuario.senha = senha;
      _logarUsuario(usuario);
    }
    setState(() {
      _mensagemErro = mensagem;
    });
  }

  _validatePermissoes() async {
    bool serviceEnabled;
    LocationPermission permission;
    serviceEnabled = await Geolocator.isLocationServiceEnabled();
    if (!serviceEnabled) {
      return Future.error('Location services are disabled.');
    }
    permission = await Geolocator.checkPermission();
    if (permission == LocationPermission.denied) {
      permission = await Geolocator.requestPermission();
      if (permission == LocationPermission.denied) {
        return Future.error('Location permissions are denied');
      }
    }
    if (permission == LocationPermission.deniedForever) {
      return Future.error(
          'Location permissions are permanently denied, we cannot request permissions.');
    }
  }

  _logarUsuario(Usuario usuario){
    setState(() {
      _carregando = true;
    });
    FirebaseAuth auth = FirebaseAuth.instance;
    auth.signInWithEmailAndPassword(
        email: usuario.email!,
        password: usuario.senha!
    ).then((value){
      _redirecionaPorTipo(value.user!.uid);
    }).catchError((error){
        _mensagemErro = "Erro ao autenticar usuario, verifique e-mail e senha!";
    });
  }

  _redirecionaPorTipo(String idUsuario) async {
    FirebaseFirestore db = FirebaseFirestore.instance;
    DocumentSnapshot snapshot = await db.collection("usuarios")
              .doc(idUsuario)
              .get();
    dynamic dados = snapshot.data();
    String tipoUsuario = dados["tipoUsuario"];
    setState(() {
      _carregando = false;
    });
    switch (tipoUsuario){
      case "passageiro":
        Navigator.pushReplacementNamed(context, "/painel-passageiro");
        break;
      case "motorista":
        Navigator.pushReplacementNamed(context, "/painel-motorista");
        break;
    }
  }

  Future _verificarUsuarioLogado() async {
    FirebaseAuth auth = FirebaseAuth.instance;
    User? usuarioLogado = await auth.currentUser;
    if (usuarioLogado != null) {
      String idUsuario = usuarioLogado.uid;
      _redirecionaPorTipo(idUsuario);
    }
  }

  @override
  void initState() {
    super.initState();
    _verificarUsuarioLogado();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        decoration: BoxDecoration(
          image: DecorationImage(
              image: AssetImage("imagens/fundo.png"),
            fit: BoxFit.cover
          )
        ),
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
                TextField(
                  controller: _controllerEmail,
                  autofocus: true,
                  keyboardType: TextInputType.emailAddress,
                  style: TextStyle(fontSize: 20),
                  decoration: InputDecoration(
                    contentPadding: EdgeInsets.fromLTRB(32, 16, 23, 16),
                    hintText: "E-mail",
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
                  keyboardType: TextInputType.visiblePassword,
                  style: TextStyle(fontSize: 20),
                  decoration: InputDecoration(
                      contentPadding: EdgeInsets.fromLTRB(32, 16, 23, 16),
                      hintText: "Senha",
                      filled: true,
                      fillColor: Colors.white,
                      border: OutlineInputBorder(
                          borderRadius: BorderRadius.circular(6)
                      )
                  ),
                ),
                Padding(
                    padding: EdgeInsets.only(top: 16, bottom: 10),
                  child: ElevatedButton(
                      onPressed: (){
                        _validarCampos();
                      },
                      child: Text(
                          "Entrar",
                          style: TextStyle(color: Colors.white, fontSize: 20),
                      ),
                    style: ElevatedButton.styleFrom(
                        primary: Color(0xff1ebbd8),
                        padding: EdgeInsets.fromLTRB(30, 16, 32, 16),
                    ),
                  ),
                ),
                Center(
                  child: GestureDetector(
                    child: Text(
                        "Não tem conta? cadastra-se",
                        style: TextStyle(
                          color: Colors.white
                        ),
                    ),
                    onTap: (){
                      Navigator.pushNamed(context, "/cadastro");
                    },
                  ),
                ),
                _carregando
                  ? Center(child: CircularProgressIndicator(backgroundColor: Colors.white,),)
                  : Container(),
                Padding(
                    padding: EdgeInsets.only(top: 16),
                  child:Center(
                    child: Text(
                      _mensagemErro,
                      style: TextStyle(
                          color: Colors.red,
                          fontSize: 20
                      ),
                    ),
                  )
                )
              ],
            ),
          ),
        ),
      ),
    );
  }
}
