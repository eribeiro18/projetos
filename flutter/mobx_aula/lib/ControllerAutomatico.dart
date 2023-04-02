import 'package:mobx/mobx.dart';
part 'ControllerAutomatico.g.dart';

class ControllerFull = ControllerAutomatico with _$ControllerFull;

//forma de utilização do mobx automatica o framework gerará os codigos
//a utilização do mixin Store é para geração dos códigos automaticos
abstract class ControllerAutomatico with Store{

  @observable
  int _contador = 0;

  @action
  incremetar(){
    contador++;
  }

  int get contador => _contador;

  set contador(int value) {
    _contador = value;
  }
}