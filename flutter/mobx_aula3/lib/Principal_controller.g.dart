// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'Principal_controller.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$PrincipalController on PrincipalControllerBase, Store {
  late final _$novoItemAtom =
      Atom(name: 'PrincipalControllerBase.novoItem', context: context);

  @override
  String get novoItem {
    _$novoItemAtom.reportRead();
    return super.novoItem;
  }

  @override
  set novoItem(String value) {
    _$novoItemAtom.reportWrite(value, super.novoItem, () {
      super.novoItem = value;
    });
  }

  late final _$PrincipalControllerBaseActionController =
      ActionController(name: 'PrincipalControllerBase', context: context);

  @override
  void setNovoItem(String valor) {
    final _$actionInfo = _$PrincipalControllerBaseActionController.startAction(
        name: 'PrincipalControllerBase.setNovoItem');
    try {
      return super.setNovoItem(valor);
    } finally {
      _$PrincipalControllerBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  void adicionarItem() {
    final _$actionInfo = _$PrincipalControllerBaseActionController.startAction(
        name: 'PrincipalControllerBase.adicionarItem');
    try {
      return super.adicionarItem();
    } finally {
      _$PrincipalControllerBaseActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
novoItem: ${novoItem}
    ''';
  }
}
