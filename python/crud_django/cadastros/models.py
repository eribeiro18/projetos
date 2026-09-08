from django.db import models

class Pessoa(models.Model):
    nome = models.CharField('Nome', max_length=100)
    cpf = models.CharField('CPF', max_length=14, unique=True)
    email = models.EmailField('E-mail', blank=True, null=True)
    data_criacao = models.DateTimeField(auto_now_add=True)
    data_atualizacao = models.DateTimeField(auto_now=True)

    class Meta:
        verbose_name = 'Pessoa'
        verbose_name_plural = 'Pessoas'
        ordering = ['nome']

    def __str__(self):
        return self.nome


class Empresa(models.Model):
    razao_social = models.CharField('Razão Social', max_length=150)
    cnpj = models.CharField('CNPJ', max_length=18, unique=True)
    email = models.EmailField('E-mail', blank=True, null=True)
    data_criacao = models.DateTimeField(auto_now_add=True)
    data_atualizacao = models.DateTimeField(auto_now=True)

    class Meta:
        verbose_name = 'Empresa'
        verbose_name_plural = 'Empresas'
        ordering = ['razao_social']

    def __str__(self):
        return self.razao_social
