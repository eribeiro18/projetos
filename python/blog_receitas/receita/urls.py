from django.urls import path
from . import views

urlpatterns = [
    path('receitas/', views.receitas, name='receitas'),
    path('detalhes_receita/<int:id_receita>/', views.detalhes_receita, name='detalhes_receita'),
    path('nova_receita/', views.nova_receita, name='nova_receita'),
    path('editar/<int:id_receita>', views.editar_receita, name='editar_receita'),
]