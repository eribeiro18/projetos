import 'package:mobx/mobx.dart';
part 'ControllerAutomatico.g.dart';

class ControllerFull = ControllerAutomatico with _$ControllerFull;

//forma de utilização do mobx automatica o framework gerará os codigos
//a utilização do mixin Store é para geração dos códigos automaticos
abstract class ControllerAutomatico with Store{

  @observable
  String email = '';

  @observable
  String senha = '';

  ControllerAutomatico(){
    //Executa sempre que um observável tem seu estado alterado
    autorun((_){
/*      print(email);
      print(senha);*/
    });
  }

  @computed
  String get emailSenha => "$email - $senha";

  @action
  void setEmail(valor) => email = valor;

  @action
  void setSenha(valor) => senha = valor;

  @computed
  bool get formularioValidado => email.length >= 5 && senha.length >= 5;

}