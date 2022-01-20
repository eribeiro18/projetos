def fizzBuzz(n1):
    if not n1 % 3 and not n1 % 5:
        return 'FizzBuzz'
    elif not n1 % 3:
        return 'Fizz'
    elif not n1 % 5:
        return 'Buzz'
    else:
        return n1


msg = fizzBuzz(int(input('Informe um valor numerico! ')))
print(msg)
