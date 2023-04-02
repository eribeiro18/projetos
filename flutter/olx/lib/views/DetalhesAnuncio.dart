import 'package:carousel_pro/carousel_pro.dart';
import 'package:flutter/material.dart';
import 'package:olx/main.dart';
import 'package:olx/models/Anuncio.dart';
import 'package:url_launcher/url_launcher_string.dart';

class DetalhesAnuncio extends StatefulWidget {

  final Anuncio anuncio;

  const DetalhesAnuncio({Key? key, required this.anuncio}) : super(key: key);

  @override
  State<DetalhesAnuncio> createState() => _DetalhesAnuncioState();
}

class _DetalhesAnuncioState extends State<DetalhesAnuncio> {

  Anuncio? _anuncio;

  List<Widget> _getListaImagens(){
    List<String> listaUrlImagens = _anuncio!.fotos!;
    return listaUrlImagens.map((e){
      return Container(
        height: 250,
        decoration: BoxDecoration(
          image: DecorationImage(
            image: NetworkImage(e),
            fit: BoxFit.fitWidth
          )
        ),
      );
    }).toList();
  }

  @override
  void initState() {
    super.initState();
    _anuncio = widget.anuncio;
  }

  _ligarTelefone(String telefone) async{
    if( await canLaunchUrlString("tel:$telefone")){
      await launchUrlString("tel:$telefone");
    }else {

    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Anúncio"),
      ),
      body: Stack(
        children: [
          ListView(
            children: [
              SizedBox(
                height: 250,
                child: Carousel(
                  images: _getListaImagens(),
                  dotSize: 8,
                  dotBgColor: Colors.transparent,
                  dotColor: Colors.white,
                  autoplay: false,
                  dotIncreasedColor: theme.primaryColor,
                ),
              ),
              Container(
                padding: EdgeInsets.all(16),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    Text(
                        "R\$ ${_anuncio!.preco!}",
                        style: TextStyle(
                          fontSize: 32,
                          fontWeight: FontWeight.bold,
                          color: theme.primaryColor
                        ),
                    ),
                    Text(
                      "${_anuncio!.titulo!}",
                      style: TextStyle(
                          fontSize: 25,
                          fontWeight: FontWeight.w400,
                      ),
                    ),
                    Padding(
                        padding: EdgeInsets.symmetric(vertical: 16),
                        child: Divider(),
                    ),
                    Text(
                      "Descição",
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      "${_anuncio!.descricao!}",
                      style: TextStyle(
                        fontSize: 18
                      ),
                    ),
                    Padding(
                      padding: EdgeInsets.symmetric(vertical: 16),
                      child: Divider(),
                    ),
                    Text(
                      "Contato",
                      style: TextStyle(
                        fontSize: 18,
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Padding(
                        padding: EdgeInsets.only(bottom: 66),
                        child: Text(
                          "${_anuncio!.telefone!}",
                          style: TextStyle(
                              fontSize: 18
                          ),
                        ),
                    )
                  ],
                ),
              )
            ],),
            Positioned(
                left: 16,
                right: 16,
                bottom: 16,
                child: GestureDetector(
                  child: Container(
                    child: Text(
                        "Ligar",
                      style: TextStyle(
                        color: Colors.white,
                        fontSize: 20
                      ),
                    ),
                    padding: EdgeInsets.all(16),
                    alignment: Alignment.center,
                    decoration: BoxDecoration(
                      color: theme.primaryColor,
                      borderRadius: BorderRadius.circular(30)
                    ),
                  ),
                  onTap: (){
                    _ligarTelefone(_anuncio!.telefone!);
                  },
                )
            )
        ],
      ),
    );
  }
}
