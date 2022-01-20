from listas import produtos
from functools import reduce


# acumula todos os valores de determinado campo, sendo
# ac acumulador, p item iterado da lista, p com chave campo somando com ac
# produtos lista que será iterada, e 0 zero valor inicial do ac
soma_valores = reduce(lambda ac, p: p['valor'] + ac, produtos, 0)
print(f'Soma todos os totais dos itens {soma_valores}')
