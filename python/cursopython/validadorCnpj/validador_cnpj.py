

def regra_calc_cnpj(count, fragcnpj, countcalc=0):
    calc = 0
    pas = False
    while True:
        calc += int(fragcnpj[countcalc]) * count
        count -= 1
        countcalc += 1
        if count == 1 and not pas:
            count = 9
            pas = True
        elif count == 1 and pas:
            break
    return calc


def formula_cnpj(valor_sum_mul):
    valor = (11 - (valor_sum_mul % 11))
    if valor > 9:
        valor = 0
    return valor


def validacao(novocnpj, cnpj):
    if novocnpj == cnpj:
        print(f'O CNPJ {cnpj} é um CNPJ válido!')
    else:
        print(f'O CNPJ {cnpj} NÃO é um CNPJ válido!')


cnpj = input('Informe um cnpj para validar(sem pontos e hífen) ')
if len(cnpj) == 14:
    novoCnpj = cnpj[:12]
    multSoma = regra_calc_cnpj(5, novoCnpj)
    digito1 = formula_cnpj(multSoma)
    novoCnpj += str(digito1)
    multSoma = regra_calc_cnpj(6, novoCnpj)
    digito2 = formula_cnpj(multSoma)
    novoCnpj += str(digito2)
    validacao(novoCnpj, cnpj)
else:
    print('Informe um CNPJ com 14 digitos sem ponto ou hífen.')
