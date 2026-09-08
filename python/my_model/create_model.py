
#cria uma pasta com o modelo base que desejar
from transformers import AutoModelForSequenceClassification, AutoTokenizer

# usa um modelo base pequeno (distilbert)
model_name = "distilbert-base-uncased"
model = AutoModelForSequenceClassification.from_pretrained(model_name, num_labels=2)
tokenizer = AutoTokenizer.from_pretrained(model_name)

# salvar localmente
model.save_pretrained("./meu-modelo-teste")
tokenizer.save_pretrained("./meu-modelo-teste")