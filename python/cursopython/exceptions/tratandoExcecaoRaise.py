def divide(n1, n2):
    try:
        return n1 / n2
    except ZeroDivisionError as erro:
        print('Log: -> ', erro)
        raise


try:
    divide(n1=1, n2=0)
except Exception as error:
    print('erro -> ', error)
