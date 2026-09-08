from fastapi import FastAPI
from myApiPython.routers import pessoa_router
from myApiPython.database.session_app import Base, engine

app = FastAPI()

Base.metadata.create_all(bind=engine)

app.include_router(pessoa_router.router)
