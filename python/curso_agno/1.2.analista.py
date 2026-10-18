from agno.agent import Agent
from agno.tools.yfinance import YFinanceTools
from agno.models.groq import Groq

from dotenv import load_dotenv
load_dotenv()

agent = Agent(
    model=Groq(id="qwen/qwen3.8-27b"),
    tools=[YFinanceTools()],
    instructions="Use tabeas par mostrar a informação final. Não inclua nenhum outro texto.",
)

##stream para ir demonstrando as saidas conforme elas vão sendo recebidas similar ao que ocorre
##com o chatgpt e demais modelos de linguagem
agent.print_response("Qual é a cotação atual da APPLE?", stream=True)