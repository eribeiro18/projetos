from django.urls import reverse_lazy
from django.views.generic import CreateView, UpdateView
from django.db.models import Sum
from django_filters.views import FilterView
from .models import ContaAPagar, ContaAReceber
from .forms import ContaAPagarForm, ContaAReceberForm
from .filters import ContaAPagarFilter, ContaAReceberFilter

# --- Contas a Pagar ---
class ContaAPagarListView(FilterView):
    model = ContaAPagar
    template_name = 'financeiro/contas_pagar_list.html'
    filterset_class = ContaAPagarFilter
    paginate_by = 10

class ContaAPagarCreateView(CreateView):
    model = ContaAPagar
    form_class = ContaAPagarForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('conta_pagar_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Nova Conta a Pagar'
        return context

class ContaAPagarUpdateView(UpdateView):
    model = ContaAPagar
    form_class = ContaAPagarForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('conta_pagar_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Editar Conta a Pagar'
        return context

# --- Contas a Receber ---
class ContaAReceberListView(FilterView):
    model = ContaAReceber
    template_name = 'financeiro/contas_receber_list.html'
    filterset_class = ContaAReceberFilter
    paginate_by = 10

class ContaAReceberCreateView(CreateView):
    model = ContaAReceber
    form_class = ContaAReceberForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('conta_receber_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Nova Conta a Receber'
        return context

class ContaAReceberUpdateView(UpdateView):
    model = ContaAReceber
    form_class = ContaAReceberForm
    template_name = 'cadastros/form.html'
    success_url = reverse_lazy('conta_receber_list')

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        context['title'] = 'Editar Conta a Receber'
        return context


# --- Relatórios ---
class RelatorioContasPagarView(FilterView):
    model = ContaAPagar
    template_name = 'financeiro/relatorio_contas_pagar.html'
    filterset_class = ContaAPagarFilter

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        qs = self.object_list
        context['total_geral'] = qs.aggregate(total=Sum('valor'))['total'] or 0
        context['total_pendente'] = qs.filter(status='P').aggregate(total=Sum('valor'))['total'] or 0
        context['total_pago'] = qs.filter(status='G').aggregate(total=Sum('valor'))['total'] or 0
        context['total_cancelado'] = qs.filter(status='C').aggregate(total=Sum('valor'))['total'] or 0
        return context

class RelatorioContasReceberView(FilterView):
    model = ContaAReceber
    template_name = 'financeiro/relatorio_contas_receber.html'
    filterset_class = ContaAReceberFilter

    def get_context_data(self, **kwargs):
        context = super().get_context_data(**kwargs)
        qs = self.object_list
        context['total_geral'] = qs.aggregate(total=Sum('valor'))['total'] or 0
        context['total_pendente'] = qs.filter(status='P').aggregate(total=Sum('valor'))['total'] or 0
        context['total_recebido'] = qs.filter(status='R').aggregate(total=Sum('valor'))['total'] or 0
        context['total_cancelado'] = qs.filter(status='C').aggregate(total=Sum('valor'))['total'] or 0
        return context
