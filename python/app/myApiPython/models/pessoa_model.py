from sqlalchemy import Column, Integer, String
from myApiPython.database.session_app import Base

class pessoa_model(Base):
    __tablename__ = "pessoas"

    id = Column(Integer, primary_key=True, index=True)
    nome = Column(String, index=True)
    idade = Column(Integer)
    email = Column(String)
