// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'ControllerAutomatico.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$ControllerFull on ControllerAutomatico, Store {
  late final _$_contadorAtom =
      Atom(name: 'ControllerAutomatico._contador', context: context);

  @override
  int get _contador {
    _$_contadorAtom.reportRead();
    return super._contador;
  }

  @override
  set _contador(int value) {
    _$_contadorAtom.reportWrite(value, super._contador, () {
      super._contador = value;
    });
  }

  late final _$ControllerAutomaticoActionController =
      ActionController(name: 'ControllerAutomatico', context: context);

  @override
  dynamic incremetar() {
    final _$actionInfo = _$ControllerAutomaticoActionController.startAction(
        name: 'ControllerAutomatico.incremetar');
    try {
      return super.incremetar();
    } finally {
      _$ControllerAutomaticoActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''

    ''';
  }
}
