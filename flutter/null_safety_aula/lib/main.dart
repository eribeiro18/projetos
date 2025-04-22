import 'package:flutter/material.dart';

String? titulo;

void main() {
/*  runApp(MaterialApp(
    title: "Null Safety",
    home: Home(),
  ));*/

  //Variavel não pode ser nula
  String nome = "Evandro Ribeiro";
  int idade = 30;
  double altura = 1.80;

  //Se precisar de variavel nulas, adicione uma interrogação
  String? complemento;
  int? numero;
  double? preco;

  //operador (!) -> null assertion operator, serve para informar ao compilador que é garantido que a variavel num
  //conterá um valor diferente de null, caso o valor chegue nulo ocorrerá erro
  int? num = 10;
  int resultado = num!;

  // Se configurar uma variavel que pode receber nulo, os atributos daquela variavel ficará inacessível, exemplo
  // abaixo definir desta forma String? nomeclatura; o length dará erro já que é nulo poderá gerar erros;
  String? nomeclatura = "Evandro";
  int size = nomeclatura.length;

  //neste caso a variavel titulo aceita nulo inclusive ela esta nula já o ?? "" serve pra definir um valor default caso a mesma esteja nula
  String tituloPrincipal = titulo ?? "";

  List<String> lista1 = ['Morango', 'Melancia', 'Abacaxi']; // totalmente obrigatoria
  List<String>? lista2; //pode ser inteira nula, mas a composição não pode
  List<String?> lista3 = ['Morango', null, 'Abacaxi']; //não pode ser nula mas a composição pode receber valores nulos
  //as mesmas regras de ?? "" e (!) tambem valem para as listas e todos os tipos de variaveis

  print("Lista1 $lista1");
  print("Lista1 $lista2");
  print("Lista1 $lista3");


  //Operador (?.) -> null aware operator, função dele é se a varivel for nulo após o operador não é executado conforme exemplo abaixo
  //Um outro exemplo pratico é o uso de Random
  List<String>? lista4;
  print("Total de itens ${lista4?.length}");

  //outro ponto é que da mesma forma que é feito para listas map tem as mesmas regras tanto para o map em si, quanto para a chave e o valor

}

class Funcionario{

  //late tardio, tarde, vc não quer atibuir um valor a ele e sera atribuido depois, mais tarde
  late double salario;

  calcular(){
    salario = 100; //calculo
  }
}
