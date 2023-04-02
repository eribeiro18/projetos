// GENERATED CODE - DO NOT MODIFY BY HAND

part of 'ControllerAutomatico.dart';

// **************************************************************************
// StoreGenerator
// **************************************************************************

// ignore_for_file: non_constant_identifier_names, unnecessary_brace_in_string_interps, unnecessary_lambdas, prefer_expression_function_bodies, lines_longer_than_80_chars, avoid_as, avoid_annotating_with_dynamic, no_leading_underscores_for_local_identifiers

mixin _$ControllerFull on ControllerAutomatico, Store {
  Computed<String>? _$emailSenhaComputed;

  @override
  String get emailSenha =>
      (_$emailSenhaComputed ??= Computed<String>(() => super.emailSenha,
              name: 'ControllerAutomatico.emailSenha'))
          .value;
  Computed<bool>? _$formularioValidadoComputed;

  @override
  bool get formularioValidado => (_$formularioValidadoComputed ??=
          Computed<bool>(() => super.formularioValidado,
              name: 'ControllerAutomatico.formularioValidado'))
      .value;

  late final _$emailAtom =
      Atom(name: 'ControllerAutomatico.email', context: context);

  @override
  String get email {
    _$emailAtom.reportRead();
    return super.email;
  }

  @override
  set email(String value) {
    _$emailAtom.reportWrite(value, super.email, () {
      super.email = value;
    });
  }

  late final _$senhaAtom =
      Atom(name: 'ControllerAutomatico.senha', context: context);

  @override
  String get senha {
    _$senhaAtom.reportRead();
    return super.senha;
  }

  @override
  set senha(String value) {
    _$senhaAtom.reportWrite(value, super.senha, () {
      super.senha = value;
    });
  }

  late final _$ControllerAutomaticoActionController =
      ActionController(name: 'ControllerAutomatico', context: context);

  @override
  void setEmail(dynamic valor) {
    final _$actionInfo = _$ControllerAutomaticoActionController.startAction(
        name: 'ControllerAutomatico.setEmail');
    try {
      return super.setEmail(valor);
    } finally {
      _$ControllerAutomaticoActionController.endAction(_$actionInfo);
    }
  }

  @override
  void setSenha(dynamic valor) {
    final _$actionInfo = _$ControllerAutomaticoActionController.startAction(
        name: 'ControllerAutomatico.setSenha');
    try {
      return super.setSenha(valor);
    } finally {
      _$ControllerAutomaticoActionController.endAction(_$actionInfo);
    }
  }

  @override
  String toString() {
    return '''
email: ${email},
senha: ${senha},
emailSenha: ${emailSenha},
formularioValidado: ${formularioValidado}
    ''';
  }
}
