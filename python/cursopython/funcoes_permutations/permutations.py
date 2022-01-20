from itertools import permutations

#neste caso cria as combinações possiveis em uma lista exemplo combinações
#de 2 igual passado na função permutations, neste caso ele não elimina as
#repetições exemplo Luiz e João e João e Luiz neste caso ele entende como
#combinações diferentes e exibe elas tambem
pessoas = ['Luiz', 'João', 'Maria', 'José']

for grupo in permutations(pessoas, 2):
    print(grupo)
