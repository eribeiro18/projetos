# args e kwargs é caso passar uma funcão que tenha parametros
# se não passar o args e kwargs não tem problemas
# decoradas é quando passa uma funcao para outra função
# def master(funcao):
#    def slave(*args, **kwargs):
#        funcao(*args, **kwargs)
#
#    return slave


# def fala_oi():
#    print('oi')


# fala_oi = master(fala_oi)

# fala_oi()

# aplicando a decoração para o script acima ficará igual abaixo
def master(funcao):
    def slave(*args, **kwargs):
        print('função decorada foi passada por aqui')
        funcao(*args, **kwargs)

    return slave


# quando anota com o @master a função passa primeiramente
# pela master igual quando fazia com script acima criando
# uma nova variavel
@master
def fala_oi():
    print('oi')


def fala_oi_sem_decorar():
    print('oi sem decorar')


fala_oi_sem_decorar()
fala_oi()

# decorador serve para medir o tempo que uma função levou pra ser
# executada ou pra aplicar logs em uma função e rastrear algo
