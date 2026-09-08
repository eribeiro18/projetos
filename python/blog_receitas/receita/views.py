from django.shortcuts import redirect, render, get_object_or_404
from receita.forms import ReceitaForm
from django.contrib import messages
from .models import Receita

# Create your views here.
def receitas(request):
    receitas = Receita.objects.all()
    context = {'receitas': receitas}
    return render(request, 'minhas_receitas.html', context)

def detalhes_receita(request, id_receita):
    receita = Receita.objects.get(id=id_receita)
    context = {'receita': receita}
    return render(request, 'detalhes_receita.html', context)

def nova_receita(request):
    if request.method == 'POST':
        form = ReceitaForm(request.POST)
        if form.is_valid():
            form.save()
            messages.success(request, 'Receita cadastrada com sucesso!')
            return redirect('receitas')
    else:
        form = ReceitaForm()
    context = {'form': form}
    return render(request, 'nova_receita.html', context)

def editar_receita(request, id_receita):
    receita = get_object_or_404(Receita, id=id_receita)
    if request.method == 'POST':
        form = ReceitaForm(request.POST, instance=receita)
        if form.is_valid():
            form.save()
            messages.success(request, 'Receita atualizada com sucesso!')
            return redirect('receitas')
    else:
        form = ReceitaForm(instance=receita)
    context = {'form': form, 'editar': True}
    return render(request, 'nova_receita.html', context)

def deletar_receita(request, id_receita):
    receita = Receita.objects.get(id=id_receita)
    receita.delete()
    messages.success(request, 'Receita deletada com sucesso!')
    return redirect('receitas')