import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:mobx_aula/Controller.dart';
import 'package:mobx_aula/ControllerAutomatico.dart';

class HomeAutomatico extends StatefulWidget {
  const HomeAutomatico({Key? key}) : super(key: key);

  @override
  State<HomeAutomatico> createState() => _HomeAutomaticoState();
}

class _HomeAutomaticoState extends State<HomeAutomatico> {

  ControllerFull controller = ControllerFull();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
            Padding(
                padding: EdgeInsets.all(16),
                child: Observer(
                    builder: (_){
                      return Text(
                        "${controller.contador}",
                        style: TextStyle(
                            color: Colors.black,
                            fontSize: 80
                        ),
                      );
                    }
                ),
            ),
            Padding(
                padding: EdgeInsets.all(16),
                child: ElevatedButton(
                    onPressed: (){
                      controller.incremetar();
                    },
                    child: Text(
                      "Incrementar",
                      style: TextStyle(color: Colors.black, fontSize: 40),
                    )
                ),
            )
          ],
        ),
      ),
    );
  }
}
