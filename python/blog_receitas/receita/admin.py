from django.contrib import admin
from .models import Receita, Categoria

# Register your models here.

class Receitas(admin.ModelAdmin):
    list_display = ['titulo', 'categoria', 'data_criacao']
    search_fields = ['titulo']
    list_filter = ['categoria', 'data_criacao']

admin.site.register(Receita, Receitas)
admin.site.register(Categoria)
