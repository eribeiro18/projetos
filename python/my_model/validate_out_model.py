
#valida se a setença foi convertida em ponto flutuante tamanho 1 a 768
from transformers import AutoTokenizer, AutoModel
import torch

# Carregar tokenizer e modelo do seu repositório
model_name = "eribeiro18/meu-modelo-teste"
tokenizer = AutoTokenizer.from_pretrained(model_name)
model = AutoModel.from_pretrained(model_name)

# Frase de exemplo
sentence = "Testando meu modelo personalizado!"

# Tokenizar a frase
inputs = tokenizer(sentence, return_tensors="pt")

# Passar pelo modelo
outputs = model(**inputs)

# Obter embeddings da frase (média dos tokens)
last_hidden_state = outputs.last_hidden_state
sentence_embedding = last_hidden_state.mean(dim=1)

print("Shape dos embeddings:", sentence_embedding.shape)
print("Embeddings da frase:", sentence_embedding)