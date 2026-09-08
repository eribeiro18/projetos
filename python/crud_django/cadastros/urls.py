from django.urls import path
from . import views

urlpatterns = [
    path('', views.IndexView.as_view(), name='index'),
    
    # Usuários
    path('usuarios/', views.UserListView.as_view(), name='user_list'),
    path('usuarios/novo/', views.UserCreateView.as_view(), name='user_create'),
    path('usuarios/<int:pk>/editar/', views.UserUpdateView.as_view(), name='user_update'),

    # Pessoas
    path('pessoas/', views.PessoaListView.as_view(), name='pessoa_list'),
    path('pessoas/nova/', views.PessoaCreateView.as_view(), name='pessoa_create'),
    path('pessoas/<int:pk>/editar/', views.PessoaUpdateView.as_view(), name='pessoa_update'),

    # Empresas
    path('empresas/', views.EmpresaListView.as_view(), name='empresa_list'),
    path('empresas/nova/', views.EmpresaCreateView.as_view(), name='empresa_create'),
    path('empresas/<int:pk>/editar/', views.EmpresaUpdateView.as_view(), name='empresa_update'),
]
