import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

import 'package:youtube/model/Video.dart';

const CHAVE_YOUTUBE_API = "AIzaSyDNJUuFQYOplvEaPv3lqyVW6V6WOyg96Vk";
const ID_CANAL = "UCn68krepnrJZ46JwjLPsFMQ";
const URL_BASE = "https://www.googleapis.com/youtube/v3/";

class Api {

  Future<List<Video>> pesquisar(String pesquisa) async {
    List<Video> videos = [];
    http.Response resp = await http.get(
        Uri.parse(URL_BASE + "search"
            "?part=snippet"
            "&type=video"
            "&maxResults=20"
            "&order=date"
            "&key=$CHAVE_YOUTUBE_API"
            "&channelId=$ID_CANAL"
            "&q=$pesquisa"
        )
    );
    if(resp.statusCode == 200){
      Map<String, dynamic> dadosJson = json.decode(resp.body);
      //poderia fazer com for mas existe outras formas com função anonima
      /*for(var video in dadosJson["items"]){}*/
      videos = dadosJson["items"].map<Video>(
          (map){
            return Video.fromJson(map);
          }
      ).toList();
    }
    return videos.toList();
  }

}