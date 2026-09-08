import django_filters
from django import forms
from .models import ContaAPagar, ContaAReceber
from cadastros.models import Empresa

class ContaAPagarFilter(django_filters.FilterSet):
    data_vencimento_inicio = django_filters.DateFilter(field_name='data_vencimento', lookup_expr='gte', label='Vencimento a partir de', widget=forms.DateInput(attrs={'type': 'date'}))
    data_vencimento_fim = django_filters.DateFilter(field_name='data_vencimento', lookup_expr='lte', label='Vencimento até', widget=forms.DateInput(attrs={'type': 'date'}))
    fornecedor_empresa = django_filters.ModelChoiceFilter(queryset=Empresa.objects.all(), label='Empresa (Fornecedor)')

    class Meta:
        model = ContaAPagar
        fields = ['status', 'fornecedor_empresa']

class ContaAReceberFilter(django_filters.FilterSet):
    data_vencimento_inicio = django_filters.DateFilter(field_name='data_vencimento', lookup_expr='gte', label='Vencimento a partir de', widget=forms.DateInput(attrs={'type': 'date'}))
    data_vencimento_fim = django_filters.DateFilter(field_name='data_vencimento', lookup_expr='lte', label='Vencimento até', widget=forms.DateInput(attrs={'type': 'date'}))
    cliente_empresa = django_filters.ModelChoiceFilter(queryset=Empresa.objects.all(), label='Empresa (Cliente)')

    class Meta:
        model = ContaAReceber
        fields = ['status', 'cliente_empresa']
