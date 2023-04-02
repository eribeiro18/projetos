import 'package:flutter/material.dart';

class InputCustomizado extends StatelessWidget {

  final String hint;
  final bool obscure;
  final Icon icon;

  const InputCustomizado({Key? key, required this.hint, this.obscure = false, this.icon = const Icon(Icons.person)}) : super(key: key);

  @override
  Widget build(BuildContext context) {



    return Container(
      padding: EdgeInsets.all(8),
      child: TextField(
        obscureText: this.obscure,
        decoration: InputDecoration(
            border: InputBorder.none,
            icon: this.icon,
            hintText: this.hint,
            hintStyle: TextStyle(
                color: Colors.grey[600]!,
                fontSize: 18
            )
        ),
      ),
    );
  }
}
