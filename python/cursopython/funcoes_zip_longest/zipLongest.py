from itertools import zip_longest

nomes = ['Luiz', 'João', 'Maria', 'José']

sobrenomes = ['Ribeiro', 'Silva', 'Oliveira', 'Rocha', 'Carvalho']

#zip_longest agrupa duas ou mais listas
#respeitando sempre a maior lista completando com None
#fillvalue utiliza nos itens a mais nas demais listas
lista = zip_longest(nomes, sobrenomes, fillvalue='Joana')
print(list(lista))
