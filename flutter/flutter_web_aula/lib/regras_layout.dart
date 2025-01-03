import 'package:flutter/material.dart';

class RegrasLayout extends StatefulWidget {
  const RegrasLayout({Key? key}) : super(key: key);

  @override
  _RegrasLayoutState createState() => _RegrasLayoutState();
}

class _RegrasLayoutState extends State<RegrasLayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Layout Builder'),
      ),
      body: Container(
        width: MediaQuery.of(context).size.width,
        //width: 300,
        height: MediaQuery.of(context).size.height,
        color: Colors.orange,
        //layoutBuilder se adequa ao tamanho do widget que esta,
        //nesta caso dentro do container
        child: LayoutBuilder(
          builder: (context, constraint){

            var largura = constraint.maxWidth;
            print("Largura: $largura");

            if( largura < 600 ){//celular
              return Text("Celulares");
            }else if( largura < 960 ) { //celular maiores e tablets
              return Text("Celulares & Tablets");
            }else{
              return Text("Telas maiores");
            }
            // return Container(
            //   child: Text('Jamilton Damasceno'),
            // );
          },
        ),
      ),
    );
  }
}
