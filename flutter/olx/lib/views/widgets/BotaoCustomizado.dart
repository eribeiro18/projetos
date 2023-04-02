import 'package:flutter/material.dart';

class BotaoCustomizado extends StatelessWidget {

  const BotaoCustomizado({Key? key,
    required this.texto,
    required this.corTexto,
    required this.onPressed,
  }) : super(key: key);

  final String texto;
  final Color corTexto;
  final VoidCallback onPressed;

  @override
  Widget build(BuildContext context) {
    return ElevatedButton(
      onPressed: this.onPressed,
      child: Text(this.texto,
        style: TextStyle(
            color: this.corTexto, fontSize: 20
        ),
      ),
      style: ElevatedButton.styleFrom(
        shape: RoundedRectangleBorder(
          borderRadius: BorderRadius.circular(6)
        ),
        primary: Color(0xff9c27b0),
        padding: EdgeInsets.fromLTRB(32, 16, 32, 16),
      ),
    );
  }
}
