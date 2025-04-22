import 'package:flutter/material.dart';
import 'package:responsividade_media_query/diferentes_tamanhos.dart';
import 'package:responsividade_media_query/orientacao.dart';
import 'package:responsividade_media_query/regras_layout.dart';
import 'package:responsividade_media_query/responsividade_media_query.dart';
import 'package:responsividade_media_query/responsividade_row_col.dart';
import 'package:responsividade_media_query/responsividade_wrap.dart';
import 'package:responsividade_media_query/tamanho_proporcional.dart';
import 'package:responsividade_media_query/tamanho_texto.dart';

void main() {
  runApp(MaterialApp(
    title: "Flutter Web",
    debugShowCheckedModeBanner: false,
    //home: ResponsividadeMediaQuery(),
    //home: ResponsividadeRowCol(),
    //home: ResponsividadeWrap(),
    //home: Orientacao(),
    //home: RegrasLayout(),
    //home: TamanhoTexto(),
    //home: TamanhoProporcional(),
    home: DiferentesTamanhos(),
  ));
}

