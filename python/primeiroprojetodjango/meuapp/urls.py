from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('saudacao/<str:nome>/', views.saudacao, name='saudacao'),
    path('produto/<int:id_produto>/', views.produto, name='produto'),
    path('mensagem/', views.mensagem, name='mensagem'),
    path('produtos/', views.lista, name='produtos'),
    path('index/', views.index, name='index'),
]