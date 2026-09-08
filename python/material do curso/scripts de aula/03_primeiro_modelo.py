from transformers import pipeline

modelo = pipeline('fill-mask')
predicoes = modelo.predict('The capital of <mask> is Brasilia.')
print(predicoes)

for predicao in predicoes:
    resposta = predicao['token_str']
    score = predicao['score']
    frase = predicao['sequence']
    print(f'Predição "{resposta}" com score {(score * 100):.2f}% -> "{frase}"')
# input()



# for frase in [
#     'Roses are red, violets are <mask>.'
#     'I really want to watch the <mask> tonight.',
#     'Welcome to the <mask>!',
# ]:
#     print(f'Frase atual: {frase}')
#     predicoes = modelo.predict(frase)
#     for predicao in predicoes:
#         resposta = predicao['token_str']
#         score = predicao['score']
#         frase = predicao['sequence']
#         print(f'Predição "{resposta}" com score {score * 100:.2f}% -> "{frase}"')
#     input()
