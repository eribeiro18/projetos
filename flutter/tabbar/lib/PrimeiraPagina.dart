import 'package:flutter/material.dart';

class PrimeiraPagina extends StatefulWidget {
  const PrimeiraPagina({Key? key}) : super(key: key);

  @override
  State<PrimeiraPagina> createState() => _PrimeiraPaginaState();
}

class _PrimeiraPaginaState extends State<PrimeiraPagina> {
  @override
  Widget build(BuildContext context) {
    return Center(
      child: Text("Primera Pagina"),
    );
  }
}
