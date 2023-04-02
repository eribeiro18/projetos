//File -> settings -> Editor -> Live Templates -> flutter, foi adicionado
//o templante onde quando houver a necessidade apenas digitar mobx e apertar
//enter já cria a estrutura igual abaixo, funcionalidade igual ao escrever stfull ou stless
//para as outras linguagem tbm pode ser feito o mesmo acima
import 'package:mobx/mobx.dart';
part 'Home_controller.g.dart';

class HomeController = HomeControllerBase with _$HomeController;

abstract class HomeControllerBase with Store {

}