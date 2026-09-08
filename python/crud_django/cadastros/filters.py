import django_filters
from django.contrib.auth.models import User
from .models import Pessoa, Empresa

class UserFilter(django_filters.FilterSet):
    username = django_filters.CharFilter(lookup_expr='icontains', label='Usuário')
    first_name = django_filters.CharFilter(lookup_expr='icontains', label='Nome')
    email = django_filters.CharFilter(lookup_expr='icontains', label='E-mail')

    class Meta:
        model = User
        fields = ['username', 'first_name', 'email']


class PessoaFilter(django_filters.FilterSet):
    nome = django_filters.CharFilter(lookup_expr='icontains', label='Nome')
    cpf = django_filters.CharFilter(lookup_expr='icontains', label='CPF')

    class Meta:
        model = Pessoa
        fields = ['nome', 'cpf']


class EmpresaFilter(django_filters.FilterSet):
    razao_social = django_filters.CharFilter(lookup_expr='icontains', label='Razão Social')
    cnpj = django_filters.CharFilter(lookup_expr='icontains', label='CNPJ')

    class Meta:
        model = Empresa
        fields = ['razao_social', 'cnpj']
