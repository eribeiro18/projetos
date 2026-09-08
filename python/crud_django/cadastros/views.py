from django.urls import reverse_lazy
from django.views.generic import CreateView, UpdateView, TemplateView
from django_filters.views import FilterView
from django.contrib.auth.models import User
from .models import Pessoa, Empresa
from .forms import UserForm, PessoaForm, EmpresaForm
from .filters import UserFilter, PessoaFilter, EmpresaFilter

class IndexView(TemplateView):
    template_name = 'cadastros/index.html'

# --- User Views ---
class UserListView(FilterView):
    model = User
    template_name = 'cadastros/user_list.html'
    filterset_class = UserFilter
    paginate_by = 10
    
    def get_queryset(self):
        return User.objects.filter(is_superuser=False).order_by('username')

class UserCreateView(CreateView):
    model = User
    form_class = UserForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('user_list')
    
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Novo Usuário'
        return context

class UserUpdateView(UpdateView):
    model = User
    form_class = UserForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('user_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Editar Usuário'
        return context

# --- Pessoa Views ---
class PessoaListView(FilterView):
    model = Pessoa
    template_name = 'cadastros/pessoa_list.html'
    filterset_class = PessoaFilter
    paginate_by = 10

class PessoaCreateView(CreateView):
    model = Pessoa
    form_class = PessoaForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('pessoa_list')
    
    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Nova Pessoa'
        return context

class PessoaUpdateView(UpdateView):
    model = Pessoa
    form_class = PessoaForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('pessoa_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Editar Pessoa'
        return context

# --- Empresa Views ---
class EmpresaListView(FilterView):
    model = Empresa
    template_name = 'cadastros/empresa_list.html'
    filterset_class = EmpresaFilter
    paginate_by = 10

class EmpresaCreateView(CreateView):
    model = Empresa
    form_class = EmpresaForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('empresa_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Nova Empresa'
        return context

class EmpresaUpdateView(UpdateView):
    model = Empresa
    form_class = EmpresaForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('empresa_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Editar Empresa'
        return context

