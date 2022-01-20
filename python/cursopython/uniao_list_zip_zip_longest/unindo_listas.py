list1 = [1, 2, 3, 4, 5, 6, 7]
list2 = [1, 2, 3, 4]

list_soma = [2, 4, 6, 8]

uniao = zip(list1, list2, list_soma)
for value1, value2, soma in uniao:
    print(f'{value1} + {value2} = {soma}')
