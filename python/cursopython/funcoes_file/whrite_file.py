# executar primeiro o script read_write_file.py para criar e escrever no arquivo usado aqui
# faz append no arquivo existente colocando o cursor na ultima linha para seguir com a escrita de onde parou
# somente o a faz o append caso colocar a+ ele tambem le o arquivo
with open('abc.txt', 'a') as file:
    print('escrevendo linhas em um arquivo já existente neste caso ocorre um apend')
    file.write('linha 4\n')
    file.write('linha 5\n')
    file.write('linha 6\n')
    print('finalizou o apped')
