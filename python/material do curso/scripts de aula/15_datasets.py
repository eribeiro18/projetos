# Manipulação básica de um Dataset
# from datasets import load_dataset
#
# dataset = load_dataset("imdb")
# print(dataset)
#
# dataset_treino = dataset['train']
# print(dataset_treino)
#
# print(dataset_treino[9])
# print(dataset_treino[9]['label'])
# print(dataset_treino['label'])

# Convertendo para um DataFrame do pandas
# from datasets import load_dataset

# dataset = load_dataset("imdb")
# dataset_treino = dataset['train']
# df = dataset_treino.to_pandas()
# print(df)

# Datasets grandes demais para a memória
from datasets import load_dataset

dataset = load_dataset("imdb", streaming=True)
dataset_treino = dataset['train']

for linha in dataset_treino:
    print(linha)  # Primeira linha
    break

linhas = dataset_treino.take(3)
print(linhas)
print(list(linhas))

for linha in dataset_treino:
    print(linha)  # Primeira linha
    break
