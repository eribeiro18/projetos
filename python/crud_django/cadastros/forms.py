from django import forms
from django.contrib.auth.models import User
from .models import Pessoa, Empresa
from django.core.exceptions import ValidationError

class UserForm(forms.ModelForm):
    class Meta:
        model = User
        fields = ['username', 'first_name', 'last_name', 'email']
        
    def __init__(self, *args, **kwargs):
        super().__init__(*args, **kwargs)
        # Make first_name and email required
        self.fields['first_name'].required = True
        self.fields['email'].required = True

class PessoaForm(forms.ModelForm):
    class Meta:
        model = Pessoa
        fields = ['nome', 'cpf', 'email']

    def clean_nome(self):
        nome = self.cleaned_data.get('nome')
        # Validate that the name contains only letters and spaces
        if not nome.replace(' ', '').isalpha():
            raise ValidationError("O nome deve conter apenas letras.")
        return nome

    def clean_cpf(self):
        cpf = self.cleaned_data.get('cpf')
        # Simple length validation (11 digits without mask, 14 with mask)
        # For simplicity, we just enforce max_length 14 and min_length 11
        if cpf:
            cpf_limpo = ''.join(filter(str.isdigit, cpf))
            if len(cpf_limpo) != 11:
                raise ValidationError("O CPF deve conter exatamente 11 dígitos numéricos.")
        return cpf


class EmpresaForm(forms.ModelForm):
    class Meta:
        model = Empresa
        fields = ['razao_social', 'cnpj', 'email']

    def clean_cnpj(self):
        cnpj = self.cleaned_data.get('cnpj')
        if cnpj:
            cnpj_limpo = ''.join(filter(str.isdigit, cnpj))
            if len(cnpj_limpo) != 14:
                raise ValidationError("O CNPJ deve conter exatamente 14 dígitos numéricos.")
        return cnpj
