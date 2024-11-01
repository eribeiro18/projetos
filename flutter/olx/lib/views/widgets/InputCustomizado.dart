import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class InputCustomizado extends StatelessWidget {

  final TextEditingController? controller;
  final String hint;
  final bool obscure;
  final bool autofocus;
  final TextInputType type;
  final int? maxLines;
  final FormFieldValidator<String>? validator;
  final FormFieldValidator<String>? onSaved;
  final List<TextInputFormatter>? inputFormatters;

  const InputCustomizado({Key? key,
    this.controller,
    required this.hint,
    this.obscure = false,
    this.autofocus = false,
    this.type = TextInputType.text,
    this.inputFormatters,
    this.maxLines = 1,
    this.validator,
    this.onSaved
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      controller: this.controller,
      obscureText: this.obscure,
      autofocus: this.autofocus,
      keyboardType: this.type,
      inputFormatters: this.inputFormatters,
      maxLength: this.maxLines,
      validator: this.validator,
      style: TextStyle(fontSize: 20),
      onSaved: this.onSaved,
      decoration: InputDecoration(
          contentPadding: EdgeInsets.fromLTRB(32, 16, 32, 16),
          hintText: this.hint,
          filled: true,
          fillColor: Colors.white,
          border: OutlineInputBorder(
              borderRadius: BorderRadius.circular(6)
          )
      ),
    );
  }
}
