import 'package:flutter/material.dart';

class DiferentesTamanhos extends StatefulWidget {
  const DiferentesTamanhos({super.key});

  @override
  State<DiferentesTamanhos> createState() => _DiferentesTamanhosState();
}

class _DiferentesTamanhosState extends State<DiferentesTamanhos> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Diferentes Tamanhos"),),
      body: IntrinsicHeight( //este widget serve para deixar todos os sub do mesmo tamanho, outra similar é o IntrinsicWidth
        child: Row(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Container(
              width: 200,
              color: Colors.orange,
              child: Text("Item 1"),
            ),
            Container(
              width: 200,
              color: Colors.green,
              child: Text("Item 2 Lorem ipsum sagittis sodales aliquam taciti interdum, donec porttitor sodales id aliquam magna fames, nibh vestibulum tellus molestie nibh. varius potenti purus tempus consectetur in mollis leo pulvinar morbi scelerisque, fusce elementum posuere congue orci blandit tempor suscipit imperdiet, sit blandit habitant dapibus sodales pellentesque etiam duis arcu. pharetra sodales fermentum amet mollis phasellus sociosqu lectus, ornare cubilia luctus curae semper curae bibendum senectus, vel arcu tincidunt ut duis imperdiet. ornare rhoncus varius sem est at eleifend donec scelerisque cras, non ut nisl nostra mollis sapien congue aenean interdum, scelerisque vehicula egestas fames nostra justo volutpat vivamus."),
            ),
          ],
        ),)
    );
  }
}
