# mutaveis lista, dicionarios
# imutavel tuplas, strings, numeros, True, False
# existem mais estes acimas são os principais


def lista_de_clientes(clientes_iteraveis, lista=[]):
    lista.extend(clientes_iteraveis)
    return lista


# ambas as listas contem os mesmos conteudos
cliente1 = lista_de_clientes(['João', 'Maria', 'Eduardo'])
cliente2 = lista_de_clientes(['Marcos', 'Jonas', 'Zico'])

print(cliente1)
print(cliente2)
# para esta situação de conter os mesmos nomes nas duas listas
# que no caso é errado, isso ocorre pelo fato de não passar
# nada no parametro 2 da função, sendo assim a lista
# continua com os dados já incluido nas chamadas anteriores

print('####' * 10)
# corrigindo o problema dito no comentario acima
# passando uma lista vazia
cliente3 = lista_de_clientes(['João', 'Maria', 'Eduardo'], [])
cliente4 = lista_de_clientes(['Marcos', 'Jonas', 'Zico'], [])

print(cliente3)
print(cliente4)

print('####' * 10)

# outra forma de corrigir é aplicando a função da forma abaixo
def lista_de_clientes2(clientes_iteraveis, lista=None):
    if lista is None:
        lista = []
    lista.extend(clientes_iteraveis)
    return lista


cliente5 = lista_de_clientes2(['João', 'Maria', 'Eduardo'])
cliente6 = lista_de_clientes2(['Marcos', 'Jonas', 'Zico'])

print(cliente5)
print(cliente6)
