import 'package:flutter/material.dart';
import 'package:olx/models/Anuncio.dart';

class ItemAnuncio extends StatelessWidget {
  Anuncio anuncio;
  VoidCallback? onTapItem;
  VoidCallback? onPressedRemover;
  ItemAnuncio({
      Key? key,
      required this.anuncio,
      this.onPressedRemover,
      this.onTapItem
    }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: this.onTapItem,
      child: Card(
        child: Padding(
            padding: EdgeInsets.all(12),
          child: Row(children: [
            SizedBox(
              width: 120,
              height: 120,
              child: Image.network(
                  anuncio.fotos![0],
                  fit: BoxFit.cover,
              ),
            ),
            Expanded(
                flex: 3,
                child: Padding(
                    padding: EdgeInsets.symmetric(
                        horizontal: 20,
                        vertical: 8
                    ),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                    Text(
                        anuncio.descricao!,
                        style: TextStyle(
                          fontSize: 18,
                          fontWeight: FontWeight.bold
                        ),
                    ),
                    Text("R\$ ${anuncio.preco}"),
                  ],),
                ),
            ),
            if(this.onPressedRemover != null ) Expanded(
                flex: 1,
                child: TextButton(
                    style: TextButton.styleFrom(
                        backgroundColor: Colors.red,
                        elevation: 15,
                        shadowColor: Colors.red,
                        padding: EdgeInsets.fromLTRB(32, 16, 32, 16),
                        shape: RoundedRectangleBorder(
                            borderRadius: BorderRadius.circular(32))),
                    onPressed: this.onPressedRemover,
                    child: Icon(Icons.delete, color: Colors.white,)
                ),
            )
          ],),
        ),
      ),
    );
  }
}
