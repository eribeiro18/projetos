from agno.agent import Agent
from agno.tools.yfinance import YFinanceTools
from agno.models.google import Gemini
from agno.db.sqlite import SqliteDb

import os
from dotenv import load_dotenv
load_dotenv()

db = SqliteDb(db_file="tmp/data.db")

agent = Agent(
    name="Analista",
    model=Gemini(id="gemini-3.8-flash"),
    tools=[YFinanceTools()],
    instructions="Use tabeas par mostrar a informação final. Não inclua nenhum outro texto.",
    db=db,
    add_history_to_context=True,
    num_history_runs=3
)

##stream para ir demonstrando as saidas conforme elas vão sendo recebidas similar ao que ocorre
##com o chatgpt e demais modelos de linguagem
agent.print_response("Qual é a cotação atual da APPLE?", stream=True)
agent.print_response("Qual é a cotação atual da petrobras?", stream=True)
agent.print_response("Quais empresas ja consultamos a cotação?", stream=True)