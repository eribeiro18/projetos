import 'package:flutter/material.dart';

void main(){
  runApp(MaterialApp(
    // abaixo remove o debug
    debugShowCheckedModeBanner: false,
    //deste jeito não funciona como esperado
    /*title: "Frases do dia",*/
    home: Container(
      //margin e padding pode usar o only(top: 50, left: 50),
      // ou seja aplica o espaçamento somente onde desejar
      //padding coloca um espaçamento interno no conteiner.
      // Caso seja igual todos os espaçamentos o fromLTRB pode ser substituido para all(30)
      padding: EdgeInsets.fromLTRB(30, 30, 30, 30),
      //margin coloca um espaçamento externo no conteiner.
      //similar ao padding ou seja pode ser usado o fromLTRB no lugar do all
      margin: EdgeInsets.all(30),
      //no conteiner pode colocar a cor conforme abaixo
      //color: Colors.white,
      //uma forma de substituir apenas o color acima pode ser incluido igual abaixo
      //neste contexto inclui uma borda conforme contem no componente abaixo
      decoration: BoxDecoration(
        border: Border.all(width: 3, color: Colors.white)
      ),
      child: Row(
        //aplicar o alinhamento no eixo do componente abaixo centraliza
        //já o MainAxisAlignment.spaceEvenly deixa os filhos organizado uniformes
        // até ocupar o espaço completo da tela o mesmo ocorre quando rotacionar o cel
        //o mesmo ocorre quando usa o componente column
        mainAxisAlignment: MainAxisAlignment.center,
        //a tag abaixo é similar a de cima, ou seja quando uma trabalha com o eixo na vertical
        // a outra trabalha com o eixo na horizontal,
        // caso o componente seja row é do jeito mecionado anterior, caso column as tags são invertidas
        // a que fica na vertical trabalha na horizontal e a que fica na horizontal trabalha na vertical
        //teste são interessantes para entendimento
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          //exemplo de FlatButton ou novo/similar a TextButton
          FlatButton(
              onPressed: (){
                print("Botão pressionado!");
              },
              child: Text(
                  "Botão",
                  style: TextStyle(
                    fontSize: 20,
                    color: Colors.black,
                    decoration: TextDecoration.none
                  ),
              )
          ),
          ],
      ),
    )
  ));
}