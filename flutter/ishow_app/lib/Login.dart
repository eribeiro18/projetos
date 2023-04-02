import 'dart:ui';
import 'package:flutter/scheduler.dart' show timeDilation;
import 'package:flutter/material.dart';
import 'package:ishow_app/BotaoAnimado.dart';
import 'package:ishow_app/InputCustomizado.dart';

class Login extends StatefulWidget {
  const Login({Key? key}) : super(key: key);

  @override
  State<Login> createState() => _LoginState();
}

class _LoginState extends State<Login> with SingleTickerProviderStateMixin {

  AnimationController? _controller;
  Animation<double>? _animationBlur;
  Animation<double>? _animationFade;
  Animation<double>? _animationSize;

  @override
  void initState() {
    super.initState();
    _controller = AnimationController(
        vsync: this,
        duration: Duration(milliseconds: 500)
    );

    _animationBlur = Tween<double>(
      begin: 5,
      end: 0
    ).animate(CurvedAnimation(
        parent: _controller!,
        curve: Curves.ease)
    );
    _animationFade = Tween<double>(
        begin: 0,
        end: 1
    ).animate(CurvedAnimation(
        parent: _controller!,
        curve: Curves.easeInOutQuint)
    );
    _animationSize = Tween<double>(
        begin: 0,
        end: 500
    ).animate(CurvedAnimation(
        parent: _controller!,
        curve: Curves.decelerate)
    );
    _controller!.forward();
  }

  @override
  void dispose() {
    _controller!.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {

    timeDilation = 10;

    return Scaffold(
      backgroundColor: Colors.white,
      body: Container(
        child: SingleChildScrollView(
          child: Column(
            children: [
              AnimatedBuilder(
                  animation: _animationBlur!,
                  builder: (context, widget) {
                    return Container(
                      height: 400,
                      decoration: BoxDecoration(
                          image: DecorationImage(
                              image: AssetImage("imagens/fundo.png"),
                              fit: BoxFit.fill
                          )
                      ),
                      child: BackdropFilter(
                        filter: ImageFilter.blur(
                            sigmaX: _animationBlur!.value,
                            sigmaY: _animationBlur!.value
                        ),
                        child: Stack(
                          children: [
                            Positioned(
                                left: 10,
                                child: FadeTransition(
                                  opacity: _animationFade!,
                                  child: Image.asset("imagens/detalhe1.png"),
                                )
                            ),
                            Positioned(
                                left: 50,
                                child: FadeTransition(
                                  opacity: _animationFade!,
                                  child: Image.asset("imagens/detalhe2.png"),
                                )
                            ),
                          ],
                        ),
                      ),
                    );
                  }
              ),
              Padding(
                  padding: EdgeInsets.only(left: 30, right: 30),
                  child: Column(
                    children: [
                      AnimatedBuilder(
                          animation: _animationSize!,
                          builder: (context, widget){
                            return Container(
                              width: _animationSize!.value,
                              padding: EdgeInsets.all(8),
                              decoration: BoxDecoration(
                                  color: Colors.white,
                                  borderRadius: BorderRadius.circular(8),
                                  boxShadow: [
                                    BoxShadow(
                                        color: Colors.grey[200]!,
                                        blurRadius: 15,
                                        spreadRadius: 4
                                    )
                                  ]
                              ),
                              child: Column(
                                children: [
                                  InputCustomizado(hint: "E-mail", obscure: false, icon: Icon(Icons.person)),
                                  InputCustomizado(hint: "Senha", obscure: true, icon: Icon(Icons.lock))
                                ],
                              ),
                            );
                          }
                      ),
                      SizedBox(height: 20,),//gerar um espaçamento entre os componentes
                      BotaoAnimado(controller: this._controller!),
                      SizedBox(height: 10,),
                      FadeTransition(
                        opacity: _animationFade!,
                        child: Text(
                          "Esqueci minha senha!",
                          style: TextStyle(
                              color: Color.fromRGBO(255, 100, 127, 1),
                              fontWeight: FontWeight.bold
                          ),
                        )
                      )
                    ],
                  ),
              )
            ],
          ),
        ),
      ),
    );
  }
}
