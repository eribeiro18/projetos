from datetime import datetime
from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def mensagem(request):
    hora_atual = datetime.now().hour
    if 5 <= hora_atual < 12:
        saudacao = "Bom dia"
    elif 12 <= hora_atual < 18:
        saudacao = "Boa tarde"
    else:
        saudacao = "Boa noite"
    return render(request, 'home.html', {"nome": "Evandro", "mensagem": mensagem})

def home(request):
    nome = "Evandro"
    return render(request, 'home.html', {"nome": nome})

def saudacao(request, nome):
    mensagem = f"Olá {nome}! Bem-vindo ao meu app."
    return HttpResponse(mensagem)

def produto(request, id_produto):
    produtos = {
        1: "iPhone 15 Pro Max",
        2: "Samsung Galaxy S25 Ultra",
        3: "Google Pixel 8 Pro"
    }
    produto = produtos.get(id_produto, "Produto não encontrado")
    mensagem = f"Produto: {produto}"
    return HttpResponse(mensagem)

def lista(request):
    produtos = [
        {"id": 1, "nome": "Produto 1", "preco": 100},
        {"id": 2, "nome": "Produto 2", "preco": 200},
        {"id": 3, "nome": "Produto 3", "preco": 300}
    ]
    return render(request, 'lista.html', {"produtos": produtos})

def index(request):
    mensagem = 'Olá, primeira herança de template!'
    return render(request, 'index.html', {'mensagem': mensagem})