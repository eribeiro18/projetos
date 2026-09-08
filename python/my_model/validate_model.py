
#script de inferência de teste e retornou um tensor(...) se estiver valido
from transformers import AutoModelForSequenceClassification, AutoTokenizer

repo_id = "eribeiro18/meu-modelo-teste"
model = AutoModelForSequenceClassification.from_pretrained(repo_id)
tokenizer = AutoTokenizer.from_pretrained(repo_id)

inputs = tokenizer("Isso é um teste!", return_tensors="pt")
outputs = model(**inputs)
print(outputs.logits)