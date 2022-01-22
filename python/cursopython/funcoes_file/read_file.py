# executar primeiro o script read_write_file.py para criar e escrever no arquivo usado aqui
with open('abc.txt', 'r') as file:
    print('Lendo linhas')

    for s in file:
        print(s, end='')

    file.seek(0)
    print("\npode ser feito assim\n")
    for s in file.readlines():
        print(s, end='')

    file.seek(0)
    print("\npode ser feito assim tbm\n")
    for s in file.read():
        print(s, end='')
