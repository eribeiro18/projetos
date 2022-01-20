from itertools import count

#linha 5 pode ser feito igual a linha abaixo
# contador = funcoes_count_e_zip(start=10, step=5)
#dica contador sem fim se usar funcoes_zip_longest terá um loop infinito
contador = count()

lista = ['Luiz', 'João', 'Maria', 'José']

#zip agrupa duas ou mais listas respeitando a menor e igonorando as maiores
lista = zip(contador, lista)
print(list(lista))
