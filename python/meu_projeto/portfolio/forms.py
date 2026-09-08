from django import forms

class ContatoForm(forms.Form):
    nome = forms.CharField(max_length=100, widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Como prefere ser chamado?'}))
    email = forms.EmailField(widget=forms.EmailInput(attrs={'class': 'form-control', 'placeholder': 'seu@email.com'}))
    assunto = forms.CharField(max_length=100, widget=forms.TextInput(attrs={'class': 'form-control', 'placeholder': 'Qual é o assunto?'}))
    mensagem = forms.CharField(widget=forms.Textarea(attrs={'class': 'form-control', 'placeholder': 'Escreva sua mensagem aqui...', 'rows': 5}))