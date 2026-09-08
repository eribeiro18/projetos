
#sobe no github do hugging-face
from huggingface_hub import upload_folder

upload_folder(
    folder_path="/home/evandro/workspace/model/meu-modelo-teste",
    repo_id="eribeiro18/meu-modelo-teste",
    repo_type="model",
    token="seu token aqui"
)
