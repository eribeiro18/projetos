from django.urls import path
from . import views

urlpatterns = [
    path('', views.home, name='home'),
    path('lista_projetos/', views.listar_projetos, name='lista_projetos'),
    path('projeto/<str:id>/', views.detalhes_projetos, name='projeto'),
]