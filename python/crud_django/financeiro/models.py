from django.db import models
from django.core.exceptions import ValidationError
from cadastros.models import Pessoa, Empresa

STATUS_PAGAR_CHOICES = (
    ('P', 'Pendente'),
    ('G', 'Pago'),
    ('C', 'Cancelado'),
)

STATUS_RECEBER_CHOICES = (
    ('P', 'Pendente'),
    ('R', 'Recebido'),
    ('C', 'Cancelado'),
)

class ContaAPagar(models.Model):
    descricao = models.CharField('Descrição', max_length=200)
    valor = models.DecimalField('Valor', max_digits=10, decimal_places=2)
    data_vencimento = models.DateField('Data de Vencimento')
    data_pagamento = models.DateField('Data de Pagamento', null=True, blank=True)
    status = models.CharField('Status', max_length=1, choices=STATUS_PAGAR_CHOICES, default='P')
    fornecedor_pessoa = models.ForeignKey(Pessoa, on_delete=models.SET_NULL, null=True, blank=True, verbose_name='Fornecedor (Pessoa Física)')
    fornecedor_empresa = models.ForeignKey(Empresa, on_delete=models.SET_NULL, null=True, blank=True, verbose_name='Fornecedor (Pessoa Jurídica)')
    data_criacao = models.DateTimeField(auto_now_add=True)
    data_atualizacao = models.DateTimeField(auto_now=True)

    class Meta:
        verbose_name = 'Conta a Pagar'
        verbose_name_plural = 'Contas a Pagar'
        ordering = ['data_vencimento', 'status']

    def __str__(self):
        return f"{self.descricao} - R$ {self.valor}"

    def clean(self):
        if self.fornecedor_pessoa and self.fornecedor_empresa:
            raise ValidationError("Uma conta não pode ter Fornecedor Pessoa e Empresa ao mesmo tempo.")
        if self.data_pagamento and self.data_pagamento < self.data_vencimento:
            raise ValidationError("A data de pagamento não pode ser anterior à data de vencimento.")


class ContaAReceber(models.Model):
    descricao = models.CharField('Descrição', max_length=200)
    valor = models.DecimalField('Valor', max_digits=10, decimal_places=2)
    data_vencimento = models.DateField('Data de Vencimento')
    data_recebimento = models.DateField('Data de Recebimento', null=True, blank=True)
    status = models.CharField('Status', max_length=1, choices=STATUS_RECEBER_CHOICES, default='P')
    cliente_pessoa = models.ForeignKey(Pessoa, on_delete=models.SET_NULL, null=True, blank=True, verbose_name='Cliente (Pessoa Física)')
    cliente_empresa = models.ForeignKey(Empresa, on_delete=models.SET_NULL, null=True, blank=True, verbose_name='Cliente (Pessoa Jurídica)')
    data_criacao = models.DateTimeField(auto_now_add=True)
    data_atualizacao = models.DateTimeField(auto_now=True)

    class Meta:
        verbose_name = 'Conta a Receber'
        verbose_name_plural = 'Contas a Receber'
        ordering = ['data_vencimento', 'status']

    def __str__(self):
        return f"{self.descricao} - R$ {self.valor}"

    def clean(self):
        if self.cliente_pessoa and self.cliente_empresa:
            raise ValidationError("Uma conta não pode ter Cliente Pessoa e Empresa ao mesmo tempo.")
        if self.data_recebimento and self.data_recebimento < self.data_vencimento:
            raise ValidationError("A data de recebimento não pode ser anterior à data de vencimento.")

