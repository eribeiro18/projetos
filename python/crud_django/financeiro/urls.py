from django.urls import path
from . import views

urlpatterns = [
    # Contas a Pagar
    path('pagar/', views.ContaAPagarListView.as_view(), name='conta_pagar_list'),
    path('pagar/nova/', views.ContaAPagarCreateView.as_view(), name='conta_pagar_create'),
    path('pagar/<int:pk>/editar/', views.ContaAPagarUpdateView.as_view(), name='conta_pagar_update'),
    path('pagar/relatorio/', views.RelatorioContasPagarView.as_view(), name='conta_pagar_relatorio'),

    # Contas a Receber
    path('receber/', views.ContaAReceberListView.as_view(), name='conta_receber_list'),
    path('receber/nova/', views.ContaAReceberCreateView.as_view(), name='conta_receber_create'),
    path('receber/<int:pk>/editar/', views.ContaAReceberUpdateView.as_view(), name='conta_receber_update'),
    path('receber/relatorio/', views.RelatorioContasReceberView.as_view(), name='conta_receber_relatorio'),
]
