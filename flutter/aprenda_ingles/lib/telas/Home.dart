import 'package:aprenda_ingles/telas/Bichos.dart';
import 'package:aprenda_ingles/telas/Numeros.dart';
import 'package:flutter/material.dart';

class Home extends StatefulWidget {
  const Home({Key? key}) : super(key: key);

  @override
  State<Home> createState() => _HomeState();
}

class _HomeState extends State<Home> with SingleTickerProviderStateMixin {

  TabController? _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 2, vsync: this);
  }

  @override
  void dispose() {
    super.dispose();
    _tabController!.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Aprenda inglês"),
        bottom: TabBar(
          indicatorWeight: 4,
          indicatorColor: Colors.white,
          labelStyle: TextStyle(
            fontSize: 18,
            fontWeight: FontWeight.bold
          ),
          //labelColor: Colors.green,
          //unselectedLabelColor:  Colors.purple,
          controller: _tabController!,
          tabs: [
            Tab(text: "Bichos"),
            Tab(text: "Números")
          ],
        ),
      ),
      body: TabBarView(
        controller: _tabController!,
        children: [
          Bichos(),
          Numeros()
        ],
      ),
    );
  }
}