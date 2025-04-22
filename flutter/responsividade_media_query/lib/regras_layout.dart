import 'package:flutter/material.dart';

class RegrasLayout extends StatefulWidget {
  const RegrasLayout({super.key});

  @override
  State<RegrasLayout> createState() => _RegrasLayoutState();
}

class _RegrasLayoutState extends State<RegrasLayout> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Orientação"), backgroundColor: Colors.lightBlue,),
        body: Container(
          width: MediaQuery.of(context).size.width,
          height: MediaQuery.of(context).size.height,
          child: LayoutBuilder(
              builder: (context, constraint){
                var largura = constraint.maxWidth;
                print("Largura: $largura");
                if(largura < 600){//celular
                  return Text("celular");
                }else if(largura < 960){ //celulares maiores e tablets
                  return Text("celulares & tablets");
                }else{
                  return Text("Telas maiores");
                }
/*                return Container(
                  child: Text("data"),
                );*/
              }
          ),
        )
    );
  }
}
