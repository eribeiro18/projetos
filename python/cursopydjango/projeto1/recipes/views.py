from django.shortcuts import render
from django.http import HttpResponse

#sempre que for usar render configurar caminho dos app dentro de settings na pasta projeto conforme INSTALLED_APPS
def home(request):
    #return HttpResponse('HOME') 
    return render(request, 'recipes/home.html', context={
        'name': 'Evandro Ribeiro',
    })

def sobre(request):
    return HttpResponse('Sobre')

def contato(request):
    return HttpResponse('Contato')