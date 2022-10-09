import 'package:flutter/material.dart';
import 'package:youtube/Api.dart';
import 'package:youtube/model/Video.dart';
import 'package:youtube/telas/YoutubePlay.dart';

class Inicio extends StatefulWidget {

  String pesquisa;

  Inicio({Key? key, required this.pesquisa}) : super(key: key);

  @override
  State<Inicio> createState() => _InicioState();
}

class _InicioState extends State<Inicio> {

  _listarVideos(String pesquisa){
    Api api =Api();
    return api.pesquisar(pesquisa);
  }

  @override
  void initState() {
    super.initState();
    print("chamado 1 - initState");
  }

  @override
  void didChangeDependencies() {
    super.didChangeDependencies();
    print("chamado 2 - didChangeDependencies");
  }

  void _abrirTelaVideo(Video video){
    Navigator.push(
        context,
        MaterialPageRoute(builder: (context) => YoutubePlay(video: video)));
  }

  @override
  void didUpdateWidget(covariant Inicio oldWidget) {
    super.didUpdateWidget(oldWidget);
    print("chamado 2 - didUpdateWidget");
  }

  @override
  void dispose() {
    super.dispose();
    //descarrega a tela ou quando finaliza a execução da mesma
    print("chamado 4 - dispose");
  }

  @override
  Widget build(BuildContext context) {
    print("chamado 3 - build");
    return FutureBuilder<List<Video>>(
      future: _listarVideos(widget.pesquisa),
      builder: (context, snapshot){
        switch(snapshot.connectionState){
          case ConnectionState.none:
          case ConnectionState.waiting:
            return Center(
              child: CircularProgressIndicator(),
            );
          case ConnectionState.active:
          case ConnectionState.done:
            if(snapshot.hasData){
              return ListView.separated(
                  itemBuilder: (context, index){
                    List<Video> videos = snapshot.data!;
                    Video video = videos[index];
                    return GestureDetector(
                      onTap: (){
                        _abrirTelaVideo(video);
                      },
                      child: Column(
                        children: [
                          Container(
                            height: 200,
                            decoration: BoxDecoration(
                                image: DecorationImage(
                                  //tag para qual cobertura a imagem irá ocupar dentro do conteiner
                                  fit: BoxFit.cover,
                                  image: NetworkImage(video.imagem!),
                                )
                            ),
                          ),
                          ListTile(
                            title: Text(video.titulo!),
                            subtitle: Text(video.canal!),
                          )
                        ],
                      ),
                    );
                  },
                  separatorBuilder: (context, index) => Divider(
                    height: 2,
                    color: Colors.grey,
                  ),
                  itemCount: snapshot.data!.length
              );
            }else{
              return Center(
                child: Text("Nenhum dado a se exibido!"),
              );
            }
        }
      },
    );
  }
}
