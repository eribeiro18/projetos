import 'package:flutter/material.dart';
import 'package:flutter_mobx/flutter_mobx.dart';
import 'package:mobx_values_computed/ControllerAutomatico.dart';

class Home2 extends StatefulWidget {
  const Home2({Key? key}) : super(key: key);

  @override
  State<Home2> createState() => _Home2State();
}

class _Home2State extends State<Home2> {

  ControllerFull controller = ControllerFull();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
        padding: EdgeInsets.all(16),
        child: Column(
          children: [
/*            Padding(
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
            ),*/
            Padding(
                padding: EdgeInsets.all(16),
                child: TextField(
                  decoration: InputDecoration(labelText: "Email"),
                  onChanged: controller.setEmail
                ),
            ),
            Padding(
              padding: EdgeInsets.all(16),
              child: TextField(
                decoration: InputDecoration(labelText: "Senha"),
                onChanged: controller.setSenha
              ),
            ),
            Padding(
              padding: EdgeInsets.all(16),
              child: Observer(builder: (_){
                return Text(
                    controller.formularioValidado ?
                    "Validado" :
                    "* Campos não validados"
                );
              },)
            ),
            Padding(
                padding: EdgeInsets.all(16),
                child: Observer(builder: (_){
                  return ElevatedButton(
                      onPressed: controller.formularioValidado ?
                          (){} : null,
                      child: Text(
                        "Logar",
                        style: TextStyle(color: Colors.black, fontSize: 30),
                      )
                  );
                }),
            )
          ],
        ),
      ),
    );
  }
}
