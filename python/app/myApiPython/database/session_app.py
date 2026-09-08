from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, declarative_base

# URL de conexão com seu banco Postgres
DATABASE_URL = "postgresql://postgres:postgres123@localhost:5432/db_teste"

# Cria o engine
engine = create_engine(DATABASE_URL)

# Cria o sessionmaker
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)

Base = declarative_base()
