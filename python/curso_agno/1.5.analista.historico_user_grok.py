from google.genai.types import TurnCompleteReason
from agno.agent import Agent
from agno.tools.yfinance import YFinanceTools
from agno.models.groq import Groq
from agno.db.sqlite import SqliteDb

import os
from dotenv import load_dotenv
load_dotenv()

db = SqliteDb(db_file="tmp/data.db")

agent = Agent(
    name="Analista",
    model=Groq(id="qwen/qwen3.8-27b"),
    tools=[YFinanceTools()],
    instructions="Voce é um analista e tem diferentes clientes. Lembre-se de cada cliente, suas informações e preferências.",
    db=db,
    add_history_to_context=True,
    num_history_runs=3,
    update_memory_on_run=True,
    enable_agentic_memory=True
)

##stream para ir demonstrando as saidas conforme elas vão sendo recebidas similar ao que ocorre
##com o chatgpt e demais modelos de linguagem
agent.print_response("Ola, prefiro as respostas em formato de tabelas, gosto de poucas informacoes. Traga as ultimas informaços financeiras da apple.", stream=True, user_id="analista_apple", session_id="apple_session_1")
agent.print_response("Ola, prefiro as respostas em formato de texto, gosto de bastante detalhes. Traga as ultimas informaços financeiras da petrobras.", stream=True, user_id="analista_petrobras", session_id="petrobras_session_1")