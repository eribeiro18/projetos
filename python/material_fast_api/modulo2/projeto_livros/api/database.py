from uuid import UUID
from sqlmodel import create_engine, Session, SQLModel, select
from pathlib import Path

from api.models import Livro

DATABASE_PATH = Path("database.db")
DATABASE_URL = f"sqlite:///{DATABASE_PATH}"
CONNECT_ARGS = {"check_same_thread": False} # sqlite

engine = create_engine(DATABASE_URL, connect_args=CONNECT_ARGS)

def criar_db_tabelas():
    SQLModel.metadata.create_all(engine)


def get_session():
    with Session(engine) as session:
        yield session


livros_default_data = [
    {
        "uuid": UUID("118bf4ae-7f31-4b2b-bf28-e2cc3456d247"),
        "autor": "George Orwell",
        "titulo": "1984",
        "editora": "Companhia das Letras",
        "ano": 1949
    },
    {
        "uuid": UUID("08d86cbf-93ea-4318-a51b-d4a716902902"),
        "autor": "J. K. Rowling",
        "titulo": "Harry Potter e a Pedra Filosofal",
        "editora": "Rocco",
        "ano": 1997
    }
]

def adicionar_livros_default():
    with Session(engine) as session:
        if not session.exec(select(Livro)).first():
            for data in livros_default_data:
                livro = Livro(
                    uuid=data.get('uuid'),
                    autor=data.get('autor'),
                    titulo=data.get('titulo'),
                    editora=data.get('editora'),
                    ano=data.get('ano')
                )
                session.add(livro)
                session.commit()
                