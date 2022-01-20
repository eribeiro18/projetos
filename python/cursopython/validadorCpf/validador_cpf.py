cpf = input('Informe um cpf para validar(sem pontos e hífen) ')
if len(cpf) == 11:
    novoCpf = cpf[:9]
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
    novoCpf += str(digito)
    if novoCpf == cpf:
        print(f'O CPF {cpf} é um cpf válido!')
    else:
        print(f'O CPF {cpf} NÃO é um cpf válido!')
else:
    print('Informe um cpf com 11 digitos sem ponto ou hífen.')
