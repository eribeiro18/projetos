import 'package:mobx/mobx.dart';

//forma de utilização do mobx manualmentemente, o próprio dev implementa o controlador
//e o observador
class Controller{

  var _contador = Observable(0);
  late Action incremetar;

  Controller(){
    incremetar = Action(_incremetar);
  }
  
  _incremetar(){
    contador++;
  }

  int get contador => _contador.value;

  set contador(int value) {
    _contador.value = value;
  }
}