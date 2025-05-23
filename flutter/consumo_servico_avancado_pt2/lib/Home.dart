import 'package:consumo_servico_avancado_pt2/Post.dart';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import 'dart:async';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> {

  String _urlBase = "https://jsonplaceholder.typicode.com";

  Future<List<Post>> _recuperarPostagens() async{
    List<Post> postagens = [];
    http.Response response = await http.get(Uri.parse(_urlBase + "/posts"));
    var dadosJson = json.decode(response.body);
    for(var post in dadosJson){
      Post p = Post(post["userId"], post["id"], post["title"], post["body"]);
      postagens.add(p);
    }
    return postagens;
  }

  _post() async{
    Post post = Post(120, null, "Titulo", "Corpo da postagem");
    var corpo = json.encode(post.toJson());
    http.Response response = await http.post(
        Uri.parse(_urlBase + "/posts"),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
        body: corpo
    );
    print("resposta: ${response.statusCode}");
    print("resposta: ${response.body}");
  }

  _put() async{
    Post post = Post(120, null, "Titulo alterado", "Corpo da postagem alterada");
    var corpo = json.encode(post.toJson());
    http.Response response = await http.put(
        Uri.parse(_urlBase + "/posts/2"),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
        body: corpo
    );
    print("resposta: ${response.statusCode}");
    print("resposta: ${response.body}");
  }

  _patch() async {
    Post post = Post(120, null, null, "Corpo da postagem alterada");
    var corpo = json.encode(post.toJson());
    http.Response response = await http.patch(
        Uri.parse(_urlBase + "/posts/2"),
        headers: {
          'Content-type': 'application/json; charset=UTF-8',
        },
        body: corpo
    );
    print("resposta: ${response.statusCode}");
    print("resposta: ${response.body}");
  }

  _delete() async{
    http.Response response = await http.delete(
        Uri.parse(_urlBase + "/posts/2")
    );
    print("resposta: ${response.statusCode}");
    print("resposta: ${response.body}");
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Consumo de serviço avançado parte 2"),
      ),
      body: Container(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            Row(
              children: [
                ElevatedButton(
                    onPressed: _post,
                    child: Text("Salvar")
                ),
                ElevatedButton(
                    onPressed: _put,
                    child: Text("Atualizar")
                ),
                ElevatedButton(
                    onPressed: _delete,
                    child: Text("Remover")
                ),
              ],
            ),
            Expanded(
                child: FutureBuilder<List<Post>>(
                    future: _recuperarPostagens(),
                    builder: (context, snapshot){
                      switch (snapshot.connectionState){
                        case ConnectionState.none:
                        case ConnectionState.waiting:
                          return Center(
                            child: CircularProgressIndicator(),
                          );
                        case ConnectionState.active:
                        case ConnectionState.done:
                          if(snapshot.hasError){
                            print("Lista: Erro ao carregar");
                          }else{
                            return ListView.builder(
                                itemCount: snapshot.data?.length,
                                itemBuilder: (context, index){
                                  List<Post>? lista = snapshot.data;
                                  Post post = lista![index];
                                  return ListTile(
                                    title: Text(post.title!),
                                    subtitle: Text(post.id.toString()),
                                  );
                                }
                            );
                          }
                          break;
                      }
                      return Center(
                        child: Text(""),
                      );
                    }
                )
            )
          ],
        ),
      ),
    );
  }
}
