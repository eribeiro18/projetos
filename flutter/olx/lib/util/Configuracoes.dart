import 'package:brasil_fields/brasil_fields.dart';
import 'package:flutter/material.dart';

class Configuracoes{

  static List<DropdownMenuItem<String>> getCateghorias(){
    List<DropdownMenuItem<String>> listaItensDropCategorias = [];
    listaItensDropCategorias.add(
        DropdownMenuItem(child: Text(
            "Categoria",
            style: TextStyle(
              color: Color(0xff9c27b0)
            ),
        ), value: null)
    );
    listaItensDropCategorias.add(
        DropdownMenuItem(child: Text("Automóvel"), value: "auto")
    );
    listaItensDropCategorias.add(
        DropdownMenuItem(child: Text("Imóvel"), value: "imovel")
    );
    listaItensDropCategorias.add(
        DropdownMenuItem(child: Text("Eletrônico"), value: "eletro")
    );
    listaItensDropCategorias.add(
        DropdownMenuItem(child: Text("Moda"), value: "moda")
    );
    listaItensDropCategorias.add(
        DropdownMenuItem(child: Text("Esporte"), value: "esporte")
    );
    return listaItensDropCategorias;
  }

  static List<DropdownMenuItem<String>> getEstados(){
    List<DropdownMenuItem<String>> listaItensDropEstados = [];
    listaItensDropEstados.add(
        DropdownMenuItem(child: Text(
          "Categoria",
          style: TextStyle(
              color: Color(0xff9c27b0)
          ),
        ), value: null)
    );
    for(var estado in Estados.listaEstadosSigla){
      listaItensDropEstados.add(
          DropdownMenuItem(child: Text(estado), value: estado)
      );
    }
    return listaItensDropEstados;
  }
}