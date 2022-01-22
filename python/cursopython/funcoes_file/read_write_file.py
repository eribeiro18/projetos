# PODE SER INCLUIR ENTRE TRY EXCEPTION PARA CAPTURAR EXCEÇÕES
# USANDO TRY PODE SER USADO O FINALLY E INCLUIR O file.close NELE

#  w+ ler e escrever no arquivo, neste caso ele mata o arquivo caso já exista e começa do zero
# with alto close pode ser feito desta forma file = open('abc.txt', 'w+')
with open('abc.txt', 'w+') as file:
    file.write('linha 1\n')
    file.write('linha 2\n')
    file.write('linha 3\n')

    print('Lendo linhas')

    # sempre que for ler o arquivo que acabou de escrever tem que dar um seek(0, 0) pu seek(0)
    file.seek(0)
    # le linha a linha pode ser usado dentro do for
    print(file.read())

    file.seek(0)
    # le a primeira linha
    print(file.readline())

    file.seek(0)
    # le linha a linha parecido com o file.read() só que vira um list/array
    print(file.readlines())

    # sempre fechar arquivo para não derrubar a aplicação quando usado o with não precisa fechar
    file.close()
