from random import randint
cpf = ''
for r in range(0, 9):
    rand = int(randint(0, 9))
    cpf += str(rand)
print(cpf)
if len(cpf) == 9:
    novoCpf = cpf
    digito = 0
    for p, r in enumerate(range(10, 1, -1)):
        calc = int(novoCpf[p]) * r
        digito += calc
    digito = (11 - (digito % 11))
    if digito > 9:
        digito = 0
    novoCpf += str(digito)
    digito = 0
    for p, r in enumerate(range(11, 1, -1)):
        calc = int(novoCpf[p]) * r
        digito += calc
    digito = (11 - (digito % 11))
    if digito > 9:
        digito = 0
    novoCpf += str(digito)
    print(f'O CPF gerado é {novoCpf}!')
else:
    print('Informe um cpf com 11 digitos sem ponto ou hífen.')
