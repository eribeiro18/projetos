try:
    print(a)
except NameError as error:
    print('erro -> ', error)
except Exception as error:
    print('erro -> ', error)

try:
    print(1/0)
except ZeroDivisionError as error:
    print('erro -> ', error)
except Exception as error:
    print('erro -> ', error)
