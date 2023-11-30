from django.urls import path
from recipes.views import contato, sobre, home

urlpatterns = [
    path('sobre/', sobre),
    path('', home),
    path('contato/', contato)
]