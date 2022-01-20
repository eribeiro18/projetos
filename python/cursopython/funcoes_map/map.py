from listas import produtos


def calcula_valores(p):
    p['novo_valor'] = round(p['valor'] * 1.10, 2)
    return p


# map ele passa por cada item da lista fazendo alguma ação
# funcionamento similar é o list comprehension
nova_lista = map(calcula_valores, produtos)
for p in nova_lista:
    print(p)

print('\n\n')

# nesta situação usando lambda função anonima consegue pega
# uma coluna especifica e fazer alguam tipo de mudança
nova_lista2 = map(lambda x: x['nome'], produtos)
for p in nova_lista2:
    print(p)
