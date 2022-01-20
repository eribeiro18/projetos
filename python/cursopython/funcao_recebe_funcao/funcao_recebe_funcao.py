def funcao1(args):
    return args()


def funcao2():
    return 'Olá mundo!!'


print(funcao1(funcao2))
