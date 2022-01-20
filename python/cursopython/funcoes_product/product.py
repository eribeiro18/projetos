from itertools import product

#neste caso cria as combinações possiveis em uma lista exemplo combinações
#de 2 igual passado na função product, neste caso ele não elimina as
#repetições que no caso é Luiz e Luiz ou seja ele pega todas as
# combinações possiveis e exibe
pessoas = ['Luiz', 'João', 'Maria', 'José']

for grupo in product(pessoas, 2):
    print(grupo)
