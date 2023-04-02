import 'package:flutter/material.dart';
import 'package:flutter_web_aula/responsividade_row_col.dart';
import 'package:flutter_web_aula/responsividade_wrap.dart';
import 'package:flutter_web_aula/resposividade_media_query.dart';

void main() {
  runApp(MaterialApp(
    title: "Flutter Web",
    debugShowCheckedModeBanner: false,
    //home: ResponsividadeMediaQuery(),
    //home: ResponsividadeRowCol(),
    home: ResponsividadeWrap(),
  ));
}
