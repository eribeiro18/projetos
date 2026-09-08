from sqlalchemy.orm import Session
from myApiPython.models.pessoa_model import pessoa_model

class pessoa_repository:
    def __init__(self, db: Session):
        self.db = db

    def get_all(self):
        return self.db.query(pessoa_model).all()

    def get_by_id(self, pessoa_id: int):
        return self.db.query(pessoa_model).filter(pessoa_model.id == pessoa_id).first()

    def create(self, pessoa: pessoa_model):
        self.db.add(pessoa)
        self.db.commit()
        self.db.refresh(pessoa)
        return pessoa

    def delete(self, pessoa_id: int):
        pessoa = self.get_by_id(pessoa_id)
        if pessoa:
            self.db.delete(pessoa)
            self.db.commit()
        return pessoa
