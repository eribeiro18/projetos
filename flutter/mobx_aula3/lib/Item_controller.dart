import 'package:mobx/mobx.dart';
part 'Item_controller.g.dart';

class ItemController = ItemControllerBase with _$ItemController;

//a utilização do mixin Store é para geração dos códigos automáticos
abstract class ItemControllerBase with Store {

  ItemControllerBase(this.titulo);

  final String titulo;

  @observable
  bool marcado = false;

  void alterarMarcado(bool valor) => marcado = valor;

}