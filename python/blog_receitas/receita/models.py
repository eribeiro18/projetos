from django.db import models

# Create your models here.
class Categoria(models.Model):
    nome = models.CharField(max_length=25)

    def __str__(self):
        return self.nome

class Receita(models.Model):
    titulo = models.CharField(max_length=100)
    ingredientes = models.TextField()
    modo_preparo = models.TextField()
    data_criacao = models.DateTimeField(auto_now_add=True)
    categoria = models.ForeignKey(Categoria, on_delete=models.CASCADE)

    def __str__(self):
        return self.titulo