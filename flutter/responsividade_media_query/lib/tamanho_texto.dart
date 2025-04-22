import 'package:auto_size_text_pk/auto_size_text_pk.dart';
import 'package:flutter/material.dart';

class TamanhoTexto extends StatefulWidget {
  const TamanhoTexto({super.key});

  @override
  State<TamanhoTexto> createState() => _TamanhoTextoState();
}

class _TamanhoTextoState extends State<TamanhoTexto> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Tamanho textos"),
      ),
      body: Column(
        children: [
          Text(
              "Lorem ipsum sagittis sodales aliquam taciti interdum, donec porttitor sodales id aliquam magna fames, nibh vestibulum tellus molestie nibh. varius potenti purus tempus consectetur in mollis leo pulvinar morbi scelerisque, fusce elementum posuere congue orci blandit tempor suscipit imperdiet, sit blandit habitant dapibus sodales pellentesque etiam duis arcu. pharetra sodales fermentum amet mollis phasellus sociosqu lectus, ornare cubilia luctus curae semper curae bibendum senectus, vel arcu tincidunt ut duis imperdiet. ornare rhoncus varius sem est at eleifend donec scelerisque cras, non ut nisl nostra mollis sapien congue aenean interdum, scelerisque vehicula egestas fames nostra justo volutpat vivamus.",
              style: TextStyle(fontSize: 18),
          ),
          SizedBox(height: 50,),
          AutoSizeText(
            "Lorem ipsum sagittis sodales aliquam taciti interdum, donec porttitor sodales id aliquam magna fames, nibh vestibulum tellus molestie nibh. varius potenti purus tempus consectetur in mollis leo pulvinar morbi scelerisque, fusce elementum posuere congue orci blandit tempor suscipit imperdiet, sit blandit habitant dapibus sodales pellentesque etiam duis arcu. pharetra sodales fermentum amet mollis phasellus sociosqu lectus, ornare cubilia luctus curae semper curae bibendum senectus, vel arcu tincidunt ut duis imperdiet. ornare rhoncus varius sem est at eleifend donec scelerisque cras, non ut nisl nostra mollis sapien congue aenean interdum, scelerisque vehicula egestas fames nostra justo volutpat vivamus.",
            style: TextStyle(fontSize: 30),
            maxLines: 2,
            minFontSize: 14, //chega no limite de maximo de linhas com o tamanho minimo da fonte começa a cortar o texto
            stepGranularity: 10, //reduz a fonte de 10 em 10 pode ser usado o presentFontSize[30, 22, 10] ou seja ao envés de cair de 10 em 10 vai caindo de 30 depois 22 e ao final 10
            overflow: TextOverflow.ellipsis, //inclui 3 pontinhos ao final do texto cortado
          ),
        ],
      ),
    );
  }
}
