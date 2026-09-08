package com.example.ia.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ChatController {
	
    private static final List<String> mensagens = new ArrayList<>();
    
    static {
        mensagens.add("Evandro: Olá, tudo bem?");
        mensagens.add("ChatGPT: Oi Evandro! Estou bem, e você?");
        mensagens.add("Evandro: Estou criando um chat com Thymeleaf 😃");
    }
	
    @GetMapping("/chat")
    public String chat(Model model) {
        model.addAttribute("mensagens", mensagens);
        return "chat";
    }


    @PostMapping("/chat")
    public String enviarMensagem(@RequestParam String mensagem) {
        mensagens.add("Você: " + mensagem);
        return "redirect:/chat";
    }
    
}
