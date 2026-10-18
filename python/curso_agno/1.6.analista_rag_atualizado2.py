from agno.agent import Agent
from agno.tools.yfinance import YFinanceTools
from agno.models.groq import Groq
from agno.db.sqlite import SqliteDb

from agno.vectordb.chroma import ChromaDb
from agno.knowledge.knowledge import Knowledge
from agno.knowledge.reader.pdf_reader import PDFReader
from agno.knowledge.chunking.semantic import SemanticChunking
from agno.knowledge.embedder.fastembed import FastEmbedEmbedder
from agno.knowledge.embedder.google import GeminiEmbedder

import os
from dotenv import load_dotenv

load_dotenv()

# STORAGE ===============================================
# Setup the SQLite database
db = SqliteDb(db_file="tmp/data.db")


# RAG ==================================================
# Initialize ChromaDB
vector_db = ChromaDb(
    collection="empresas_relatorios",
    path="tmp/chromadb",
        embedder=GeminiEmbedder(id="text-embedding-004", 
        api_key=os.getenv("GEMINI_API_KEY")
    ),
    persistent_client=True
)

# Create knowledge base
knowledge = Knowledge(
    vector_db=vector_db,
    max_results=4
)

knowledge.insert(
    path="files/PETR/",
    reader=PDFReader(
        chunking_strategy=SemanticChunking(chunk_size=800, embedder=FastEmbedEmbedder())
    ),
    metadata={
        "company": "Petrobras",
        "sector": "Petróleo e Gás",
        "country": "Brazil",
    },
    skip_if_exists=True
)

knowledge.insert(
    path="files/VALE/",
    reader=PDFReader(
        chunking_strategy=SemanticChunking(chunk_size=800, embedder=FastEmbedEmbedder())
    ),
    metadata={
        "company": "Vale",
        "sector": "Mineração",
        "country": "Brazil",
    },
    skip_if_exists=True
)


# AGENT ================================================
agent = Agent(
    name="analista_financeiro",
    model=Groq(id="openai/gpt-oss-120b"),
    tools=[YFinanceTools()],
    instructions="Voce é um analista e tem diferentes clientes. Lembre-se de cada cliente, suas informações e preferências.",
    db=db,
    search_knowledge=False,
    add_history_to_context=False,
    num_history_runs=3,
    add_memories_to_context=True,
    enable_agentic_memory=False,
    update_memory_on_run=False,
    knowledge=knowledge,
    add_knowledge_to_context=True
)

# agent.print_response("Ola, prefiro as respostas em formato de tabelas, gosto de poucas informacoes.", session_id="petrobras_session_1", user_id="analista_petrobras")
# agent.print_response("Ola, prefiro as respostas em formato de texto, gosto de bastante detalhes.", session_id="vale_session_1", user_id="analista_vale")

# agent.print_response("Qual é a cotação da petrobras?", session_id="petrobras_session_2", user_id="analista_petrobras")
# agent.print_response("Qual é a cotação da vale?", session_id="vale_session_2", user_id="analista_vale")

agent.print_response("Ola, qual foi o lucro liquido da petrobras em 2T25?", session_id="petrobras_session_4", user_id="analista_petrobras")
agent.print_response("Ola, o que foi comentado sobre o CAPEX da vale no 2T25?", session_id="vale_session_4", user_id="analista_vale")
