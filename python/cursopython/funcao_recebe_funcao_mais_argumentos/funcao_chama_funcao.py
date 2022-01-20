def funcao1(funcoes, saudacao=None, nome=None, pergunta=None):
    if funcoes == funcao_fala_oi:
        return funcoes(saudacao, nome)
    if funcoes == funcao_fala_oi_pergunta:
        return funcoes(saudacao, nome, pergunta)


def funcao_fala_oi(saudacao, nome):
    return f'{saudacao}, {nome}'


def funcao_fala_oi_pergunta(saudacao, nome, pergunta):
    return f'{saudacao}, {nome}, {pergunta}'


print('\nForma 1 de execução')
print(funcao1(funcao_fala_oi, 'oi', 'Evandro'))
print(funcao1(funcao_fala_oi_pergunta, 'Bom dia', 'Evandro', 'Como Vai?'))


# pode ser feito assim tbm
def mestre(funcao, *args, **kwargs):
    return funcao(*args, **kwargs)


def fala_oi(nome):
    return f'oi {nome}'


def saudacao(nome, saudacao):
    return f'{saudacao} {nome}'


print('\nForma 2 de execução')
exec1 = mestre(fala_oi, 'Evandro')
print(exec1)
exec2 = mestre(saudacao, 'Evandro', saudacao='Boa tarde')
print(exec2)
