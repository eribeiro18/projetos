from contextlib import asynccontextmanager
from fastapi import FastAPI

from api.database import adicionar_livros_default, criar_db_tabelas
from api.routers import livros_router


@asynccontextmanager
async def lifespan(app: FastAPI):
    criar_db_tabelas()
    adicionar_livros_default()
    yield

app = FastAPI(title="API de Livros", lifespan=lifespan)
app.include_router(livros_router.router)