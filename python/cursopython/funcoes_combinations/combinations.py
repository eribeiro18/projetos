from itertools import combinations

#neste caso cria as combinações possiveis em uma lista exemplo combinações
#de 2 igual passado na função combiantions, neste caso ele elimina as
#repetições exemplo Luiz e João e João e Luiz neste caso ocorerá somente
#uma vez ou seja elimina as repetições
pessoas = ['Luiz', 'João', 'Maria', 'José']

for grupo in combinations(pessoas, 2):
    print(grupo)
