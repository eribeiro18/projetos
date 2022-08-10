import 'package:flutter/material.dart';

class EntradaSlider extends StatefulWidget {
  const EntradaSlider({Key? key}) : super(key: key);

  @override
  State<EntradaSlider> createState() => _EntradaSliderState();
}

class _EntradaSliderState extends State<EntradaSlider> {

  double slider1 = 0;
  String label = "0";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Entrada Switch"),
        ),
        body: Container(
          padding: EdgeInsets.all(60),
          child: Column(
            children: [
                Slider(
                    value: slider1,
                    min: 0,
                    max: 10,
                    //para usar o label deve usar o divisions
                    //neste caso o division pode assumir o valor maximo
                    //tbm pode ser maior que o max
                    label: label,
                    divisions: 10,
                    //desmarcada aparece na cor abaixo
                    inactiveColor: Colors.purple,
                    //marcado aparece na cor abaixo
                    activeColor: Colors.red,
                    onChanged: (double? n){
                      setState((){
                        slider1 = n!;
                        label = n.toString();
                      });
                      print("Valor slider: " + n.toString());
                    }
                ),
              ElevatedButton(
                  onPressed: (){
                    print("Valor Selecionado: " + slider1.toString());
                  },
                  child: Text(
                    "Salvar",
                    style: TextStyle(
                        fontSize: 20
                    ),
                  )
              ),
            ],
          ),
        )
    );
  }
}
