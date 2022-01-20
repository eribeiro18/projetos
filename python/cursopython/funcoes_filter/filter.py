from listas import produtos


def filtra(p):
    return p['valor'] > 10


# nesta situação usando lambda função anonima consegue pega
# uma coluna especifica e fazer filtro e só capturar os item que deseja
nova_lista = filter(lambda x: x['valor'] > 10, produtos)
for p in nova_lista:
    print(p)

print('\n\n')

# nesta situação usando uma função propria para filtragem dos dados
# neste caso fica mais limpo e atende igual o código de cima
nova_lista2 = filter(filtra, produtos)
for p in nova_lista2:
    print(p)
