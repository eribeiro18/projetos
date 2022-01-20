from itertools import groupby

alunos = [
    {'nome': 'Mané1', 'nota': 'A'},
    {'nome': 'Mané2', 'nota': 'B'},
    {'nome': 'Mané3', 'nota': 'E'},
    {'nome': 'Mané4', 'nota': 'C'},
    {'nome': 'Mané5', 'nota': 'D'},
    {'nome': 'Mané6', 'nota': 'A'},
    {'nome': 'Mané7', 'nota': 'A'},
    {'nome': 'Mané8', 'nota': 'B'},
    {'nome': 'Mané9', 'nota': 'E'},
    {'nome': 'Mané10', 'nota': 'B'},
    {'nome': 'Mané11', 'nota': 'C'},
]
#Para agrupar primeiro tem que estar ordernado com base em uma coluna
alunos.sort(key=lambda item: item['nota'])
alunos_agrupados = groupby(alunos, lambda item: item['nota'])
#"a" valor agrupado com base na coluna da lista
#"v" lista que estão dentro do agrupamento a
for a, v in alunos_agrupados:
    list_value = list(v)
    print(f'Nota -> {a}, quantidade de alunos {len(list_value)}')
    for values in list_value:
        print(f'\t{values}')

