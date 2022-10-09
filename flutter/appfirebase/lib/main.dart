import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_auth/firebase_auth.dart';

void main() async {

  //OS COMANDO ABAIXO PARA FUNCIONAR DEVE DESCOMENTAR A LINHA INICIADA EM FirebaseFirestore
  //inicializar o firebase
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  /* FirebaseFirestore db = await FirebaseFirestore.instance;*/
  //FIM DO COMENTARIO

  //usando o doc utiliza um identificado gerado pelo proprio programador
  /*db.collection("usuarios")
      .doc("002")
      .set({
        "nome": "Ana Maria Silva",
        "idade": 25
  });*/
  //fim comentario

  //usando o add diferentemente do acima o proprio firebase gera um "id" unico
  /*DocumentReference ref = await db.collection("noticias")
  .add({
    "titulo": "Novo Planeta é Descoberto!!!",
    "descricao": "texto de exemplo..."
  });
  print("Referencia: " + ref.id);*/
  //fim comentario

  //abaixo atualizando com um id criado pelo firebase
  /*db.collection("noticias")
      .doc("WLCCBI0OUaMbT85IiABp")
      .set({
          "titulo": "Novo Planeta é Descoberto alterado!!!",
          "descricao": "texto de exemplo..."
        });*/
  //fim comentario

  //deletar um documento
  /*db.collection("usuarios")
  .doc("003").delete();*/
  //fim comentario

  //recuperando um unico documento
  /*DocumentSnapshot snapshot = await db.collection("usuarios")
                                      .doc("001")
                                      .get();
  dynamic dados = snapshot.data();
  print("dados formatados => nome: " + dados["nome"] + " idade: " + dados["idade"].toString());*/
  //fim comentario

  //recuperando todos os documentos
  /*QuerySnapshot querySnapshot = await db
      .collection("usuarios")
      .get();
  print("dados usuarios: " + querySnapshot.docs.toString());

  for(DocumentSnapshot item in querySnapshot.docs){
    dynamic dados = item.data();
    print("dados formatados => nome: " + dados["nome"] + " idade: " + dados["idade"].toString());
  }*/
  //fim comentario

  //recuperando todos os documentos com notificação caso no firebase adicione/exclua dados
  /*await db
  .collection("usuarios").snapshots().listen(
      (snapshot) {
        for(DocumentSnapshot item in snapshot.docs){
          dynamic dados = item.data();
          print("dados formatados => nome: " + dados["nome"] + " idade: " + dados["idade"].toString());
        }
      });*/
  //fim comentario

  //recuperando dados com condições ou seja dados especificos
  /*QuerySnapshot querySnapshot = await db.collection("usuarios")
      //.where("nome", isEqualTo: "evandro")
      //.where("idade", isEqualTo: 32)
      .where("idade", isLessThanOrEqualTo: 32)
      .where("idade", isGreaterThanOrEqualTo: 25)
      .orderBy("idade", descending: false)
      .orderBy("nome", descending: true)
      .limit(2)
      .get();

  for(DocumentSnapshot item in querySnapshot.docs){
    dynamic dados = item.data();
    print("dados formatados => nome: ${dados["nome"]} idade: ${dados["idade"].toString()}");
  }*/
  //fim comentario

  //recuperando dados com condições ou seja dados especificos em campos de string
  // no segundo where o + "\uf8ff" serve para tipo forçar o retorno da condição com valor "e"
/*  var pesquisaIni = "a"; //dados que viriam da tela
  var pesquisaFim = "e"; //dados que viriam da tela
  QuerySnapshot querySnapshot = await db.collection("usuarios")
      .where("nome", isGreaterThanOrEqualTo: pesquisaIni)
      .where("nome", isLessThanOrEqualTo: pesquisaFim + "\uf8ff")
      .get();

  for(DocumentSnapshot item in querySnapshot.docs){
    dynamic dados = item.data();
    print("dados formatados => nome: ${dados["nome"]} idade: ${dados["idade"].toString()}");
  }*/
  //fim comentario


  //TRABALHANDO COM AUTENTICAÇÃO
  /*CRIANDO USÚARIO COM E-MAIL E SENHA*/
  //PARA ISSO DEVE SER HABILITADO A OPÇÃO NO FIREBASE AUTENTICA POR EMAIL E SENHA
  /*FirebaseAuth auth = await FirebaseAuth.instance;
  String email = "evandro2@gmail.com"; //dados viria de um cadastro com tela
  String senha = "123456"; //dados viria de um cadastro com tela
  auth.createUserWithEmailAndPassword(
      email: email,
      password: senha
  ).then((firebaseUser) {
    print("novo usuario: sucesso!! e-mail: ${firebaseUser.user!.email}");
  }).catchError((erro){
    print("novo usuario: erro!! " + erro.toString());
  });*/
  //FIM COMENTARIOS

  //VERIFICANDO SE EXISTE USUARIO LOGADO, PARA ESTE CONTEXTO COMO ELE ACOBOU DE
  //SER CRIADO O USUARIO RETORNADO SERA O DO TRECHO COMENTADO ACIMA
/*  FirebaseAuth auth = await FirebaseAuth.instance;
  User? usuarioAtual = await auth.currentUser;
  if(usuarioAtual != null){
    print("usuario atual logado ${usuarioAtual.email}");
  }else{
    print("usuario atual DESLOGADO!!!");
  }*/
  //FIM COMENTARIOS

  //DESLOGAR O USUARIO
/*  FirebaseAuth auth = await FirebaseAuth.instance;
  auth.signOut();*/
  //FIM COMENTARIOS

  //LOGAR O USUARIO
/*  FirebaseAuth auth = await FirebaseAuth.instance;
  String email = "evandro2@gmail.com"; //dados viria de um cadastro com tela
  String senha = "123456"; //dados viria de um cadastro com tela
  auth.signInWithEmailAndPassword(
      email: email, 
      password: senha
  ).then((firebaseUser) {
    //deu certo manda para a tela home ou tela inicial
    print("Logar usuario: sucesso!! e-mail: ${firebaseUser.user!.email}");
  }).catchError((erro){
    print("Logar usuario: erro!! " + erro.toString());
  });*/
  //FIM COMENTARIOS

  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[

          ],
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ), // This trailing comma makes auto-formatting nicer for build methods.
    );
  }
}
