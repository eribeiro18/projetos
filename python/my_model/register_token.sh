
#primeiro cria o venv com base no python instalado
python3.13 -m venv venv \

#segundo ativa o venv criado
source venv/bin/activate \

#terceiro instala o hub cli e transformers
pip install --upgrade "huggingface_hub[cli]" transformers

#quarto instala PyTorch
pip install torch torchvision torchaudio --index-url https://download.pytorch.org/whl/cpu

#quinto loga, lembrando que deve ser passado um token criado no site da hugging-face
huggingface-cli login --token SEU_TOKEN \