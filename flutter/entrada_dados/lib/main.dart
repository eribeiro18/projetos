import 'package:entrada_dados/CampoTexto.dart';
import 'package:entrada_dados/EntradaCheckBox.dart';
import 'package:entrada_dados/EntradaRadionButon.dart';
import 'package:entrada_dados/EntradaSlider.dart';
import 'package:entrada_dados/EntradaSwitch.dart';
import 'package:flutter/material.dart';

void main1() {
  runApp(MaterialApp(
    home: CampoTexto(),
  ));
}

void main2(){
  runApp(
      MaterialApp(
        home: EntradaCheckBox(),
      )
  );
}

void main3(){
  runApp(
      MaterialApp(
        home: EntradaRaionButton(),
      )
  );
}

void main4(){
  runApp(
      MaterialApp(
        home: EntradaSwitch(),
      )
  );
}

void main(){
  runApp(
      MaterialApp(
        home: EntradaSlider(),
      )
  );
}