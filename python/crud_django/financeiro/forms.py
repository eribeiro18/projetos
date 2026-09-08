from django import forms
from .models import ContaAPagar, ContaAReceber

class ContaAPagarForm(forms.ModelForm):
    class Meta:
        model = ContaAPagar
        fields = ['descricao', 'valor', 'data_vencimento', 'data_pagamento', 'status', 'fornecedor_pessoa', 'fornecedor_empresa']
        widgets = {
            'data_vencimento': forms.DateInput(format='%Y-%m-%d', attrs={'type': 'date'}),
            'data_pagamento': forms.DateInput(format='%Y-%m-%d', attrs={'type': 'date'}),
        }

class ContaAReceberForm(forms.ModelForm):
    class Meta:
        model = ContaAReceber
        fields = ['descricao', 'valor', 'data_vencimento', 'data_recebimento', 'status', 'cliente_pessoa', 'cliente_empresa']
        widgets = {
            'data_vencimento': forms.DateInput(format='%Y-%m-%d', attrs={'type': 'date'}),
            'data_recebimento': forms.DateInput(format='%Y-%m-%d', attrs={'type': 'date'}),
        }
