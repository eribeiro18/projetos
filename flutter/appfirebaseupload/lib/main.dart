import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:image_picker/image_picker.dart';
import 'dart:io';
import 'package:firebase_storage/firebase_storage.dart';

void main() async {
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp();
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter example upload arq firebase',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const MyHomePage(title: 'Flutter example upload arq firebase'),
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

  File? _imagem;
  String _statusUpload = "Upload não iniciado";
  String _urlImagemRecuperada = "";

  Future _recuperarImagem(bool daCamera) async{
    XFile? imagemSelecionada;
    if(daCamera){ //carrega da camera
      imagemSelecionada = await ImagePicker().pickImage(source: ImageSource.camera);
    }else{//carrega da galeria
      imagemSelecionada = await ImagePicker().pickImage(source: ImageSource.gallery);
    }
    setState(() {
      _imagem = File(imagemSelecionada!.path);
    });
  }

  Future _uploadImagem() async {
    FirebaseStorage storage = FirebaseStorage.instance;
    Reference pastaRaiz = storage.ref();
    Reference arquivo = pastaRaiz
        .child("fotos")
        .child("fotos1.jpg");

    UploadTask task = arquivo.putFile(_imagem!);
    task.snapshotEvents.listen((TaskSnapshot taskSnapshot) {
      if(taskSnapshot.state == TaskState.running){
        setState(() {
          _statusUpload = "Em progresso";
        });
      }else if(taskSnapshot.state == TaskState.success){
        _recuperarUrlImagem(taskSnapshot);
        setState(() {
          _statusUpload = "Upload realizado com sucesso!";
        });
      }
    });
  }

  Future _recuperarUrlImagem(TaskSnapshot taskSnapshot) async{
    String url = await taskSnapshot.ref.getDownloadURL();
    print("resultado url: " + url);
    setState(() {
      _urlImagemRecuperada = url;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(widget.title),
      ),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            Text(_statusUpload),
            ElevatedButton(
                onPressed: (){
                  _recuperarImagem(true);
                },
                child: Text("Camera")
            ),
            ElevatedButton(
                onPressed: (){
                  _recuperarImagem(false);
                },
                child: Text("Galeria")
            ),
            _imagem == null ? Container() : Image.file(_imagem!),
            _imagem == null ? Container() :
              ElevatedButton(
                onPressed: (){
                  _uploadImagem();
                },
                child: Text("Upload Storage"),
            ),
            _urlImagemRecuperada == null || _urlImagemRecuperada == "" ?
                Container() :
                Image.network(_urlImagemRecuperada)
          ],
        ),
      ),
 /*     floatingActionButton: FloatingActionButton(
        onPressed: (){},
        tooltip: 'Increment',
        child: const Icon(Icons.add),
      ),*/
    );
  }
}
