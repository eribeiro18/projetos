from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.orm import Session
from myApiPython.database.session_app import SessionLocal
from myApiPython.services.pessoa_service import pessoa_service

router = APIRouter()

def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@router.get("/pessoas")
def listar_pessoas(db: Session = Depends(get_db)):
    service = pessoa_service(db)
    return service.listar_pessoas()

@router.get("/pessoas/{pessoa_id}")
def obter_pessoa(pessoa_id: int, db: Session = Depends(get_db)):
    service = pessoa_service(db)
    pessoa = service.obter_pessoa(pessoa_id)
    if not pessoa:
        raise HTTPException(status_code=404, detail="Pessoa não encontrada")
    return pessoa

@router.post("/pessoas")
def criar_pessoa(nome: str, idade: int, email: str, db: Session = Depends(get_db)):
    service = pessoa_service(db)
    return service.criar_pessoa(nome, idade, email)

@router.delete("/pessoas/{pessoa_id}")
def deletar_pessoa(pessoa_id: int, db: Session = Depends(get_db)):
    service = pessoa_service(db)
    pessoa = service.deletar_pessoa(pessoa_id)
    if not pessoa:
        raise HTTPException(status_code=404, detail="Pessoa não encontrada")
    return {"mensagem": "Pessoa deletada com sucesso"}
