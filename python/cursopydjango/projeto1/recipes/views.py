from django.shortcuts import render

#sempre que for usar render configurar caminho dos app dentro de settings na pasta projeto conforme INSTALLED_APPS
def home(request):
    #return HttpResponse('HOME') 
    return render(request, 'recipes/pages/home.html', context={
        'name': 'Evandro Ribeiro',
    })