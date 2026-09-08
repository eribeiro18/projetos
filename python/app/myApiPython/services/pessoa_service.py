from sqlalchemy.orm import Session
from myApiPython.models.pessoa_model import pessoa_model
from myApiPython.repositories.pessoa_repository import pessoa_repository

class pessoa_service:
    def __init__(self, db: Session):
        self.repository = pessoa_repository(db)

    def listar_pessoas(self):
        return self.repository.get_all()

    def obter_pessoa(self, pessoa_id: int):
        return self.repository.get_by_id(pessoa_id)

    def criar_pessoa(self, nome: str, idade: int, email: str):
        nova_pessoa = pessoa_model(nome=nome, idade=idade, email=email)
        return self.repository.create(nova_pessoa)

    def deletar_pessoa(self, pessoa_id: int):
        return self.repository.delete(pessoa_id)
